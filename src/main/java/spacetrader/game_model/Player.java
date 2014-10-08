/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacetrader.game_model;

/**
 *
 * @author fsanchez
 */
public class Player {
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
        return ship;
    }
    
    public void setShip(Ship s) {
        ship = s;
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
}