package Exercise4;

import java.io.IOException;
import java.util.ArrayList;

public class Application {
    public static void main(String[] args) {
        System.out.println("Reading distance values");
        
        Graph<String> graph = new Graph<>(true);
        try {
        ShortestPaths.loadData(graph,"Exercise4\\italian_dist_graphTest.csv");
        }catch (IOException | GraphException e){
            System.out.println(e);
        }

        System.out.println("Number of edges: "+graph.getEdgesNum());
        System.out.println("Number of nodes: "+graph.getNodesNum());
        //graph.printEdges();

        System.out.println("");

        try {
            ArrayList<String> min = new ArrayList<>();
            String s = "torrita";
            min = ShortestPaths.Dijkstra(graph,s);
            for(int i = 0; i < min.size(); i++){
                System.out.println("["+i+"]Distance from: |"+s+"| to: |"+min.get(i)+"| -> "+graph.getAdjList().get(min.get(i)).getDistance());
            }
            System.out.println(graph.getNodesNum());
        } catch (GraphException e) {
            //e.printStackTrace();
        }

    }
}
