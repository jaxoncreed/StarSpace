/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacetrader.game_model;

/**
 *
 * @author Tyler Allen <tallen40@gatech.edu>
 */
public class Item implements Tradeable {
    private final String name;
    private final double basePrice;
    private int howMany;
    
    /**
     * Constructor for Item
     * @param name
     * @param basePrice
     */
    public Item(String name, double basePrice, int howMany) {
        this.name = name;
        this.basePrice = basePrice;
    }
    
    public int getHowMany() {
        return howMany;
    }
    
    public void setHowMany(int howMany) {
        this.howMany = howMany;
    }
    
    public String getName() {
        return name;
    }
    
    @Override
    public double getBasePrice() {
        return basePrice;
    }
}
