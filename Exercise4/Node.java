package Exercise4;

public class Node<T> {
    private T key;
    private Float d;

    public Node(){}

    public Node(T key){
        this.key = key;
    }

    public Node(T key, Float d){
        this.key = key;
        this.d = d;
    }

    public T getKey() {
        return key;
    }
    public void setKey(T key) {
        this.key = key;
    }
    public Float getD() {
        return d;
    }
    public void setD(Float d) {
        this.d = d;
    }

    
}
