package spacetrader.galaxygenerators;


//import spacetrader.Util;
import spacetrader.shared.Util;
import spacetrader.game_model.system.Galaxy;

/**
 * Shared properties among all generated galaxies
 * 
 * @author Michael Lane
 */
public abstract class GalaxyGenerator {

	/** The name of the galaxy to generate */
	protected String name;

	/** The number of systems in the galaxy to generate */
	protected Integer numSystems;

	/** 
	 * The number of systems in the galaxy to generate will be some
	 * distribution (prolly gaussian) with this field as its expected 
	 * value.
	 */
	protected Double numSystemsMean;

	/**
	 * The number of systems in the galaxy to generate will be some
	 * distribution (prolly gaussian) with this field as its standard
	 * deviation.
	 */
	protected Double numSystemsSD;

	/** 
	 * Set to true to randomly sample the number of systems in the generated
	 * galaxy from a (prolly gaussian) distribution.
	 */
	protected Boolean sampleNumSystems;
    
    private boolean numSystemsSampled;

	/** The minimum distance between distances in the galaxy to generate */
	protected Double minSystemDist;

	/** The width (x-coord) of the galaxy to generate */
	protected Double width;

	/** The height (y-coord) of the galaxy to generate */
	protected Double height; 

	/** The generator to use for StarSystem generatior */
	protected StarSystemGenerator starSystemGenerator;

	/** 
	 * @return Returns a galaxy with the given specifications
	 */
	public abstract Galaxy generate();

	/**
	 * @param name must be non-null and non-empty
	 */
	public final void setName(String name) {
		if (name == null || name.length() == 0) {
			throw new IllegalArgumentException("Invalid name for galaxy; " + name + " given");
		}
		this.name = name;
	}

	/**
	 * @param numSystems must be 1 or larger
	 */
	public final void setNumSystems(Integer numSystems) {
		if (numSystems <= 0) {
			throw new IllegalArgumentException("numSystems must be at least 1; " + numSystems + " given");
		}
		this.numSystems = numSystems;
	}

	/**
	 * @param minSystemDist must be nonnegative
	 */
	public final void setMinSystemDist(Double minSystemDist) {
		if (minSystemDist < 0) {
			throw new IllegalArgumentException("minSystemDist must be nonnegative; " + minSystemDist + " given");
		}
		this.minSystemDist = minSystemDist;
	}

	/**
	 * @param width must be positive
	 */
	public final void setWidth(Double width) {
		if (width <= 0) {
			throw new IllegalArgumentException("width must be positive; " + width + " given");
		}
		this.width = width;
	}

	/**
	 * @param height must be positive
	 */
	public final void setHeight(Double height) {
		if (height <= 0) {
			throw new IllegalArgumentException("height must be positive; " + height + " given");
		}
		this.height = height;
	}

	/**
	 * @param numSystemsMean must be positive
	 */
	public final void setNumSystemsMean(Double numSystemsMean) {
		if (numSystemsMean <= 0) {
			throw new IllegalArgumentException("numSystemsMean must be positive; " + numSystemsMean + " given");
		}
		this.numSystemsMean = numSystemsMean;
	}

	/**
	 * @param numSystemsSD must be positive
	 */
	public final void setNumSystemsSD(Double numSystemsSD) {
		if (numSystemsSD <= 0) {
			throw new IllegalArgumentException("numSystemsSD must be positive; " + numSystemsSD + " given");
		}
		this.numSystemsSD = numSystemsSD;
	}

	/**
	 * @param sampleNumSystems
	 */
	public final void setSampleNumSystems(Boolean sampleNumSystems) {
		this.sampleNumSystems = sampleNumSystems;
	}

	/**
	 * @param starSystemGenerator must be non-null
	 */
	public final void setStarSystemGenerator(StarSystemGenerator starSystemGenerator) {
		if (starSystemGenerator == null) {
			throw new IllegalArgumentException("starSystemGenerator must be non-null");
		}
		this.starSystemGenerator = starSystemGenerator;
	}

	/**
	 * If sampleNumSystems is false, then return numSystems.
	 * Else sample from Normal(systumNumMean, systumNumSD)
     * @return
	 */
	public int getNumSystems() {
		if (sampleNumSystems && !numSystemsSampled) {
            int sample = (int) Util.sampleFromNormal(numSystemsMean, numSystemsSD);
            numSystems = sample;
            numSystemsSampled = true;
		}
        return numSystems;
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