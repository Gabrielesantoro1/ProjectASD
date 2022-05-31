package Exercise4;

import java.util.ArrayList;


public class Application {
    public static void main(String[] args) {
        Graph<Integer> G = new Graph<>();

        Node<Integer> v0 = new Node<>(0);
        Node<Integer> v1 = new Node<>(1);
        Node<Integer> v2 = new Node<>(2);
        Node<Integer> v3 = new Node<>(3);
        Node<Integer> v4 = new Node<>(4);


        Edge<Integer> e1 = new Edge<>(v0,v1,0);
        Edge<Integer> e2 = new Edge<>(v0,v2,0);
        Edge<Integer> e3 = new Edge<>(v1,v3,0);
        Edge<Integer> e4 = new Edge<>(v2,v3,0); 

        G.addNode(v0);
        G.addNode(v1);
        G.addNode(v2);
        G.addNode(v3);

        G.addEdge(e1);
        G.addEdge(e2);
        G.addEdge(e3);
        G.addEdge(e4);

        System.out.println(G.containNode(v0));
        System.out.println(G.containNode(v1));
        System.out.println(G.containNode(v3));
        System.out.println(G.containNode(v4));

        System.out.println("");

        System.out.println("Printing the adj list of v0");
        ArrayList<Node<Integer>> tmp = G.adj(v0);
        for(int i = 0; i< tmp.size(); i++){
            tmp.get(i).print();
        }

        System.out.println("Number of edges : "+G.getEdgesNum());

        System.out.println("");

        /*
        System.out.println("Before deleting v0");
        G.printNodes();
        G.printEdges();

        System.out.println("");
        G.removeNode(v0);

        System.out.println("");
        
        System.out.println("After deleting v0");
        G.printNodes();
        G.printEdges();
        */

        System.out.println("Before deleting:");
        G.printEdges();

        G.removeEdge(e1);
        G.removeEdge(e2);
        G.removeEdge(e3);

        System.out.println("After deleting:");
        G.printEdges();


    
    }
}
