package Exercise4;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import Exercise4.MinHeapLibrary.ComparatorFloat;
import Exercise4.MinHeapLibrary.MinHeap;

public class ShortestPaths{

    public static void loadData(Graph<String> graph, String filepath) throws IOException, GraphException{
    System.out.println("\nLoading data from file...\n");
    
    Path inputFilePath = Paths.get(filepath);
    try(
      BufferedReader fileInputReader = Files.newBufferedReader(inputFilePath)){
      String line = null;
      while((line = fileInputReader.readLine()) != null){      
        String[] lineElements = line.split(",");
        
        String source = lineElements[0];
        String destination = lineElements[1];
        Float distance = Float.valueOf(lineElements[2]);

        Edge<String> edge = new Edge<>(source, destination, distance);
        graph.addEdge(edge);
      }
      fileInputReader.close();

    }catch(IOException | GraphException e){
        System.out.println(e);
    }
    System.out.println("\nData loaded\n");
    initialize(graph);
    }

    
    private static void initialize(Graph<String> graph){
      for (String node : graph.getNodes()) {
        graph.getAdjList().get(node).setDistance(Float.MAX_VALUE);
      }
    }

    private static boolean relax(String minimum, String adiacent, Graph<String> graph, MinHeap Q) throws GraphException{
      boolean relaxed = false;
      float adiacent_distance = graph.getAdjList().get(adiacent).getDistance(); 
      float minimum_distance =  graph.getAdjList().get(minimum).getDistance();
      float edgeWeight = graph.getWeight(graph.getEdge(minimum, adiacent));
      //System.out.println("Edge ("+minimum+"->"+adiacent+"): "+edgeWeight);

        if(adiacent_distance > minimum_distance + edgeWeight){
          graph.getAdjList().get(adiacent).setDistance(minimum_distance + edgeWeight);
          Q.heapDecreaseKey(adiacent, edgeWeight);
          relaxed = true;
        }
      return relaxed;
    }

    public static ArrayList<String> Dijkstra(Graph<String> graph, String s) throws GraphException{
      //Call the inizialize method to set all the node in V
      initialize(graph);
      graph.getAdjList().get(s).setDistance((float) 0);
      //now we have to create the min priority heap for the nodes in graph
      ComparatorFloat comparator = new ComparatorFloat();

      MinHeap Q = new MinHeap(new ArrayList<String>(graph.getNodes()), comparator, graph);
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
}
