package Exercise4;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

public class Graph<T>{
    private Boolean oriented = false;
    private HashMap<T, LinkedList<Edge<T>>> adjList;

    public Graph(){
        adjList = new HashMap<>();
    }

    public Graph(Boolean oriented){
        adjList = new HashMap<>();
        this.oriented = oriented; 
    }

    public Graph(HashMap<T, LinkedList<Edge<T>>> adjList, Boolean oriented){
        if(adjList != null){
        this.adjList = adjList;
        this.oriented = oriented;
        }
            System.err.println("adjList parameter is null");
    }

    /**
     * This function adds a node to the graph
     * 
     * @param node The node to be added to the graph.
     */
    public void addNode(T node) throws GraphException{
        if(node == null)
            throw new GraphException("addNode: node parameter is null");
        if(this.containNode(node))
            throw new GraphException("addNode: node parameter is already in the graph");
        this.adjList.put(node, new LinkedList<Edge<T>>());
    }

    /**
     * This function adds an edge to the graph
     * 
     * @param edge the edge to be added
     */
    public void addEdge(Edge<T> edge) throws GraphException{
        if(edge == null)
            throw new GraphException("addEdge: edge parameter is null");
        if(this.containNode(edge.getSource())==false){
            this.addNode(edge.getSource());
        }
        if(this.containNode(edge.getDestination())==false){
            this.addNode(edge.getDestination());
        }
        if(this.containEdge(edge))
            throw new GraphException("addEdge: edge parameter is already in the graph");
            
            adjList.get(edge.getSource()).add(edge);
            if(!this.isOriented()){
                Edge<T> edgerev = new Edge<>(edge.getDestination(), edge.getSource(), edge.getWeight(),edge.getIndex());
                adjList.get(edge.getDestination()).add(edgerev);
            }
        }

    /**
     * This function returns an ArrayList of all the nodes that are adjacent to the node passed in as a
     * parameter
     * 
     * @param node the node to get the adjacent nodes of
     * @return An ArrayList of Nodes that are adjacent to the node passed in as a parameter.
     */
    public ArrayList<T> adj(T node) throws GraphException{
        if(node == null)
            throw new GraphException("adj: node parameter is null");
        if(!this.containNode(node))
            throw new GraphException("adj: the graph does not contain the node");
        LinkedList<Edge<T>> tmp = this.adjList.get(node);
        ArrayList<T> destination = new ArrayList<>();
        for(int i = 0; i < tmp.size(); i++){
            destination.add(tmp.get(i).getDestination());
        }
        return destination;
    }
    
    /**
     * This function returns a collection of all the edges in the graph
     * 
     * @return A collection of LinkedList of Edge of T.
     */
    public Collection<LinkedList<Edge<T>>> getEdges(){
        return this.adjList.values();
    }
    
    /**
     * This function returns a set of all the nodes in the graph
     * 
     * @return A set of nodes.
     */
    public Set<T> getNodes(){
        return this.adjList.keySet();
    }

    /**
     * This function removes a node from the graph
     * 
     * @param node the node to be removed
     * @return A boolean value.
     */
    public boolean removeNode(T node) throws GraphException{
        if(node == null)
            throw new GraphException("removeNode: node parameter is null");
        if(!this.containNode(node))
            throw new GraphException("removeNode: the graph does not conatin the node");
        boolean result = false;
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
            result = true;
        return result;
    }

    /**
     * This function removes an edge from the graph
     * 
     * @param edge the edge to be removed
     * @return The method returns a boolean value.
     */
    public boolean removeEdge(Edge<T> edge) throws GraphException{
        if(edge == null)
            throw new GraphException("removeEdge: edge parameter is null");
        if(!this.containEdge(edge))
            throw new GraphException("removeEdge: the graph does not contain the edge");
        boolean result = false;
        int indexrev = edge.getIndex();
        result = this.adjList.get(edge.getSource()).remove(edge);
            if(!this.isOriented()){
            this.adjList.get(edge.getDestination()).remove(indexrev);
            }
        return result;
        
    }

    /**
     * This function returns the number of nodes in the graph
     * 
     * @return The number of nodes in the graph.
     */
    public int getNodesNum(){
        return this.adjList.size();
    }

    /**
     * This function returns the number of edges that are connected to the node
     * 
     * @param node the node whose edges we want to count
     * @return The number of edges connected to the node.
     */
    public int getEdgesNum(T node) throws GraphException{
        if(node == null)
            throw new GraphException("getEdgesNum: node parameter is null");
        if(!this.containNode(node))
            throw new GraphException("getEdgesNum: the graph does not contain the node");
        return this.adjList.get(node).size();
    }

    /**
     * It returns the number of edges in the graph
     * 
     * @return The number of edges in the graph.
     */
    public int getEdgesNum(){
        Collection<LinkedList<Edge<T>>> coll = this.getEdges();
        Iterator<LinkedList<Edge<T>>> iter = coll.iterator();
        int edgesNum = 0;

        while(iter.hasNext()){
            edgesNum += iter.next().size();
        }
        return edgesNum;
    }

    /**
     * This function returns the weight of the edge
     * 
     * @param edge the edge whose weight is to be returned
     * @return The weight of the edge.
     */
    public Float getWeight(Edge<T> edge) throws GraphException{
        if(edge == null)
            throw new GraphException("getWeight: edge parameter is null");
        if(!this.containEdge(edge))
            throw new GraphException("getWeight: the graph does not contain the edge");
        T key = edge.getSource();
        return this.adjList.get(key).get(edge.getIndex()).getWeight();        
    }

    /**
     * This function checks if the graph contains a node
     * 
     * @param node the node to be checked
     */
    public boolean containNode(T node) throws GraphException{
        if(node == null)
            throw new GraphException("containNode: node parameter is null");
        return this.adjList.containsKey(node);
    }

    /**
     * This function checks if the edge is in the graph
     * 
     * @param edge the edge to be checked
     */
    public boolean containEdge(Edge<T> edge) throws GraphException{
        if(edge == null)
            throw new GraphException("containEdge: edge parameter is null");
        return this.adjList.get(edge.getSource()).contains(edge);
    }

    /**
     * This function returns true if the graph is oriented, false otherwise
     * 
     * @return The boolean value of the variable oriented.
     */
    private boolean isOriented(){
        return this.oriented;
    }

    /**
     * This function prints all the nodes in the graph
     */
    public void printNodes() {
        Set<T> set = this.getNodes();
        Iterator<T> iter = set.iterator();
        while(iter.hasNext()){
            System.out.println(iter.next());
        }
    }

    /**
     * It prints the edges of the graph
     */
    public void printEdges() {
        Collection<LinkedList<Edge<T>>> coll = this.getEdges();
        Iterator<LinkedList<Edge<T>>> iter = coll.iterator();
        int counter = -1;
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