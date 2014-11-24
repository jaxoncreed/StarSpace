package spacetrader.game_model.graph;

import java.util.Objects;
import spacetrader.game_model.system.StarSystem;

/**
 *
 * @author Michael Lane <mlane@gatech.edu>
 */
public class Undirected implements UndirectedEdge {
    private StarSystem node1;
    private StarSystem node2;
    private double weight;
    
    public Undirected(StarSystem node1, StarSystem node2, double weight) {
        this.node1 = node1;
        this.node2 = node2;
        this.weight = weight;
    }
    
    @Override
    public Node getNode1() {
        return node1;
    }

    @Override
    public Node getNode2() {
        return node2;
    }

    @Override
    public double getWeight() {
        return weight;
    }
    
    @Override
    public String toString() {
        return node1.toString() + " -- " + node2.toString() + ": " + weight;
    }
    
    @Override
    public boolean equals(Object o) {
        
        if (!(o instanceof Undirected)) {
            return false;
        }
        Undirected undir = (Undirected) o;
        return this.weight == undir.getWeight()
        && (undir.getNode1().equals(this.node1) && undir.getNode2().equals(this.node2))
            || (undir.getNode1().equals(this.node2) && undir.getNode2().equals(this.node1));
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.node1) + Objects.hashCode(this.node2);
        hash = 29 * hash + (int) (Double.doubleToLongBits(this.weight) ^ (Double.doubleToLongBits(this.weight) >>> 32));
        return hash;
    }
}
