/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spacetrader.menu;

import spacetrader.MainCtrl;
import spacetrader.ViewCtrl;
import spacetrader.Window;

/**
 *
 * @author Jackson Morgan
 */
public class MenuCtrl extends ViewCtrl {
    MainCtrl mainCtrl;
    
    public MenuCtrl(MainCtrl aParent, Window aWindow) {
        super(aParent, aWindow);
        view = new MenuView(aWindow, this);
        mainCtrl = aParent;
    }

    @Override
    public void startView() {
        view.render();
    }

    @Override
    public void stopView() {
        view.hide();
    }

    void newGame() {
        mainCtrl.createCharacter();
    }
    
    void closeApplication() {
        mainCtrl.closeApplication();
    }
    public void loadGame() {
        //code for loading game
        System.out.println("Working");
    }
    
    
}
