import java.math.BigInteger;
import java.util.Random;

public class BigIntTest {

    public static void main(String[] args) {
        Random rand = new Random();
        BigInteger huge1, huge2, hugeAdd, hugeMultiply, hugeSubtract;
        int hugeCompare;
        long startTime, endTime;
        double runTimeAdd=0.0; double runTimeMult=0.0; double runTimeComp=0.0; double runTimeSub=0.0;

        for (int numInts=0; numInts < 100; numInts++) {
            huge1 = new BigInteger(5000, rand);
            huge2 = new BigInteger(5000, rand);
            startTime = System.currentTimeMillis();
            for(int numRun=0; numRun < 100; numRun++) {
                hugeAdd = huge1.add(huge2);
            }
            endTime = System.currentTimeMillis();
            runTimeAdd +=(double) (endTime - startTime)/((double)100);
        }
        runTimeAdd = runTimeAdd/((double)100);
        System.out.println("Run time for addition: " + runTimeAdd);

        for (int numInts=0; numInts < 100; numInts++) {
            huge1 = new BigInteger(5000, rand);
            huge2 = new BigInteger(5000, rand);
            startTime = System.currentTimeMillis();
            for(int numRun=0; numRun < 100; numRun++) {
                hugeSubtract = huge1.subtract(huge2);
            }
            endTime = System.currentTimeMillis();
            runTimeSub +=(double) (endTime - startTime)/((double)100);
        }
        runTimeSub = runTimeSub/((double)100);
        System.out.println("Run time for subtraction: " + runTimeSub);

        for (int numInts=0; numInts < 100; numInts++) {
            huge1 = new BigInteger(5000, rand);
            huge2 = new BigInteger(5000, rand);
            startTime = System.currentTimeMillis();
            for(int numRun=0; numRun < 100; numRun++) {
                hugeMultiply = huge1.multiply(huge2);
            }
            endTime = System.currentTimeMillis();
            runTimeMult +=(double) (endTime - startTime)/((double)100);
        }
        runTimeMult = runTimeMult/((double)100);
        System.out.println("Run time for multiplication: " + runTimeMult);

        for (int numInts=0; numInts < 100; numInts++) {
            huge1 = new BigInteger(5000, rand);
            huge2 = new BigInteger(5000, rand);
            startTime = System.currentTimeMillis();
            for(int numRun=0; numRun < 100; numRun++) {
                hugeCompare = huge1.compareTo(huge2);
            }
            endTime = System.currentTimeMillis();
            runTimeComp +=(double) (endTime - startTime)/((double)100);
        }
        runTimeComp = runTimeComp/((double)100);
        System.out.println("Run time for comparison: " + runTimeComp);
    }
}

