/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacetrader.game_model.gameLogic;
import spacetrader.game_model.gameLogic.Item;
import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
/**
 *
 * @author fsanchez
 */
public class Inventory implements Serializable {
    private Map<Item,Integer> items;
    private int maxSize;
    private int size;
    
    public Inventory(List<Item> inventory, int maxSize) {
        items=new HashMap<Item,Integer>();
        size=0;
        for(Item i:inventory){
            items.put(i,1);
            size++;
        }
        this.maxSize = maxSize;
    }
    public Inventory(int maxSize) {
        this(new ArrayList(), maxSize);
    }
    public Inventory() {
        this(new ArrayList(), 10);
    }
    public boolean add(Item item) {
        if (size < maxSize) {
            items.put(item,this.getAmount(item)+1);
            size++;
            return true;
        }
        return false;
    }

    public boolean remove(Item item) {
        if(items.get(item)>0){
            items.put(item, this.getAmount(item)-1);
            size--;
            if(getAmount(item)==0){
                items.remove(item);
            }
            return true;
        }
        return false;
    }
    public int getAmount(Item item){
        if(items.containsKey(item))
            return items.get(item);
        return 0;
    }
    
    public List<Item> getItems() {
        return new ArrayList<Item>(items.keySet());
    }
    public void setMaxSize(int size) {
        maxSize = size;
    }
    public void clearInventory() {
        items = new HashMap();
    }
}