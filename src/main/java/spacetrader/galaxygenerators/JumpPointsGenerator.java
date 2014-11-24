package spacetrader.galaxygenerators;

import java.util.ArrayList;
import spacetrader.game_model.Galaxy;
import spacetrader.game_model.StarSystem;
import spacetrader.game_model.StarType;
import java.util.List;
import spacetrader.game_model.GameModel;
import spacetrader.game_model.JumpPoint;
import spacetrader.game_model.Planet;
import spacetrader.game_model.Position;
import spacetrader.game_model.Ship;
import spacetrader.game_model.positioncontainer.Bounds;
import spacetrader.shared.Util;
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
     
    private List<JumpPoint> jumpPoints;
    private GameModel gameModel;

	public JumpPointsGenerator(GameModel gameModel) {
        this.gameModel = gameModel;
        jumpPoints = new ArrayList();
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
						&& system1.getStarType() == StarType.BLACK_HOLE)
						&& system2.getStarType() == StarType.BLACK_HOLE) {
					
                    Bounds bounds1 = system1.getBounds();
                    Bounds bounds2 = system2.getBounds();
                    Position pos1 = null;
                    Position pos2 = null;
                    boolean tryAgain = true;
                    double shipInteraction = gameModel.getPlayer().getShip().getInteractionRange();
                    while (tryAgain) {
                        double x1 = Util.sampleFromUniformReal(bounds1.getMinX(), bounds1.getMaxX());
                        double y1 = Util.sampleFromUniformReal(bounds1.getMinY(), bounds1.getMaxY());
                        pos1 = new Position(x1, y1);
                        boolean broken = false;
                        for (Planet p : system1.getPlanets()) {
                            double dist = pos1.distTo(p.getPosition());
                            if (dist < p.getInteractionRange() + shipInteraction + JumpPoint.INTERACTION_RANGE) {
                                broken = true;
                                break;
                            }
                        }
                        if (!broken) {
                            tryAgain = false;
                        }
                    }
                    tryAgain = true;
                    while (tryAgain) {
                        double x2 = Util.sampleFromUniformReal(bounds2.getMinX(), bounds2.getMaxX());
                        double y2 = Util.sampleFromUniformReal(bounds2.getMinY(), bounds2.getMaxY());
                        pos2 = new Position(x2, y2);
                        boolean broken = false;
                        for (Planet p : system2.getPlanets()) {
                            double dist = pos2.distTo(p.getPosition());
                            if (dist < p.getInteractionRange() + shipInteraction + JumpPoint.INTERACTION_RANGE) {
                                broken = true;
                                break;
                            }
                        }
                        if (!broken) {
                            tryAgain = false;
                        }
                    }
					jumpPoints.addAll(system1.addJumpPoint(system2, pos1, pos2));
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
    
    public List<JumpPoint> getJumpPointList() {
        return jumpPoints;
    }

    public final void setConnectBlackHoles(Boolean connect){
        this.connectBlackHoles = true;
    }
        
}