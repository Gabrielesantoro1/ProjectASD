package Exercise4;

public class Edge<T> {
    private T source;
    private T destination;
    private Float weight;
    private int index;

    public Edge(){}

    public Edge(T source, T destination){
        this.source = source;
        this.destination = destination;
    }

    public Edge(T source, T destination, Float weight){
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    public T getSource() {
        return source;
    }

    public void setSource(T source) {
        this.source = source;
    }

    public T getDestination() {
        return destination;
    }

    public void setDestination(T destination) {
        this.destination = destination;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }


    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void print(){
        System.out.println("From:"+this.getSource()+"->"+this.getDestination()+":"+this.getWeight());
    }

    
    
}