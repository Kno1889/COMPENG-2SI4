package Binary.SearchTrees;

// array implementation of MyStack
public class myStack {
    int topInd;
    TNode[] stck;
    int max = 500;

    public myStack(){ // default constructor is with no input => make a TNode array of size max
        stck = new TNode[max];
    }

    public myStack(int n){
        stck = new TNode[n];
    } // if a size is defined, TNode array will be of that size

    public void push(TNode n){ // push
        if(topInd == stck.length - 1){ // only runs if the index of the top is the stack length minus 1
            TNode[] nStck = new TNode[2 * stck.length]; // creates a new stack of double the length
            for(int i = 0; i < nStck.length; i++)
                nStck[i] = stck[i]; // each element in the original stack is added to the new stack
            stck = nStck;
        }
        stck[++topInd] = n; // pre-increment top index and add new node
    }

    public TNode pop(){
        if(this.isEmpty() == true)
            throw new IllegalArgumentException("Empty Stack");

        TNode temp = stck[topInd]; // topmost element is returned
        stck[topInd--] = null; // change popped node's position in stack to null
        return temp;
    }

    public boolean isEmpty(){
        if((topInd < 0) == true)
            return true;
        else
            return false;
    }

    public int size(){
        return stck.length;
    }
}

public class TNode{
    int element;
    TNode left;
    TNode right;
    TNode(int i, TNode l, TNode r)
    { element = i; left = l; right = r; }
}//end class

/* Worst Case time complexities:
constructor 1: 1
constructor 2: n^2
isIn: log(n)
add: log(n)
remove: // log(n)
union:
intersection: //
difference:
print:
print non rec:
*/
public class BSTSet {
    // instance field. We were only allowed one private node
    private TNode root;

    // constructors
    public BSTSet() {
        // empty set constructor: initializes root to null.
        root = null;
    }

    public BSTSet(int[] input) {
        // initialize an object and eliminate repetitions
        // to achieve minimum height, I will sort the input list, eliminate repetitions and take the root as the middle element
        int inLen = input.length; // each node object needs a length, a temporary integer to store elements and an array to hold sorted, non-repetitive elements
        int[] sorted;
        int temp;

        // implements bubble sort to sort the input array in increasing order
        for (int i = 0; i < inLen - 1; i++) {
            for (int j = 0; j < inLen - i - 1; j++) {
                if (input[j + 1] < input[j]) {
                    temp = input[j];
                    input[j] = input[j + 1];
                    input[j + 1] = temp;
                }
            }
        }

        // remove duplicates
        for (int i = 0; i < inLen - 1; i++) {
            if (input[i] == input[i + 1]) {
                for (int j = i + 1; j < inLen - 1; j++) {
                    input[j] = input[j + 1];
                }
                inLen -= 1;
            }
        }

        sorted = new int[inLen];
        for (int i = 0; i <= inLen - 1; i++) {
            sorted[i] = input[i];
        } // makes the sorted array from the input array after sorting, checking for repetitions

        // initializes nodes to create set object
        root = new TNode(sorted[inLen / 2], null, null); // node is taken at the middle of the array
        TNode x = root;

        for (int i = (inLen / 2) - 1; i >= 0; i--) {
            this.add(sorted[i]);  // this for loop
        }

        for (int i = (inLen / 2) - 1; i < inLen; i++) {
            this.add(sorted[i]);
        }
        //this.trav(root);
    }


    public boolean isIn(int v) {
        // return true if v is in this, false otherwise
        TNode x = root;
        while (x != null) {
            if (x.element == v) // v is the root => found in the first time
                return true;
            else if (v < x.element) // worst case it's at a leaf of the tree => log(n) as binary search was implemented
                x = x.left;
            else if (v > x.element)
                x = x.right;
        }
        return false; // if none of the if statements are satisfied
    }

    public void add(int v) {
        // adds v to this if it wasn't already there, nothing otherwise
        TNode x = root;
        if (isIn(v) == false) { // if it doesn't already exist
            while (x != null) {
                if (x.element > v) { // if v is less than the current node's element, go left
                    if (x.left == null) {
                        x.left = new TNode(v, null, null);
                        break;
                    } else
                        x = x.left;
                }
                if (x.element < v) { // if v is greater than the current node's element, go right
                    if (x.right == null) {
                        x.right = new TNode(v, null, null);
                        break;
                    } else
                        x = x.right;
                }
            }
        }
    }

