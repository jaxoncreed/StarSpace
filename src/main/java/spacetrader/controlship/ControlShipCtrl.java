/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spacetrader.controlship;

import java.util.ArrayList;
import java.util.List;
import spacetrader.menu.*;
import spacetrader.MainCtrl;
import spacetrader.ViewCtrl;
import spacetrader.Window;
import spacetrader.game_model.Planet;
import spacetrader.game_model.StarSystem;

/**
 *
 * @author Jackson Morgan
 */
public class ControlShipCtrl extends ViewCtrl {
    ControlShipView view;
    MainCtrl mainCtrl;
    private StarSystem sys;
    private Planet planet;
 
    public ControlShipCtrl(MainCtrl aParent, Window aWindow,StarSystem s) {
        super(aParent, aWindow);
        view = new ControlShipView(aWindow, this);
        mainCtrl = aParent;
        sys=s;
        planet=s.getPlanets().get(0);
    }

    @Override
    public void startView() {
        view.renderMainMenu();
    }

    @Override
    public void stopView() {
        view.remove();
    }
    
    public List<Planet> getPlanets(){
        return sys.getPlanets();
    }
    public Planet getPlanet(){
        return planet;
    }
    public void setPlanet(Planet p){
        planet=p;
    }
    
    void newTrade() {
        mainCtrl.makeTrade(planet.getMarket().getCargo());
    }
    
}
