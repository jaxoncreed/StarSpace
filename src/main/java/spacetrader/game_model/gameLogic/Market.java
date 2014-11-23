/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacetrader.game_model.gameLogic;

import spacetrader.game_model.system.Planet;
import java.io.Serializable;
import spacetrader.game_model.interactable.InteractableObject;

/**
 *
 * @author fsanchez
 */
public class Market implements Serializable {

    private Inventory cargo;
    private Planet planet;
    
    public Market(Inventory cargo, Planet planet) {
        this.cargo = cargo;
        this.planet = planet;
    }
    
    public boolean addItem(Item i) {
        return cargo.add(i);
    }
    
    public boolean removeItem(Item i) {
        return cargo.remove(i);
    }

    public void dumpCargo() {
        cargo.clearInventory();
    }
    
    public Inventory getItems() {
        return cargo;
    }
    public Planet getPlanet() {
        return planet;
    }
    public Inventory getCargo() {
        return cargo;
    }

    public void setCargo(Inventory cargo) {
        this.cargo = cargo;
    }
}
