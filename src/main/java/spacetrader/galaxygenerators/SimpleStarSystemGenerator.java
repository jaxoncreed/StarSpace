package spacetrader.galaxygenerators;


//import spacetrader.Util;
import spacetrader.shared.Util;
import spacetrader.game_model.system.StarSystem;
import java.util.List;
import java.util.ArrayList;
import spacetrader.game_model.gameLogic.Position;
import org.apache.commons.math3.distribution.NormalDistribution;
import spacetrader.game_model.interactable.InteractableObject;
import spacetrader.game_model.system.Planet;

/**
 * @author Michael Lane
 */
public class SimpleStarSystemGenerator extends StarSystemGenerator{

	/** 
	 * The expected value of the separation between the orbits of planets
     */
	private Double planetSepMean;

	/** 
	 * The standard deviation of the separation between the orbits of planets.
	 */
	private Double planetSepSD;

	/**
	 * The min separation between the orbits of planets.
	 */
	private Double minPlanetSep;

	/** The maximum distance of a planet from its home star.  */
	private Double plutoDist;
    
    /** mean star mass */
    private Double starMassMean;
    
    /** star mass sd */
    private Double starMassSD;

    /**
     * @param starMassMean nonnegative
     */
    public final void setStarMassMean(Double starMassMean) {
        this.starMassMean = starMassMean;
    }
    
    /**
     * @param starMassSD positive
     */
    public final void setStarMassSD(Double starMassSD) {
        this.starMassSD = starMassSD;
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
		boolean tryAgain = true;
		while (tryAgain) {
            float total = 0;
			for (int i = 0; i < getNumPlanets(); i++) {
				double sample =  distr.sample();
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
			if (total <= plutoDist) {
				tryAgain = false;
			} else {
				distsFromStar = new ArrayList();
				total = 0;
			}
		}

		StarSystem system = new StarSystem(name, pos, starType);
        double mass = Util.sampleFromNormal(starMassMean, starMassSD);
        system.setStarMass((mass > 0) ? mass : starMassMean);
		for (int i = 0; i < getNumPlanets(); i++) {
			float dist = distsFromStar.get(i);
			// randomly generate a position, given a distance
			double theta = Util.sampleFromUniformReal(0, Math.PI);
			double x = dist * Math.cos(theta);
			double y = dist * Math.sin(theta);
			Position posAboutStar = new Position(x, y);
            posAboutStar.rotate(theta);
            Position planetPos = new Position(posAboutStar.x, posAboutStar.y);
            // SET PROPERTIES FOR PLANET GENERATION HERE
            planetGenerator.setName(name + ", Planet " + i);
            planetGenerator.setPosition(planetPos);
            planetGenerator.setSystem(system);
            Planet planet = planetGenerator.generate();
			system.addPlanet(planetGenerator.generate());
		}
                
		return system;

	}
}