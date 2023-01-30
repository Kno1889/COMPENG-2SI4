// package Hash.Tables;

public class HashTableLin {
    // all instance fields private.....
    private Integer[] table; // table to hold keys
    private int tableSize; // size of table, contains occupied and null keys.
    private int maxKeys; // max number of keys the table can hold before rehash
    private int numKeys; // number of non-null keys in the table
    private double currentLoadF; // current load factor given by numKeys / tableSize
    private int numProbes = 0; // number of probes

    public HashTableLin(int maxNum, double currentLoad) {
        this.currentLoadF = currentLoad;
        this.maxKeys = maxNum;
        int x = (int) (maxNum / currentLoad); // minimum possible tableSize, which may or may not be prime => get the next prime

        while (this.isPrime(x) == false)
            x++;

        this.table = new Integer[x]; // once the minimum prime is found, we create the table and assign appropriate values
        this.tableSize = x;
        this.numKeys = 0;
    }

    public void insert(int n) {
        int ind = n % this.tableSize; // given hash function n % tableSize

        if(((this.numKeys + 1) / tableSize) > this.currentLoadF){ // if adding one more element causes the load factor to be larger than the allowed maximum derived from the input,
            this.rehash();                                        // then we rehash and then insert the required element
            this.insert(n);
        }else{
            numProbes++;
            while(this.table[ind] != null){ // keep going while table[ind] is occupied
                if(this.table[ind] == n) // if n already exists at any point, we return nothing
                    return;

                //linear probing procedure: increment the index to probe for possible indexes
                if(ind < (this.tableSize - 1)) { // if we're not at the last index, increment.
                    ind++;
                    numProbes++; // number of probes we examine, potential indexes checked
                }
                else { // otherwise, go back to index zero and check all over again
                    ind = 0;
                    numProbes++; // number of probes we examine, potential indexes checked
                }
            }
            this.table[ind] = n; // finally insert the new element and increase the numKeys occupying table fields
            numKeys++;

        }
    }

    public void rehash(){
        HashTableLin tempBigger; // a temporary object of size equalling the next prime after (this.tableSize * 2) is created to temporarily house all the elements currently
                                 // in the table to avoid data loss
        tempBigger = new HashTableLin((this.tableSize * 2), this.currentLoadF);

        for(int i = 0; i < this.tableSize; i++){ // this for loop inserts all the elements in this table into the temporary one. We don't just
            if(this.table[i] != null)            // insert in the same index; we must insert them as we would to any blank hash table!
                tempBigger.insert(this.table[i]);
        }
        //System.out.println("Rehash Required! ");

        // equating this object to the temporary object
        this.table = tempBigger.table;
        this.maxKeys = tempBigger.maxKeys;
        this.tableSize = tempBigger.tableSize;
        this.numKeys = tempBigger.numKeys;
    }

    public boolean isIn(int n){
        int ind = n % this.tableSize;

        while(this.table[ind] != null){
            if(this.table[ind] == n)
                return true;
            if(ind < this.tableSize - 1)
                ind++;
            else
                ind = 0;

        }


        return false;
    }

    public void printKeys(){ // print keys only whenever non-null field is observed
        for(int i = 0; i < this.tableSize; i++){
            if(this.table[i] != null)
                System.out.println("Key: " + this.table[i]);
        }
    }

    public void printKeysAndIndexes(){ // same as printKeys except prints indexes as well as keys when non-null field
        for(int i = 0; i < this.tableSize; i++){
            if(this.table[i] != null)
                System.out.println("Key: " + this.table[i] + ", Index: " + i);
        }
    }

    public boolean isPrime(int n){ // checks for prime numbers
        int factors = 0; // keeps count of number of factors

        for(int i = 2; i < (n / 2); i++){ // it is only meaningful to check for factors from 2 to half the number
                if(n % i == 0) // everytime the remainder is zero, another factor is discovered and the number of factors is increased
                factors++;
        }

        if(factors == 0) // no factors => is Prime => true
            return true;
        else
            return false;

    }

    // getters for various fields since all fields had to be set to private
    public int getMaxKeys(){ return this.maxKeys;}

    public double getLoadFactor(){return this.currentLoadF;}

    public int getKeysNum(){return this.numKeys;}

    public int getTableSize(){return this.tableSize;}

    public int getProbeNum(){return numProbes;}

}
