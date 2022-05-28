package Exercise4;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Vector;


public class Graph{
    private int num_vertex;
    private int num_edge;
    private Boolean oriented = false;

    private ArrayList<LinkedList<Vertex>> adjList;

    public Graph(){
        adjList = new ArrayList<>();
        num_vertex = 0;
    }

    public Graph(Boolean oriented){
        this.oriented = oriented; 
        adjList = new ArrayList<>();
        num_vertex = 0;
    }

    public Graph(ArrayList<LinkedList<Vertex>> adjList){}

    public void addVertex(Vertex vertex){
        if(vertex != null){
        LinkedList<Vertex> l = new LinkedList<>();
        l.add(vertex);
        this.adjList.add(l);
        vertex.setIndex(num_vertex);
        num_vertex++;
        }else{
            System.err.println("The vertex given is null");
            System.exit(1);
        }
    }

    public boolean containVertex(Vertex vertex){
        boolean contain = false;
        if(this.adjList.get(vertex.getIndex()).get(0) == vertex){
            contain = true;
        }
        return contain;
    }

    public void addEdge(Vertex start, Vertex end){
        if(start != null || end != null){
        int startIndex = start.getIndex();
        int endIndex = end.getIndex();
        
        adjList.get(startIndex).add(end);
        num_edge++;
            if(!isOriented()){
            adjList.get(endIndex).add(start);
            }
        }else{
            System.err.println("Parameters are null");
            System.exit(1);
        }
    }

    public boolean containEdge(Vertex start, Vertex end){
        if(this.containVertex(start)){
        LinkedList<Vertex> tmp = adjList.get(start.getIndex());
        return tmp.contains(end);
        }
        return false;
    }

    private boolean isOriented(){return this.oriented;}

    @Override
    public String toString() {
        System.out.println("Printing of adjList");
        for(int i = 0; i < adjList.size(); i++){
            System.out.println("\nAdj list["+i+"]");
            for(int j = 0; j < adjList.get(i).size(); j++){
                adjList.get(i).get(j).print();
            }
        }
        return "End printing";
    }

    public int getNum_vertex() {return num_vertex;}

    public void setNum_vertex(int num_vertex) {this.num_vertex = num_vertex;}

    public int getNum_edge() {return num_edge;}

    public void setNum_edge(int num_edge) {this.num_edge = num_edge;}

    public ArrayList<LinkedList<Vertex>> getAdjList() {return adjList;}

    public void setAdjList(ArrayList<LinkedList<Vertex>> adjList) {this.adjList = adjList;}


}