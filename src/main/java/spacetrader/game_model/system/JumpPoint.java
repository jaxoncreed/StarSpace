package spacetrader.game_model.system;

import java.io.Serializable;
import spacetrader.game_model.interactable.InteractableObject;
import spacetrader.PhysicsSimulator;
import spacetrader.game_model.GameModel;
import spacetrader.game_model.gameLogic.Position;
import spacetrader.game_model.Ship;
import spacetrader.game_model.interactable.InteractionType;

public class JumpPoint implements Serializable, InteractableObject {

	public static final String TERM = "Hargin Point";

	private StarSystem targetSys;
	private Position targetPos;
	private Position pos;
	private int level;
	private double interactionRange;

	public JumpPoint(Position pos, StarSystem targetSys,
		Position targetPos) {
		this.pos = pos;
		this.targetSys = targetSys;
		this.targetPos = targetPos;
		level = 1;
	}

	public Position getPos() {
		return pos;
	}

	public StarSystem getTargetSystem() {
		return targetSys;
	}

	public Position getTargetPos() {
		return new Position(targetPos);
	}

	public int getLevel(){
		return level;
	}

	public double getInteractionRange() {
		return interactionRange;
	}

        public String getInteractionMessage() {
            return "Jump to " + targetSys.getName();
        }
        
	public void interact(Ship ship, GameModel gm) {
		ship.jump(this);

		// If the ship belongs to the player,
		// change the physics simulator's target system
		if (gm.getPlayer().getShip() == ship) {
			PhysicsSimulator.setSystem(gm.getPlayer().getSystem());
		}
	}

    @Override
    public InteractionType getType() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}