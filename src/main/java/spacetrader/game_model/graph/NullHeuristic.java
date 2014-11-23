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
public class NullHeuristic extends Heurstic {

    public NullHeuristic() {
        toRecalc = true;
        isAdmissible = true;
        isConsistent = true;
    }
    
    @Override
    public double calculate(Node n1, Node n2) {
        return 0;
    }
    
}
