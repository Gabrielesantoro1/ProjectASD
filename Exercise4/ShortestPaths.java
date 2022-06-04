package Exercise4;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
    }

    /*
    private static void initialize(Graph<String> graph){
      for (String node : graph.getNodes) {
        node.setDistance(Float.MAX_VALUE);
      }
    }

    private static boolean relax(Node<String> minimum, Node<String> adiacent, Graph<String> graph, MinHeap<Node<String>> Q){
      boolean relaxed = false;
        //adiacent.getDistance() > minimum.getDistance + graph.getWeight(minimum -> adiacent))
        //adiacent.setDistance(minimum.getDistance + graph.getWeight(minimum -> adiacent));
        //Q.heapDecreaseKey(adiacent.getWeight, graph.getWeight(minimum -> adiacent)));
      
      return relaxed;
    }

    public static ArrayList<Node<String>> Dijkstra(Graph<String> graph, String s, Float weight) throws GraphException{
      //Call for the inizialize method to set for all the node in V:
      //- the d(distance) value to MAX
      initialize(graph);
      
      //now we have to create the min priority heap for the nodes in graph
      ComparatorNode comp = new ComparatorNode();
      MinHeap<Node<String>> Q = new MinHeap<>(new ArrayList<>(graph.getNodes()),comp);
      
      //now we have to create the list that contains the nodes for which we already got the minimun distance path set
      ArrayList<Node<String>> S = new ArrayList<>();


      //now we have to glide all the minheap
      while(!Q.getArray().isEmpty()){
        //extract of the least Node (based on the distance value of the node)
        Node<String> minimum = Q.heapExtractMin();
        S.add(minimum);
        //for all the adiacent nodes of u
        for (Node<String> adiacent : graph.adj(minimum)) {  
          relax(minimum, adiacent, graph, Q);
        }
      }
      return S;
    }
    */
}
