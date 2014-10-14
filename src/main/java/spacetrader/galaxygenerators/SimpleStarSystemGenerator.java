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

	private List<Double> _distsFromStar;

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
		      (Util.sampleFromBinomial(1, 0.5) > 0) ? StarType.GIANT : StarType.DWARF,
		      galaxy);

		// generates the total distances from the star of the planets,
		// so that the furthest distance does not excede plutoDist
		_distsFromStar = new ArrayList<Double>();
		NormalDistribution distr = new NormalDistribution(planetSepMean, planetSepSD);
		double total = 0;
		boolean tryAgain = true;
		while (tryAgain) {
			for (int i = 0; i < numPlanets; i++) {
				total += distr.sample();
				_distsFromStar.add(total);
			}

			// if the furthest planet is wi`thin plutoDist from the center,
			// and if the orbit of the furthest planet does not exist the bounds of the galaxy
			if (total <= plutoDist
				&& Math.abs(pos.x) + total < galaxy.getWidth()/2
				&& Math.abs(pos.x) + total < galaxy.getWidth()/2) {
				tryAgain = false;
			} else {
				_distsFromStar = new ArrayList<Double>();
				total = 0;
			}
		}
	}		

	/**
	 * @return A randomly generated StarSystem with the current specifications
	 */
	public StarSystem generate() {

		StarSystem system = new StarSystem(name, pos, starType);
		
		for (int i = 0; i < numPlanets; i++) {

			double dist = _distsFromStar.get(i);

			// randomly generate a position, given a distance
			double theta = Util.sampleFromUniformReal(0, Math.PI);
			double x = dist * Math.cos(theta);
			double y = dist * Math.sin(theta);
            Position planetPos = new Position(pos.x + x, pos.y + y);
            planetPos = planetPos.rotate(theta);
            
			SimplePlanetGenerator planetGen = 
				new SimplePlanetGenerator("Planet "+i, planetPos, system);
			system.addPlanet(planetGen.generate());
		}
                
		return system;

	}
}