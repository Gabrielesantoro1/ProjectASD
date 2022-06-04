package Exercise4;

public class Node<T> {
    private T key;
    private Float distance;
    
    public Node(){}

    public Node(T key){this.key = key;}

    public Node(T key, Float distance){this.key = key; this.distance = distance;}

    public T getValue() {return key;}

    public T getKey() {return key;}

    public void setKey(T key) {this.key = key;}

    public Float getDistance() {return distance;}

    public void setDistance(Float distance) {this.distance = distance;}

    public void print(){
        System.out.println("Node:"+(this.getKey()));
    }
}
