package Exercise4.graph;

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
      this.adjList = new HashMap<>();
    }

    public Graph(Boolean oriented){
      this.adjList = new HashMap<>();
      this.oriented = oriented; 
    }

    public Graph(HashMap<T, ValuesKey<T,W>> adjList, Boolean oriented) throws GraphException{
      if(adjList == null)
        throw new GraphException("Graph: the adjList parameter is null");
      this.adjList = adjList;
      this.oriented = oriented;
    }

    /**
     * This function adds a node to the graph
     * 
     * @param node The node to be added to the graph
     * @param distance the distance from the node to the current node
     * @throws GraphException
     */
    public void addNode(T node, W distance) throws GraphException{
        if(node == null)
            throw new GraphException("addNode: node parameter is null");
        if(!this.containNode(node)){
          this.adjList.put(node, new ValuesKey<T,W>(distance));
        }
    }

    /**
     * This function adds a node to the graph
     * 
     * @param node The node to be added to the graph.
     * @throws GraphException
     */
    public void addNode(T node) throws GraphException{
      if(node == null)
        throw new GraphException("addNode: node parameter is null");
      if(!this.containNode(node)){
        this.adjList.put(node, new ValuesKey<T,W>());
      }
    }

    /**
     * This function adds an edge to the graph
     * 
     * @param edge the edge to be added to the graph
     * @throws GraphException
     */
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

    /**
     * This function returns an ArrayList of all the nodes that are adjacent to the node passed in as a
     * parameter
     * 
     * @param node the node to get the adjacent nodes of
     * @return An ArrayList of the nodes that are adjacent to the node passed in as a parameter.
     * @throws GraphException
     */
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
    
    /**
     * This function returns a collection of all the edges in the graph
     * 
     * @return A collection of linked lists of edges.
     */
    public Collection<LinkedList<Edge<T,W>>> getEdges(){
      Collection<LinkedList<Edge<T,W>>> coll = new ArrayList<>();
      Iterator<T> iter = this.adjList.keySet().iterator();
      while(iter.hasNext()){
        coll.add(this.adjList.get(iter.next()).getEdges());
      }
      return coll;
    }
    
    /**
     * This function returns a set of all the nodes in the graph
     * 
     * @return The set of nodes in the graph.
     */
    public Set<T> getNodes(){
      return this.adjList.keySet();
    }

    /**
     * This function removes a node from the graph
     * 
     * @param node the node to be removed
     * @return A boolean value.
     * @throws GraphException
     */
    public boolean removeNode(T node) throws GraphException{
      if(node == null)
        throw new GraphException("removeNode: node parameter is null");
      if(!this.containNode(node))
        throw new GraphException("removeNode: the graph does not contain the node");
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

    /**
     * This function removes an edge from the graph
     * 
     * @param edge the edge to be removed
     * @return The method returns a boolean value.
     * @throws GraphException
     */
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

    /**
     * This method returns the number of Nodes in the graph
     * 
     * @return The number of nodes in the graph.
     */
    public int getNodesNum(){
      return this.adjList.size();
    }

    /**
     * This function returns the number of edges that are connected to the node
     * 
     * @param node the node to get the number of edges from
     * @return The number of edges that are connected to the node.
     * @throws GraphException
     */
    public int getEdgesNum(T node) throws GraphException{
      if(node == null)
        throw new GraphException("getEdgesNum: node parameter is null");
      if(!this.containNode(node))
        throw new GraphException("getEdgesNum: the graph does not contain the node");
      return this.adjList.get(node).getEdges().size();
    }

    /**
     * This function returns the number of Edges in the graph
     * 
     * @return The number of edges in the graph.
     */
    public int getEdgesNum(){
      Collection<LinkedList<Edge<T,W>>> coll = this.getEdges();
      Iterator<LinkedList<Edge<T,W>>> iter = coll.iterator();
      int edgesNum = 0;
      while(iter.hasNext()){
        edgesNum += iter.next().size();
      }
      return edgesNum;
    }

    /**
     * This function returns the weight of the edge passed in as a parameter
     * 
     * @param edge the edge whose weight is to be returned
     * @return The weight of the edge.
     * @throws GraphException
     */
    public W getWeight(Edge<T,W> edge) throws GraphException{
      if(edge == null)
        throw new GraphException("getWeight: edge parameter is null");
      if(!this.containEdge(edge))
        throw new GraphException("getWeight: the graph does not contain the edge");
      T key = edge.getSource();
      Edge<T,W> tmp = this.adjList.get(key).getEdges().get(edge.getIndex());
      return tmp.getLabel();
    }

    /**
     * This function checks if the graph contains the node passed as a parameter
     * 
     * @param node the node to be checked
     * @throws GraphException
     */
    public boolean containNode(T node) throws GraphException{
      if(node == null)
        throw new GraphException("containNode: node parameter is null");
      return this.adjList.containsKey(node);
    }

    /**
     * 
     * 
     * This function checks if the graph contains the edge passed as a parameter
     * 
     * @param edge the edge to be checked
     * @throws GraphException
     */
    public boolean containEdge(Edge<T,W> edge) throws GraphException{
        if(edge == null)
            throw new GraphException("containEdge: edge parameter is null");
        if(!this.containNode(edge.getSource()))
          throw new GraphException("containEdge: source node is null");
        if(!this.containNode(edge.getDestination()))
          throw new GraphException("containEdge: dest node is null");
        return this.adjList.get(edge.getSource()).getEdges().contains(edge);
    }

    /**
     * This function returns true if the graph is oriented, false otherwise
     * 
     * @return The boolean value of the variable oriented.
     */
    public boolean isOriented(){
      return this.oriented;
    }

    /**
     * This function returns the edge between the source and destination vertices
     * 
     * @param source The source vertex
     * @param destination The destination vertex of the edge
     * @return The edge between the source and destination.
     */
    public Edge<T,W> getEdge(T source, T destination){
      for(int i = 0; i < this.adjList.get(source).getEdges().size(); i++){
        if(this.adjList.get(source).getEdges().get(i).getDestination() == destination){
          return this.adjList.get(source).getEdges().get(i);
        }
      }
      return null;
    }

    /**
     * This function prints out all the nodes in the graph
     */
    public void printNodes() {
      Set<T> set = this.getNodes();
      Iterator<T> iter = set.iterator();
        while(iter.hasNext()){
          T node = iter.next();
          System.out.println(node);
          
        }
    }

    /**
     * It prints the edges of the graph
     */
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