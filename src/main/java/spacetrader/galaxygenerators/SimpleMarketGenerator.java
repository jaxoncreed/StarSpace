package spacetrader.galaxygenerators;

import java.util.ArrayList;
import java.util.List;
import spacetrader.game_model.gameLogic.Item;
import spacetrader.game_model.gameLogic.LuxuryItem;
import spacetrader.game_model.gameLogic.WeaponItem;
import spacetrader.game_model.gameLogic.NecessityItem;
import spacetrader.game_model.gameLogic.OreItem;
import spacetrader.game_model.gameLogic.Market;
import spacetrader.game_model.gameLogic.Inventory;
import spacetrader.game_model.system.Planet;
import spacetrader.shared.Util;

/**
 *
 * @author Michael Lane <mlane@gatech.edu>
 */
public class SimpleMarketGenerator extends MarketGenerator {
    
    private List<Item> items;
    private double numItemsSD;
    private double numItemsMean;
    
    
    public SimpleMarketGenerator() {
        // kludged this in because idgaf
        items = new ArrayList();
        items.add(new LuxuryItem(LuxuryItem.Type.PS10));
        items.add(new LuxuryItem(LuxuryItem.Type.KUMQUAT));
        items.add(new WeaponItem(WeaponItem.Type.RAILGUN));
        items.add(new NecessityItem(NecessityItem.Type.GRAIN));
        items.add(new NecessityItem(NecessityItem.Type.OXYGEN));
        items.add(new NecessityItem(NecessityItem.Type.WATER));
        items.add(new OreItem(OreItem.Type.CARBON));
        items.add(new OreItem(OreItem.Type.COPPER));
        items.add(new OreItem(OreItem.Type.DIAMOND));
        items.add(new OreItem(OreItem.Type.GOLD));
        items.add(new OreItem(OreItem.Type.IRON));
        items.add(new OreItem(OreItem.Type.STEEL));
    }
    
    public Market generate() {
        
        Inventory inventory = new Inventory(Integer.MAX_VALUE);
        
        int numItems = 0;
        boolean tryAgain = true;
        while (tryAgain) {
            double numItemsSample = Util.sampleFromNormal(numItemsSD, numItemsMean);
            if (numItemsSample >= 1) {
                numItems = (int) numItemsSample;
                tryAgain = false;
            } 
        }
        
        for (int i = 0; i < numItems; i++) {
            inventory.add(new Item(this.pickItem()));
        }
        
        return new Market(inventory, planet);
    }
    
    public void setNumItemsSD(Double numItemsMean) {
        this.numItemsMean = numItemsMean;
    }   
    
    public void setNumItemsMean(Double numItemsSD) {
        this.numItemsSD = numItemsSD;
    }
    
    private Item pickItem() {
        int sample = (int) Util.sampleFromUniformReal(0, items.size());
        sample = (sample < items.size()) ? sample : sample - 1;
        return items.get(sample);
    }
    
    
}
