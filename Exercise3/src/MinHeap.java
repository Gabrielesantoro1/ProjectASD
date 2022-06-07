package Exercise3.src;

import java.util.ArrayList;
import java.util.Comparator;

public class MinHeap<T>{
    private int length;
    private int heap_size;
    private ArrayList<T> array;
    private Comparator<? super T> comparator;

    public MinHeap(Comparator<? super T> comparator) throws MinHeapException{
      if(comparator == null)
        throw new MinHeapException("MinHeap: the comparator parameter is null");
      this.comparator = comparator;
      array = new ArrayList<>();
      length = array.size();
      heap_size = 0;
    }

    public MinHeap(ArrayList<T> array, Comparator<? super T> comparator) throws MinHeapException {
      if(comparator == null)
        throw new MinHeapException("Minheap: the comparator parameter is null");
      if(array == null)
        throw new MinHeapException("Minheap: the array parameter is null");
      this.comparator = comparator;
      this.array = array;
      this.length = array.size();
      this.heap_size = 0; 
    }

    /**
     * It returns the index of the parent of the node at index i.
     * 
     * @param i the index of the node in the array
     * @return The index of the parent node.
     * @throws MinHeapException
     */
    private int parent(int i) throws MinHeapException{
      if(i < 0)
        throw new MinHeapException("parent: the i parameter is negative");
      if(i == 0){
        return 0;
      }else{
        return (i-1)/2;
      }
    }

    /**
     * It returns the left child of the node at index i.
     * 
     * @param i the index of the node in the array
     * @return The index of the left child of the node at index i.
     * @throws MinHeapException
     */
    private int left(int i) throws MinHeapException{
      if(i < 0)
        throw new MinHeapException("left: i parameter is negative");
      if(2*i+1 <= this.getHeapSize()){
        return 2*i+1;
      }else{
        return i;
      }
    }

    /**
     * It returns the right child of the node at index i.
     * 
     * @param i the index of the node in the array
     * @return The right child of the node at index i.
     * @throws MinHeapException
     */
    private int right(int i) throws MinHeapException{
      if(i < 0)
        throw new MinHeapException("right: the i parameter is negative");
      if(2*i+2 <= this.getHeapSize()){
        return 2*i+2;
      }else{
        return i;
      }
    }

    /**
     * If the left or right child of the current node is smaller than the current node, swap the
     * current node with the smaller child
     * 
     * @param array the array that is being heapified
     * @param i index of the node to be heapified
     * @throws MinHeapException
     */
    private void minHeapify(ArrayList<T> array, int i) throws MinHeapException{
      if(array == null)
        throw new MinHeapException("minHeapify: the array parameter is null");
      if(i < 0)
        throw new MinHeapException("minHeapify: the i parameter is negative");
      int minimum = i;
      int l = left(i);
      int r = right(i);

      if(l < getHeapSize() && comparator.compare(array.get(l), array.get(minimum)) < 0){
        minimum = l;
      }
      if(r < getHeapSize() && comparator.compare(array.get(r), array.get(minimum)) < 0){
        minimum = r;
      }
      if(minimum != i){
        swap(array, i, minimum);
        minHeapify(array, minimum);
      }
    }

    /**
     * It builds a min heap from an array.
     * @throws MinHeapException
     */
    public void buildMinHeap() throws MinHeapException{
        setHeapSize(this.getLength());
        for(int i = this.getLength()/2-1; i >= 0; i--){
            minHeapify(this.array,i);
        }
    }

    /**
     * We add the new key to the end of the array, then we call heapDecreaseKey to fix the heap
     * 
     * @param key the key to be inserted
     * @throws MinHeapException
     */
    public void minHeapInsert(T key) throws MinHeapException{
        setHeapSize(this.getHeapSize()+1);
        int i = getHeapSize();
        array.add(key);        

        heapDecreaseKey(i-1, key);
    }

