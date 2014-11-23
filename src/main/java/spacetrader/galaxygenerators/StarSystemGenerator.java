package spacetrader.galaxygenerators;

//import spacetrader.Util;
import spacetrader.shared.Util;
import spacetrader.game_model.system.Galaxy;
import spacetrader.game_model.gameLogic.Position;
import spacetrader.game_model.system.StarSystem;
import spacetrader.game_model.system.StarType;

/**
 * @author Michael Lane
 */
public abstract class StarSystemGenerator {

	/** The name of the StarSystem to generate */
	protected String name;

	/** The Position of the StarSystem to generate */
	protected Position pos;

	/** The number of planets in the StarSystem to generate */
	protected Integer numPlanets;

	/** 
	 * The number of planets in the system to generate will be some
	 * distribution (prolly gaussian) with this field as its expected 
	 * value.
	 */
	protected Double numPlanetsMean;

	/**
	 * The number of planets in the system to generate will be some
	 * distribution (prolly gaussian) with this field as its standard
	 * deviation.
	 */
	protected Double numPlanetsSD;

	/** 
	 * Set to true to randomly sample the number of planets in the generated
	 * system from a (prolly gaussian) distribution.
	 */
	protected Boolean sampleNumPlanets;


	/** The galaxy that the StarSystem to generate will belong to */
	protected Galaxy galaxy;

	/** The StarType of the StarSystem to generate */
	protected StarType starType;

	/** The generator to use for planet generation */
	protected PlanetGenerator planetGenerator;
    
    private boolean numPlanetsSampled = false;

	/**
	 * @return Returns a StarSystem with the given specifications. 
	 */
	public abstract StarSystem generate();

	/**
	 * @param name must be non-null and non-empty
	 */
	public final void setName(String name) {
		 if (name == null || name.length() == 0) {
		 	throw new IllegalArgumentException("invalid system name");
		 }
		 this.name = name;
	}

	/**
	 * @param numPlanets must be nonnegative 
	 */
	public final void setNumPlanets(Integer numPlanets) {
		 if (numPlanets < 0) {
		 	throw new IllegalArgumentException("numPlanets must be nonnegative; " + numPlanets + " given");
		 }
		 this.numPlanets = numPlanets;
	}

	/**
	 * @param numPlanetsMean must be positive
	 */
	public final void setNumPlanetsMean(Double numPlanetsMean) {
		if (numPlanetsMean <= 0) {
			throw new IllegalArgumentException("numPlanetsMean must be positive; " + numPlanetsMean + " given");
		}
		this.numPlanetsMean = numPlanetsMean;
	}

	/**
	 * @param numPlanetsSD must be positive
	 */
	public final void setNumPlanetsSD(Double numPlanetsSD) {
		if (numPlanetsSD <= 0) {
			throw new IllegalArgumentException("numPlanetsSD must be positive; " + numPlanetsSD + " given");
		}
		this.numPlanetsSD = numPlanetsSD;
	}


	/**
	 * @param sampleNumPlanets
	 */
	public final void setSampleNumPlanets(Boolean sampleNumPlanets) {
		this.sampleNumPlanets = sampleNumPlanets;
	}


	/**
	 * @param pos must be non-null
	 */
	public final void setPosition(Position pos) {
		if (pos == null) {
			throw new IllegalArgumentException("pos must be non-null");
		}
		this.pos = pos;
	}

	/**
	 * @param galaxy must be non-null
	 */
	public final void setGalaxy(Galaxy galaxy) {
		 if (galaxy == null) {
		 	throw new IllegalArgumentException("galaxy cannot be null");
		 }
		 this.galaxy = galaxy;
	}

	/**
	 * @param planetGenerator must be non-null
	 */
	public final void setPlanetGenerator(PlanetGenerator planetGenerator) {
		if (planetGenerator == null) {
			throw new IllegalArgumentException("planetGenerator must be non-null");
		}
		this.planetGenerator = planetGenerator;
	}

	public final void setStarType(StarType starType) {
		this.starType = starType;
	}

	/**
	 * If sampleNumPlanets is false, then return numSystems.
	 * Else sample from Normal(systumNumMean, systumNumSD)
	 */
	public int getNumPlanets() {
		if (sampleNumPlanets && !numPlanetsSampled) {
            int sample = (int) Util.sampleFromNormal(numPlanetsMean, numPlanetsSD);
            numPlanets = (sample > 0) ? sample : 1; 
            numPlanetsSampled = true;
		}
        return numPlanets;
	}

	public Galaxy getGalaxy() {
		return galaxy;
	}
}