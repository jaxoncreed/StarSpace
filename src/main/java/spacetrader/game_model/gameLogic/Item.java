/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacetrader.game_model.gameLogic;

import java.io.Serializable;
import java.util.Objects;
import spacetrader.game_model.Tradeable;

/**
 *
 * @author Tyler Allen <tallen40@gatech.edu>
 */
public class Item implements Tradeable, Serializable {
    private final String name;
    private final double basePrice;
    
    /**
     * Constructor for Item
     * @param name
     * @param basePrice
     */
    public Item(String name, double basePrice) {
        this.name = name;
        this.basePrice = basePrice;
    }        
    public String getName() {
        return name;
    }
    
    @Override
    public double getBasePrice() {
        return basePrice;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Item other = (Item) obj;
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.name);
        hash = 79 * hash + (int) (Double.doubleToLongBits(this.basePrice) ^ (Double.doubleToLongBits(this.basePrice) >>> 32));
        return hash;
    }
}
