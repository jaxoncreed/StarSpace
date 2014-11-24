package spacetrader.game_model.system;

import java.io.Serializable;
import java.util.Objects;
import spacetrader.game_model.interactable.InteractableObject;
import spacetrader.game_model.graph.Node;
import spacetrader.PhysicsSimulator;
import spacetrader.game_model.GameModel;
import spacetrader.game_model.gameLogic.Position;

import spacetrader.game_model.graph.DirectedEdge;
import spacetrader.game_model.interactable.InteractionType;

public class JumpPoint implements Serializable, DirectedEdge, Interactable {
	
    public static final String TERM = "Hargin Point";
    private StarSystem fromSystem;
    private StarSystem toSystem;
	private Position fromPos;
    private Position toPos;
    private int level;
    public static final double INTERACTION_RANGE = 0;

	public JumpPoint(StarSystem fromSystem, StarSystem toSystem, Position fromPos, Position toPos) {
		this.toSystem = toSystem;
		this.fromSystem = toSystem;
		this.fromPos = fromPos;
        this.toPos = toPos;
        level=1;
	}
    
    public int getLevel(){
        return level;
    }
    
    @Override
    public Node getFromNode() {
        return fromSystem;
    }
    
    @Override
    public Node getToNode() {
        return toSystem;
    }
    
    public StarSystem getTargetSystem() {
        return (StarSystem) getToNode();
    }
    
    @Override
    public double getWeight() {
        return 1;
    }
    
    public Position getToPosition() {
        return toPos;
    }
    
    public Position getFromPosition() {
        return fromPos;
    }
    
    public Position getTargetPos() {
        return getToPosition();
    }
    
    public boolean equals(Object o) {
        if (o == null || !(o instanceof JumpPoint)) {
            return false;
        }
        JumpPoint that = (JumpPoint) o;
        return 
            this.getToNode().equals(that.getToNode()) 
            && this.getFromNode().equals(that.getFromNode()) 
            && this.getFromPosition().equals(that.getFromPosition()) 
            && this.getToPosition().equals(that.getToPosition())
            && this.getWeight() == that.getWeight();
    }

	public double getInteractionRange() {
		return INTERACTION_RANGE;
	}

        public String getInteractionMessage() {
            return "Jump to " + toSystem.getName();
        }
        
	public void interact(Ship ship, GameModel gm) {
		ship.jump(this);

		// If the ship belongs to the player,
		// change the physics simulator's target system
		if (gm.getPlayer().getShip().equals(ship)) {
			PhysicsSimulator.setSystem(gm.getPlayer().getSystem());
		}
	}

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.toSystem);
        hash = 89 * hash + Objects.hashCode(this.fromSystem);
        hash = 89 * hash + Objects.hashCode(this.toPos);
        hash = 89 * hash + Objects.hashCode(this.fromPos);
        hash = 89 * hash + Objects.hashCode(this.getWeight());
        return hash;
    }
}