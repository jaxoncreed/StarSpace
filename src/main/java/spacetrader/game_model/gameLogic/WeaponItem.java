package spacetrader.game_model.gameLogic;

/**
 *
 * @author Michael Lane <mlane@gatech.edu>
 */
public class WeaponItem extends Item {
    
    public WeaponItem(String name, double basePrice) {
        super(name, basePrice);
    }
    
    public WeaponItem(Type type) {
        super(type.toString(), type.getBasePrice());
    }
    
    public static enum Type {
        
        RAILGUN(300.00);
        
        private final double basePrice;
        
        Type(double basePrice) {
            this.basePrice = basePrice;
        }        
        
        public double getBasePrice() {
            return this.basePrice;
        }        
    }
}
