package Exercise4;

import java.io.IOException;

public class Application {
    public static void main(String[] args) {
        System.out.println("Reading distance values");
        
        Graph<String> graph = new Graph<>(true);
        try {
        ShortestPaths.loadData(graph,"Exercise4\\italian_dist_graph.csv");
        }catch (IOException | GraphException e){
            System.out.println(e);
        }

        System.out.println("Number of nodes: "+graph.getEdgesNum());
        System.out.println("Number of edges: "+graph.getNodesNum());
        
        System.out.println("");
        System.out.println("");
        System.out.println("");
        //graph.printNodes();
        //graph.printEdges();

    }
}