    public boolean remove(int v) {
        // remove v if it exists and returns true. Return false otherwise
        // there are 3 cases for this, as discussed in lecture
        if (this.isIn(v) == true) { // if the element exists in the tree

            TNode r = this.root;
            TNode temp = this.root;
            TNode prevR = null;

            if (v < r.element) {
                r = r.left;
                while (v != r.element) {
                    if (v < r.element) {
                        temp = r;
                        r = r.left;

                    } else if (v > r.element) {
                        temp = r;
                        r = r.right;
                    }
                }
            } else if (v > r.element) {
                r = r.right;
                while (v != r.element) {
                    if (v < r.element) {
                        temp = r;
                        r = r.left;
                    } else if (v > r.element) {
                        temp = r;
                        r = r.right;
                    }
                }
            }
            // after this block, we iterate through the tree till we have r, the element we want to remove, and temp, the parent of r.
            if (r.left == null && r.right == null) { // case 1: the node to remove is a leaf
                if (v < temp.element) { // cuts off r to the left of temp if v is less than temp
                    temp.left = null;
                } else if (v > temp.element) { // and to the right if v is larger than temp
                    temp.right = null;
                }
                r = null; // "deletes" r by setting it to null
                return true; //
            }

            else if (r.left == null && r.right != null) { // Case 2a: r has one child to its right
                if (v > temp.element) {
                    temp.right = r.right;
                } else if (v < temp.element) {
                    temp.left = r.right;
                }
                r = null;
                return true;
            }

            else if (r.left != null && r.right == null) { // Case 2b: r has one child to its left
                if (v > temp.element) {
                    temp.right = r.left;
                } else if (v < temp.element) {
                    temp.left = r.left;
                }
                r = null;
                return true;
            }

            else if (r.left != null && r.left != null) { // Case 3, the most complex one: r has two children
                TNode node2Remove = r;
                int smallest = r.right.element; //minimum value becomes the value that r was

                while (r.left != null && r.right != null) {
                    if (r.right != null && r.left != null) {
                        if (smallest > r.right.element) { // traverse to find the smallest in that branch when r (or its children) have two children
                            smallest = r.right.element;
                        }
                        r = r.right; // keep traversing
                    }
                    if (r.left == null && r.right != null) { // no option to go but right
                        if (smallest > r.right.element) {
                            smallest = r.right.element;
                        }
                        r = r.right;
                    }
                    if (r.left != null && r.right == null) { // go left
                        if (smallest > r.left.element) {
                            smallest = r.left.element;
                        }
                        r = r.left;
                    }
                }

                node2Remove.element = smallest;

                if (node2Remove.right.right != null) {
                    node2Remove.right = node2Remove.right.right; // swap
                } else if (node2Remove.right.right == null) { // just cut off
                    node2Remove.right = null;
                }
                return true;
            }
        }
        return false;
    }


    public BSTSet union(BSTSet s) {
        // return a new object representing the union without modifying this, s
        if (this.size() == 0) // checks if either this or s are empty
            return s;
        else if (s.size() == 0)
            return this;
        else {
            int[] union = new int[this.size() + s.size()]; // maximum possible. Excess initialized zeros are removed when we call the 2nd constructor

            makeUArray(root, union, 0, s); // recursive function called to add all elements in this to the union set
            makeUArray(s.root, union, this.size(), this); // recursive function called to add all elements in s to union
            BSTSet unionSet = new BSTSet(union); // constructor called
            return unionSet; //
        }
    }

    public static int makeUArray(TNode n, int[] arr, int ind, BSTSet s) { // returns index to add the element at in the union set
        if (n.right != null)
            ind = makeUArray(n.right, arr, ind, s); // keep traversing right
        if (n.left != null)
            ind = makeUArray(n.left, arr, ind, s); // keep traversing left
        if (s.isIn(n.element) == false) // modifies union set to avoid duplicates: if it exists in either it is added
            arr[ind] = n.element;// add the element to the array
        return (ind + 1);
    }


