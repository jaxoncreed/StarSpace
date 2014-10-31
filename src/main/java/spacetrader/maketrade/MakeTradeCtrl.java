/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacetrader.maketrade;

import java.util.List;
import spacetrader.MainCtrl;
import spacetrader.ViewCtrl;
import spacetrader.Window;
import spacetrader.game_model.*;
import spacetrader.maketrade.MakeTradeView;

/**
 *
 * @author Jackson Morgan
 */
public class MakeTradeCtrl extends ViewCtrl {
    MakeTradeView view;
    MainCtrl mainCtrl;
    Inventory store;
    Player player;
    
    public MakeTradeCtrl(MainCtrl aParent, Window window, Player player) {
        super(aParent, window);
        view = new MakeTradeView(window, this);
        mainCtrl = aParent;
        this.player = player;
    }

    public boolean sell(Item item,int amount) {
        Inventory cargo = player.getShip().getCargo();
        cargo.remove(item);
        store.add(item);
        int profit = (int)(item.getBasePrice());
        player.setWoolongs(player.getWoolongs() + profit);
        return true;
    }
    
    public boolean buy(Item item,int amount) {
        Inventory cargo = player.getShip().getCargo();
        int cost = (int)(item.getBasePrice());
        if (player.getWoolongs() < cost) {
            return false;
        }
        store.remove(item);
        cargo.add(item);
        player.setWoolongs(player.getWoolongs() - cost);
        return true;
    }
    
    public void renderMarket(Inventory store) {
        this.store = store;
        startView();
    }
    
    @Override
    public void startView() {
        view.renderMakeTrade(player.getShip().getCargo().getItems(),
                             player.getWoolongs(), store.getItems());
    }

    @Override
    public void stopView() {
        view.removeMakeTrade();
    }
    public void shipControl() {
        stopView();
        mainCtrl.controlShip();
    }
    public List<Item> getItemsStore(){
        return store.getItems();
    }
   public List<Item> getItemsPlayer(){
        return player.getShip().getCargo().getItems();
    }
   public int getStoreItemAmount(Item i){
       return store.getAmount(i);
   }
   public int getPlayerItemAmount(Item i){
       return player.getShip().getCargo().getAmount(i);
   }
   public int getPlayerMoney(){
       return player.getWoolongs();
   }
}
