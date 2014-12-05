package spacetrader.galaxygenerators;

import spacetrader.game_model.gameLogic.Item;
import spacetrader.game_model.system.Planet;
//import spacetrader.Util;

/**
 * @author Michael Lane
 */
public class SimplePlanetGenerator extends PlanetGenerator {
    
	public Planet generate() {
        //TODO: actual item generation
        Planet p = new Planet(name, pos);
        marketGen.setPlanet(p);
        p.setMarket(marketGen.generate());
        p.setSystem(system);
		return p;
	}
}
