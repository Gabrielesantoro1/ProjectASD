package Exercise4;

public class Node<T>{
    private T key;
    
    public Node(){}

    public Node(T key){this.key = key;}

    public T getValue() {return key;}

    public T getKey() {return key;}

    public void setKey(T key) {this.key = key;}

    public void print(){
        System.out.println("Node:"+(this.getKey()));
    }
    
}
