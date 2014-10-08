/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spacetrader.controlship;

import spacetrader.menu.*;
import spacetrader.MainCtrl;
import spacetrader.ViewCtrl;
import spacetrader.Window;

/**
 *
 * @author Jackson Morgan
 */
public class ControlShipCtrl extends ViewCtrl {
    ControlShipView view;
    MainCtrl mainCtrl;
    
    public ControlShipCtrl(MainCtrl aParent, Window aWindow) {
        super(aParent, aWindow);
        view = new ControlShipView(aWindow, this);
        mainCtrl = aParent;
    }

    @Override
    public void startView() {
        view.renderMainMenu();
    }

    @Override
    public void stopView() {
        
    }

    void newGame() {
        mainCtrl.createCharacter();
    }
    
    void closeApplication() {
        mainCtrl.closeApplication();
    }
}
