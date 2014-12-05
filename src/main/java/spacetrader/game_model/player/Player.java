/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacetrader.game_model.player;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import spacetrader.game_model.Faction;
import spacetrader.game_model.gameLogic.Position;
import spacetrader.game_model.Ship;
import spacetrader.game_model.ShipDef;
import spacetrader.game_model.system.JumpPoint;
import spacetrader.game_model.system.StarSystem;

/**
 *
 * @author fsanchez
 */
public class Player implements Serializable {
    private final String name;
    private final Faction faction;
    private int woolongs; //currency
    private Ship ship;
    private Skillset skillSet;
    private List<JumpPoint> jumppoints;
    public Player(String name, Faction faction) {
        this.name = name;
        this.faction = faction;
        woolongs = 10000;
        ShipDef shipDef = new ShipDef();
        shipDef.name = "Fart Duster";
        ship = new Ship(shipDef);
        jumppoints=new ArrayList();
    }
    
    public void setWoolongs(int i) {
        woolongs = i;
    }
    
    public Ship getShip() {
        return this.ship;
    }
    
    public void setShip(Ship s) {
        this.ship = s;
    }

    public int getWoolongs() {
        return woolongs;
    }

    public String getName() {
        return name;
    }

    public Faction getFaction() {
        return faction;
    }

    public Position getPosition(){
        return ship.getPosition();
    }

    public StarSystem getSystem() {
        return ship.getSystem();
    }

    public void setSystem(StarSystem system) {
        ship.setSystem(system);
    }
    public void setSkillset(Skillset skill){
        skillSet=skill;
    }

    public void setJumpPath(List<JumpPoint> jp) {
       jumppoints=jp;
    }
    public List<JumpPoint> getJumpPath(){
        return jumppoints;
    }
}