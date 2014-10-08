/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacetrader.maketrade;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import spacetrader.MainCtrl;
import spacetrader.ViewCtrl;
import spacetrader.Window;
import spacetrader.createcharacter.CreateCharacterView;
import spacetrader.menu.MenuView;
import spacetrader.game_model.*;

/**
 *
 * @author Jackson Morgan
 */
public class MakeTradeCtrl extends ViewCtrl {
    MakeTradeView view;
    MainCtrl mainCtrl;
    Inventory store;
    Player player
    
    public MakeTradeCtrl(MainCtrl aParent, Window window, Player player) {
        super(aParent, window);
        view = new MakeTradeView(window, this);
        mainCtrl = aParent;
        this.player = player;
    }

    public boolean sell(Inventory store, Item item) {
        Inventory cargo = player.getShip().getCargo();
        cargo.remove(item);
        store.add(item);
        int profit = (int)(item.getBasePrice() * (double)item.getHowMany());
        player.setWoolongs(player.getWoolongs() + profit);
        return true;
    }
    
    public boolean buy(Inventory store, Item item) {
        Inventory cargo = player.getShip().getCargo();
        int cost = (int)(item.getBasePrice() * (double)item.getHowMany());
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
        view.renderMakeTrade(player.getShip().getCargo().getItems(),
                             player.getWoolongs(), store.getItems());
    }

    @Override
    public void stopView() {
        view.removeMakeTrade();
    }
    
}
