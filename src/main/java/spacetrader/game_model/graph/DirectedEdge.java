/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacetrader.game_model.graph;

/**
 *
 * @author michael
 */
public interface DirectedEdge<N extends Node> extends Edge<N> {
    
    public N getFromNode();
    public N getToNode();
}
