package spacetrader.game_model;

import java.io.Serializable;

/**
 * Ship model!
 */
public class Ship implements Tradeable, Serializable {

    private double basePrice;
    private final String name;
    private int firePower;
    private final Inventory cargo;
    private PhysicsDescriptor physicsDescriptor;
    private Position position;
    private int health;
    private int maxHealth;
    private StarSystem system;
    
    public Ship(String name) {
        this.name = name;
        basePrice = 1000;
        firePower = 10;
        cargo = new Inventory();
        this.position = new Position(0.0f, 0.0f);
    }
    public Ship(String name, double basePrice, int firePower, int cargoSize) {
        this.name = name;
        this.firePower = firePower;
        this.basePrice = basePrice;
        cargo = new Inventory(cargoSize);
        this.position = new Position(0.0f, 0.0f);
    }
    
    public void setSystem(StarSystem system) {
        this.system = system;
    }

    public StarSystem getSystem() {
        return system;
    }

    public boolean addItem(Item i) {
        return cargo.add(i);
    }
    
    public boolean removeItem(Item i) {
        return cargo.remove(i);
    }
    
    @Override
    public double getBasePrice() {
        return basePrice;
    }
    
    public String getName() {
        return name;
    }
    
    public Inventory getCargo() {
        return cargo;
    }
    public int getFirePower() {
        return firePower;
    }

    public void setCargoSize(int size) {
        cargo.setMaxSize(size);
    }
    
    public void setFirePower(int power) {
        firePower = power;
    }
    
    public boolean addToCargo(Item i) {
        return cargo.add(i);
    }
    public void setBasePrice(double value) {
        basePrice = value;
    }
    public void setPosition(Position pos){
        position=pos;
    }
    public Position getPosition(){
        return position;
    }

    
    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }
    
    public void setHealth(int health) {
        this.health = health; 
    }
    
    public void incrementHealth(int delta) {
        this.health += delta;
    }
}