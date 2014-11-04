/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spacetrader.controlship;

import spacetrader.shared.Util;
import java.util.List;
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
    
    private static final double PIRATE_ATTACK_PROB = 0.1;
    private static final int PIRATE_ATTACK_DAMAGE = 50;
    private static final String PIRATE_ATTACK_MSG = 
        "Oh no! You were attacked by pirates and lost " + PIRATE_ATTACK_DAMAGE + 
        " health.";
 
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
        double sample = Util.sampleFromUniformReal(0, 1);
        if (sample < PIRATE_ATTACK_PROB && !p.equals(planet)) {
            mainCtrl.getPlayer().getShip().incrementHealth(PIRATE_ATTACK_DAMAGE);
            System.out.println(PIRATE_ATTACK_MSG);
        } else {
           planet=p;
        }
    }
    
    void newTrade() {
        mainCtrl.makeTrade(planet.getMarket().getCargo());
    }
    
    public void saveGame() {
        mainCtrl.saveGame();
    }

    
    
}
