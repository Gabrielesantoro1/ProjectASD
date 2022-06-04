package Exercise4;

import java.util.LinkedList;

public class ValuesKey<T>{
    private Float distance;
    private LinkedList<Edge<T>> edges;

    public ValuesKey(){
        this.edges = new LinkedList<>();
    }

    public ValuesKey(Float distance){
        this.distance = distance;
        this.edges = new LinkedList<>();
    }

    public ValuesKey(Float distance, LinkedList<Edge<T>> edges){
        this.distance = distance;
        this.edges = edges;
    }

    public Float getDistance() {
        return distance;
    }
    public void setDistance(Float distance) {
        this.distance = distance;
    }
    public LinkedList<Edge<T>> getEdges() {
        return edges;
    }
    public void setEdges(LinkedList<Edge<T>> edges) {
        this.edges = edges;
    }

    
    
}
