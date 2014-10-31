/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacetrader.buyship;

import java.util.List;
import spacetrader.MainCtrl;
import spacetrader.ViewCtrl;
import spacetrader.Window;
import spacetrader.game_model.*;
import spacetrader.maketrade.MakeTradeView;


/**
 *
 * @author fsanchez
 */
public class BuyShipCtrl extends ViewCtrl {
    BuyShipView view;
    MainCtrl mainCtrl;
    Inventory shipyard;
    Player player;
    
    public BuyShipCtrl(MainCtrl aParent, Window window, Player player) {
        super(aParent, window);
        view = new BuyShipView(window, this);
        mainCtrl = aParent;
        this.player = player;
    }
    
    @Override
    public void startView() {
        view.renderBuyShip(player.getWoolongs());
    }
    @Override
    public void stopView() {
        view.removeBuyShip();
    }
    public void shipControl() {
        stopView();
        mainCtrl.controlShip();
    }
    //String name, double basePrice, int firePower, int health,
         //   Inventory cargo
    public void buy1() {
        if (player.getWoolongs() >= 500) {
            Inventory cargo = player.getShip().getCargo();
            System.out.println("Old ship: " + player.getShip());
            Ship s = new Ship("Arwing", 500, 20, 15, cargo);
            player.setShip(s);
            System.out.println("New ship: " + player.getShip());
            player.setWoolongs(player.getWoolongs() - 500);
            view.updateMoneyLabel();
            System.out.println("Purchase Complete");
        } else {
            System.out.println("Not enough woolongs");
        }
        
    }
    public void buy2() {
        if (player.getWoolongs() >= 550) {
            Inventory cargo = player.getShip().getCargo();
            Ship s = new Ship("Saturn V", 550, 10, 40, cargo);
            System.out.println("Old ship: " + player.getShip());
            player.setShip(s);
            System.out.println("New ship: " + player.getShip());
            player.setWoolongs(player.getWoolongs() - 550);
            System.out.println("Purchase Complete");
            view.updateMoneyLabel();
        } else {
            System.out.println("Not enough woolongs");
        }
    }
    public void buy3() {
        if (player.getWoolongs() >= 1500) {
            Inventory cargo = player.getShip().getCargo();
            System.out.println("Old ship: " + player.getShip());
            Ship s = new Ship("Millenium Falcon", 1500, 60, 40, cargo);
            player.setShip(s);
            System.out.println("New ship: " + player.getShip());
            player.setWoolongs(player.getWoolongs() - 1500);
            System.out.println("Purchase Complete");
            view.updateMoneyLabel();
        } else {
            System.out.println("Not enough woolongs");
        }
    }
    public void buy4() {
        if (player.getWoolongs() >= 2500) {
            Inventory cargo = player.getShip().getCargo();
            System.out.println("Old ship: " + player.getShip());
            Ship s = new Ship("The Bebop", 2500, 25, 100, cargo);
            player.setShip(s);
            System.out.println("New ship: " + player.getShip());
            player.setWoolongs(player.getWoolongs() - 2500);
            System.out.println("Purchase Complete");
            view.updateMoneyLabel();
        } else {
            System.out.println("Not enough woolongs");
        }
    }
    public int getPlayerWoolongs() {
        return player.getWoolongs();
    }
    
}
