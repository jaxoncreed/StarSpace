package spacetrader.game_model.graph;

/**
 *
 * @author Michael Lane
 */
public interface UndirectedEdge<N extends Node> extends Edge<N> {
    
    public N getNode1();
    public N getNode2();
}
