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

    /*Da rivedere:
     *
    public void removeVertex(Vertex vertex){
        this.adjList.remove(vertex);

        Collection<LinkedList<Edge>> coll = this.getEdges();
        Iterator<LinkedList<Edge>> iter = coll.iterator();

        while(iter.hasNext()){
            LinkedList<Edge> tmp = iter.next();
            
        }
    }
    */

    /*
    public boolean removeEdge(Vertex start, Vertex end, int weight){
        boolean result = false;
        if(this.containEdge(start, end, weight)){
            result = this.adjList.get(start).remove(new Edge(start,end,weight));
            if(!this.isOriented()){ //if not oriented
            result = this.adjList.get(end).remove(new Edge(end,start,weight));
            }
        }
        return result;
    }
    */

    public int getVertexNum(){
        return this.adjList.size();
    }

    public int getEdgesNum(Vertex vertex){
        if(this.containVertex(vertex)){
            return this.adjList.get(vertex).size();
        }else{
            System.err.println("The vertex given is not in the graph");
            System.exit(1);
        }
        return -1;
    }

    public int getEdgesNum(){
        Collection<LinkedList<Edge>> coll = this.getEdges();
        Iterator<LinkedList<Edge>> iter = coll.iterator();
        int edgesNum = 0;

        while(iter.hasNext()){
            edgesNum += iter.next().size();
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
        int counter = 0;

        while(iter.hasNext()){
            counter++;
            System.out.println("AdjList["+counter+"]");
            LinkedList<Edge> tmpLL = iter.next();
            for(int i = 0; i < tmpLL.size(); i++){
                tmpLL.get(i).print();
            }
            System.out.println("");
        }
    }

    public int getNum_vertex() {return num_vertex;}

    public void setNum_vertex(int num_vertex) {this.num_vertex = num_vertex;}

    public int getNum_edge() {return num_edge;}

    public void setNum_edge(int num_edge) {this.num_edge = num_edge;}



}