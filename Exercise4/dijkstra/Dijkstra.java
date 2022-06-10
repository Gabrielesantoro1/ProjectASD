package Exercise4.dijkstra;

import java.util.ArrayList;

import Exercise4.MinHeapLib.*;
import Exercise4.graph.Graph;
import Exercise4.graph.GraphException;

public class Dijkstra{

  /**
   * The function takes a graph and a starting node as input, and returns a list of nodes in the graph
   * in the order in which they are visited by Dijkstra's algorithm
   * 
   * @param graph the graph we want to find the shortest path in
   * @param s the source node
   * @return The shortest path from the source to the other nodes reachable.
   * @throws GraphException
   * @throws MinHeapException
   */
  public Graph<String,Float> dijkstra(Graph<String,Float> graph, String s) throws GraphException, MinHeapException{
    Graph<String, Float> shortestPathGraph = new Graph<>(true);

    initialize(graph);
    graph.getAdjList().get(s).setDistance((float)0);

    ComparatorFloat comparator = new ComparatorFloat();
    MinHeap<String,Float> Q = new MinHeap<>(new ArrayList<String>(graph.getNodes()), comparator, graph);
    Q.buildMinHeap();

    while(!Q.getArray().isEmpty()){
      String minimum = Q.heapExtractMin();
      graph.getAdjList().get(minimum).setFlag(false);
      for (String adiacent : graph.adj(minimum)) {
        if(graph.getAdjList().get(adiacent).isFlag())
          if(relax(minimum, adiacent, graph, Q))
            shortestPathGraph.addEdge(graph.getEdge(minimum,adiacent));
      }
    }
  return shortestPathGraph;
}

  /**
   * For each node in the graph, set the distance to infinity and the predecessor to null
   * 
   * @param graph The graph to be searched
   */
  private void initialize(Graph<String,Float> graph){
    for (String node : graph.getNodes()) {
      graph.getAdjList().get(node).setDistance(Float.MAX_VALUE);
      graph.getAdjList().get(node).setPredecessor(null);
    }
  }

  /**
   * It relaxes the edge between the minimum vertex and its adiacent vertex
   * 
   * @param minimum the vertex with the minimum distance from the source
   * @param adiacent the node that is being relaxed
   * @param graph the graph we're working on
   * @param Q the minheap
   * @return The method returns a boolean value.
   * @throws GraphExcetion
   * @throws MinHeapException
   */
  private boolean relax(String minimum, String adiacent, Graph<String,Float> graph, MinHeap<String,Float> Q) throws GraphException, MinHeapException{
    boolean relaxed = false;
    float adiacent_distance = graph.getAdjList().get(adiacent).getDistance(); 
    float minimum_distance =  graph.getAdjList().get(minimum).getDistance();
    float edgeWeight = graph.getWeight(graph.getEdge(minimum, adiacent));

    if(adiacent_distance > minimum_distance + edgeWeight){
      graph.getAdjList().get(adiacent).setDistance(minimum_distance + edgeWeight);
      graph.getAdjList().get(adiacent).setPredecessor(minimum);
      Q.heapDecreaseKey(adiacent, edgeWeight);
      relaxed = true;
    }
    return relaxed;
  }

  /**
   * This function prints the path from the source to the destination, and the total distance from the
   * source to the destination
   * 
   * @param src the source node
   * @param dst destination
   * @param graph the graph object
   */
  public void printPath(String src, String dst, Graph<String,Float> graph) throws GraphException{
    String destination = dst;
    String predecessor = null;
    Float totalDistance = graph.getAdjList().get(destination).getDistance();
    while(!destination.equals(src)){
      predecessor = graph.getAdjList().get(destination).getPredecessor();
      System.out.println("destination: "+destination+" <- predecessor: "+predecessor);
      destination = predecessor;
    }
    System.out.println("");
    System.out.println(totalDistance/1000+" kilometers to get from "+src+" to " +dst);
  }

    
}
