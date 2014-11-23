/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacetrader.game_model.graph;

import java.util.HashSet;
import java.util.Set;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import spacetrader.game_model.Position;
import spacetrader.game_model.StarSystem;
import spacetrader.game_model.StarType;

/**
 *
 * @author michael
 */
public class UndirectedTest {
    
    private StarSystem[] systems;
    private Undirected undir1;
    private Undirected undir2;
    private int num;
    
    
    public UndirectedTest() {
    }
    
    @Before
    public void setUp() {
        

        
        num = 4;
        systems = new StarSystem[num];
        for (int i = 0; i < num; i++) {
            systems[i] = new StarSystem("" + i, Position.origin, StarType.BLACK_HOLE);
        }
        
        undir1 = new Undirected(systems[0], systems[1], 1.0);
        undir2 = new Undirected(systems[1], systems[0], 1.0);      
    }
    
//    @After
//    public void tearDown() {
//    }

//    /**
//     * Test of getNode1 method, of class Undirected.
//     */
//    @Test
//    public void testGetNode1() {
//        
//    }
//
//    /**
//     * Test of getNode2 method, of class Undirected.
//     */
//    @Test
//    public void testGetNode2() {
//    }
//
//    /**
//     * Test of getWeight method, of class Undirected.
//     */
//    @Test
//    public void testGetWeight() {
//    }
//
//    /**
//     * Test of toString method, of class Undirected.
//     */
//    @Test
//    public void testToString() {
//    }

    /**
     * Test of equals method, of class Undirected.
     */
    @Test
    public void testEquals() {
        assertEquals(new Undirected(systems[0], systems[1], 1.0), 
            new Undirected(systems[0], systems[1], 1.0));
        assertEquals(new Undirected(systems[0], systems[1], 1.0), 
            new Undirected(systems[1], systems[0], 1.0));
        
        Set<Undirected> set1 = new HashSet();
        set1.add(undir1);
        set1.add(undir2);
        
        Set<Undirected> set2 = new HashSet();
        set2.add(undir2);
        set2.add(undir1);
        
        assertEquals(set1, set2);
    }

//    /**
//     * Test of hashCode method, of class Undirected.
//     */
//    @Test
//    public void testHashCode() {
//    }
    
}
