package Exercise4;

import java.util.LinkedList;

public class ValuesKey<T,W>{
    private W distance;
    private LinkedList<Edge<T,W>> edges;

    public ValuesKey(){
        this.edges = new LinkedList<>();
    }

    public ValuesKey(W distance){
        this.distance = distance;
        this.edges = new LinkedList<>();
    }

    public ValuesKey(W distance, LinkedList<Edge<T,W>> edges){
        this.distance = distance;
        this.edges = edges;
    }

    public W getDistance() {
        return distance;
    }
    public void setDistance(W distance) {
        this.distance = distance;
    }
    public LinkedList<Edge<T,W>> getEdges() {
        return edges;
    }
    public void setEdges(LinkedList<Edge<T,W>> edges) {
        this.edges = edges;
    }

    
    
}
