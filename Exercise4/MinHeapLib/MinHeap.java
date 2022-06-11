package MinHeapLib;

import java.util.ArrayList;
import java.util.Comparator;

import graph.Graph;

public class MinHeap<T,W>{
    private int length;
    private int heap_size;
    private ArrayList<T> array;
    private Comparator<W> comparator;
    private Graph<T,W> graph;

    public MinHeap(Comparator<W> comparator, Graph<T,W> graph) throws MinHeapException{
      if(comparator == null)
        throw new MinHeapException("MinHeap: the comparator parameter is null");
      this.array = new ArrayList<>();
      this.comparator = comparator;
      this.graph = graph;
      this.length = array.size();
      this.heap_size = 0;
    }

    public MinHeap(ArrayList<T> array, Comparator<W> comparator, Graph<T,W> graph) throws MinHeapException {
      if(comparator == null)
        throw new MinHeapException("Minheap: the comparator parameter is null");
      if(array == null)
        throw new MinHeapException("Minheap: the array parameter is null");
      this.array = array;
      this.comparator = comparator;
      this.graph = graph;
      this.length = array.size();
      this.heap_size = 0; 
    }

    private int parent(int i) throws MinHeapException{
      if(i < 0)
        throw new MinHeapException("parent: the i parameter is negative");
      if(i == 0){
        return 0;
      }else{
        return (i-1)/2;
      }
    }

    private int left(int i) throws MinHeapException{
      if(i < 0)
        throw new MinHeapException("left: i parameter is negative");
      if(2*i+1 <= this.getHeapSize()){
        return 2*i+1;
      }else{
        return i;
      }
    }

    private int right(int i) throws MinHeapException{
      if(i < 0)
        throw new MinHeapException("right: the i parameter is negative");
      if(2*i+2 <= this.getHeapSize()){
        return 2*i+2;
      }else{
        return i;
      }
    }

    private void minHeapify(ArrayList<T> array, int i) throws MinHeapException{
      if(array == null)
        throw new MinHeapException("minHeapify: the array parameter is null");
      if(i < 0)
        throw new MinHeapException("minHeapify: the i parameter is negative");
      int minimum = i;
      int l = left(i);
      int r = right(i);

      if(l < getHeapSize() && comparator.compare(graph.getAdjList().get(array.get(l)).getDistance(),graph.getAdjList().get(array.get(minimum)).getDistance()) < 0){
        minimum = l;
      }
      if(r < getHeapSize() && comparator.compare(graph.getAdjList().get(array.get(r)).getDistance(),graph.getAdjList().get(array.get(minimum)).getDistance()) < 0){
        minimum = r;
      }
      if(minimum != i){
        swap(array, i, minimum);
        minHeapify(array, minimum);
      }
    }

    public void buildMinHeap() throws MinHeapException{
      setHeapSize(this.getLength());
      for(int i = this.getLength()/2-1; i >= 0; i--){
        minHeapify(this.array,i);
      }
    }

    public void minHeapInsert(T key) throws MinHeapException{
      setHeapSize(this.getHeapSize()+1);
      int i = getHeapSize();
      array.add(key);        

      heapDecreaseKey(array.get(i-1),graph.getAdjList().get(key).getDistance());
    }
    
    public void heapDecreaseKey(T s, W key) throws MinHeapException {
      int i = this.array.indexOf(s);
      if(comparator.compare(key, graph.getAdjList().get(array.get(i)).getDistance() ) > 0)
        throw new MinHeapException("heapDecreaseKey: key value is minor then the precedent");
      while(i > 0 && comparator.compare(graph.getAdjList().get(array.get(i)).getDistance(),graph.getAdjList().get(array.get(parent(i))).getDistance()) < 0){
        swap(array, i, parent(i));
        i = parent(i);
      }
    }

    public T heapExtractMin() throws MinHeapException{
      T min;
      if(getHeapSize() < 0)
        throw new MinHeapException("heapExcractMin: underflow of heap");
      min = array.get(0);
      array.set(0, array.get(getHeapSize()-1));
      array.remove(array.get(getHeapSize()-1));
      setHeapSize(getHeapSize()-1);
      minHeapify(array, 0);
      return min;
    }

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

    public T getParentElem(ArrayList<T> array, int i) throws MinHeapException{
      if(array == null)
        throw new MinHeapException("getParentElem: array parameter is null");
      return array.get(parent(i));
    }

    public T getRightElem(ArrayList<T> array, int i) throws MinHeapException{
      if(array == null)
        throw new MinHeapException("getRightElem: array parameter is null");
      int rightSonIndx = right(i);
      if(rightSonIndx<getHeapSize() && rightSonIndx != i)
        return array.get(rightSonIndx);
      else
        return null;
    }

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