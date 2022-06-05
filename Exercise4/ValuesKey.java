package Exercise4;

import java.util.LinkedList;

public class ValuesKey<T>{
    private float distance;
    private LinkedList<Edge<T>> edges;

    public ValuesKey(){
        this.edges = new LinkedList<>();
    }

    public ValuesKey(float distance){
        this.distance = distance;
        this.edges = new LinkedList<>();
    }

    public ValuesKey(float distance, LinkedList<Edge<T>> edges){
        this.distance = distance;
        this.edges = edges;
    }

    public float getDistance() {
        return distance;
    }
    public void setDistance(float distance) {
        this.distance = distance;
    }
    public LinkedList<Edge<T>> getEdges() {
        return edges;
    }
    public void setEdges(LinkedList<Edge<T>> edges) {
        this.edges = edges;
    }

    
    
}
