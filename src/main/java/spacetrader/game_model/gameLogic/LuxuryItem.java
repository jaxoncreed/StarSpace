package spacetrader.game_model.gameLogic;

/**
 *
 * @author Michael Lane <mlane@gatech.edu>
 */
public class LuxuryItem extends Item {
    
    public LuxuryItem(String name, double basePrice) {
        super(name, basePrice);
    }
    
    public LuxuryItem(Type type) {
        super(type.toString(), type.getBasePrice());
    }
    
    public static enum Type {
        
        PS10(400.00),
        KUMQUAT(1000.00);
        
        private final double basePrice;
        
        Type(double basePrice) {
            this.basePrice = basePrice;
        }        
        
        public double getBasePrice() {
            return this.basePrice;
        }        
    }
}
