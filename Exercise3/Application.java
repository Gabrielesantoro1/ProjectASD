package Exercise3;

import java.util.Random;

/**
 * Application class where it s possible to try our library MinHeap
 * 
 */
public class Application {
    
    public static void main(String[] args) {
        ArrayMinHeap<Integer> array = new ArrayMinHeap<Integer>();
        
        Random r = new Random();
        for(int i = 0; i < 20; i++){
            array.add(r.nextInt(20)+1);
        }
        System.out.println(array.toString());
        
        MinHeap<Integer> heap = new MinHeap<Integer>(array);
        heap.buildMinHeap();
        System.out.println(heap.toString());
    }
    
}
