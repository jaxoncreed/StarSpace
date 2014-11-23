package spacetrader.galaxygenerators;

import spacetrader.game_model.system.Galaxy;
import spacetrader.game_model.system.StarSystem;
import spacetrader.game_model.system.StarType;
import java.util.List;
/**
 * A JumpPoint is placed between two StarSystems if they are sufficiently "attracted"
 * to one another. This attraction is a function of the two star masses, of the
 * distance between the StarSystems, and of a given "constant."
 * 
 * @author Michael Lane <mlane@gatech.edu>
 */
public class JumpPointsGenerator {

	private Galaxy galaxy;
    /** The constant used in the calculation of attraction; see the class javadoc. */
	private double constant;
    /** The minimum level of attraction required for two StarSystems to be connected
     * by a JumpPoint; see the class javadoc.
     */
	private double threshold;
    /** true iff black holes should always be connected; otherwise, black holes 
     * are treated the same as any other StarType.
     */
	public boolean connectBlackHoles; 

	private double calculateAttraction(StarSystem system1, StarSystem system2) {
		
		double dist = system1.getPosition().distTo(system2.getPosition());
		double starMass1 = system1.getStarMass();
		double starMass2 = system2.getStarMass();

		return constant * (starMass1 + starMass2) / 
		     //----------------------------------
		             Math.pow(dist, 1.5);
	}

	public Galaxy generate() {
		
		// #todo terribly inefficient
		List<StarSystem> systems = galaxy.getSystems();
		int numSystems = systems.size();
		for (int i = 0; i < numSystems; i++) {
			StarSystem system1 = systems.get(i);
			for (int j = i + 1; j < numSystems; j++) {
				StarSystem system2 = systems.get(j);
				double attraction = calculateAttraction(system1, system2);
				if (attraction >= threshold
					|| (connectBlackHoles 
						&& system1.getStarType() == StarType.BLACK_HOLE)
						&& system2.getStarType() == StarType.BLACK_HOLE) {
					
					system1.addJumpPoint(
						system1.getPosition(), 
						system2, 
						system2.getPosition());
				}
			}
		}

		galaxy.replaceSystems(systems);
		return galaxy;
	}

	public final void setGalaxy(Galaxy galaxy) {
		
		if (galaxy == null) {
			throw new IllegalArgumentException("galaxy must be non-null");
		}
		this.galaxy = galaxy;
	}

	public final void setConstant(Double constant) {

		if (constant <= 0) {
			throw new IllegalArgumentException("constant = " + constant + " given; constant must be positive");
		}
		this.constant = constant;
	}

	public final void setThreshold(Double threshold) {

		if (threshold <= 0) {
			throw new IllegalArgumentException("threshold = " + threshold + " given; threshold must be positive");
		}
		this.threshold = threshold;
	}
    
    public final void setConnectBlackHoles(Boolean connect){
        this.connectBlackHoles = true;
    }
        
}