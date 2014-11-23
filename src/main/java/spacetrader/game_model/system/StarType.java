package spacetrader.game_model.system;

import java.io.Serializable;

public enum StarType implements Serializable {
	DWARF, GIANT, BLACK_HOLE; // these two need to be here for SimpleStarSystemGenerator to work
}