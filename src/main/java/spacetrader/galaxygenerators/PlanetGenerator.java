package spacetrader.galaxygenerators;

//import spacetrader.Util;
import spacetrader.game_model.Planet;
import spacetrader.game_model.Position;
import spacetrader.game_model.StarSystem;

/**
 * @author Michael Lane
 */
public abstract class PlanetGenerator {

	/** The name of the planet to generate */
	protected String name;

	/** The Position of the Planet to generate */
	protected Position pos;

	/** The StarSystem that this Planet belongs to */
	protected StarSystem system;

	/**
	 * @return Returns a Planet with the given specifications. 
	 */
	public abstract Planet generate();

	/**
	 * @param name must be non-null and non-empty
	 */
	public final void setName(String name) {
		if (name == null || name.length() == 0) {
			throw new IllegalArgumentException("invalid planet name");
		}
		this.name = name;
	}

	/**
	 * @param pos must be non-null
	 */
	public final void setPosition(Position pos) {
		if (pos == null) {
			throw new IllegalArgumentException("position must be non-null");
		}
		this.pos = pos;
	}

	/**
	 * @param system must be non-null
	 */
	public final void setSystem(StarSystem system) {
		if (system == null) {
			throw new IllegalArgumentException("system must be non-null");
		}
		this.system = system;
	}

}