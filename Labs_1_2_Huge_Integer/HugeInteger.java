package Huge.Integers;
/*TODO
* subtracting all cases (both +ve, both -ve, 1 & 1)
*
 */
public class HugeInteger{
    // instance fields
    private int[] HugeNum; // Integer Array to store digits
    private int size;  // size of the Huge Integer
    private boolean sign; // true is -ve, false is +ve

    // constructors
    public HugeInteger(String val){

        if((int)val.charAt(0) == 45){
            sign = true;
            val = val.substring(1);
        }
        else{
            sign = false; // and keep string as is}
        }

        val = val.replaceFirst("^0+(?!$)", ""); // to remove leading zeros from the string
        size = val.length();

        if(size == 0){throw new IllegalArgumentException("String Length has to be greater than zero");} // check if length is valid

        for(int i = 0; i < size; i++) { // check if input is all letters
            if (val.charAt(i) < 48 || val.charAt(i) > 57 ){
                throw new IllegalArgumentException("Invalid Input, String contains non-digit");
            }
        }

        this.HugeNum = new int[size];
        // creates the integer array and stores values in it
        for(int i = 0; i < size; i++){
            HugeNum[i] = Character.getNumericValue(val.charAt(i));
        }
    }


    public HugeInteger(int n) {
        if (n >= 1) {
            HugeNum = new int[n];
            size = n;

            /*double rand = Math.random(); // to generate the plus or minus randomly
            int x = (int)Math.round(rand);
            sign = (x==0); */

            HugeNum[0] = (int) (Math.random() * 8 + 1); // generate the first number which cannot be zero
            int i = 1;
            while (i < n) {
                HugeNum[i] = (int) (Math.random() * 9); // generate the rest of the numbers, which can be zero
                i++;
            }
        } else { // if the given size is less than 0
            throw new IllegalArgumentException("The n value used should be greater than or equal to one!");
        }
    }


    public int getSize(){
        return this.size;
    }



    public HugeInteger add(HugeInteger h) {
        if ((this.sign == false && h.sign == false) || (this.sign == true && h.sign == true)) {
            // if both numbers are positive, or if both are negative, the process is the same.
            // Only difference is the sign of the result
            String num1 = "";
            String num2 = "";
            // create two empty Strings and add values from the integer Array to create the String corresponding to the Huge Integer
            for (int i = 0; i < this.size; i++)
                num1 += this.HugeNum[i];

            for (int i = 0; i < h.size; i++)
                num2 += h.HugeNum[i];

            int size1 = num1.length();
            int size2 = num2.length();

            int min = (size1 < size2 ? size1 : size2); // finding which of the lengths is higher as following steps depend on the larger number
            int max = (size1 < size2 ? size2 : size1);

            int n1[] = new int[max];
            int n2[] = new int[max]; // temporary integer arrays to house string digits

            //System.out.println("max is "+ max +" and min is "+min);
            //System.out.println("Num1 is " + num1 + " and Num2 is " + num2);

            for (int i = 0; i < size1; i++) {
                n1[i] = (int) num1.charAt(size1 - 1 - i) - 48; // looping through the strings and adding the individual elements to the array in reverse order
            }
            // the smaller array has zeros filling in so that its size doesn't affect addition
            //System.out.println(num1);

            for (int i = 0; i < size2; i++) {
                n2[i] = (int) num2.charAt(size2 - 1 - i) - 48;
            }

            //System.out.println(num2);

            int carry = 0;
            int sum[] = new int[max + 1]; // max possible is larger than the larger number by 1 since we're adding
            int k = 0;

            for (k = 0; k < max; k++) { // arithmetic, the same way we learned in elementary school, from right to left. That is why integer arrays are in reverse order
                sum[k] = (n1[k] + n2[k] + carry) % 10;

                if ((n1[k] + n2[k] + carry) >= 10)
                    carry = 1;
                else
                    carry = 0;
            }

            sum[max] = carry;
            String temp = "";
            for (int i = (max); i >= 0; i--) {
                temp += sum[i];
            }

            HugeInteger output = new HugeInteger(temp); // construct and return the output object from the sum
            if (this.sign == false && h.sign == false) // if the two inputs are +ve, the sign of the output should be +ve as well
                output.sign = false;

            if (this.sign == true && h.sign == true)
                output.sign = true;


            return output;
        }
        else{ // 1 is +ve, 1 is -ve => find abs diff, sign of output is the sign of the larger one
            String num1 = "";
            String num2 = "";

            for (int i = 0; i < this.size; i++)
                num1 += this.HugeNum[i];

            for (int i = 0; i < h.size; i++)
                num2 += h.HugeNum[i];

            String absResult = "";
            absResult = this.absDiff(h);

            HugeInteger output = new HugeInteger(absResult);
            // sign of output to be sign of the absolutely larger num
            if(isLessThan(num1, num2) == true)
                output.sign = h.sign;

            if(isLessThan(num1, num2) == false)
                output.sign = this.sign;

            return output;
        }
    }

