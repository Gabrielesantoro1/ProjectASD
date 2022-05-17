package Exercise3;

import java.util.ArrayList;
import java.util.Random;

/**
 * Application class where it s possible to try our library MinHeap
 * 
 */
public class Application {
    
    public static void main(String[] args) {
        ComparatorInteger compInt = new ComparatorInteger();
        ArrayList<Integer> array = new ArrayList<>();
        
        Random r = new Random();
        for(int i = 0; i < 10; i++){
            array.add(r.nextInt(10));
        }
        System.out.println("Before build min heap:");
        System.out.println(array.toString());
        
        System.out.println("\n");

        MinHeap<Integer> heap = new MinHeap<Integer>(array, compInt);
        heap.buildMinHeap();
        System.out.println("After build min heap:");
        System.out.println(heap.toString());
        System.out.println(heap.getHeapSize());

        System.out.println("\n");

        int x = -1;
        System.out.println("After insert new element:"+x);
        heap.minHeapInsert(x);
        System.out.println(heap.toString());
        System.out.println(heap.getHeapSize());

        System.out.println("\n");

        System.out.println("After decreased element in index:"+1);
        heap.heapDecreaseKey(1, -1);
        System.out.println(heap.toString());
        System.out.println(heap.getHeapSize());

        System.out.println("\n");

        System.out.println("Extraction of the min value of the heap");
        System.out.println(heap.heapExtractMin());
        System.out.println(heap.toString());
        System.out.println(heap.getHeapSize());

        System.out.println("");

        System.out.println("Creation and adding of an minHeap empty");
        MinHeap<Integer> heap2 = new MinHeap<>(compInt);
        System.out.println(heap2.toString());
        heap2.minHeapInsert(3);
        heap2.minHeapInsert(56);
        heap2.minHeapInsert(1);
        heap2.minHeapInsert(-3);
        heap2.minHeapInsert(6);
        System.out.println(heap2.toString());
    }
    
}
