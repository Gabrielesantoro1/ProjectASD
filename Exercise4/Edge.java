package Exercise4;

public class Edge {
    private Vertex source;
    private Vertex destination;
    private Integer weight;

    public Edge(){}

    public Edge(Vertex source, Vertex destination){
        this.source = source;
        this.destination = destination;
    }

    public Edge(Vertex source, Vertex destination, Integer weight){
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    public Vertex getSource() {
        return source;
    }

    public void setSource(Vertex source) {
        this.source = source;
    }

    public Vertex getDestination() {
        return destination;
    }

    public void setDestination(Vertex destination) {
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