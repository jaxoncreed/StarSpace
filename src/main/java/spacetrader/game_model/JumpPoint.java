package spacetrader.game_model;

import java.io.Serializable;

public class JumpPoint implements Serializable {

	public static final String TERM = "Hargin Point";

	private StarSystem targetSys;
	private Position targetPos;
	private Position pos;
	private int level;

	public JumpPoint(Position pos, StarSystem targetSys,
		Position targetPos) {
		pos = this.pos;
		targetSys = this.targetSys;
		targetPos = this.targetPos;
		level = 1;
	}

	public Position getPos() {
		return pos;
	}

	public StarSystem getTargetSystem() {
		return targetSys;
	}

	public Position getTargetPos() {
		return targetPos;
	}
	public int getLevel(){
		return level;
	}

}