    public HugeInteger subtract(HugeInteger h){
        // + - + => find absDiff() , sign is of the larger
        // - - - => find absDiff(), sign is the sign of the larger

        // + - - => add normally, sign is +ve
        // - - + => find sum normally, ans is -ve
        if((this.sign == false && h.sign == false) || (this.sign == true && h.sign == true)){ // both same sign => find absDiff, sign is that of the larger number
            String num1 = "";
            String num2 = "";
            String abs = "";
            boolean outSign;
            for (int i = 0; i < this.size; i++)
                num1 += this.HugeNum[i];

            for (int i = 0; i < h.size; i++)
                num2 += h.HugeNum[i];

            abs = this.absDiff(h);
            if(abs == "0"){
                HugeInteger output = new HugeInteger("0");
                output.sign = false;
                return output;
            }
            HugeInteger output = new HugeInteger(abs);

            String str1 = "";
            String str2 = "";
            for (int i = 0; i < this.size; i++)
                str1 += this.HugeNum[i];

            for (int i = 0; i < h.size; i++)
                str2 += h.HugeNum[i];

            if(this.sign == false && h.sign == false){ // if they're both positive
                if(isLessThan(str1, str2) == true) // if str2 is bigger, sign should be negative
                    output.sign = true;

                if(isLessThan(str1, str2) == false) // if str1 is bigger, sign should be positive
                    output.sign = false;
            }

            if(this.sign == true && h.sign == true){ // if they're both negative
                if(this.compareTo(h) == 0){
                    output.sign = false;
                    return output;
                }

                if(isLessThan(str1, str2) == true) // this is a smaller number
                    output.sign = false;
                else if(isLessThan(str1, str2) == false) // if h is larger
                    output.sign = true;

            }
            return output;
        }

        else if(this.sign == false && h.sign == true){
            h.sign = false; // needed to simulate addition of two positive numbers, result is +ve
            HugeInteger output = this.add(h);
            output.sign = false;
            h.sign = true;
            return output;
        }

        else{ // this.sign == true && h.sign == false, only case left
            this.sign = false;
            HugeInteger output = this.add(h);
            output.sign = true;
            this.sign = true;
            return output;
        }
    }

    public HugeInteger multiply(HugeInteger h){
        String num1 = "";
        String num2 = "";

        for (int i = 0; i < this.size; i++)
            num1 += this.HugeNum[i];

        for (int i = 0; i < h.size; i++)
            num2 += h.HugeNum[i];

        if(num1 == "0" || num2 == "0"){ // checking for 0
            HugeInteger output = new HugeInteger("0");
            output.sign = false;
            return output;
        }

        HugeInteger temp = new HugeInteger("0"); // creates new HugeInteger objects temp, x, y ans Strings sum1 and multiplier
        String sum1;
        String multiplier = "";
        HugeInteger x = new HugeInteger("0");
        HugeInteger y;

        for (int i = h.size - 1 ; i >= 0; i--){ // We iterate through h backwards
            for (int j = 1; j <= h.HugeNum[i]; j++){ // for every element, we multiply that element of h by the element in this object
                temp = temp.add(this);              // by adding it this to itself h element's number of times
            }
            sum1 = temp.toString();
            sum1 += multiplier;
            y = new HugeInteger(sum1); // create
            x = x.add(y); // adding y, the temp to the existing number every time
            temp = new HugeInteger("0"); // reset temp
            multiplier += '0'; // "adding a 0" to the string added to sum1 after each step ~ as if we're multiplying by 10 every time we add
        }

        if ((this.sign == false && h.sign == false) || (this.sign == true && h.sign == true)){
            x.sign = false;
            return x;
        }
        else{
            x.sign = true;
            return x;
        }
    }

