/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacetrader.maketrade;

import spacetrader.game_model.gameLogic.Inventory;
import spacetrader.game_model.gameLogic.Item;
import spacetrader.game_model.player.Player;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import spacetrader.MainCtrl;
import spacetrader.ViewCtrl;
import spacetrader.Window.Window;
import spacetrader.game_model.*;
import spacetrader.maketrade.MakeTradeView;
import spacetrader.game_model.GameModel;

import javafx.stage.Stage;
import spacetrader.CtrlViewTypes;
import spacetrader.ViewCtrlFactory;
import spacetrader.Window.JavaFXWindow;
import spacetrader.game_model.gameLogic.Market;

/**
 *
 * @author Jackson Morgan
 */
public class MakeTradeCtrl extends ViewCtrl {
    MainCtrl mainCtrl;
    Inventory store;
    Player player;
    Market market;
    public MakeTradeCtrl(MainCtrl aParent, Window window,Market m) {
        super(aParent, window);
        view = ViewCtrlFactory.getView(CtrlViewTypes.Trade, window, this);
        mainCtrl = aParent;
        this.player = GameModel.get().getPlayer();
        market=m;
        this.store=market.getItems();
    }

    public boolean sell(Item item,int amount) {
        Inventory cargo = player.getShip().getCargo();
        cargo.remove(item);
        store.add(item);
        int profit = (int)(item.getAdjustedPrice(player.getFaction(), market.getPlanet().getSystem().getFaction()));
        player.setWoolongs(player.getWoolongs() + profit);
        return true;
    }
    
    public boolean buy(Item item,int amount) {
        Inventory cargo = player.getShip().getCargo();
        int cost = (int)(item.getAdjustedPrice(player.getFaction(), market.getPlanet().getSystem().getFaction()));
        if (player.getWoolongs() < cost) {
            return false;
        }
        store.remove(item);
        cargo.add(item);
        player.setWoolongs(player.getWoolongs() - cost);
        return true;
    }    
    @Override
    public void startView() {
        view.render();
    }
    public void returnToControlShip(){
        mainCtrl.switchViews(CtrlViewTypes.ControlShip);
    }

    @Override
    public void stopView() {
        view.hide();
    }
    public void shipControl(){
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
