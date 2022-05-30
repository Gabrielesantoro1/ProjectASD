package Exercise4;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;


public class Graph{
    private int num_vertex;
    private int num_edge;
    private Boolean oriented = false;


    private HashMap<Vertex, LinkedList<Edge>> adjList;

    public Graph(){
        adjList = new HashMap<>();
        num_vertex = 0;
    }

    public Graph(Boolean oriented){
        adjList = new HashMap<>();
        this.oriented = oriented; 
        num_vertex = 0;
    }

    public Graph(HashMap<Vertex, LinkedList<Edge>> adjList){
        this.adjList = adjList;
    }

    public void addVertex(Vertex vertex){
        adjList.put(vertex, new LinkedList<Edge>());
    }

    public void addEdge(Vertex start, Vertex end, Integer weight){
        Edge edgeStart = new Edge(start,end,weight);
        adjList.get(start).add(edgeStart);
        if(!this.isOriented()){
            Edge edgeEnd = new Edge(end,start,weight);
            adjList.get(end).add(edgeEnd);
        }
    }

    public Collection<LinkedList<Edge>> getEdges(){
        return this.adjList.values();
    }

    //Da rivedere: dobbiamo tornare un'array con 
    //solo le destizionazioni, non gli archi interi
    //però ci chiedono di farlo in 0(1) quindi
    //non possiamo fare scansione lineare.
    public LinkedList<Edge> adj(Vertex vertex){
        return this.adjList.get(vertex);
    }

    //Da rivedere: non mantiene l'ordine ma 
    //sembra essere normale per le Set
    public Set<Vertex> getVertex(){
        return this.adjList.keySet();
    }

    public void removeVertex(Vertex vertex){
        this.adjList.remove(vertex);
        int i = 0;

        Collection<LinkedList<Edge>> coll = this.getEdges();
        Iterator<LinkedList<Edge>> iter = coll.iterator();
           
        //devo controllare in ogni lista se c'è un edge in cui il nostro vertex da cancellare è la destinazione;
        //se c'è, cancello l'arco corrispondente
        
    }

    public boolean removeEdge(Edge edge, Vertex start){
        boolean res = this.adjList.get(start).remove(edge);
        
        //dovrebbe essere così, ma non funziona AAAARGHHHHHHHH
        if(!this.oriented){
            Vertex end = edge.getDestination();
            int Weight = edge.getWeight();
            return res && this.adjList.get(end).remove(new Edge(end, start, Weight));
        }

        return res;
    }

    public int getVertexNum(){
        return this.adjList.size();
    }


    public int getEdgesNum(){
        Collection<LinkedList<Edge>> coll = this.getEdges();
        Iterator<LinkedList<Edge>> iter = coll.iterator();
        int edgesNum = 0;

        while(iter.hasNext()){
            if(this.oriented){
                edgesNum += iter.next().size();
            }/*else{
                //cosa va verificato? Che non ci siano due archi con fine = inizio e viceversa e stesso peso
                //va capita un attimo
            }*/
        }
        return edgesNum;
    }

    public boolean containVertex(Vertex vertex){
        return this.adjList.containsKey(vertex);
    }

    public boolean containEdge(Vertex start, Vertex end, Integer weight){
        return this.adjList.get(start).contains(new Edge(start,end,weight));
    }

    private boolean isOriented(){return this.oriented;}

    public void printVertex() {
        Set<Vertex> set = this.getVertex();
        Iterator<Vertex> iter = set.iterator();

        while(iter.hasNext()){
            iter.next().print();
        }
    }

    public void printEdges() {
        Collection<LinkedList<Edge>> coll = this.getEdges();
        Iterator<LinkedList<Edge>> iter = coll.iterator();

        while(iter.hasNext()){
            System.out.println("");
            LinkedList<Edge> tmpLL = iter.next();
            for(int i = 0; i < tmpLL.size(); i++){
                tmpLL.get(i).print();
            }
        }
    }

    public int getNum_vertex() {return num_vertex;}

    public void setNum_vertex(int num_vertex) {this.num_vertex = num_vertex;}

    public int getNum_edge() {return num_edge;}

    public void setNum_edge(int num_edge) {this.num_edge = num_edge;}



}