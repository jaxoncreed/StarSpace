package spacetrader.galaxygenerators;

//import spacetrader.Util;
import spacetrader.game_model.Galaxy;
import spacetrader.game_model.Position;
import spacetrader.game_model.StarSystem;
import spacetrader.game_model.StarType;
import org.apache.commons.math3.distribution.NormalDistribution;

/**
 * @author Michael Lane
 */
public abstract class StarSystemGenerator {

	protected String name;
	protected Position pos;
	protected int numPlanets;
	protected Galaxy galaxy;
	protected StarType starType;

	protected StarSystemGenerator(
		String name, 
		Position pos,
		double planetNumMean, 
		double planetNumSD,
		StarType starType,
		Galaxy galaxy) {

		this(name, pos, 
		     (int) Util.sampleFromNormal(planetNumMean, planetNumSD),
		     starType, galaxy);
	}

	protected StarSystemGenerator(
		String name,
		Position pos,
		int numPlanets,
		StarType starType,
		Galaxy galaxy) {

		setName(name);
		setPosition(pos);
		setNumPlanets(numPlanets);
		this.starType = starType;
		setGalaxy(galaxy);
	}

	public abstract StarSystem generate();

	public final void setName(String name) {
		 
		 if (name == null || name.length() == 0) {
		 	throw new IllegalArgumentException("invalid system name");
		 }

		 this.name = name;
	}

	public final void setNumPlanets(int numPlanets) {
		 
		 if (numPlanets <= 0) {
		 	throw new IllegalArgumentException("every system must have at least 1 planet");
		 }

		 this.numPlanets = numPlanets;
	}

	public final void setPosition(Position pos) {

		if (pos == null) {
			throw new IllegalArgumentException("pos must be non-null");
		}

		this.pos = pos;
	}

	public final void setGalaxy(Galaxy galaxy) {
		 
		 if (galaxy == null) {
		 	throw new IllegalArgumentException("galaxy cannot be null");
		 }

		 this.galaxy = galaxy;
	}


	public Galaxy getGalaxy() {
		return galaxy;
	}

}