/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacetrader.game_model;

import spacetrader.game_model.gameLogic.Item;
import spacetrader.game_model.gameLogic.Market;

/**
 * 
 * @author Michael Lane
 */
public class ItemRequest {
    
    private Market fromMarket;
    private Market toMarket;
    private Item item;
    
    /**
     * @param item The item being requested; must be non-null.
     * @param fromMarket The market the item is being requested from; must be non-null.
     * @param toMarket The requesting market; must be non-null.
     */
    public ItemRequest(Item item, Market fromMarket, Market toMarket) {
        setItem(item);
        setFromMarket(fromMarket);
        setToMarket(toMarket);
    }
    
    /**
     * Trade the item from fromMarket to toMarket.
     * @return true iff trade is successful
     */
    public boolean trade() {
        boolean success;
        success = fromMarket.removeItem(item);
        if (success) {
            return toMarket.addItem(item);
        } 
        return false;
    }
    
    /**
     * @param item must be non-null
     */
    public final void setItem(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("item must be non-null");
        }
        this.item = item;
    }
    
    /**
     * @param fromMarket must be non-null
     */
    public final void setFromMarket(Market fromMarket) {
        if (fromMarket == null) {
            throw new IllegalArgumentException("fromMarket must be non-null");
        }
        this.fromMarket = fromMarket;
    }
    
    /**
     * @param toMarket must be non-null
     */
    public final void setToMarket(Market toMarket) {
        if (toMarket == null) {
            throw new IllegalArgumentException("toMarket must be non-null");
        }
        this.toMarket = toMarket;
    }
}
