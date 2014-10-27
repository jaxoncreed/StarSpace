/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacetrader.controlship;

import spacetrader.galaxygenerators.SimpleStarSystemGenerator;
import spacetrader.game_model.Faction;
import spacetrader.game_model.Galaxy;
import spacetrader.game_model.Planet;
import spacetrader.game_model.Position;
import spacetrader.game_model.StarSystem;
import spacetrader.game_model.StarType;

/**
 *
 * @author Tyler Allen <tallen40@gatech.edu>
 */
class RealTimeShipController {
    private StarSystem system;
    public RealTimeShipController(){
        system=new StarSystem("Test", new Position(0,0), StarType.GIANT);
        system.addPlanet(new Planet("TEST1",new Position (-1,0)));
        system.addPlanet(new Planet("TEST2",new Position (1,0)));
        system.addPlanet(new Planet("TEST3",new Position (0,-1)));
        system.addPlanet(new Planet("TEST4",new Position (0,1)));


    }
    public StarSystem getSystem(){
        return system;
    }
}
