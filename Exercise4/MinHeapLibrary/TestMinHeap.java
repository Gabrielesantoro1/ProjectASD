package Exercise4.MinHeapLibrary;

import java.util.ArrayList;
import java.util.Random;

import Exercise4.Edge;
import Exercise4.Graph;
import Exercise4.GraphException;
import Exercise4.Dijkstra;

public class TestMinHeap {
    public static void main(String[] args) {
        Graph<String,Float> graph = new Graph<>(true);

        Edge<String,Float> e1 = new Edge<>("C", "D", (float)1);  
        Edge<String,Float> e2 = new Edge<>("A", "F", (float)2);
        Edge<String,Float> e3 = new Edge<>("E", "B", (float)3);

        try {
            graph.addEdge(e1);
            graph.addEdge(e2);
            graph.addEdge(e3);
        } catch (GraphException e) {
            e.printStackTrace();
        }

        System.out.println("Num of nodes: "+graph.getNodesNum());
        System.out.println("Num of edges: "+graph.getEdgesNum());

        Random r = new Random();
        for (String node : graph.getNodes()) {
            graph.getAdjList().get(node).setDistance(r.nextFloat()+1);
        }

        graph.printNodes();
        graph.printEdges();

        /*
        MinHeap min = new MinHeap(new ArrayList<String>(graph.getNodes()), new ComparatorFloat(), graph);


        min.buildMinHeap();
        String minus = min.heapExtractMin();
        System.out.println(minus);
        */
        
        Dijkstra dijkstra = new Dijkstra();
        try {
            ArrayList<String> arr = dijkstra.dijkstra(graph, "");
            for(int i = 0; i < arr.size(); i++){
                System.out.println("Node "+arr.get(i)+":"+graph.getAdjList().get(arr.get(i)).getDistance());
            }
            
        } catch (GraphException e) {
            e.printStackTrace();
        }

    }
    
}
