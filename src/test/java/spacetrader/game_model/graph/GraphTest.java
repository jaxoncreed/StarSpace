package spacetrader.game_model.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import spacetrader.game_model.Position;
import spacetrader.game_model.StarSystem;
import spacetrader.game_model.StarType;

/**
 *
 * @author Michael Lane
 */
public class GraphTest {
    
    private int numNodesX;
    private int numNodesY;
    
    private StarSystem[] systems;
    private Graph trivialUndirected;
    private Graph trivialDirected;
    private Graph disconnected;
    private Graph neighbors;
    private Graph twoPaths;
    private Graph trickyTwoPaths;
    private Graph undirectedTwoPaths;
    private Graph bfsVisited;
    private Graph bfsFunVisited;
    private Graph aStarSimple;
    private Graph aStarSimpleEuclidean;
    
    public GraphTest() { 
    }
    
//    @BeforeClass
//    public static void setUpClass() {
//    }
//    
//    @AfterClass
//    public static void tearDownClass() {
//    }
    
    @Before
    public void setUp() {
        
        numNodesX = 5;
        numNodesY = 5;
        systems = new StarSystem[numNodesX * numNodesY];
        for (int ix = 0; ix < numNodesX; ix++) {
            for (int iy = 0; iy < numNodesY; iy++) {
                systems[ix + iy*numNodesY] = 
                    new StarSystem("(" + ix + ", " + iy + ")", 
                    new Position(ix, iy), 
                    StarType.BLACK_HOLE);                
            }
        }
        
        DirectedEdge dir = new Directed(systems[0], systems[1], 0.0);
        List<DirectedEdge> dirs = new ArrayList();
        dirs.add(dir);
        trivialDirected = new Graph(dirs, null);
        
        UndirectedEdge undir = new Undirected(systems[0], systems[1], 0.0);
        List<UndirectedEdge> undirs = new ArrayList();
        undirs.add(undir);
        trivialUndirected = new Graph(null, undirs);
        
        List<Node> disc = new ArrayList();
        disc.add(systems[0]);
        disc.add(systems[1]);
        disconnected = new Graph(null, null, disc);
        
        undir = new Undirected(systems[0], systems[5], 0.0);
        undirs = new ArrayList();
        dirs = new ArrayList();
        undirs.add(undir);
        dir = new Directed(systems[0], systems[1], 0.0);
        dirs.add(dir);
        dir = new Directed(systems[0], systems[3], 0.0);
        dirs.add(dir);
        dir = new Directed(systems[0], systems[2], 0.0);
        dirs.add(dir);
        dir = new Directed(systems[3], systems[4], 0.0);
        dirs.add(dir);
        neighbors = new Graph(dirs, undirs);
        
        dirs = new ArrayList();
        dir = new Directed(systems[0], systems[1], 0.0);
        dirs.add(dir);
        dir = new Directed(systems[0], systems[2], 0.0);
        dirs.add(dir);
        dir = new Directed(systems[2], systems[3], 0.0);
        dirs.add(dir);
        dir = new Directed(systems[3], systems[1], 0.0);
        dirs.add(dir);
        twoPaths = new Graph(dirs, null);
        
        dirs = new ArrayList();
        dir = new Directed(systems[1], systems[0], 0.0);
        dirs.add(dir);
        dir = new Directed(systems[0], systems[2], 0.0);
        dirs.add(dir);
        dir = new Directed(systems[2], systems[3], 0.0);
        dirs.add(dir);
        dir = new Directed(systems[3], systems[1], 0.0);
        dirs.add(dir);
        trickyTwoPaths = new Graph(dirs, null);
        
        undirs  = new ArrayList();
        undir = new Undirected(systems[1], systems[0], 0.0);
        undirs.add(undir);
        undir = new Undirected(systems[0], systems[2], 0.0);
        undirs.add(undir);
        undir = new Undirected(systems[2], systems[3], 0.0);
        undirs.add(undir);
        undir = new Undirected(systems[3], systems[1], 0.0);
        undirs.add(undir);
        undirectedTwoPaths = new Graph(null, undirs);
        
        dirs = new ArrayList();
        dir = new Directed(systems[0], systems[4], 0.0);
        dirs.add(dir);
        dir = new Directed(systems[4], systems[3], 0.0);
        dirs.add(dir);
        dir = new Directed(systems[3], systems[2], 0.0);
        dirs.add(dir);
        dir = new Directed(systems[0], systems[1], 0.0);
        dirs.add(dir);
        dir = new Directed(systems[1], systems[2], 0.0);
        dirs.add(dir);
        dir = new Directed(systems[2], systems[5], 0.0);
        dirs.add(dir);
        dir = new Directed(systems[5], systems[6], 0.0);
        dirs.add(dir);
        dir = new Directed(systems[6], systems[7], 0.0);
        dirs.add(dir);
        bfsVisited = new Graph(dirs, null);
        
        dirs = new ArrayList();
        dir = new Directed(systems[0], systems[10], 0.0);
        dirs.add(dir);
        dir = new Directed(systems[0], systems[7], 0.0);
        dirs.add(dir);
        dir = new Directed(systems[0], systems[3], 0.0);
        dirs.add(dir);
        dir = new Directed(systems[7], systems[10], 0.0);
        dirs.add(dir);
        dir = new Directed(systems[3], systems[10], 0.0);
        dirs.add(dir);
        dir = new Directed(systems[10], systems[2], 0.0);
        dirs.add(dir);
        dir = new Directed(systems[10], systems[4], 0.0);
        dirs.add(dir);
        dir = new Directed(systems[10], systems[1], 0.0);
        dirs.add(dir);
        dir = new Directed(systems[2], systems[1], 0.0);
        dirs.add(dir);
        dir = new Directed(systems[4], systems[1], 0.0);
        dirs.add(dir);
        dir = new Directed(systems[1], systems[8], 0.0);
        dirs.add(dir);
        dir = new Directed(systems[1], systems[9], 0.0);
        dirs.add(dir);
        dir = new Directed(systems[1], systems[5], 0.0);
        dirs.add(dir);
        dir = new Directed(systems[9], systems[5], 0.0);
        dirs.add(dir);
        dir = new Directed(systems[8], systems[5], 0.0);
        dirs.add(dir);
        dir = new Directed(systems[5], systems[6], 0.0);
        dirs.add(dir);
        dir = new Directed(systems[5], systems[12], 0.0);
        dirs.add(dir);
        dir = new Directed(systems[5], systems[11], 0.0);
        dirs.add(dir);
        dir = new Directed(systems[11], systems[6], 0.0);
        dirs.add(dir);
        dir = new Directed(systems[12], systems[6], 0.0);
        dirs.add(dir);
        bfsFunVisited = new Graph(dirs, null);
        
        undirs = new ArrayList();
        for (int ix = 0; ix < numNodesX; ix++) {
            for (int iy = 0; iy < numNodesY; iy++) {
                if (ix > 0) {
                    undir = new Undirected(
                        systems[ix + iy*numNodesY], systems[ix - 1 + iy*numNodesY], 1);
                    undirs.add(undir);
//                    System.out.println("Connecting " + systems[ix + iy*numNodesY].toString() 
//                        + " to " + systems[ix - 1 + iy*numNodesY].toString());
                    
                }
                if (ix < numNodesX - 1) {
                    undir = new Undirected(
                        systems[ix + iy*numNodesY], systems[ix + 1 + iy*numNodesY], 1);
                    undirs.add(undir);
//                    System.out.println("Connecting " + systems[ix + iy*numNodesY].toString() 
//                        + " to " + systems[ix + 1 + iy*numNodesY].toString());
                }
                if (iy > 0) {
                    undir = new Undirected(
                        systems[ix + iy*numNodesY], systems[ix + (iy - 1)*numNodesY], 1);
                    undirs.add(undir);
//                    System.out.println("Connecting " + systems[ix + iy*numNodesY].toString() 
//                        + " to " + systems[ix + (iy - 1)*numNodesY].toString());
                }
                if (iy < numNodesY - 1) {
                    undir = new Undirected(
                        systems[ix + iy*numNodesY], systems[ix + (iy + 1)*numNodesY], 1);
                    undirs.add(undir);
//                    System.out.println("Connecting " + systems[ix + iy*numNodesY].toString() 
//                        + " to " + systems[ix + (iy + 1)*numNodesY].toString());
                }
            }
        }
//        prettyPrint("undirs:", undirs, 1);
        aStarSimple = new Graph(null, undirs);
        
        
        
        
//        for (Node node : aStarSimple.getNodes()) {
//            System.out.println("Neighbors of " + node.toString());
//            prettyPrint(aStarSimple.getNeighbors(node));
//        }
//        
//        System.out.println("Edges:");
//        prettyPrint(aStarSimple.getEdges());
        
    }
    
//    @After
//    public void tearDown() {
//    }

