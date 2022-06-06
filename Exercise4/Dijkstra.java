package Exercise4;

import java.util.ArrayList;

import Exercise4.MinHeapLibrary.ComparatorFloat;
import Exercise4.MinHeapLibrary.MinHeap;

public class Dijkstra{
  public static ArrayList<String> dijkstra(Graph<String,Float> graph, String s) throws GraphException{
      //Call the inizialize method to set all the node in V
      initialize(graph);
      graph.getAdjList().get(s).setDistance((float) 0);
      //now we have to create the min priority heap for the nodes in graph
      ComparatorFloat comparator = new ComparatorFloat();

      MinHeap<String,Float> Q = new MinHeap<>(new ArrayList<String>(graph.getNodes()), comparator, graph);
      Q.buildMinHeap();
      System.out.println(Q.getHeapSize());
      //now we have to create the list that contains the nodes for which we already got the minimun distance path set
      ArrayList<String> S = new ArrayList<>();

      //now we have to glide all the minheap
      while(!Q.getArray().isEmpty()){
        //extract of the least Node (based on the distance value of the node)
        String minimum = Q.heapExtractMin();
        S.add(minimum);
        //for all the adiacent nodes of u
        for (String adiacent : graph.adj(minimum)) { 
          relax(minimum, adiacent, graph, Q);
        }
      }
    return S;
  }

    private static void initialize(Graph<String,Float> graph){
      for (String node : graph.getNodes()) {
        graph.getAdjList().get(node).setDistance(Float.MAX_VALUE);
      }
    }

    private static boolean relax(String minimum, String adiacent, Graph<String,Float> graph, MinHeap<String, Float> Q) throws GraphException{
      boolean relaxed = false;
      float adiacent_distance = graph.getAdjList().get(adiacent).getDistance(); 
      float minimum_distance =  graph.getAdjList().get(minimum).getDistance();
      float edgeWeight = graph.getWeight(graph.getEdge(minimum, adiacent));

        if(adiacent_distance > minimum_distance + edgeWeight){
          graph.getAdjList().get(adiacent).setDistance(minimum_distance + edgeWeight);
          Q.heapDecreaseKey(adiacent, edgeWeight);
          relaxed = true;
        }
      return relaxed;
    }

    
}
