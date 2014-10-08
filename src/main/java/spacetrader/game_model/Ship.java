package spacetrader.game_model;

/**
 * Ship model!
 */
public class Ship implements Tradeable {

    private double basePrice;
    private final String name;
    private int firePower;
    private final Inventory cargo;
    
    public Ship(String name) {
        this.name = name;
        basePrice = 1000;
        firePower = 10;
        cargo = new Inventory();
    }
    public Ship(String name, double basePrice, int firePower, int cargoSize) {
        this.name = name;
        this.firePower = firePower;
        this.basePrice = basePrice;
        cargo = new Inventory(cargoSize);
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
}