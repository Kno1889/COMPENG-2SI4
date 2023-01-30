// package Max.Heap;

public class MaxHeap{
    // instance fields
    private Integer[] heapArr; // Integer array to hold elements of the heap. Empty space (max - num) is null
    private int numNodes; // int representing number of occupied, non-null elements in the array. The root of the heap "bonary tree"/ the largest element is stored at index 0
                          // and the latest insertion/last element is stored at index (numNodes - 1)
    private int maxNodes; // int representing maximum capacity of heap. Doubled when necessary

    // constructors
    public MaxHeap(int size){ // empty heap constructor
        this.maxNodes = size; // capacity is set to the input
        this.heapArr = new Integer[maxNodes]; // initialize the Integer array
        this.numNodes = 0; // it's empty so the number of elements is zero
    }

    public MaxHeap(Integer[] someArray){ // second constructor. Creates a heap array from an Integer array
        this.maxNodes = someArray.length; // we need to allocate memory for the number of elements in the input array
        heapArr = new Integer[maxNodes]; // initialize the heap array of size equal to the input array

        for(int i = 0; i < maxNodes; i++)
            this.insert(someArray[i]); // call the insert method to place elements in their appropriate indexes
    }

    // getters
    public int getCapacity(){return this.maxNodes;} // returns max possible size (occupied and null indexes)

    public int getSize(){return this.numNodes;} // returns the size of the heap, or the number of occupied indexes

    // methods
    public void insert(int n){ // add the element n to the end of the heap array then "bubble up" till it reaches its appropriate spot in the heap

        if(this.numNodes + 1 > this.maxNodes){ // if this insertion will cause an overflow in the heap array,
            Integer[] tempBigger = new Integer[this.maxNodes * 2]; // create a temporary array of size double that of the current heap array
            for(int i = 0; i < this.maxNodes; i++)
                tempBigger[i] = this.heapArr[i]; // copy all elements currently in the heap array into the second, temporary array

            tempBigger[this.maxNodes] = n; // finally add n at the end of the temporary array at index maxNodes of the old heap array

            this.maxNodes = tempBigger.length; // the new maxNodes is now the size of the bigger array
            this.heapArr = tempBigger; // set the bigger array to become the new heap array of the object
        }
        heapArr[numNodes++] = n; // this adds the element at the end of the heap array. If we had to enter the previous block, it just overwrites the element we just added with its
                                 // same value. Otherwise, that element is added for the first time. In other words, the last non-null element in the heap array will be n, which is
                                 // what we need.

        // bubble up the last element. This means swapping the element with its parent when this element is greater than its parent since in a maxheap the parent must always be
        // bigger than its children. If a child is at index [n], its parent is at [(n - 1) / 2] always

        int currentN = numNodes - 1; // current element to start bubbling up is the last one, the most recently added
        int current = heapArr[currentN];

        while(current > heapArr[(currentN - 1) / 2] && currentN > 0){ // if current is bigger than its parent, and we're within array boundaries (index > 0)
            heapArr[currentN] = heapArr[(currentN - 1) / 2]; // swap current with its parent
            currentN = (currentN - 1) / 2; // set current's index to it's parent's index to move on to the next level of bubbling up
        }
        heapArr[currentN] = current;

    }

    public int deleteMax(){ // removes the max element, the element at index 0. First, this method swaps it with the last value (lowest, right most if we're imagining a binary tree)
                             // then bubbles down the temporary max to restore the max heap as it should be, with a new maximum element at the top (index 0)

        int elementToReturn = this.heapArr[0]; // store max element in a variable to return
        heapArr[0] = heapArr[numNodes - 1]; // replace top with last item
        numNodes--; // decrement number of active elements

        // now to bubble down the new temporary top
        int n = 0;
        int childN;
        int temp = heapArr[n];

        while(2 * n + 1 < numNodes){ // while within array boundaries
            childN = largerChildN(n); // we want to swap with the larger of the two children

            if(temp < heapArr[childN]) // if child is larger
                heapArr[n] = heapArr[childN]; // change the value of the parent
            else
                break; // otherwise break
            n = childN; // moving on to the next level of the binary tree to carry on bubbling down
        }
        heapArr[n] = temp;

        return elementToReturn;
    }

    private int largerChildN(int n){ // method for finding the larger child. returns the index of the larger of the two children of the index n
        int LChildN = 2 * n + 1; // left child is at 2n+1
        int RChildN = 2 * n + 2; // right child is at 2n+2

        if(heapArr[LChildN] > heapArr[RChildN])
            return LChildN;
        else if(heapArr[RChildN] > heapArr[LChildN])
            return RChildN;
        else // even if both are equal, return the index of the larger child
            return RChildN;
    }

    public String toString(){ // convert the heap array to a String form to be able to view it as system output

        String output = "";

        if(heapArr[0] == null){
            output = output + "Empty Heap";
            return output;
        }

        output = Integer.toString(heapArr[0]);
        for(int i = 1; i < this.numNodes; i++)
            output = output + ", " + Integer.toString(heapArr[i]);

        return output;
    }

    public static void heapsort(Integer[] arrayToSort){ // returns the elements of the heap in descending order

        Integer[] sortedArray = arrayToSort; // initialize a new array to hold sorted elements without changing elements of the input array
        MaxHeap heap1 = new MaxHeap(sortedArray);

        for(int i = 0; i < sortedArray.length; i++)
            sortedArray[i] = heap1.deleteMax(); // to get descending order
        arrayToSort = sortedArray;
    }
}
