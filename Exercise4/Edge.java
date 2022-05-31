package Exercise4;

public class Edge<T> {
    private Node<T> source;
    private Node<T> destination;
    private Integer weight;

    public Edge(){}

    public Edge(Node<T> source, Node<T> destination){
        this.source = source;
        this.destination = destination;
    }

    public Edge(Node<T> source, Node<T> destination, Integer weight){
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    public Node<T> getSource() {
        return source;
    }

    public void setSource(Node<T> source) {
        this.source = source;
    }

    public Node<T> getDestination() {
        return destination;
    }

    public void setDestination(Node<T> destination) {
        this.destination = destination;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public void print(){
        System.out.println(this.getSource().getKey()+"->"+this.getDestination().getKey());
    }

    
    
}