package spacetrader.game_model.graph;

/**
 *
 * @author Michael Lane
 */
public interface UndirectedEdge extends Edge {
    
    public Node getNode1();
    public Node getNode2();
}
