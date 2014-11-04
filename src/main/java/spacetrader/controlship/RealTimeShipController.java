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
import spacetrader.game_model.Player;
import spacetrader.game_model.Position;
import spacetrader.game_model.Ship;
import spacetrader.game_model.StarSystem;
import spacetrader.game_model.StarType;

/**
 *
 * @author Tyler Allen <tallen40@gatech.edu>
 */
class RealTimeShipController {
    private Player player;
    private StarSystem system;
    public RealTimeShipController(){
        system=new StarSystem("Test", new Position(0,0), StarType.GIANT);
        system.addPlanet(new Planet("TEST1",new Position (-1,0)));
        system.addPlanet(new Planet("TEST2",new Position (1,0)));
        system.addPlanet(new Planet("TEST3",new Position (0,-1)));
        system.addPlanet(new Planet("TEST4",new Position (0,1)));
        player=new Player("TEST",Faction.Test1);
        Ship s=new Ship("TEST SHIP");
        s.setPosition(Position.origin);
        player.setShip(s);
    }
    public StarSystem getSystem(){
        return system;
    }
    public Player getPlayer(){
        return player;
    }
    public void movePlayer(float x,float y){
        player.getShip().getPosition().add(new Position(x,y));
    }
}
