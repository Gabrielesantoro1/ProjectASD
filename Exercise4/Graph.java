package Exercise4;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

public class Graph<T>{
    private Boolean oriented = false;
    private HashMap<Node<T>, LinkedList<Edge<T>>> adjList;

    public Graph(){
        adjList = new HashMap<>();
    }

    public Graph(Boolean oriented){
        adjList = new HashMap<>();
        this.oriented = oriented; 
    }

    public Graph(HashMap<Node<T>, LinkedList<Edge<T>>> adjList){
        this.adjList = adjList;
    }

    public void addNode(Node<T> vertex){
        adjList.put(vertex, new LinkedList<Edge<T>>());
    }

    public void addEdge(Node<T> start, Node<T> end, Integer weight){
        Edge<T> edgeStart = new Edge<>(start,end,weight);
        adjList.get(start).add(edgeStart);
        if(!this.isOriented()){
            Edge<T> edgeEnd = new Edge<T>(end,start,weight);
            adjList.get(end).add(edgeEnd);
        }
    }

    public Collection<LinkedList<Edge<T>>> getEdges(){
        return this.adjList.values();
    }

    //Da rivedere: dobbiamo tornare un'array con 
    //solo le destizionazioni, non gli archi interi
    //però ci chiedono di farlo in 0(1) quindi
    //non possiamo fare scansione lineare.
    public LinkedList<Edge<T>> adj(Node<T> node){
        return this.adjList.get(node);
    }

    public Set<Node<T>> getNode(){
        return this.adjList.keySet();
    }

    public void removeNode(Node<T> node){
        this.adjList.remove(node);
        
    }

    /*Non funziona perchè la remove/contain/ecc sono operazioni che si basano 
    * sulla equal di due oggetti; ma poiché noi gli passiamo un oggetto Edge nuovo
    * non è verificata l'uguaglianza. Dobbiamo capire un attimo come fare.
    */
    public boolean removeEdge(Node<T> start, Node<T> end, int weight){
        boolean result = false;
        if(this.containEdge(start, end, weight)){
            result = this.adjList.get(start).remove(new Edge<>(start,end,weight));
            if(!this.isOriented()){ //if not oriented
            result = this.adjList.get(end).remove(new Edge<>(end,start,weight));
            }
        }
        return result;
    }

    public int getNodeNum(){
        return this.adjList.size();
    }

    public int getEdgesNum(Node<T> vertex){
        if(this.containNode(vertex)){
            return this.adjList.get(vertex).size();
        }else{
            System.err.println("The vertex given is not in the graph");
            System.exit(1);
        }
        return -1;
    }

    public int getEdgesNum(){
        Collection<LinkedList<Edge<T>>> coll = this.getEdges();
        Iterator<LinkedList<Edge<T>>> iter = coll.iterator();
        int edgesNum = 0;

        while(iter.hasNext()){
            edgesNum += iter.next().size();
        }
        return edgesNum;
    }

    public boolean containNode(Node<T> vertex){
        return this.adjList.containsKey(vertex);
    }

    public boolean containEdge(Node<T> start, Node<T> end, Integer weight){
        return this.adjList.get(start).contains(new Edge<T>(start,end,weight));
    }

    private boolean isOriented(){return this.oriented;}

    public void printNodes() {
        Set<Node<T>> set = this.getNode();
        Iterator<Node<T>> iter = set.iterator();

        while(iter.hasNext()){
            iter.next().print();
        }
    }

    public void printEdges() {
        Collection<LinkedList<Edge<T>>> coll = this.getEdges();
        Iterator<LinkedList<Edge<T>>> iter = coll.iterator();
        int counter = 0;

        while(iter.hasNext()){
            counter++;
            System.out.println("AdjList["+counter+"]");
            LinkedList<Edge<T>> tmpLL = iter.next();
            for(int i = 0; i < tmpLL.size(); i++){
                tmpLL.get(i).print();
            }
            System.out.println("");
        }
    }




}