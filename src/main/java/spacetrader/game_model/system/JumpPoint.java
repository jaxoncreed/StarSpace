package spacetrader.game_model.system;

import java.io.Serializable;
import java.util.Objects;
import spacetrader.Interactable;
import spacetrader.game_model.graph.Node;
import spacetrader.PhysicsSimulator;
import spacetrader.game_model.gameLogic.Position;
import spacetrader.game_model.graph.DirectedEdge;
import spacetrader.game_model.interactable.InteractableObject;
import spacetrader.game_model.interactable.InteractionType;
import spacetrader.game_model.system.StarSystem;

public class JumpPoint implements Serializable, DirectedEdge<StarSystem>, InteractableObject {
	
    public static final String TERM = "Hargin Point";
    private StarSystem fromSystem;
    private StarSystem toSystem;
    private Position fromPos;
    private Position toPos;
    private int level;
    public static final double INTERACTION_RANGE = 100;

    public JumpPoint(StarSystem fromSystem, StarSystem toSystem, Position fromPos, Position toPos) {
        this.toSystem = toSystem;
        this.fromSystem = fromSystem;
        this.fromPos = fromPos;
        this.toPos = toPos;
        level=1;
    }
    
    public int getLevel(){
        return level;
    }
    
    @Override
    public StarSystem getFromNode() {
        return fromSystem;
    }
    
    @Override
    public StarSystem getToNode() {
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

    @Override
    public Position getPos() {
        return fromPos;
    }

    @Override
    public InteractionType getType() {
        return InteractionType.Travel;
    }
}