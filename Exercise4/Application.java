package Exercise4;

public class Application {
    public static void main(String[] args) {
        Vertex<Integer> v1 = new Vertex<>(0);
        Vertex<Integer> v2 = new Vertex<>(1);
        Vertex<Integer> v3 = new Vertex<>(2);
        Vertex<Integer> v4 = new Vertex<>(3);
        Vertex<Integer> v5 = new Vertex<>(4);

        Graph G = new Graph(false);

        G.addVertex(v1);
        G.addVertex(v2);
        G.addVertex(v3);
        G.addVertex(v4);
        G.addVertex(v5);

        //G.toString();

        G.addEdge(v1, v2);
        G.addEdge(v2, v3);
        G.addEdge(v3, v4);
        G.addEdge(v2, v5);
        G.addEdge(v1, v5);

        System.out.println("");
        //G.toString();


        Vertex<Integer> w1 = new Vertex<>(0);
        Vertex<Integer> w2 = new Vertex<>(1);
        Vertex<Integer> w3 = new Vertex<>(2);
        Vertex<Integer> w4 = new Vertex<>(3);
        Vertex<Integer> w5 = new Vertex<>(4);
        Vertex<Integer> w6 = new Vertex<>(5);

        Graph G2 = new Graph(true);

        G2.addVertex(w1);
        G2.addVertex(w2);
        G2.addVertex(w3);
        G2.addVertex(w4);
        G2.addVertex(w5);

        //G.toString();

        G2.addEdge(w1, w2);
        G2.addEdge(w2, w3);
        G2.addEdge(w3, w4);
        G2.addEdge(w2, w5);
        G2.addEdge(w1, w5);

        System.out.println("");
        G2.toString();

        System.out.println(G2.containVertex(w1));
        System.out.println(G2.containVertex(w2));
        System.out.println(G2.containVertex(w3));
        System.out.println(G2.containVertex(w4));
        System.out.println(G2.containVertex(w5));
        System.out.println(G2.containVertex(w6));

        System.out.println("");

        System.out.println(G2.containEdge(w1,w2));
        System.out.println(G2.containEdge(w2,w3));
        System.out.println(G2.containEdge(w5,w6));
        System.out.println(G2.containEdge(w5,w2));
        
    }
}
