package Exercise3;

/**
 * Application class where it s possible to try our library MinHeap
 * 
 */
public class Application {
    
    public static void main(String[] args) {
        ArrayMinHeap<Integer> array = new ArrayMinHeap<Integer>();
        array.add(120);
        array.add(6);
        array.add(89);
        System.out.println(array.toString());
        MinHeap<Integer> heap = new MinHeap<Integer>(array);
        heap.buildMinHeap();
        System.out.println(heap.toString());
    }
    
}
