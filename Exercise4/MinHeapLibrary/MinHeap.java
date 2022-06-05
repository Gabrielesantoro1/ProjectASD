package Exercise4.MinHeapLibrary;

import java.util.ArrayList;
import java.util.Comparator;

import Exercise4.Graph;

public class MinHeap{
    private int length;
    private int heap_size;
    private ArrayList<String> array;
    private Comparator<Float> comparator;

    private Graph<String> graph;


    public MinHeap(Comparator<Float> comparator, Graph<String> graph){
        array = new ArrayList<>();
        this.comparator = comparator;
        this.graph = graph;
        length = array.size();
        heap_size = 0;

    }

    public MinHeap(ArrayList<String> array, Comparator<Float> comparator, Graph<String> graph) {
        this.array = array;
        this.comparator = comparator;
        this.length = array.size();
        this.heap_size = 0; 
        this.graph = graph;
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

    private void minHeapify(ArrayList<String> array, int i){
        int minimum = i;
        int l = left(i);
        int r = right(i);

        //System.out.println("minHeapify Minimum: "+ minimum+ " Left: "+l+" Right: "+r);
        if(l < getHeapSize() && comparator.compare(graph.getAdjList().get(array.get(l)).getDistance(),graph.getAdjList().get(array.get(minimum)).getDistance()) < 0){
            minimum = l;
            //System.out.println("minimum"+array.get(minimum));
        }
        if(r < getHeapSize() && comparator.compare(graph.getAdjList().get(array.get(r)).getDistance(),graph.getAdjList().get(array.get(minimum)).getDistance()) < 0){
            minimum = r;
            //System.out.println("minimum"+array.get(minimum));
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

    public void minHeapInsert(String key){
        setHeapSize(this.getHeapSize()+1);
        int i = getHeapSize();
        array.add(key);        

        heapDecreaseKey(array.get(i-1),graph.getAdjList().get(key).getDistance());
    }

    public int getIndex(String s){
        int index = -1;
        for(int i = 0; i < this.getHeapSize();i++){
            if(array.get(i).equals(s)){
                index = i;
                return index;
            }
        }
        return index;
    }

    public void heapDecreaseKey(String s, float key) {
        System.out.println(s);
        int i = getIndex(s);
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

    public String heapExtractMin(){
        String min;
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

    private void swap(ArrayList<String> array, int i, int y){
        String tmp = array.get(i);
        array.set(i, array.get(y));
        array.set(y, tmp);
    }


    @Override
    public String toString() {return this.array.toString();}

    public int getLength() {return length;}

    public void setLength(Integer length) {this.length = length;}

    public int getHeapSize() {return heap_size;}

    public void setHeapSize(Integer heap_size) {this.heap_size = heap_size;}

    public ArrayList<String> getArray() {return array;}

    public void setArray(ArrayList<String> array) {this.array = array;}
}