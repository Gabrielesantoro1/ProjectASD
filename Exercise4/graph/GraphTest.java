package Exercise4.graph;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import Exercise4.graph.*;

import org.junit.Test;
import org.junit.Before;

public class GraphTest{
    private String s1, s2, s3, s4;
    private Float f1, f2, f3, f4;
    private Graph<String, Float> orientedGraph;
    private Graph<String, Float> not_orientedGraph;

    @Before
    public void createGraph() throws Exception{
        f1= 1f;
        f2 = 2f;
        f3 = 3f;
        f4 = 4f;

        s1 = "a";
        s2 = "b";
        s3 = "c";
        s4 = "d";

        orientedGraph = new Graph<>(true);
        not_orientedGraph = new Graph<>(false);
    }

    @Test
    public void testAddNode() throws GraphException{
        orientedGraph.addNode(s1);
        not_orientedGraph.addNode(s1);
        assertTrue(orientedGraph.containNode(s1));
        assertTrue(not_orientedGraph.containNode(s1));
    }

    @Test
    public void testNotNode() throws GraphException{
        assertFalse(orientedGraph.containNode(s1));
        assertFalse(not_orientedGraph.containNode(s1));
    }

    @Test
    public void testAddEdge() throws GraphException{
        Edge<String,Float> edge = new Edge<String,Float>(s1, s2);
        orientedGraph.addEdge(edge);
        not_orientedGraph.addEdge(edge);
        assertTrue(orientedGraph.containEdge(edge));
        assertTrue(not_orientedGraph.containEdge(edge));
    }

    @Test
    public void testNotEdge() throws GraphException{
        orientedGraph.addNode(s1);
        orientedGraph.addNode(s2);
        not_orientedGraph.addNode(s1);
        not_orientedGraph.addNode(s2);
        Edge<String,Float> edge = new Edge<String,Float>(s1, s2);
        assertFalse(orientedGraph.containEdge(edge));
        assertFalse(not_orientedGraph.containEdge(edge));
    }

    @Test
    public void testAdj() throws GraphException{
        ArrayList<String> expected = new ArrayList<>();
        expected.add(s2);
        expected.add(s3);
        expected.add(s4);

        orientedGraph.addNode(s1);
        orientedGraph.addNode(s2);
        orientedGraph.addNode(s3);
        orientedGraph.addNode(s4);
        orientedGraph.addEdge(new Edge<String,Float>(s1, s2));
        orientedGraph.addEdge(new Edge<String,Float>(s1, s3));
        orientedGraph.addEdge(new Edge<String,Float>(s1, s4));
        assertArrayEquals(expected.toArray(), orientedGraph.adj(s1).toArray());
    }

    @Test
    public void testRemoveNode()throws GraphException{
        orientedGraph.addNode(s1);
        orientedGraph.addNode(s2);
        assertTrue(orientedGraph.containNode(s2));
        orientedGraph.removeNode(s2);
        assertFalse(orientedGraph.containNode(s2));
    }

    @Test
    public void testRemoveEdge() throws GraphException{
        orientedGraph.addNode(s1);
        orientedGraph.addNode(s2);

        Edge<String,Float> edge = new Edge<String,Float>(s1, s2);

        orientedGraph.addEdge(edge);
        assertTrue(orientedGraph.containEdge(edge));
        orientedGraph.removeEdge(edge);
        assertFalse(orientedGraph.containEdge(edge));
    }

    @Test
    public void testNodesNum() throws GraphException{
        orientedGraph.addNode(s1);
        orientedGraph.addNode(s2);
        orientedGraph.addNode(s3);
        orientedGraph.addNode(s4);

        assertEquals(4, orientedGraph.getNodesNum());
    }

    @Test
    public void testEdgeNumOriented() throws GraphException{
        orientedGraph.addNode(s1);
        orientedGraph.addNode(s2);
        orientedGraph.addNode(s3);
        orientedGraph.addNode(s4);
        orientedGraph.addEdge(new Edge<String,Float>(s1, s2));
        orientedGraph.addEdge(new Edge<String,Float>(s1, s3));
        orientedGraph.addEdge(new Edge<String,Float>(s1, s4));
        assertEquals(3, orientedGraph.getEdgesNum());
        
    }

    @Test
    public void testEdgeNumNotOriented() throws GraphException{
        not_orientedGraph.addNode(s1);
        not_orientedGraph.addNode(s2);
        not_orientedGraph.addNode(s3);
        not_orientedGraph.addNode(s4);
        not_orientedGraph.addEdge(new Edge<String,Float>(s1, s2));
        not_orientedGraph.addEdge(new Edge<String,Float>(s1, s3));
        not_orientedGraph.addEdge(new Edge<String,Float>(s1, s4));

        assertEquals(6, not_orientedGraph.getEdgesNum());

    }

    @Test
    public void testIsOriented() throws GraphException{
        assertTrue(orientedGraph.isOriented());
        assertFalse(not_orientedGraph.isOriented());
    }
}