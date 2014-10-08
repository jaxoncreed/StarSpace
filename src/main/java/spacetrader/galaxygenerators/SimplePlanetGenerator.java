package spacetrader.galaxygenerators;

import spacetrader.game_model.Planet;
import spacetrader.game_model.Position;
import spacetrader.game_model.StarSystem;
//import spacetrader.Util;

/**
 * @author Michael Lane
 */
public class SimplePlanetGenerator extends PlanetGenerator {

    public SimplePlanetGenerator(
    	String name, 
    	Position pos, 
    	StarSystem system) {
    	
    	super(name, pos, system);
    }
    
	public Planet generate() {
		return new Planet(name, pos);
	}
}
