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

	protected String name;
	protected int numSystems;
	protected double minSystemDist;
	protected double width;
	protected double height; 

	/**
	 * Constructs a galaxy with a random number of systems, distributed normally.
	 * 
	 * @param name
	 * @param systemNumMean The average number of systems in the galaxy
	 * @param systemNumSD The standard deviation to the average number of systems in the galaxy 
	 * @param minSystemDist The minimum distance between StarSystems in the generated galaxy
	 * @param width The distance between two opposite edges of the galaxy
	 * @param height The distance between two opposite edges of the galaxy
	 */
	protected GalaxyGenerator(
		String name, 
		double systemNumMean, 
		double systemNumSD, 
		double minSystemDist,
		double width,
		double height) {

		setName(name);
		int sample = (int) Math.abs(Util.sampleFromNormal(systemNumMean, systemNumSD));
		setNumSystems(sample > 0 ? sample : 1);
		setMinSystemDist(minStarSystemDist);
		setWidth(width);
		setHeight(height);

		assureInputSanity();
	}

	/**
	 * Constructs a galaxy with a given number of systems
	 * 
	 * @param name The galaxy's name
	 * @param numSystems The number of systems
	 * @param minSystemDist The minimum distance between StarSystems in the generated galaxy
 	 * @param width The distance between two opposite edges of the galaxy
	 * @param height The distance between two opposite edges of the galaxy
	 */
	protected GalaxyGenerator(
		String name, 
		int numSystems,
		double minSystemDist,
		double width,
		double height)  {

		setName(name);
		setNumSystems(numSystems);
		setMinSystemDist(minSystemDist);

	}

	/** 
	 * @return Returns a galaxy with the current parameters
	 */
	public abstract Galaxy generate();

	public final void setName(String name) {

		if (name == null || name.length() == 0) {
			throw new IllegalArgumentException("Invalid name for galaxy; " + name + " given");
		}

		this.name = name;
	}

	public final void setNumSystems(int numSystems) {

		if (numSystems <= 0) {
			throw new IllegalArgumentException("numSystems must be at least 1; " + numSystems + " given");
		}
		this.numSystems = numSystems;
	}

	public final void setMinSystemDist(double minSystemDist) {

		if (minSystemDist < 0) {
			throw new IllegalArgumentException("minSystemDist must be nonnegative; " + minSystemDist + " given");
		}
		this.minSystemDist = minSystemDist;
	}

	public final void setWidth(double width) {

		if (width <= 0) {
			throw new IllegalArgumentException("width must be positive; " + width + " given");
		}
		this.width = width;
	}


	public final void setHeight(double height) {

		if (height <= 0) {
			throw new IllegalArgumentException("height must be positive; " + height + " given");
		}
		this.height = height;
	}

	private void assureInputSanity() {

		if (minSystemDist > width) {
			throw new IllegalArgumentException("minSystemDist excedes width");
		}
		if (minSystemDist > height) {
			throw new IllegalArgumentException("minSystemDist excedes height");			
		}
	}
}