    public BSTSet intersection(BSTSet s) {
        // return a new object of the intersection without modifying this or s
        if (size() == 0) { // initial checks if either this or s are of size 0
            return new BSTSet();
        }
        else if (s.size() == 0) {
            return new BSTSet();
        }
        else {
            int length = (size() <= s.size()) ? size() : s.size(); // length has to be the smallest size of the two
            int[] inter = new int[length];
            //recursive algorithm to turn BST into an array

            makeIArray(root, inter, 0, s);

            int counter = 0;
            for (int i = 0; i < inter.length; i++) {
                if (inter[i] == 0) {
                    counter++;
                }
            }
            if (counter == inter.length) {
                return new BSTSet(); // if all zeros, return an empty BSTSet
            }

            if (isIn(0) == true && s.isIn(0) == true) {
                counter--;
            }

            int[] output = new int[inter.length - counter];
            int j = 0;
            int i = 0;
            while (i < inter.length - counter) {
                if (inter[i] != 0) {
                    output[j] = inter[i];
                    j++;
                }
                i++;
            }

            inter = output;
            BSTSet interSet = new BSTSet(inter);
            return interSet;
        }
    }

    public static int makeIArray(TNode n, int[] arr, int ind, BSTSet s) { // same recursive function as for union, only slightly modified
        if (n.right != null) {
            if (s.isIn(n.right.element) == true)
                ind = makeIArray(n.right, arr, ind, s);
        }
        if (n.left != null) {
            if (s.isIn(n.left.element) == true)
                ind = makeIArray(n.left, arr, ind, s);
        }
        if (s.isIn(n.element) == true) // if it exists in both it is added
            arr[ind] = n.element;
        return (ind + 1);
    }

    public int[] makeDArray() {
        // makes an array from a BST Set
        // root == array[size / 2]
        // go from halfway to the end to add while r != null
        // go from halfway to zero while l != null
        int len = this.size();
        int[] arr = new int[len];
        if (len == 0) // initial check
            return arr;
        else {
            makeDArray(this.root, arr, 0);
            return arr;
        }
    }

    public int makeDArray(TNode n, int[] arr, int ind) { // recursive function to make an array from a BSTSet object. array is not in order
        if (n == null)
            return ind;
        ind = makeDArray(n.left, arr, ind);
        arr[ind++] = n.element;
        ind = makeDArray(n.right, arr, ind);
        return ind;
    }

    public BSTSet difference(BSTSet s) {
        // return a new object of the difference between this & s without modifying this or s
        BSTSet diff = new BSTSet();
        int[] thisArr = this.makeDArray();

        for (int i = 0; i < thisArr.length; i++) {
            if (s.isIn(thisArr[i]) == false) // if the element we're checking is not in s, we add it to the diff object
                diff.add(thisArr[i]);
        }
        return diff;
    }

    public int size() {
        // return number of elements in this set
        return size(root);
    }

    public int size(TNode n) { // recursive function called to find the size
        if (n == null)
            return 0;
        else
            return (size(n.left) + 1 + size(n.right));
    }

    public int height() {
        // return the height of this BSTSet. empty => -1
        return height(root);
    }

    public int height(TNode n) {
        if (n == null)
            return -1;
        else
            return (Math.max(height(n.left) + 1, height(n.right) + 1));
    }


    public void printBSTSet() {
        // output elements of this set in increasing order
        if (root == null)
            System.out.println("The set is empty");
        else {
            System.out.println("The set elements are: ");
            printBSTSet(root);
            System.out.println("\n");
        }
    }

    private void printBSTSet(TNode t) {
        if (t != null) {
            printBSTSet(t.left);
            System.out.print(" " + t.element + ", ");
            printBSTSet(t.right);
        }
    }

    public void printNonRec() {
        // uses stack to print integers in this set in increasing order (in-order traversal).
        myStack stackBST = new myStack();
        TNode curr = this.root;

        while(stackBST.isEmpty() == false || curr !=null){
            if(curr != null){
                stackBST.push(curr);
                curr = curr.left;
            }
            else{
                curr = stackBST.pop();
                if(curr != null){
                    System.out.print(curr.element + ", ");
                    curr = curr.right;
                }
            }
        }
    System.out.println("\n");
    }

}
