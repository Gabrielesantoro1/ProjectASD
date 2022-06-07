package Exercise3;

import java.util.ArrayList;
import java.util.Random;

import Exercise3.src.ComparatorInteger;
import Exercise3.src.ComparatorString;
import Exercise3.src.MinHeap;
import Exercise3.src.MinHeapException;

/**
 * Application class where it is possible to try our library MinHeap
 */
public class Application {
    public static void main(String[] args) {
        /**
         * MinHeap for Integer data
         */
        ComparatorInteger compInt = new ComparatorInteger();
        Random r = new Random();

        ArrayList<Integer> array = new ArrayList<>();
        for(int k = 0; k<135; k++){
          array.add(r.nextInt(200)+1);    
        }

        System.out.println("Before build min heap:");
        System.out.println(array.toString());
        System.out.println("Array size:" + array.size());
        System.out.println("\n");

        try{
        MinHeap<Integer> heap = new MinHeap<>(compInt);
        heap.setArray(array);

        int x = -1;
        System.out.println("After insert new element:"+x);
        heap.minHeapInsert(x);
        System.out.println(heap.toString());
        System.out.println("Heap size:"+heap.getHeapSize());

        System.out.println("\n");

        System.out.println("After decreased element in index:"+1);
        heap.heapDecreaseKey(1, -1);
        System.out.println(heap.toString());
        System.out.println("Heap size:"+heap.getHeapSize());

        System.out.println("\n");

        System.out.println("Extraction of the min value of the heap:");
        System.out.println(heap.heapExtractMin());
        System.out.println(heap.toString());
        System.out.println("Heap size:"+heap.getHeapSize());

        }catch(MinHeapException e){
          e.printStackTrace();
        }
        System.out.println("");

        /**
         * MinHeap for String data 
         */
        ComparatorString compStr = new ComparatorString();
        ArrayList<String> array_str = new ArrayList<>();
        array_str.add("AAABC");
        array_str.add("SDFAD");
        array_str.add("asdasd");
        array_str.add("adhfdbs");
        array_str.add("arrrra");

        System.out.println("Before build min heap:");
        System.out.println(array_str.toString());
        System.out.println("Array size:"+array_str.size());

        System.out.println("");
        try{
        MinHeap<String> heap_str = new MinHeap<>(compStr);
        heap_str.setArray(array_str);
        heap_str.buildMinHeap();
        System.out.println("After build min heap:");
        System.out.println(heap_str.toString());
        System.out.println("Heap size:"+heap_str.getHeapSize());
        
        System.out.println("");

        String s = "z";
        System.out.println("After insert new element:"+s);
        heap_str.minHeapInsert(s);
        System.out.println(heap_str.toString());
        System.out.println("Heap size:"+heap_str.getHeapSize());

        System.out.println("\n");

        System.out.println("After decreased element in index:"+1);
        heap_str.heapDecreaseKey(1, "z");
        System.out.println(heap_str.toString());
        System.out.println("Heap size:"+heap_str.getHeapSize());

        System.out.println("\n");

        System.out.println("Extraction of the min value of the heap:");
        System.out.println(heap_str.heapExtractMin());
        System.out.println(heap_str.toString());
        System.out.println("Heap size:"+heap_str.getHeapSize());
        
        }catch(MinHeapException e){
          e.printStackTrace();
        }
        System.out.println("");
    }
}
