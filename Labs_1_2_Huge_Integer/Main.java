package Huge.Integers;

import java.math.BigInteger;
import java.util.Random;
class Main {
    public static void main(String[] args) {
        // 1st constructor test
        HugeInteger a = new HugeInteger("1234");
        //System.out.println(a.toString());

        // 2nd constructor test
        HugeInteger b = new HugeInteger("-5678");
        //System.out.println(b.toString());

        // add test // try with both +ve, both -ve, 1 & 1
        // System.out.println(a.add(b).toString());

        // subtract test // try with both +ve, both -ve, 1 & 1
        // System.out.println(a.subtract(b).toString());

        //multiply test
        //System.out.println(a.multiply(b).toString());

        // compareTo test // should return 1 if a is larger than b, -1 if a is less than b & 0 if they're equal
        // System.out.println(a.compareTo(b));

        //abdDiff test
        String x = "";
        x = a.absDiff(b);
        System.out.println(x);
        /*HugeInteger huge1, huge2, huge3;
        int x;
        long startTime, endTime;
        double runTime=0.0;
        for (int numInts=0; numInts < 100; numInts++){
            huge1 = new HugeInteger(10000); //creates a random integer of n digits
            huge2 = new HugeInteger(10000); //creates a random integer of n digits
            startTime = System.currentTimeMillis();
            for(int numRun=0; numRun < 100; numRun++)
            { huge3 = huge1.multiply(huge2); }
            endTime = System.currentTimeMillis();
            runTime +=(double) (endTime - startTime)/((double) 100);
        }
        runTime = runTime/((double)100);
        System.out.println(runTime);*/
    }
}