package Exercise4.dijkstra;

import java.util.ArrayList;

import Exercise4.MinHeapLib.*;
import Exercise4.graph.Graph;
import Exercise4.graph.GraphException;

public class Dijkstra{

  public ArrayList<String> dijkstra(Graph<String,Float> graph, String s) throws GraphException, MinHeapException{
    initialize(graph);
    graph.getAdjList().get(s).setDistance((float)0);

    ComparatorFloat comparator = new ComparatorFloat();
    MinHeap<String,Float> Q = new MinHeap<>(new ArrayList<String>(graph.getNodes()), comparator, graph);
    Q.buildMinHeap();

    ArrayList<String> S = new ArrayList<>();
    
    while(!Q.getArray().isEmpty()){
      String minimum = Q.heapExtractMin();
      S.add(minimum);
      for (String adiacent : graph.adj(minimum)) {
        if(Q.getArray().contains(adiacent)){ 
          relax(minimum, adiacent, graph, Q);
        }
      }
    }
  return S;
}

  private void initialize(Graph<String,Float> graph){
    for (String node : graph.getNodes()) {
      graph.getAdjList().get(node).setDistance(Float.MAX_VALUE);
      graph.getAdjList().get(node).setPredecessor(null);
    }
  }

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

    
}
