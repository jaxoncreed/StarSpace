/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacetrader.game_model;

import java.io.Serializable;

/**
 *
 * @author fsanchez
 */
public class Player implements Serializable {
    private final String name;
    private final Faction faction;
    private int woolongs; //currency
    private Ship ship;
    
    public Player(String name, Faction faction) {
        this.name = name;
        this.faction = faction;
        woolongs = 10000;
        ship = new Ship("Fart Duster");
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

}