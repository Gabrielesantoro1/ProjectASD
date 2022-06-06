package Exercise4.MinHeapLibrary;

import java.util.ArrayList;
import java.util.Comparator;

import Exercise4.Graph;

public class MinHeap<T,W>{
    private int length;
    private int heap_size;
    private ArrayList<T> array;
    private Comparator<W> comparator;
    private Graph<T,W> graph;

    public MinHeap(Comparator<W> comparator, Graph<T,W> graph){
      this.array = new ArrayList<>();
      this.comparator = comparator;
      this.graph = graph;
      this.length = array.size();
      this.heap_size = 0;
    }

    public MinHeap(ArrayList<T> array, Comparator<W> comparator, Graph<T,W> graph) {
      this.array = array;
      this.comparator = comparator;
      this.graph = graph;
      this.length = array.size();
      this.heap_size = 0; 
    }

    private int parent(int i){
      if(i == 0){
        return 0;
      }else{
        return (i-1)/2;
      }
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

    public void buildMinHeap(){
      setHeapSize(this.getLength());
      for(int i = this.getLength()/2-1; i >= 0; i--){
        minHeapify(this.array,i);
      }
    }

    /*Non ha senso, perchè nel nostro la chiave che passiamo
    è un valore nuovo di cui non abbiamo l'oggetto nel grafo
    Però non mi sembra che utilizziamo questo metodo. 
    */
    public void minHeapInsert(T key){
      setHeapSize(this.getHeapSize()+1);
      int i = getHeapSize();
      array.add(key);        

      heapDecreaseKey(array.get(i-1),graph.getAdjList().get(key).getDistance());
    }
    
    public void heapDecreaseKey(T s, W key) {
      System.out.println(s);
      int i = -1;
      try{
        i = this.array.indexOf(s);
      }catch(IndexOutOfBoundsException e){
      }
      if(i!=-1){
        if(comparator.compare(key, graph.getAdjList().get(array.get(i)).getDistance() ) > 0){
          System.out.println("Key value is bigger then the old one");
          return;
        }
      //graph.getAdjList().get(array.get(i)).setDistance(key); -> non dobbiamo settare la distanza ma cambiare l'elemento
        while(i > 0 && comparator.compare(graph.getAdjList().get(array.get(i)).getDistance(),graph.getAdjList().get(array.get(parent(i))).getDistance()) < 0){
          swap(array, i, parent(i));
          i = parent(i);
        }
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

    @Override
    public String toString() {return this.array.toString();}

    public int getLength() {return length;}

    public void setLength(Integer length) {this.length = length;}

    public int getHeapSize() {return heap_size;}

    public void setHeapSize(Integer heap_size) {this.heap_size = heap_size;}

    public ArrayList<T> getArray() {return array;}

    public void setArray(ArrayList<T> array) {this.array = array;}
}