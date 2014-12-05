package spacetrader.galaxygenerators;

import spacetrader.game_model.gameLogic.Market;
import spacetrader.game_model.system.Planet;

/**
 *
 * @author Michael Lane <mlane@gatech.edu>
 */
public abstract class MarketGenerator {
    
    protected Planet planet;
    
    public abstract Market generate();
    public void setPlanet(Planet planet) {
        this.planet = planet;
    }    
}
