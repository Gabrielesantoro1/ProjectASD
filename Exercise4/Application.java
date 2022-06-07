package Exercise4;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import Exercise4.dijkstra.Dijkstra;
import Exercise4.graph.Edge;
import Exercise4.graph.Graph;
import Exercise4.graph.GraphException;

public class Application {

  public static void loadData(Graph<String,Float> graph, String filepath) throws IOException, GraphException{
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
        distance = Math.abs(distance);  
    
        Edge<String,Float> edge = new Edge<>(source, destination, distance);
        graph.addEdge(edge);
      }
      fileInputReader.close();
    }catch(IOException | GraphException e){
      System.out.println(e);
    }
    System.out.println("\nData loaded\n");
  }

  public static void main(String[] args) {
    System.out.println("Reading distance values");

    Graph<String,Float> graph = new Graph<>(true);
    try {
      loadData(graph,"Exercise4\\italian_dist_graph.csv");
      }catch (IOException | GraphException e){
        System.out.println(e);
      }
      System.out.println("Number of edges: "+graph.getEdgesNum());
      System.out.println("Number of nodes: "+graph.getNodesNum());
      System.out.println("");
        
      ArrayList<String> min = new ArrayList<>();
      String source = "torino";
      Dijkstra dijkstra = new Dijkstra();
      try {
        min = dijkstra.dijkstra(graph, source);    
      } catch (GraphException e) {
        e.printStackTrace();
      }
      for(int i = 0; i < min.size(); i++){
        if(graph.getAdjList().get(min.get(i)).getDistance() != Float.MAX_VALUE){  
        System.out.println("["+i+"]Distance from: |"+source+"| to: |"+min.get(i)+"| -> "+graph.getAdjList().get(min.get(i)).getDistance());
        }
      }
  }
}
