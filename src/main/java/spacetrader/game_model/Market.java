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
public class Market {

    private Inventory cargo;
    private final Planet planet;
    
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
