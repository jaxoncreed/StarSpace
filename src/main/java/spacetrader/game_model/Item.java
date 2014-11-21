/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacetrader.game_model;

import java.io.Serializable;
import java.util.Objects;
import static spacetrader.game_model.ItemClass.*;

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
    
    private static enum ItemType {
        
        // ores
        COPPER(ORE, 1.00),
        IRON(ORE, 1.00), 
        CARBON(ORE, 1.00),
        GOLD(ORE, 5.00), 
        DIAMOND(ORE, 10.00),
        STEEL(ORE, 2.50),
        
        
        // luxury goods
        PS10(LUXURY, 400.00),
        KUMQUAT(LUXURY, 1000.00),
        
        // weapons
        RAILGUN(WEAPON, 300.00),
        
        // necessitities
        GRAIN(NECESSITY, 0.50),
        WATER(NECESSITY, 0.50),
        OXYGEN(NECESSITY, 0.50);
        
        private final ItemClass itemClass;
        private final double basePrice;
        
        private ItemType(ItemClass itemClass, double basePrice) {
            this.itemClass = itemClass;
            this.basePrice = basePrice;
        }
        
        public double getBasePrice() {
            return basePrice;
        }
        
        public ItemClass getItemClass() {
            return itemClass;
        }

    }
}
