package Exercise3;

import java.util.ArrayList;
import java.util.Random;

/**
 * Application class where it s possible to try our library MinHeap
 * 
 */
public class Application {
    
    public static void main(String[] args) {
        /**
         * MinHeap for Integer data
         * 
         */
        ComparatorInteger compInt = new ComparatorInteger();
        int j = 0;
        Random r = new Random(); 
        for(int k = 0; k<135; k++){
            ArrayList<Integer> array = new ArrayList<>();
            j = j + 500000;
                for(int i = 0; i < j; i++){
                array.add(r.nextInt());
            }
            System.out.println("Array size:" + array.size());
            MinHeap<Integer> heap = new MinHeap<Integer>(array, compInt);
        }

        // System.out.println("\n");

        // int x = -1;
        // System.out.println("After insert new element:"+x);
        // heap.minHeapInsert(x);
        // System.out.println(heap.toString());
        // System.out.println("Heap size:"+heap.getHeapSize());

        // System.out.println("\n");

        // System.out.println("After decreased element in index:"+1);
        // heap.heapDecreaseKey(1, -1);
        // System.out.println(heap.toString());
        // System.out.println("Heap size:"+heap.getHeapSize());

        // System.out.println("\n");

        // System.out.println("Extraction of the min value of the heap:");
        // System.out.println(heap.heapExtractMin());
        // System.out.println(heap.toString());
        // System.out.println("Heap size:"+heap.getHeapSize());

        // System.out.println("");

        // System.out.println("Creation and adding of an minHeap empty:");
        // MinHeap<Integer> heap2 = new MinHeap<>(compInt);
        // System.out.println(heap2.toString());
        // heap2.minHeapInsert(3);
        // heap2.minHeapInsert(56);
        // heap2.minHeapInsert(1);
        // heap2.minHeapInsert(-3);
        // heap2.minHeapInsert(6);
        // System.out.println(heap2.toString());
        // System.out.println("Heap size:"+heap.getHeapSize());

        // System.out.println("");

        // /**
        //  * MinHeap for String data 
        //  */
        // ComparatorString compStr = new ComparatorString();
        // ArrayList<String> array_str = new ArrayList<>();

        // array_str.add("a");
        // array_str.add("b");
        // array_str.add("c");
        // array_str.add("d");
        // array_str.add("h");

        // System.out.println("Before build min heap:");
        // System.out.println(array_str.toString());
        // System.out.println("Array size:"+array_str.size());

        // System.out.println("");

        // MinHeap<String> heap_str = new MinHeap<>(array_str, compStr);
        // heap_str.buildMinHeap();
        // System.out.println("After build min heap:");
        // System.out.println(heap_str.toString());
        // System.out.println("Heap size:"+heap_str.getHeapSize());
        
        // System.out.println("");

        // String s = "z";
        // System.out.println("After insert new element:"+s);
        // heap_str.minHeapInsert(s);
        // System.out.println(heap_str.toString());
        // System.out.println("Heap size:"+heap_str.getHeapSize());

        // System.out.println("\n");

        // System.out.println("After decreased element in index:"+1);
        // heap_str.heapDecreaseKey(1, "f");
        // System.out.println(heap_str.toString());
        // System.out.println("Heap size:"+heap_str.getHeapSize());

        // System.out.println("\n");

        // System.out.println("Extraction of the min value of the heap:");
        // System.out.println(heap_str.heapExtractMin());
        // System.out.println(heap_str.toString());
        // System.out.println("Heap size:"+heap_str.getHeapSize());

        // System.out.println("");
    }
}
