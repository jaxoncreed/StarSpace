package spacetrader.game_model;

import java.io.Serializable;
import java.util.Objects;
import spacetrader.game_model.graph.Node;
import spacetrader.game_model.graph.UndirectedEdge;

public class JumpPoint implements Serializable, UndirectedEdge {
	private StarSystem system1;
    private StarSystem system2;
	private Position pos1;
    private Position pos2;
	public static final String term = "Hargin Point";
    private int level;

	public JumpPoint(StarSystem system1, StarSystem system2, Position pos1, Position pos2) {
		this.system1 = system1;
		this.system2 = system2;
		this.pos1 = pos1;
        this.pos2 = pos2;
        level=1;
	}
    
    public int getLevel(){
        return level;
    }
    
    public Node getNode1() {
        return system1;
    }
    
    public Node getNode2() {
        return system2;
    }
    
    public double getWeight() {
        return 1;
    }
    
    public Position getPosition1() {
        return pos1;
    }
    
    public Position getPosition2() {
        return pos2;
    }
    
    public boolean equals(Object o) {
        if (o == null || !(o instanceof JumpPoint)) {
            return false;
        }
        JumpPoint that = (JumpPoint) o;
        Node thisNode1 = this.getNode1();
        Node thisNode2 = this.getNode2();
        Node thatNode1 = that.getNode1();
        Node thatNode2 = that.getNode2();
        double thisW = this.getWeight();
        double thatW = that.getWeight();
        Position thisPos1 = this.getPosition1();
        Position thisPos2 = this.getPosition2();
        Position thatPos1 = that.getPosition1();
        Position thatPos2 = that.getPosition2();
        return 
            (thisNode1.equals(thatNode1) && thisNode2.equals(thatNode2) 
                && thisPos1.equals(thatPos1) && thisPos2.equals(thatPos2))
            || 
            (thisNode1.equals(thatNode2) && thisNode2.equals(thatNode1) 
                && thisPos1.equals(thatPos2) && thisPos2.equals(thatPos1))
        &&  thisW == thatW;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.system1) + Objects.hashCode(this.system2);
        hash = 89 * hash + Objects.hashCode(this.pos1) + Objects.hashCode(this.pos2);
        hash = 89 * hash + Objects.hashCode(this.getWeight());
        return hash;
    }
}