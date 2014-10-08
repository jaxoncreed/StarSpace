package spacetrader.game_model;

import spacetrader.game_model.StarSystem;
import java.awt.geom.Point2D;

public class JumpPoint {
	private StarSystem targetSys;
	private Position targetPos;
	private Position pos;
	public static final String term = "Hargin Point";
        private int level;

	public JumpPoint(Position pos, StarSystem targetSys,
					 Position targetPos) {
		pos = this.pos;
		targetSys = this.targetSys;
		targetPos = this.targetPos;
                level=1;
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