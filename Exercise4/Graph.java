package Exercise4;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class Graph<T,W>{
    private Boolean oriented = false;
    private Map<T,ValuesKey<T,W>> adjList;

    public Graph(){
        adjList = new HashMap<>();
    }

    public Graph(Boolean oriented){
        adjList = new HashMap<>();
        this.oriented = oriented; 
    }

    public Graph(Map<T, ValuesKey<T,W>> adjList, Boolean oriented){
        if(adjList != null){
        this.adjList = adjList;
        this.oriented = oriented;
        }
            System.err.println("adjList parameter is null");
    }

    public void addNode(T node, W distance) throws GraphException{
        if(node == null)
            throw new GraphException("addNode: node parameter is null");
        if(this.containNode(node))
            throw new GraphException("");
        this.adjList.put(node, new ValuesKey<T,W>(distance));
    }

    public void addNode(T node) throws GraphException{
        if(node == null)
            throw new GraphException("addNode: node parameter is null");
        if(!this.containNode(node)){
            this.adjList.put(node, new ValuesKey<T,W>());
        }
    }

    public void addEdge(Edge<T,W> edge) throws GraphException{
        if(edge == null)
            throw new GraphException("addEdge: edge parameter is null");
        this.addNode(edge.getSource());
        this.addNode(edge.getDestination());
        if(this.containEdge(edge))
            throw new GraphException("addEdge: edge parameter is already in the graph");
        edge.setIndex(this.adjList.get(edge.getSource()).getEdges().size());
        adjList.get(edge.getSource()).getEdges().add(edge);
        if(!this.isOriented()){
            Edge<T,W> edgerev = new Edge<>(edge.getDestination(), edge.getSource(), edge.getLabel());
            edge.setIndex(this.adjList.get(edge.getDestination()).getEdges().size());
            adjList.get(edge.getDestination()).getEdges().add(edgerev);
        }
    }

    public ArrayList<T> adj(T node) throws GraphException{
        if(node == null)
            throw new GraphException("adj: node parameter is null");
        if(!this.containNode(node))
            throw new GraphException("adj: the graph does not contain the node");
        LinkedList<Edge<T,W>> tmp = this.adjList.get(node).getEdges();
        ArrayList<T> destination = new ArrayList<>();
        for(int i = 0; i < tmp.size(); i++){
            destination.add(tmp.get(i).getDestination());
        }
        return destination;
    }
    
    public Collection<LinkedList<Edge<T,W>>> getEdges(){
        Collection<LinkedList<Edge<T,W>>> coll = new ArrayList<>();
        Iterator<T> iter = this.adjList.keySet().iterator();

        while(iter.hasNext()){
            coll.add(this.adjList.get(iter.next()).getEdges());
        }
        return coll;

    }
    
    public Set<T> getNodes(){
        return this.adjList.keySet();
    }

    public boolean removeNode(T node) throws GraphException{
        if(node == null)
            throw new GraphException("removeNode: node parameter is null");
        if(!this.containNode(node))
            throw new GraphException("removeNode: the graph does not conatin the node");
        boolean result = false;
        this.adjList.remove(node);
        
        Collection<LinkedList<Edge<T,W>>> coll = this.getEdges();
        Iterator<LinkedList<Edge<T,W>>> iter = coll.iterator();
            while(iter.hasNext()){
                LinkedList<Edge<T,W>> tmpLL = iter.next();
                for(int i = 0; i < tmpLL.size(); i++){
                    if(tmpLL.get(i).getDestination() == node){
                        tmpLL.remove(i);
                    }
                }
            }
            result = true;
        return result;
    }

    public boolean removeEdge(Edge<T,W> edge) throws GraphException{
        if(edge == null)
            throw new GraphException("removeEdge: edge parameter is null");
        if(!this.containEdge(edge))
            throw new GraphException("removeEdge: the graph does not contain the edge");
        boolean result = false;
        int indexrev = edge.getIndex();
        result = this.adjList.get(edge.getSource()).getEdges().remove(edge);
            if(!this.isOriented()){
            this.adjList.get(edge.getDestination()).getEdges().remove(indexrev);
            }
        return result;
        
    }
    public int getNodesNum(){
        return this.adjList.size();
    }

    public int getEdgesNum(T node) throws GraphException{
        if(node == null)
            throw new GraphException("getEdgesNum: node parameter is null");
        if(!this.containNode(node))
            throw new GraphException("getEdgesNum: the graph does not contain the node");
        return this.adjList.get(node).getEdges().size();
    }

    public int getEdgesNum(){
        Collection<LinkedList<Edge<T,W>>> coll = this.getEdges();
        Iterator<LinkedList<Edge<T,W>>> iter = coll.iterator();
        int edgesNum = 0;

        while(iter.hasNext()){
            edgesNum += iter.next().size();
        }
        return edgesNum;
    }

    public W getWeight(Edge<T,W> edge) throws GraphException{
        if(edge == null)
            throw new GraphException("getWeight: edge parameter is null");
        if(!this.containEdge(edge))
            throw new GraphException("getWeight: the graph does not contain the edge");
        T key = edge.getSource();
        Edge<T,W> tmp = this.adjList.get(key).getEdges().get(edge.getIndex());
        return tmp.getLabel();
    }

    public boolean containNode(T node) throws GraphException{
        if(node == null)
            throw new GraphException("containNode: node parameter is null");
        return this.adjList.containsKey(node);
    }

    public boolean containEdge(Edge<T,W> edge) throws GraphException{
        if(edge == null)
            throw new GraphException("containEdge: edge parameter is null");
        return this.adjList.get(edge.getSource()).getEdges().contains(edge);
    }

    private boolean isOriented(){
        return this.oriented;
    }

    public Edge<T,W> getEdge(T source, T destination){
        for(int i = 0; i < this.adjList.get(source).getEdges().size(); i++){
            if(this.adjList.get(source).getEdges().get(i).getDestination() == destination){
                return this.adjList.get(source).getEdges().get(i);
            }
        }
        return null;
    }

    public void printNodes() {
        Set<T> set = this.getNodes();
        Iterator<T> iter = set.iterator();
        while(iter.hasNext()){
            T node = iter.next();
            System.out.println(node);
            System.out.println("distance: "+this.adjList.get(node).getDistance());
        }
    }

    public void printEdges() {
        Collection<LinkedList<Edge<T,W>>> coll = this.getEdges();
        Iterator<LinkedList<Edge<T,W>>> iter = coll.iterator();
        int counter = -1;
        while(iter.hasNext()){
            counter++;
            LinkedList<Edge<T,W>> tmpLL = iter.next();
            System.out.println("Adiacent list["+counter+"]");
            for(int i = 0; i < tmpLL.size(); i++){
                tmpLL.get(i).print();
            }
            System.out.println("");
        }
    }

    public Map<T, ValuesKey<T,W>> getAdjList() {
        return adjList;
    }

    public void setAdjList(Map<T, ValuesKey<T,W>> adjList) {
        this.adjList = adjList;
    }

    
    

}