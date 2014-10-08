package spacetrader.galaxygenerators;


//import spacetrader.Util;
import spacetrader.game_model.Galaxy;
import org.apache.commons.math3.distribution.NormalDistribution;

/**
 * Shared properties among all generated galaxies
 * 
 * @author Michael Lane
 */
public abstract class GalaxyGenerator {

	/** The galaxy's name */
	protected String name;

	/** The number of systems in the galaxy. */
	protected int numSystems;

	/**
	 * Constructs a galaxy with a random number of systems, distributed normally.
	 * 
	 * @param name
	 * @param systemNumMean The average number of systems in the galaxy
	 * @param systemNumSD The standard deviation to the average number of systems in the galaxy 
	 */
	protected GalaxyGenerator(String name, double systemNumMean, double systemNumSD) {

		setName(name);
		double sample = Util.sampleFromNormal(systemNumMean, systemNumSD);
		setNumSystems((int) Math.abs(sample));
	}

	/**
	 * Constructs a galaxy with a given number of systems
	 * 
	 * @param name The galaxy's name
	 * @param numSystems The number of systems
	 */
	protected GalaxyGenerator(String name, int numSystems)  {

		setName(name);
		setNumSystems(numSystems);
	}

	/** 
	 * @return Returns a galaxy with the current parameters
	 */
	public abstract Galaxy generate();

	public final void setName(String name) {

		if (name == null || name.length() == 0) {
			throw new IllegalArgumentException("Invalid name for galaxy");
		}

		this.name = name;
	}

	public final void setNumSystems(int numSystems) {

		if (numSystems <= 0) {
			throw new IllegalArgumentException("There must be at least 1 system in every galaxy.");
		}

		this.numSystems = numSystems;
	}
}