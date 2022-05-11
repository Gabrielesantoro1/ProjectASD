package Exercise3;

import java.util.ArrayList;

public class MinHeap extends ArrayList<Node>{
    private Integer length;
    private Integer heap_size;

    /**
     * Empty constructo
     */
    public MinHeap(){}

    /**
     * Constructor 1
     * @param length
     * @param heap_size
     */
    public MinHeap(Integer length, Integer heap_size) {
        this.length = length;
        this.heap_size = heap_size;
    }

    /**
     * Constructor 2, where it s possible to give an intial capacity to the minheap
     * @param initialCapacity
     * @param length
     * @param heap_size
     */
    public MinHeap(int initialCapacity, Integer length, Integer heap_size) {
        super(initialCapacity);
        this.length = length;
        this.heap_size = heap_size;
    }

    @Override
    public String toString() {
        return "MinHeap [heap_size=" + heap_size + ", length=" + length + "]";
    }

    public Integer getLength() {return length;}

    public void setLength(Integer length) {this.length = length;}

    public Integer getHeap_size() {return heap_size;}

    public void setHeap_size(Integer heap_size) {this.heap_size = heap_size;}

    

    























}