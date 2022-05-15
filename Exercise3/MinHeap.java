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
        this.heap_size = 0;
        this.length = array.size();
        this.array = array; 
    }

    private int parent(int i){
        return i/2;
    }

    private int left(int i){
        if(2*i+1 <= heap_size){
            return 2*i+1;
        }else{
            return i;
        }
    }

    private int right(int i){
        if((2*i+2) <= heap_size){
            return 2*i+2;
        }else{
            return i;
        }
    }

    private void minHeapify(ArrayMinHeap<T> array, int i){
        int minimum = i;
        int l = left(i);
        int r = right(i);

        System.out.println("minHeapify Minimum INDEX: "+ minimum+ " Left: "+l+" Right: "+r);
        if(l < getLength() && array.compare(array.get(l), array.get(i)) < 0)
            minimum = l;

        if(r < getLength() && array.compare(array.get(r), array.get(minimum)) < 0)
            minimum = r;
    
        if(minimum != i ){
            T tmp = array.get(i);
            array.set(i,array.get(minimum));
            array.set(minimum, tmp);

            minHeapify(array, minimum);
        }
        this.increHeapSize();
    }

    public void buildMinHeap(){
        int start_index = this.getLength()/2 - 1 ;
        System.out.println("BuildMinHeap Start INDEX: "+start_index);
        for(int i = start_index; i >= 0; i--){
            minHeapify(this.array,i);
        }
    }

    public void minHeapInsert(T x){
        array.add(x);
        int i=array.size();
    
        while(i > 1 && array.compare(array.get(i), array.get(parent(i))) == -1){
            T tmp = array.get(i);
            array.set(i, array.get(parent(i)));
            array.set(parent(i), tmp);

            i=parent(i);
        }
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