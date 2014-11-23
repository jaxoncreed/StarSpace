package spacetrader.game_model.graph;

import java.util.Objects;
import spacetrader.game_model.StarSystem;

/**
 *
 * @author Michael Lane <mlane@gatech.edu>
 */
public class Directed implements DirectedEdge {

    private StarSystem fromNode;
    private StarSystem toNode;
    private double weight;
    
    public Directed(StarSystem fromNode, StarSystem toNode, double weight) {
        this.fromNode = fromNode;
        this.toNode = toNode;
        this.weight = weight;
    }
    
    @Override
    public Node getFromNode() {
        return (Node) fromNode;
    }

    @Override
    public Node getToNode() {
        return toNode;
    }

    @Override
    public double getWeight() {
        return weight;
    }
    
    
    @Override
    public String toString() {
        return fromNode.toString() + " -> " + toNode.toString() + ": " + weight;
    }
    
    @Override
    public boolean equals(Object o) {
        
        if (!(o instanceof Directed)) {
            return false;
        }
        Directed dir = (Directed) o;
        return this.weight == dir.getWeight()
        && (dir.getFromNode().equals(this.fromNode) && dir.getToNode().equals(this.toNode));
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.fromNode);
        hash = 47 * hash + Objects.hashCode(this.toNode);
        hash = 47 * hash + (int) (Double.doubleToLongBits(this.weight) ^ (Double.doubleToLongBits(this.weight) >>> 32));
        return hash;
    }

}
