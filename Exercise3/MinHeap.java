package Exercise3;

import java.util.ArrayList;
import java.util.Comparator;

public class MinHeap<T>{
    private int length;
    private int heap_size;
    private ArrayList<T> array;
    private Comparator<? super T> comparator;

    /**
     * Empty constructor
     */
    public MinHeap(Comparator<? super T> comparator){
        array = new ArrayList<>();
        this.comparator = comparator;
        length = array.size();
        heap_size = 0;

    }

    public MinHeap(ArrayList<T> array, Comparator<? super T> comparator) {
        this.array = array;
        this.comparator = comparator;
        this.length = array.size();
        this.heap_size = 0; 
    }

    private int parent(int i){
        return i/2;
    }

    private int left(int i){
        if(2*i+1 <= this.getHeapSize()){
            return 2*i+1;
        }else{
            return i;
        }
    }

    private int right(int i){
        if(2*i+2 <= this.getHeapSize()){
            return 2*i+2;
        }else{
            return i;
        }
    }

    private void minHeapify(ArrayList<T> array, int i){
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

    public void buildMinHeap(){
        setHeapSize(this.getLength());
        for(int i = this.getLength()/2-1; i >= 0; i--){
            minHeapify(this.array,i);
        }
    }

    public void minHeapInsert(T key){
        setHeapSize(this.getHeapSize()+1);
        int i = getHeapSize();
        array.add(key);        

        heapDecreaseKey(i-1, key);
    }

    public void heapDecreaseKey(int i, T key) {
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

    public T heapExtractMin(){
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

    private void swap(ArrayList<T> array, int i, int y){
        T tmp = array.get(i);
        array.set(i, array.get(y));
        array.set(y, tmp);
    }

    public T getFather(ArrayList<T> array, int i){
        return array.get(parent(i));
    }

    public T getRightSon(ArrayList<T> array, int i){
        int rightSonIndx = right(i);
        if(rightSonIndx<getHeapSize() && rightSonIndx != i)
            return array.get(rightSonIndx);
        else
            return null;
    }

    public T getLeftSon(ArrayList<T> array, int i){
        int leftSonIndx=left(i);
        if(leftSonIndx<getHeapSize() && leftSonIndx != i)
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