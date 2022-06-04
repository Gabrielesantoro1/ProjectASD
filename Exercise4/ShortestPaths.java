package Exercise4;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;

import Exercise3.*;

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

        Node<String> src = new Node<>(source);
        Node<String> dst = new Node<>(destination);

        Edge<String> edge = new Edge<>(src, dst, distance);
        graph.addEdge(edge);
      }
      fileInputReader.close();

    }catch(IOException | GraphException e){
        System.out.println(e);
    }
    System.out.println("\nData loaded\n");
    }

    private static void initialize(Graph<String> graph){
      for(int i = 0; i < graph.getNodesNum(); i++){

      }
    }

    /*
    public static void Dijkstra(Graph<String> graph, String s, Float weight) throws GraphException{
      //Call for the inizialize method to set for all the node in V:
      //- the d(distance) value to MAX
      //- the π(father) value to nil -> not necessary for us
      
      //now we have to create the min priority heap for the nodes in graph
      MinHeap<String> Q = new MinHeap<>(new ArrayList<String>(graph.getNodes()),new ComparatorString());
      
      //S is a set the contains the nodes for which we already got the minimun distance path
      ArrayList<String> S = new ArrayList<>();

      //untile the minheap is not empty
      while(!Q.getArray().isEmpty()){
        //estraiamo il minimo
        String u = Q.heapExtractMin();
        S.add(u);

        //for all the adiacent nodes of u
        for (String string : graph.adj(u)) {
          
        }

      }
    }
    */

}
