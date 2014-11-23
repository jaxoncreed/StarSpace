package spacetrader.galaxygenerators;

import spacetrader.game_model.system.Planet;
import spacetrader.game_model.gameLogic.Position;
import spacetrader.game_model.system.StarSystem;
//import spacetrader.Util;

/**
 * @author Michael Lane
 */
public class SimplePlanetGenerator extends PlanetGenerator {
    
	public Planet generate() {
		return new Planet(name, pos);
	}
}