    @Test
    public void testGetEdges() {
        Set<Edge> actual = new HashSet(undirectedTwoPaths.getEdges());
        Set<Edge> expected = new HashSet();
        expected.add(new Undirected(systems[0], systems[1], 0.0));
        expected.add(new Undirected(systems[0], systems[2], 0.0));
        expected.add(new Undirected(systems[2], systems[3], 0.0));
        expected.add(new Undirected(systems[3], systems[1], 0.0));
        
        int i = 0;
        for (Edge actualEdge : actual) {
            for (Edge expectedEdge : expected) {
                if (actualEdge.equals(expectedEdge)) {
                    i++;
                    break;
                }
            }
        }
        System.out.println(i);
        
        assertEquals(expected, actual);
        
    }
    
    /**
     * Test of getNeighbors method, of class Graph.
     */
    @Test
    public void testGetNeighbors() {
        
        List<Node> actual = disconnected.getNeighbors(systems[0]);
        List<Node> expected = new ArrayList();
        assertEquals(expected, actual);
        
        actual = trivialDirected.getNeighbors(systems[0]);
        expected = new ArrayList();
        expected.add(systems[1]);
        assertEquals(expected, actual);
        
        actual = trivialDirected.getNeighbors(systems[1]);
        expected = new ArrayList();
        assertEquals(expected, actual);
        
        actual = trivialUndirected.getNeighbors(systems[0]);
        expected = new ArrayList();
        expected.add(systems[1]);
        assertEquals(expected, actual);
        
        actual = trivialUndirected.getNeighbors(systems[1]);
        expected = new ArrayList();
        expected.add(systems[0]);
        assertEquals(expected, actual);
        
        actual = aStarSimple.getNeighbors(systems[0 + 1*numNodesY]);
        expected = new ArrayList();
        expected.add(systems[0 + 0*numNodesY]);
        expected.add(systems[0 + 2*numNodesY]);
        expected.add(systems[1 + 1*numNodesY]);
    }
//
//    /**
//     * Test of getNodes method, of class Graph.
//     */
//    @Test
//    public void testGetNodes() {
//        System.out.println("getNodes");
//        Graph instance = null;
//        List<Node> expResult = null;
//        List<Node> result = instance.getNodes();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getEdge method, of class Graph.
//     */
//    @Test
//    public void testGetEdge() {
//        System.out.println("getEdge");
//        Node fromNode = null;
//        Node toNode = null;
//        Graph instance = null;
//        Edge expResult = null;
//        Edge result = instance.getEdge(fromNode, toNode);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    
    /**
     * Test of bfs method, of class Graph.
     */
    @Test
    public void testBfs() {
        
        // debug test
        for (StarSystem system : systems) {
            assertNotNull(system);
        }
        
        //normal test
//        System.out.println("trivialDirected");
        List<Node> actual = trivialDirected.bfs(systems[0], systems[1]);
        List<Node> expected = new ArrayList();
        expected.add(systems[1]);
        assertEquals(expected, actual);
        
        // normal test
//        System.out.println("twoPaths");
//        System.out.println("GRAPH STRUCTURE IMMEDIATELY BEFORE BFS CALL:");
        List<Node> nodes = twoPaths.getNodes();
//        for (Node node : nodes) {
//            System.out.println(((StarSystem) node).getName() + " neighbors:");
//            for (Node neighbor: twoPaths.getNeighbors(node)) {
//                System.out.println("\t" + ((StarSystem) neighbor).getName());
//            }
//        }
//        System.out.println();
        actual = twoPaths.bfs(systems[0], systems[1]);
        expected = new ArrayList();
        expected.add(systems[1]);
        assertEquals(expected, actual);
        
        // normal test
//        System.out.println("trickyTwoPaths");
        actual = trickyTwoPaths.bfs(systems[0], systems[1]);
        expected = new ArrayList();
        expected.add(systems[2]);
        expected.add(systems[3]);
        expected.add(systems[1]);
        assertEquals(expected, actual);
        
        // normal test
//        System.out.println("undirectedTwoPaths");
        actual = undirectedTwoPaths.bfs(systems[0], systems[1]);
        expected = new ArrayList();
        expected.add(systems[1]);
        assertEquals(expected, actual);
        
        // normal test
//        System.out.println("undirectedTwoPaths");
        actual = undirectedTwoPaths.bfs(systems[1], systems[0]);
        expected = new ArrayList();
        expected.add(systems[0]);
        assertEquals(expected, actual);
        
        // edge case
        actual = disconnected.bfs(systems[0], systems[1]);
        assertNull(actual);
        
        // edge case
        actual = trivialDirected.bfs(systems[0], systems[0]);
        expected = new ArrayList();
        assertEquals(expected, actual);
        
        actual = bfsVisited.bfs(systems[0], systems[7]);
        expected = new ArrayList();
        expected.add(systems[1]);
        expected.add(systems[2]);
        expected.add(systems[5]);
        expected.add(systems[6]);
        expected.add(systems[7]);
        assertEquals(expected, actual);
        
        actual = bfsFunVisited.bfs(systems[0], systems[6]);
        expected = new ArrayList();
        expected.add(systems[10]);
        expected.add(systems[1]);
        expected.add(systems[5]);
        expected.add(systems[6]);
        assertEquals(expected, actual);
    }

