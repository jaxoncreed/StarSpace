package spacetrader.game_model.gameLogic;

/**
 *
 * @author Michael Lane <mlane@gatech.edu>
 */
public class OreItem extends Item {
    
    public OreItem(String name, double basePrice) {
        super(name, basePrice);
    }
    
    public OreItem(Type type) {
        super(type.toString(), type.getBasePrice());
    }
    
    public static enum Type {
        
        COPPER(1.00),
        IRON(1.00), 
        CARBON(1.00),
        GOLD(5.00), 
        DIAMOND(10.00),
        STEEL(2.50);
        
        private final double basePrice;
        
        Type(double basePrice) {
            this.basePrice = basePrice;
        }        
        
        public double getBasePrice() {
            return this.basePrice;
        }        
    }
}
