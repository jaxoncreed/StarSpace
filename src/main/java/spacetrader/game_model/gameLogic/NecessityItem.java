package spacetrader.game_model.gameLogic;

/**
 *
 * @author Michael Lane <mlane@gatech.edu>
 */
public class NecessityItem extends Item {
    
    public NecessityItem(String name, double basePrice) {
        super(name, basePrice);
    }
    
    public NecessityItem(Type type) {
        super(type.toString(), type.getBasePrice());
    }
    
    public static enum Type {
        
        GRAIN(0.50),
        WATER(0.50),
        OXYGEN(0.50);
        
        private final double basePrice;
        
        Type(double basePrice) {
            this.basePrice = basePrice;
        }        
        
        public double getBasePrice() {
            return this.basePrice;
        }        
    }
}
