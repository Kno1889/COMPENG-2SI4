// package Hash.Tables;
// most things for this class are similar to the linear probing one, with a few exceptions

public class HashTableQuad {
    private Integer[] table;
    private int tableSize; // stays constant until rehashing
    private int keysNum; // number of non-null fields. Incremented at every insertion
    private int maxKeys; // maximum load allowed before rehashing
    private double currentLoadF; // current load factor = keysNum / tableSize
    private int probeNum = 0; // number of probes needed to find an index for each element

    public HashTableQuad(int maxNum, double load){
        this.maxKeys = maxNum;
        this.currentLoadF = load;

        int x = (int) (maxKeys / load); // minimum size based on inputs. May or may not be prime => check

        while(this.isPrime(x) == false)
            x++;

        this.table = new Integer[x];
        this.tableSize = x;
        this.keysNum = 0;
    }

    public void insert(int n){
        int tempInd = n % this.tableSize;
        int actInd = n % this.tableSize;
        int step = 0;

        if(((this.keysNum + 1) / tableSize) > this.currentLoadF){
            this.rehash();
            this.insert(n);
        }else{
            // keysNum++;
            while(this.table[actInd] != null){
                probeNum++;

                if(this.table[actInd] == n)
                    return;

                //quadratic probing procedure
                step++;
                if((tempInd + Math.pow(step, 2)) < this.tableSize - 1) {
                    actInd = (int) (tempInd + Math.pow(step, 2)); // increase index probed  by step^2
                    probeNum++;
                }
                else {
                    actInd = (int) (tempInd + Math.pow(step, 2)) % this.tableSize;
                    probeNum++;
                }
            }
            this.table[actInd] = n;
            keysNum++;
        }
    }

    public void rehash(){
        HashTableQuad tempBigger;
        tempBigger = new HashTableQuad((this.tableSize * 2), this.currentLoadF);

        for(int i = 0; i < this.tableSize; i++){
            if(this.table[i] != null) {
                tempBigger.insert(table[i]);
            }
        }

        //System.out.println("Rehash Required! ");
        this.table = tempBigger.table;
        this.tableSize = tempBigger.tableSize;
        this.maxKeys = tempBigger.maxKeys;
        this.keysNum = tempBigger.keysNum;
    }

    public boolean isIn(int n){
        int tempInd = n % this.tableSize;
        int actInd = n % this.tableSize;
        int step = 0;

        while(this.table[actInd] != null){
            if(this.table[actInd] == n)
                return true;

            step++;
            if((tempInd + Math.pow(step, 2)) < (this.tableSize - 1))
                actInd = (int) (tempInd + Math.pow(step, 2));
            else
                actInd = (int) (tempInd + Math.pow(step, 2)) % this.tableSize;
        }
        return false;
    }

    public void printKeys(){
        for(int i = 0; i < this.tableSize; i++){
            if(this.table[i] != null)
                System.out.println("Key: " + table[i]);
        }
    }

    public void printKeysAndIndexes(){
        for(int i = 0; i < this.tableSize; i++){
            if(this.table[i] != null)
                System.out.println("Key: " + this.table[i] + ", Index: " + i);
        }
    }

    public int getTableSize(){return this.tableSize;}

    public int getKeysNum(){return this.keysNum;}

    public int getMaxKeys(){return this.maxKeys;}

    public double getLoadFactor(){return this.currentLoadF;}

    public int getProbeNum(){return this.probeNum;}


    public boolean isPrime(int n){ // method found on stackOverflow
        int counter = 0;           // this method returns the next prime number after n

        for(int i = 2; i < (n / 2); i++){
            if(n % 2 == 0)
                counter++;
        }

        if(counter == 0)
            return true;
        else
            return false;

    }
}
