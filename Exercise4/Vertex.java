package Exercise4;

public class Vertex<T> {
    private T key;
    private int index;
    
    public Vertex(){}

    public Vertex(T key){this.key = key;}

    public T getValue() {return key;}

    public T getKey() {return key;}

    public int getIndex() {return index;}

    public void setIndex(int index) {this.index = index;}

    public void setKey(T key) {this.key = key;}

    public void print(){
        System.out.println("Vertex:"+(this.getKey()));
    }
    
}
