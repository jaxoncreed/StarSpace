/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacetrader.game_model;
import java.util.List;
import java.util.ArrayList;
/**
 *
 * @author fsanchez
 */
public class Inventory {
    private List<Item> items;
    private int maxSize;
    
    public Inventory(List<Item> inventory, int maxSize) {
        this.items = inventory;
        this.maxSize = maxSize;
    }
    public Inventory(int maxSize) {
        this(new ArrayList<>(), maxSize);
    }
    public Inventory() {
        this(new ArrayList<>(), 10);
    }
    public boolean add(Item item) {
        if (items.size() < maxSize) {
            items.add(item);
            return true;
        }
        return false;
    }

    public boolean remove(Item item) {
        return items.remove(item);
    }
    
    public Item[] getItems() {
        return (Item[]) items.toArray();
    }
    public void setMaxSize(int size) {
        maxSize = size;

    }
    public void clearInventory() {
        items = new ArrayList<>();
    }
}