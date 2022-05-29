package Exercise4;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

public class Application {
    public static void main(String[] args) {
        Graph G = new Graph();

        Vertex<Integer> v0 = new Vertex<>(0);
        Vertex<Integer> v1 = new Vertex<>(1);
        Vertex<Integer> v2 = new Vertex<>(2);
        Vertex<Integer> v3 = new Vertex<>(3);
        Vertex<Integer> v4 = new Vertex<>(4);

        G.addVertex(v0);
        G.addVertex(v1);
        G.addVertex(v2);
        G.addVertex(v3);

        G.addEdge(v0, v1, 0);
        G.addEdge(v0, v2, 0);
        G.addEdge(v1, v3, 0);

        //G.toString();

        System.out.println(G.containVertex(v0));
        System.out.println(G.containVertex(v1));
        System.out.println(G.containVertex(v3));
        System.out.println(G.containVertex(v4));

        System.out.println("");

        LinkedList<Edge> tmp = G.adj(v0);
        for(int i = 0; i< tmp.size(); i++){
            tmp.get(i).print();
        }

        System.out.println("");
        System.out.println("Printing all vertices:");
        G.printVertex();
        
        System.out.println("");
        System.out.println("Printing all edges:");
        G.printEdges();

    
    }
}
