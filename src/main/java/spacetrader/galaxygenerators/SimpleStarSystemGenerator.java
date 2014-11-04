package spacetrader.galaxygenerators;


//import spacetrader.Util;
import spacetrader.shared.Util;
import spacetrader.game_model.StarSystem;
import java.util.List;
import java.util.ArrayList;
import spacetrader.game_model.Position;
import org.apache.commons.math3.distribution.NormalDistribution;

/**
 * @author Michael Lane
 */
public class SimpleStarSystemGenerator extends StarSystemGenerator{

	/** 
	 * The expected value of the separation between the orbits of planets
	 * in the generated StarSystem.
	 */
	private Double planetSepMean;

	/** 
	 * The standard deviation of the separation between the orbits of planets
	 * in the generated StarSystem.
	 */
	private Double planetSepSD;

	/**
	 * The min distance of the separation between the orbits of planets
	 * in the generated StarSystem.
	 */
	private Double minPlanetSep;

	/** The maximum distance of any planet in the generated StarSystem. */
	private Double plutoDist;

	public SimpleStarSystemGenerator() {

	}		

	/**
	 * @param planetSepMean must be nonnegative
	 */
	public final void setPlanetSepMean(Double planetSepMean) {
		if (planetSepMean < 0) {
			throw new IllegalArgumentException("planetSepMean must be nonnegative; " + planetSepMean + " given");
		}
		this.planetSepMean = planetSepMean;
	}

	/**
	 * @param planetSepSD must be positive
	 */
	public final void setPlanetSepSD(Double planetSepSD) {
		if (planetSepSD <= 0) {
			throw new IllegalArgumentException("planetSepSD must be positive; " + planetSepSD + " given");
		}
		this.planetSepSD = planetSepSD;
	}

	/**
	 * @param minPlanetSep must be nonnegative
	 */
	public final void setMinPlanetSep(Double minPlanetSep) {
		if (minPlanetSep < 0) {
			throw new IllegalArgumentException("minPlanetSep must be nonnegative; " + minPlanetSep + " given");
		}
		this.minPlanetSep = minPlanetSep;
	}

	/**
	 * @param plutoDist must be positive
	 */
	public final void setPlutoDist(Double plutoDist) {
		if (plutoDist <= 0) {
			throw new IllegalArgumentException("plutoDist must be positive; " + plutoDist + " given");
		}
		this.plutoDist = plutoDist;
	}


    @Override
	public StarSystem generate() {

		// generates the total distances from the star of the planets,
		// so that the furthest distance does not excede plutoDist
		List<Float> distsFromStar = new ArrayList();
		NormalDistribution distr = new NormalDistribution(planetSepMean, planetSepSD);
		float total = 0;
		boolean tryAgain = true;
		while (tryAgain) {
			for (int i = 0; i < getNumPlanets(); i++) {
				float sample = (float) distr.sample();
				// if this planet's orbit is too close to the previous
				if (minPlanetSep != null && sample < minPlanetSep) {
					// then try again
					i--;
				} else {
					total += sample;
					distsFromStar.add(total);
				}
			}

			// if the furthest planet is within plutoDist from the center,
			// and if the orbit of the furthest planet does not exist the bounds of the galaxy
			if (total <= plutoDist
				&& Math.abs(pos.x) + total < galaxy.getWidth()/2
				&& Math.abs(pos.y) + total < galaxy.getHeight()/2) {
				tryAgain = false;
			} else {
				distsFromStar = new ArrayList();
				total = 0;
			}
		}

		StarSystem system = new StarSystem(name, pos, starType);
		for (int i = 0; i < getNumPlanets(); i++) {
			float dist = distsFromStar.get(i);
			// randomly generate a position, given a distance
			double theta = Util.sampleFromUniformReal(0, Math.PI);
			double x = dist * Math.cos(theta);
			double y = dist * Math.sin(theta);
			Position posAboutStar = new Position((float)x, (float)y);
            posAboutStar.rotate(theta);
            Position planetPos = new Position(pos.x + posAboutStar.x, pos.y + posAboutStar.y);
            // SET PROPERTIES FOR PLANET GENERATION HEREI
            planetGenerator.setName(name + ", Planet " + i);
            planetGenerator.setPosition(planetPos);
			system.addPlanet(planetGenerator.generate());
		}
                
		return system;

	}
}