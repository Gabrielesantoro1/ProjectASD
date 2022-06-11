import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import MinHeapLib.MinHeapException;
import dijkstra.Dijkstra;
import graph.Edge;
import graph.Graph;
import graph.GraphException;

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
    Scanner scanner = new Scanner(System.in);
    System.out.println("Hello, insert the path name of the dataset");
    String pathname = scanner.nextLine();

    System.out.println("Hello, type the string name of the source:");
    String source = scanner.nextLine();
    System.out.println("Now the data are going to be loaded in the graph");

    Graph<String,Float> graph = new Graph<>(true);
    try {
      loadData(graph,pathname);
      }catch (IOException | GraphException e){
        System.out.println(e);
      }
    System.out.println("Do you want to print the number of nodes in the graph? y/n");
    String printNodes = scanner.nextLine();
    if(printNodes.equals("y")){
      System.out.println("Number of nodes: "+graph.getNodesNum());
    }
    System.out.println("Do you want to print the number of edges in the graph y/n");
    String printEdges = scanner.nextLine();
    if(printEdges.equals("y")){
    System.out.println("Number of edges: "+graph.getEdgesNum());
    }
    System.out.println("");
      
    Graph<String,Float> min = new Graph<>(true);
    Dijkstra dijkstra = new Dijkstra();
    try {
      min = dijkstra.dijkstra(graph, source);    
    } catch (GraphException | MinHeapException e) {
      e.printStackTrace();
    }
    System.out.println("Dijkstra algorithm has done calculating the shortest path from the source "+source);
    System.out.println("");

    System.out.println("Do you want to print the number of nodes in the new graph returned by the algorithm? y/n");
    String printNodesSPG = scanner.nextLine();
    if(printNodesSPG.equals("y")){
      min.printNodes();
    }

    System.out.println("Do you want to print the distances of the nodes? y/n");
    String printDistances = scanner.nextLine();
    if(printDistances.equals("y")){
      min.printEdges();
    }
    
    System.out.println("If you want to get the complete route from "+source+" to a destination type the destination:");
    String destination = scanner.nextLine();
    try{
      dijkstra.printPath(source, destination, graph);
    }catch(GraphException e){
      e.printStackTrace();
    }
    scanner.close();
  }
}
