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
                p.getMarket().getCargo().setMaxSize(100);
                p.getMarket().addItem(new Item("Kool-Aid", 22.23));
                p.getMarket().addItem(new Item("Kool-Aid", 22.23));
                p.getMarket().addItem(new Item("Kool-Aid", 22.23));
                p.getMarket().addItem(new Item("Kool-Aid", 22.23));
                p.getMarket().addItem(new Item("Nissan Leaf", 21510.00));
                p.getMarket().addItem(new Item("Nissan Leaf", 21510.00));
                p.getMarket().addItem(new Item("Domain Name", 56.99));
                p.getMarket().addItem(new Item("Christopher Nolan's Interstellar", -5.00));
                p.getMarket().addItem(new Item("Christopher Nolan's Interstellar", -5.00));
                p.getMarket().addItem(new Item("Christopher Nolan's Interstellar", -5.00));
                p.getMarket().addItem(new Item("Stanley Kubrick's 2001 A Space Odyssey", 496218.99));
                p.getMarket().addItem(new Item("Day with Kim Jong Un!", 98.00));
                p.getMarket().addItem(new Item("Some Sleep", 36.42));
                p.getMarket().addItem(new Item("Some more Sleep", 45.96));
                p.getMarket().addItem(new Item("A Hug", 2.00));
                
		return p;
	}
}