    public int compareTo(HugeInteger h){
        // returns -1 if this is less than h
        // returns 1 if this is larger than h
        // returns 0 if this is equal to h
        if(this.sign == false && h.sign == false){ // if both numbers are positive
            if(this.size < h.size) // compare sizes
                return -1;
            else if (this.size > h.size)
                return 1;
            else {
                for (int i = 0; i < this.size; i++) {
                    if (this.HugeNum[i] < h.HugeNum[i])
                        return -1;
                    if (this.HugeNum[i] > h.HugeNum[i])
                        return 1;
                }
                return 0; // if all else fails
            }
        }
        else if(this.sign == true && h.sign == true){ // // if both numbers are negative
            if (this.size > h.size)
                return -1;
            else if (h.size > this.size)
                return 1;
            else{
                for(int i = 0; i < this.size; i++){
                    if (this.HugeNum[i] > h.HugeNum[i])
                        return -1;
                    if (this.HugeNum[i] < h.HugeNum[i])
                        return 1;
                }
                return 0;
            }
        }
        else if(this.sign == true && h.sign == false) // h is larger by sign
            return -1;
        else // this is larger by sign
            return 1;
    }

    public boolean isLessThan(String num1, String num2){
        // returns tru if string num1 is less than string num2
        int len1 = num1.length();
        int len2 = num2.length();

        if(len1 < len2)
            return true;
        if(len2 > len1)
            return false;

        for(int i = 0; i < len1; i++){ // if they're equal in length
            if((int)num1.charAt(i) < (int)num2.charAt(i))
                return true;
            else
                return false;
        }
        return false;
    }

    public String absDiff(HugeInteger h){
        String str1 = "";
        String str2 = "";
        for (int i = 0; i < this.size; i++)
            str1 += this.HugeNum[i];

        for (int i = 0; i < h.size; i++)
            str2 += h.HugeNum[i];

        if(isLessThan(str1, str2) == true){ // swapping; we want str1 to be absolutely bigger
            String temp = str1;
            str1 = str2;
            str2 = temp;
        }
        // System.out.println(str1);
        // System.out.println(str2);
        String ans = "";
        int len1 = str1.length();
        int len2 = str2.length();
        int diff = len1 - len2;

        //System.out.println(diff);

        int carry = 0;
        for(int i = len2 - 1; i >= 0; i--){
            // Do school mathematics, compute difference of
            // current digits and carry
            int sub = ((str1.charAt(i + diff) - (int)'0') -
                    (str2.charAt(i) - (int)'0') - carry);
            if (sub < 0)
            {
                sub = sub+10;
                carry = 1;
            }
            else
                carry = 0;

            ans += String.valueOf(sub);
        }	// subtract remaining digits of str1[]
        for (int i = len1 - len2 - 1; i >= 0; i--)
        {
            if (str1.charAt(i) == '0' && carry > 0)
            {
                ans += "9";
                continue;
            }
            int sub = (((int)str1.charAt(i) - (int)'0') - carry);
            if (i > 0 || sub > 0) // remove preceding 0's
                ans += String.valueOf(sub);
            carry = 0;

        }

        // reverse resultant string
        return new StringBuilder(ans).reverse().toString();

}



    public String toString(){ // convert from an integer array form to string form
        String output = "";
        if(sign == true){
            output += "-";
        }

        for(int i = 0; i < this.getSize(); i++){
            output += HugeNum[i];
        }
        return output;
    }
}