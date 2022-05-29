package Exercise4;

public class Vertex<T> {
    private T key;
    
    public Vertex(){}

    public Vertex(T key){this.key = key;}

    public T getValue() {return key;}

    public T getKey() {return key;}

    public void setKey(T key) {this.key = key;}

    public void print(){
        System.out.println("Vertex:"+(this.getKey()));
    }
    
}
