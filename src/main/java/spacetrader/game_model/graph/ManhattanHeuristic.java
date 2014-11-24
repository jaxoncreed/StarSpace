package spacetrader.game_model.graph;

import spacetrader.game_model.Positionable;

/**
 * I used this for testing/debugging; it prolly won't be used elsewhere.
 * 
 * @author Michael Lane <mlane@gatech.edu>
 */
public class ManhattanHeuristic extends Heurstic {
    
    public ManhattanHeuristic() {
        this(true);
    }
    
    public ManhattanHeuristic(boolean toRecalc) {
        this.toRecalc = toRecalc;
        this.isAdmissible = true;
        this.isConsistent = true;
    }

    @Override
    public double calculate(Node n1, Node n2) {
        double x1 = ((Positionable) n1).getPosition().x;
        double x2 = ((Positionable) n2).getPosition().x;
        double y1 = ((Positionable) n1).getPosition().y;
        double y2 = ((Positionable) n2).getPosition().y;
        double dx = Math.abs(x1 - x2);
        double dy = Math.abs(y1 - y2);
        return dx + dy;
    }
}
