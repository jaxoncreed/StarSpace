package spacetrader.galaxygenerators;

import spacetrader.game_model.Galaxy;
import spacetrader.game_model.StarSystem;
import java.util.List;
/**
 * @author Michael Lane <mlane@gatech.edu>
 */
public class JumpPointsGenerator {

	private Galaxy galaxy;
	private double constant;
	private double threshold;
	public boolean connectBlackHoles; 

	public JumpPointsGenerator(
		Galaxy galaxy,
		double constant,
		double threshold,
		boolean connectBlackHoles) {

		setGalaxy(galaxy);
		setConstant(constant);
		setThreshold(threshold);
		this.connectBlackHoles = connectBlackHoles;
	}

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
						&& system1.starType == StarType.BLACK_HOLE)
						&& system2.starType == StarType.BLACK_HOLE) {
					
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

	public final void setConstant(double constant) {

		if (constant <= 0) {
			throw new IllegalArgumentException("constant = " + constant + " given; constant must be positive");
		}
		this.constant = constant;
	}

	public final void setThreshold(double threshold) {

		if (threshold <= 0) {
			throw new IllegalArgumentException("threshold = " + threshold + " given; threshold must be positive");
		}
		this.threshold = threshold;
	}
}