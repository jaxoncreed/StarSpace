package spacetrader.galaxygenerators;

import spacetrader.game_model.Planet;
import spacetrader.game_model.Position;
import spacetrader.game_model.StarSystem;
//import spacetrader.Util;

/**
 * @author Michael Lane
 */
public class SimplePlanetGenerator extends PlanetGenerator {
    
	public Planet generate() {
		return new Planet(name, pos);
	}
}
