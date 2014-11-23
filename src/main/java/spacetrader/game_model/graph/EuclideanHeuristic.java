package spacetrader.game_model.graph;

import spacetrader.game_model.Positionable;

/**
 *
 * @author Michael Lane <mlane@gatech.edu>
 */
public class EuclideanHeuristic extends Heurstic {

    public EuclideanHeuristic() {
        this(true);
    }
    
    public EuclideanHeuristic(boolean toRecalc) {
        this.toRecalc = toRecalc;
        this.isAdmissible = true;
        this.isConsistent = true;
    }
    
    @Override
    public double calculate(Node n1, Node n2) {
        return ((Positionable) n1).getPosition().distTo(((Positionable) n2).getPosition());
    }
}
