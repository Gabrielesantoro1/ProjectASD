package graph;

public class Edge<T,W> {
    private T source;
    private T destination;
    private W label;
    private int index;

    public Edge(){}

    public Edge(T source, T destination){
        this.source = source;
        this.destination = destination;
    }

    public Edge(T source, T destination, W label){
        this.source = source;
        this.destination = destination;
        this.label = label;
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

    public W getLabel() {
        return label;
    }

    public void setLabel(W label) {
        this.label = label;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void print(){
        System.out.println("From:"+this.getSource()+"-> to: "+this.getDestination()+" distance: "+this.getLabel());
    }

    
    
}