    /**
     * Test of aStarTreeSearch method, of class Graph.
     */
    @Test
    public void testAStarTreeSearch() {
        System.out.println("aStarTreeSearch");
        Node start = null;
        Node end = null;
        Heurstic heur = null;
        Graph instance = null;
        List<Node> expResult = null;
//        List<Node> result = instance.aStarTreeSearch(start, end, heur);
//        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of aStarGraphSearch method, of class Graph.
     */
    @Test
    public void testAStarGraphSearch() {
//        List<Node> actual = aStarSimple.aStarGraphSearch(
//            systems[0], systems[numNodesY * numNodesX - 1], new ManhattanHeuristic());
        List<Edge> actual = aStarSimple.aStarGraphSearch(
        systems[0], systems[numNodesY * numNodesX - 1], new EuclideanHeuristic());
//        prettyPrint("Actual:", actual, 1);
    }
    
    
    /**
     * Pretty print a map.
     * @param <K> key type
     * @param <V> value type
     * @param message the message to print at the top of the map; null for no message
     * @param map non-null
     */
    public static <K, V> void prettyPrint(String message, Map<K, V> map) {
        prettyPrint(message, map, 0);
    }
    
    /**
     * Pretty print a map, with a given number of tabs.
     * @param <K> key type
     * @param <V> value type
     * @param message the message to print at the top of the map; null for no message.
     *                Messages are printed at one tab less than numTabs; unless 
     *                numTabs is 0, in which case the message is printed with no
     *                tabs also
     * @param map non-null
     * @param numTabs nonnegative
     */
    public static <K, V> void prettyPrint(String message, Map<K, V> map, int numTabs) {
        
        if (map == null) return;
        if (numTabs < 0) numTabs = 0;
        
        String tabs = repeat('\t', numTabs);
        if (message != null) {
            numTabs = (numTabs > 0) ? numTabs - 1 : 0;
            String tabsMessage = repeat('\t', numTabs);
            System.out.println(tabsMessage + message);
        }
        Iterable<K> keys = map.keySet();  
        for (K key : keys) {
            V value = map.get(key);
            String kStr;
            String vStr;
            if (key != null) kStr = key.toString();
            else kStr = "null";
            if (value != null) vStr = value.toString();
            else vStr = "null";
            System.out.println(tabs + kStr + " => " + vStr);
        }
    }
    
    public static <T> void prettyPrint(String message, Iterable<T> iter) {
        prettyPrint(message, iter, 0);
    }
    
    /**
     * Pretty print a list, with a given number of tabs.
     * @param <T> element type
     * @param message the message to print at the top of the list; null for no message.
     *                Messages are printed at one tab less than numTabs; unless 
     *                numTabs is 0, in which case the message is printed with no 
     *                tabs also.
     * @param iter non-null
     * @param numTabs nonnegative
     */
    public static <T> void prettyPrint(String message, Iterable<T> iter, int numTabs) {
        
        if (iter == null) return;
        if (numTabs < 0) numTabs = 0;
        
        String tabs = repeat('\t', numTabs);
        if (message != null) {
            numTabs = (numTabs > 0) ? numTabs - 1 : 0;
            String tabsMessage = repeat('\t', numTabs);
            System.out.println(tabsMessage + message);            
        }
        for (T item : iter) {
            String str;
            if (item != null) str = item.toString();
            else str = "null";
            System.out.println(tabs + str);
        }
    }
    
    public static String repeat(char c, int times) {
        StringBuilder build = new StringBuilder();
        for (int i = 0; i < times; i++) {
            build.append(c);
        }
        return build.toString();
    }
   
}
