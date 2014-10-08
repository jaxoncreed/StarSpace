package spacetrader.galaxygenerators;


//import spacetrader.Util;
import spacetrader.game_model.Galaxy;
import spacetrader.game_model.StarSystem;
import java.util.List;
import java.util.ArrayList;
import spacetrader.game_model.Position;
import spacetrader.game_model.StarType;
import org.apache.commons.math3.distribution.NormalDistribution;

/**
 * @author Michael Lane
 */
public class SimpleStarSystemGenerator extends StarSystemGenerator{

	private List<Double> distsFromStar;

	/**
	 * A StarSystemGenerator without much generative fluff
	 * 
	 * @param name The system's name
	 * @param pos The system's position
	 * @param planetNumMean The expected number of planets in the system
	 * @param planetNumSD The sd to the expected number of planets in the system
	 * @param planetSepMean The expected seperation between the orbits of planets
	 * @param planetSepSD The sd to the expected seperation bwteen the orbits of planets
	 * @param plutoDist The upper bound of the distance of the furthest star from the sun
	 * @param galaxy This system's galaxy
	 */
	public SimpleStarSystemGenerator(
		String name, 
		Position pos,
		double planetNumMean, 
		double planetNumSD,
		double planetSepMean,
		double planetSepSD,
		double plutoDist,
		Galaxy galaxy) {

		super(name, pos, planetNumMean, planetNumSD,
		      (Util.sampleFromBinomial(1, 0.5) > 0) ? StarType.BIG : StarType.LITTLE,
		      galaxy);

		// generates the total distances from the star of the planets,
		// so that the furthest distance does not excede plutoDist
		distsFromStar = new ArrayList<Double>();
		NormalDistribution distr = new NormalDistribution(planetSepMean, planetSepSD);
		double total = 0;
		boolean tryAgain = true;
		while (tryAgain) {
			for (int i = 0; i < numPlanets; i++) {
				total += distr.sample();
				distsFromStar.add(total);
			}
			if (total <= plutoDist) {
				tryAgain = false;
			} else {
				distsFromStar = new ArrayList<Double>();
				total = 0;
			}
		}
	}		

	public StarSystem generate() {

		StarSystem system = new StarSystem(name, pos, starType);
		
		for (int i = 0; i < numPlanets; i++) {

			double dist = distsFromStar.get(i);

			// randomly generate a position 
			double theta = Util.sampleFromUniformReal(0, Math.PI);
			double x = dist * Math.cos(theta);
			double y = dist * Math.sin(theta);
            Position pos = new Position(x, y);
            pos = pos.rotate(theta);
            
			SimplePlanetGenerator planetGen = 
				new SimplePlanetGenerator("Planet "+i, pos, system);
			system.addPlanet(planetGen.generate());
		}
                
		return system;

	}
}