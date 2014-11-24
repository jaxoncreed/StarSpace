/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spacetrader.gameMenu;

import spacetrader.MainCtrl;
import spacetrader.ViewCtrl;
import spacetrader.Window.Window;
import spacetrader.game_model.GameModel;

import javafx.stage.Stage;
import spacetrader.CtrlViewTypes;
import spacetrader.ViewCtrlFactory;
import spacetrader.Window.JavaFXWindow;

/**
 *
 * @author Jackson Morgan
 */
public class GameMenuCtrl extends ViewCtrl {
    private MainCtrl mainCtrl;
    public GameMenuCtrl(MainCtrl aParent, Window aWindow) {
        super(aParent, aWindow);
        view = ViewCtrlFactory.getView(CtrlViewTypes.GameMenu, window, this);
        mainCtrl=aParent;
    }

    @Override
    public void startView() {
        view.render();
    }

    @Override
    public void stopView() {
        view.hide();
    }
    
    public void exit(){
        mainCtrl.close();
    }

    void mainMenu() {
        mainCtrl.switchViews(CtrlViewTypes.MainMenu);
    }

    void saveGame() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void retunToGame() {
        mainCtrl.switchViews(CtrlViewTypes.ControlShip);
    }
}
