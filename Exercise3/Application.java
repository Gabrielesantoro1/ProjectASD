package Exercise3;

import java.io.FileWriter;
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
        
         /* TESTING */
        int j = 500000;
        ComparatorInteger compInt = new ComparatorInteger();
        
        Random r = new Random(); 
        for(int k = 0; k<80; k++){
        ArrayList<Integer> array = new ArrayList<>();
        j = j + 500000;
        for(int i = 0; i < j; i++){
            array.add(r.nextInt());
        }
        System.out.println("Array size:" + array.size());
        MinHeap<Integer> heap = new MinHeap<Integer>(array, compInt);
        heap.buildMinHeap();
        long start = System.currentTimeMillis();
        // heap.heapExtractMin();
        // heap.minHeapInsert(r.nextInt());
        System.out.println("Array size after: " + array.size());
        long end = System.currentTimeMillis();
        double msec = (end-start)/1000.0;
        System.out.println("Time execution: "+ msec);
        System.out.println();
        try{
            FileWriter out = new FileWriter("testing.txt",true);
            out.write(msec+"\n");
            out.close();
        }catch(Exception e){
            e.getStackTrace();
        }
        }   
        /* END TESTING */

        // ComparatorInteger compInt = new ComparatorInteger();
        // ArrayList<Integer> array = new ArrayList<>();
        
        // Random r = new Random();
        // for(int i = 0; i < 10; i++){
        //     array.add(r.nextInt(10));
        // }

        // System.out.println("Before build min heap:");
        // System.out.println(array.toString());
        // System.out.println("Array size:"+array.size());
        
        // System.out.println("\n");

        // MinHeap<Integer> heap = new MinHeap<Integer>(array, compInt);
        // heap.buildMinHeap();
        // System.out.println("After build min heap:");
        // System.out.println(heap.toString());
        // System.out.println("Heap size:"+heap.getHeapSize());
        // System.out.println("Father of the " +array.get(0)+": " +heap.getFather(array, 0));
        // System.out.println("Son Left of the "+array.get(0)+": "+heap.getLeftSon(array, 0));
        // System.out.println("Son right of the "+array.get(0)+": "+heap.getRightSon(array, 0));
        // System.out.println("Father of the "+array.get(1)+": "+heap.getFather(array, 1));
        // System.out.println("Son left of the "+array.get(1)+": "+heap.getLeftSon(array, 1));
        // System.out.println("Son right of the "+array.get(1)+": "+heap.getRightSon(array, 1));
        // System.out.println("Father of the "+array.get(2)+": "+heap.getFather(array, 2));
        // System.out.println("Son Left of the "+array.get(2)+": "+heap.getLeftSon(array, 2));
        // System.out.println("Son right of the "+array.get(2)+": "+heap.getRightSon(array, 2));
        
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
