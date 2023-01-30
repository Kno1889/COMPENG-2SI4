// package Max.Heap;

public static void myTest(){
        Integer[] a1 = {1, 2, 3, 4, 5, 6};
        int size = 5;

        // constructor 1 Test
        System.out.println("Constructor 1 Test: ");
        MaxHeap h1 = new MaxHeap(size);
        System.out.println(h1.toString());
        System.out.println("\n");

        // constructor 2 Test
        System.out.println("Constructor 2 Test: ");
        MaxHeap h2 = new MaxHeap(a1);
        System.out.println(h2.toString());
        System.out.println("\n");

        // insertion test
        System.out.println("Insert Method Test: ");
        h1.insert(1);
        h1.insert(2);
        h1.insert(3);
        h1.insert(4);
        h1.insert(5);
        h1.insert(6);
        System.out.println("The following two heaps should be the same: ");
        System.out.println("Heap 1: " + h1.toString());
        System.out.println("Heap 2: " + h2.toString());
        System.out.println("\n");

        // deleteMax test (Note: deleteMax must be turned public to allow testing it individually)
        //for(int i = 0; i < a1.length; i++)
        //     System.out.println(h2.deleteMax());
        // System.out.println("\n");

        // toString is tested throughout

        // heapsort test
        System.out.println("Heapsort Method Test: ");
        System.out.println("Array before heapsort: " + Arrays.toString(a1));
        MaxHeap.heapsort(a1);
        System.out.println("Array after heapsort: " + Arrays.toString(a1));
}
