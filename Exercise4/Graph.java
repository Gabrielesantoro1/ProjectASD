package Exercise4;

import java.util.ArrayList;
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

    public void addNode(Node<T> node){
        adjList.put(node, new LinkedList<Edge<T>>());
    }

    public void addEdge(Edge<T> edge){
        adjList.get(edge.getSource()).add(edge);
        if(!this.isOriented()){
            Edge<T> edgerev = new Edge<>(edge.getDestination(), edge.getSource(), edge.getWeight(),-edge.getIndex());
            adjList.get(edge.getDestination()).add(edgerev);
        }
    }

    public Collection<LinkedList<Edge<T>>> getEdges(){
        return this.adjList.values();
    }

    public ArrayList<Node<T>> adj(Node<T> node){
        LinkedList<Edge<T>> tmp = this.adjList.get(node);
        ArrayList<Node<T>> destination = new ArrayList<>();
        for(int i = 0; i < tmp.size(); i++){
            destination.add(tmp.get(i).getDestination());
        }
        return destination;
    }

    public Set<Node<T>> getNodes(){
        return this.adjList.keySet();
    }

    public void removeNode(Node<T> node){
        this.adjList.remove(node);
        
        Collection<LinkedList<Edge<T>>> coll = this.getEdges();
        Iterator<LinkedList<Edge<T>>> iter = coll.iterator();

        while(iter.hasNext()){
            LinkedList<Edge<T>> tmpLL = iter.next();
            for(int i = 0; i < tmpLL.size(); i++){
                if(tmpLL.get(i).getDestination() == node){
                    tmpLL.remove(i);
                }
            }
        }
        
    }

    public boolean removeEdge(Edge<T> edge){
        boolean result = false;
        if(this.containEdge(edge)){
            int indexrev = -edge.getIndex();
            result = this.adjList.get(edge.getSource()).remove(edge);
            if(!this.isOriented()){ //if not oriented
            this.adjList.get(edge.getDestination()).remove(indexrev);
            }
        }
        return result;
    }

    public int getNodeNum(){
        return this.adjList.size();
    }

    public int getEdgesNum(Node<T> node){
        if(this.containNode(node)){
            return this.adjList.get(node).size();
        }else{
            System.err.println("The node given is not in the graph");
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

    public boolean containNode(Node<T> node){
        return this.adjList.containsKey(node);
    }

    public boolean containEdge(Edge<T> edge){
        return this.adjList.get(edge.getSource()).contains(edge);
    }

    private boolean isOriented(){return this.oriented;}

    public void printNodes() {
        Set<Node<T>> set = this.getNodes();
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
            LinkedList<Edge<T>> tmpLL = iter.next();
            System.out.println("Adiacent list["+counter+"]");
            for(int i = 0; i < tmpLL.size(); i++){
                tmpLL.get(i).print();
            }
            System.out.println("");
        }
    }




}