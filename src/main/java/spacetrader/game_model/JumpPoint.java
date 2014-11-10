package spacetrader.game_model;

import java.io.Serializable;
import spacetrader.Interactable;
import spacetrader.PhysicsSimulator;

public class JumpPoint implements Serializable, Interactable {

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

}