    /**
     * The function takes an index and a key as input. If the key is smaller than the value at the
     * index, then the value at the index is replaced with the key. Then, the heap property is restored
     * by swapping the value at the index with its parent until the heap property is restored
     * 
     * @param i the index of the element to decrease
     * @param key the new value of the element at index i.
     * @throws MinHeapException
     */
    public void heapDecreaseKey(int i, T key) throws MinHeapException {
      if(i < 0)
        throw new MinHeapException("heapDecreaseKey: i parameter is negative");
      if(comparator.compare(key, array.get(i)) > 0){
        System.out.println("Key value is bigger then the old one");
        return;
      }
      array.set(i, key);
      while(i > 0 && comparator.compare(array.get(i), array.get(parent(i))) < 0){
        swap(array, i, parent(i));
        i = parent(i);
      }
    }

    /**
     * The function removes the minimum element from the heap and returns it
     * 
     * @return The minimum value in the heap.
     * @throws MinHeapException
     */
    public T heapExtractMin() throws MinHeapException{
        T min;
        if(getHeapSize() < 0){
            System.out.println("Underflow of heap");
        }
        min = array.get(0);
        array.set(0, array.get(getHeapSize()-1));
        array.remove(array.get(getHeapSize()-1));
        setHeapSize(getHeapSize()-1);
        minHeapify(array, 0);
        return min;
    }

    /**
     * Swap the values of the two elements in the array at the given indices
     * 
     * @param array The array to be sorted
     * @param i the index of the first element
     * @param y the index of the element to be swapped
     * @throws MinHeapException
     */
    private void swap(ArrayList<T> array, int i, int y) throws MinHeapException{
      if(array == null)
        throw new MinHeapException("swap: array parameter is null");
      if(i < 0)
        throw new MinHeapException("swap: i parameter is negative");
      if(y < 0)
        throw new MinHeapException("swap: y parameter is negative");
      T tmp = array.get(i);
      array.set(i, array.get(y));
      array.set(y, tmp);
    }

    /**
     * This function returns the parent element of the element at index i in the array
     * 
     * @param array The array that the heap is stored in
     * @param i the index of the element in the array
     * @return The parent element of the array.
     * @throws MinHeapException
     */
    public T getParentElem(ArrayList<T> array, int i) throws MinHeapException{
      if(array == null)
        throw new MinHeapException("getParentElem: array parameter is null");
      return array.get(parent(i));
    }

    /**
     * If the right son index is less than the heap size and the right son index is not equal to the
     * index, return the right son. Otherwise, return null
     * 
     * @param array the array that the heap is built on
     * @param i the index of the element in the array
     * @return The right element of the array.
     * @throws MinHeapException
     */
    public T getRightElem(ArrayList<T> array, int i) throws MinHeapException{
      if(array == null)
        throw new MinHeapException("getRightElem: array parameter is null");
      int rightSonIndx = right(i);
      if(rightSonIndx<getHeapSize() && rightSonIndx != i)
        return array.get(rightSonIndx);
      else
        return null;
    }

    /**
     * If the left child of the node at index i exists, return the left child of the node at index i.
     * Otherwise, return null
     * 
     * @param array the array that the heap is built on
     * @param i the index of the element in the array
     * @return The left element of the array.
     * @throws MinHeapException
     */
    public T getLeftElem(ArrayList<T> array, int i) throws MinHeapException{
      if(array == null)
        throw new MinHeapException("getLeftElem: array parameter is null");
      int leftSonIndx = left(i);
      if(leftSonIndx < getHeapSize() && leftSonIndx != i)
        return array.get(leftSonIndx);
      else
        return null;
    }

    @Override
    public String toString() {return this.array.toString();}

    public int getLength() {return length;}

    public void setLength(Integer length) {this.length = length;}

    public int getHeapSize() {return heap_size;}

    public void setHeapSize(Integer heap_size) {this.heap_size = heap_size;}

    public ArrayList<T> getArray() {return array;}

    public void setArray(ArrayList<T> array) {this.array = array;}
}