// package Hash.Tables;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        HashTableLin a=new HashTableLin(5, 0.4);

        System.out.println("Table Size is: " + a.getTableSize());

        a.insert(1);
        a.insert(14);
        a.insert(38);
        a.insert(91);
        a.insert(13);
        a.insert(61);
        a.insert(8);
        a.insert(254);

        //System.out.println(a.getTableSize());
        System.out.println("Print Tests");
        a.printKeys();
        System.out.println("\n");

        a.printKeysAndIndexes();
        System.out.println("\n");
        System.out.println("isIn() Tests");

        System.out.println("isIn(20) - True");
        System.out.println(a.isIn(20));
        System.out.println("isIn(91) - True");
        System.out.println(a.isIn(91));
        System.out.println("isIn(86) - False");
        System.out.println(a.isIn(86));
        System.out.println("isIn(7) - True");
        System.out.println(a.isIn(7));

        System.out.println("\n");
        System.out.println("Getters Tests");
        System.out.println("x Number of Keys:");
        System.out.println(a.getKeysNum());

        System.out.println("Hash Table Size:");
        System.out.println(a.getTableSize());

        System.out.println("Hash Table Load Factor:");
        System.out.println(a.getLoadFactor());

        System.out.println("\n");
        System.out.println("Quadratic :");

        HashTableQuad b = new HashTableQuad(5, 0.4);

        b.insert(1);
        b.insert(14);
        b.insert(27);
        b.insert(76);
        b.insert(26);
        b.insert(56);
        b.insert(53);
        b.insert(7);
        b.insert(23);
        b.insert(6);

        System.out.println("Print Tests");
        b.printKeys();
        System.out.println("\n");

        b.printKeysAndIndexes();
        System.out.println("\n");
        System.out.println("isIn() Tests");

        System.out.println("isIn(6) - True");
        System.out.println(b.isIn(6));
        System.out.println("isIn(19) - True");
        System.out.println(b.isIn(19));
        System.out.println("isIn(86) - False");
        System.out.println(b.isIn(86));
        System.out.println("isIn(45) - False");
        System.out.println(b.isIn(45));
        System.out.println("isIn(104) - True");
        System.out.println(b.isIn(104));

        System.out.println("\n");
        System.out.println("Getters Tests");
        System.out.println("Number of Keys:");
        System.out.println(b.getKeysNum());

        System.out.println("Hash Table Size:");
        System.out.println(b.getTableSize());

        System.out.println("x Hash Table Load Factor:");
        System.out.println(b.getLoadFactor());

        System.out.println("\n");
        //System.out.println("Average Linear Probes: ");
        // linProbesTest();
        System.out.println("\n");
        //System.out.println("Average Quadratic Probes: ");
        // quadProbesTest();

        System.out.println("Average Probes Check:  ");
        System.out.println("Linear Average: " + avgProbesLinear());
        System.out.println("Quadratic Average: " + avgProbesQuadratic());
    }

    static double avgProbesLinear(){
        int n = 10000; // maxKeys allowed for each check
        double avgFor1 = 0, avgFor2 = 0, avgFor3 = 0, avgFor4 = 0, avgFor5 = 0, avgFor6 = 0, avgFor7 = 0, avgFor8 = 0, avgFor9 = 0;
        //double
        Random rand = new Random();
        HashTableLin testTable;

        for(int i = 0; i < 100; i++){
            testTable = new HashTableLin(n, 0.1);
            for(int j=0; j<n; j++){
                testTable.insert(rand.nextInt(123456789));
            }
            avgFor1 += testTable.getProbeNum();
        }
        avgFor1 = avgFor1 / (100*n);


        //0.2
        for(int i=0; i<100; i++){
            testTable = new HashTableLin(n, 0.2);
            for(int j=0; j<n; j++){
                testTable.insert(rand.nextInt(123456789));
            }
            avgFor2 += testTable.getProbeNum();
        }
        avgFor2 = avgFor2 / (100*n);


        //0.3
        for(int i=0; i<100; i++){
            testTable = new HashTableLin(n, 0.3);
            for(int j=0; j<n; j++){
                testTable.insert(rand.nextInt(123456789));
            }
            avgFor3 += testTable.getProbeNum();
        }
        avgFor3 = avgFor3 / (100*n);


        //0.4
        for(int i=0; i<100; i++){
            testTable = new HashTableLin(n, 0.4);
            for(int j=0; j<n; j++){
                testTable.insert(rand.nextInt(123456789));
            }
            avgFor4 += testTable.getProbeNum();
        }
        avgFor4 = avgFor4 / (100*n);


        //0.5
        for(int i=0; i<100; i++){
            testTable = new HashTableLin(n, 0.5);
            for(int j=0; j<n; j++){
                testTable.insert(rand.nextInt(123456789));
            }
            avgFor5 += testTable.getProbeNum();
        }
        avgFor5 = avgFor5 / (100*n);


        //0.6
        for(int i=0; i<100; i++){
            testTable = new HashTableLin(n, 0.6);
            for(int j=0; j<n; j++){
                testTable.insert(rand.nextInt(123456789));
            }
            avgFor6 += testTable.getProbeNum();
        }
        avgFor6 = avgFor6 / (100*n);


        //0.7
        for(int i=0; i<100; i++){
            testTable = new HashTableLin(n, 0.7);
            for(int j=0; j<n; j++){
                testTable.insert(rand.nextInt(123456789));
            }
            avgFor7 += testTable.getProbeNum();
        }
        avgFor7 = avgFor7 / (100*n);


        //0.8
        for(int i=0; i<100; i++){
            testTable = new HashTableLin(n, 0.8);
            for(int j=0; j<n; j++){
                testTable.insert(rand.nextInt(123456789));
            }
            avgFor8 += testTable.getProbeNum();
        }
        avgFor8 = avgFor8 / (100*n);


        //0.9
        for(int i=0; i<100; i++){
            testTable = new HashTableLin(n, 0.9);
            for(int j=0; j<n; j++){
                testTable.insert(rand.nextInt(123456789));
            }
            avgFor9 += testTable.getProbeNum();
        }
        avgFor9 = avgFor9 / (100*n);

        System.out.println("0.1: " + avgFor1 + "; 0.2: " + avgFor2 + "; 0.3: " + avgFor3 + "; 0.4: " + avgFor4 + "; 0.5: " + avgFor5 + "; 0.6: " + avgFor6 + "; 0.7: " + avgFor7 + "; 0.8: " + avgFor8 + "; 0.9: " + avgFor9);
        return (avgFor1 + avgFor2 + avgFor3 + avgFor4 + avgFor5 + avgFor6 + avgFor7 + avgFor8 + avgFor9)/9;
    }

    static double avgProbesQuadratic(){
        int n = 10000;
        double avgFor1=0, avgFor2=0, avgFor3=0, avgFor4=0, avgFor5=0, avgFor6=0, avgFor7=0, avgFor8=0, avgFor9=0;
        Random rand = new Random();

        HashTableQuad testTable;

        //0.1
        for(int i=0; i<100; i++){
            testTable = new HashTableQuad(n, 0.1);
            for(int j=0; j<n; j++){
                testTable.insert(rand.nextInt(123456789));
            }
            avgFor1 += testTable.getProbeNum();
        }
        avgFor1 = avgFor1 / (100*n);


        //0.2
        for(int i=0; i<100; i++){
            testTable = new HashTableQuad(n, 0.2);
            for(int j=0; j<n; j++){
                testTable.insert(rand.nextInt(123456789));
            }
            avgFor2 += testTable.getProbeNum();
        }
        avgFor2 = avgFor2 / (100*n);


        //0.3
        for(int i=0; i<100; i++){
            testTable = new HashTableQuad(n, 0.3);
            for(int j=0; j<n; j++){
                testTable.insert(rand.nextInt(123456789));
            }
            avgFor3 += testTable.getProbeNum();
        }
        avgFor3 = avgFor3 / (100*n);


        //0.4
        for(int i=0; i<100; i++){
            testTable = new HashTableQuad(n, 0.4);
            for(int j=0; j<n; j++){
                testTable.insert(rand.nextInt(123456789));
            }
            avgFor4 += testTable.getProbeNum();
        }
        avgFor4 = avgFor4 / (100*n);


        //0.5
        for(int i=0; i<100; i++){
            testTable = new HashTableQuad(n, 0.5);
            for(int j=0; j<n; j++){
                testTable.insert(rand.nextInt(123456789));
            }
            avgFor5 += testTable.getProbeNum();
        }
        avgFor5 = avgFor5 / (100*n);


        //0.6
        for(int i=0; i<100; i++){
            testTable = new HashTableQuad(n, 0.6);
            for(int j=0; j<n; j++){
                testTable.insert(rand.nextInt(123456789));
            }
            avgFor6 += testTable.getProbeNum();
        }
        avgFor6 = avgFor6 / (100*n);


        //0.7
        for(int i=0; i<100; i++){
            testTable = new HashTableQuad(n, 0.7);
            for(int j=0; j<n; j++){
                testTable.insert(rand.nextInt(123456789));
            }
            avgFor7 += testTable.getProbeNum();
        }
        avgFor7 = avgFor7 / (100*n);


        //0.8
        for(int i=0; i<100; i++){
            testTable = new HashTableQuad(n, 0.8);
            for(int j=0; j<n; j++){
                testTable.insert(rand.nextInt(123456789));
            }
            avgFor8 += testTable.getProbeNum();
        }
        avgFor8 = avgFor8 / (100*n);


        //0.9
        for(int i=0; i<100; i++){
            testTable = new HashTableQuad(n, 0.9);
            for(int j=0; j<n; j++){
                testTable.insert(rand.nextInt(123456789));
            }
            avgFor9 += testTable.getProbeNum();
        }
        avgFor9 = avgFor9 / (100*n);

        System.out.println("0.1: " + avgFor1 + "; 0.2: " + avgFor2 + "; 0.3: " + avgFor3 + "; 0.4: " + avgFor4 + "; 0.5: " + avgFor5 + "; 0.6: " + avgFor6 + "; 0.7: " + avgFor7 + "; 0.8: " + avgFor8 + "; 0.9: " + avgFor9);
        return (avgFor1 + avgFor2 + avgFor3 + avgFor4 + avgFor5 + avgFor6 + avgFor7 + avgFor8 + avgFor9)/9;
    }
}
