package Exercise3;

public class MinHeap<T>{
    private int length;
    private int heap_size;
    private ArrayMinHeap<T> array;

    /**
     * Empty constructor
     */
    public MinHeap(){
        array = new ArrayMinHeap<>();
        length = array.size();
        heap_size = 0;
    }

    public MinHeap(ArrayMinHeap<T> array) {
        this.array = array;
        this.length = array.size();
        this.heap_size = 0; 
    }

    private int parent(int i){
        return i/2;
    }

    private int left(int i){
        if(2*i+1 <= this.getHeap_size()){
        return (2*i)+1;
        }
        return i;
    }

    private int right(int i){
        if(2*i+2 <= this.getHeap_size()){
        return (2*i)+2;
        }
        return i;
    }

    private void minHeapify(ArrayMinHeap<T> array, int i){
        int minimum = i;
        int l = left(i);
        int r = right(i);

        //System.out.println("minHeapify Minimum: "+ minimum+ " Left: "+l+" Right: "+r);
        if(l < getHeap_size() && array.compare(array.get(l), array.get(minimum)) < 0){
            minimum = l;
            //System.out.println("minimum"+array.get(minimum));
        }
        if(r < getHeap_size() && array.compare(array.get(r), array.get(minimum)) < 0){
            minimum = r;
            //System.out.println("minimum"+array.get(minimum));
        }
        if(minimum != i){
            swap(array, i, minimum);
            minHeapify(array, minimum);
        }
    }

    public void buildMinHeap(){
        setHeap_size(this.getLength());
        for(int i = this.getLength()/2-1; i >= 0; i--){
            minHeapify(this.array,i);
        }
    }

    public void minHeapInsert(T x){
        array.add(x);
        setHeap_size(this.getHeap_size()+1);
        int i = getHeap_size();
    
        while(i > 0 && array.compare(array.get(i), array.get(parent(i))) < 0){
            swap(array, i, parent(i));
            i = parent(i);
        }
    }

    public void swap(ArrayMinHeap<T> array, int i, int y){
        T tmp = array.get(i);
        array.set(i, array.get(y));
        array.set(y, tmp);
    }

    @Override
    public String toString() {
        return this.array.toString();
    }

    public int getLength() {return length;}

    public void setLength(Integer length) {this.length = length;}

    public int getHeap_size() {return heap_size;}

    public void setHeap_size(Integer heap_size) {this.heap_size = heap_size;}

    public void increHeapSize(){this.heap_size++;}

}