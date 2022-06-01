package Exercise4;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
        
        Edge<String> edge = new Edge<>(source, destination, distance);
        graph.addEdge(edge);
      }
      fileInputReader.close();

    }catch(IOException | GraphException e){
        System.out.println(e);
    }
    System.out.println("\nData loaded\n");
    }

    public static void Dijkstra(){

    }

}
