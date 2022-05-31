package Exercise4;

import java.util.LinkedList;


public class Application {
    public static void main(String[] args) {
        Graph<Integer> G = new Graph<>();

        Node<Integer> v0 = new Node<>(0);
        Node<Integer> v1 = new Node<>(1);
        Node<Integer> v2 = new Node<>(2);
        Node<Integer> v3 = new Node<>(3);
        Node<Integer> v4 = new Node<>(4);

        G.addNode(v0);
        G.addNode(v1);
        G.addNode(v2);
        G.addNode(v3);

        G.addEdge(v0, v1, 0);
        G.addEdge(v0, v2, 0);
        G.addEdge(v1, v3, 0);
        G.addEdge(v2, v3, 0);

        //G.toString();

        System.out.println(G.containNode(v0));
        System.out.println(G.containNode(v1));
        System.out.println(G.containNode(v3));
        System.out.println(G.containNode(v4));

        System.out.println("");

        LinkedList<Edge<Integer>> tmp = G.adj(v0);
        for(int i = 0; i< tmp.size(); i++){
            tmp.get(i).print();
        }

        System.out.println("Number of edges : "+G.getEdgesNum());

        G.removeNode(v0);

        G.printNodes();
        G.printEdges();
    
    }
}
