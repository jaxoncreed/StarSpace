/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spacetrader.menu;

import spacetrader.MainCtrl;
import spacetrader.ViewCtrl;
import spacetrader.Window;
import spacetrader.game_model.GameModel;

import javafx.stage.Stage;

/**
 *
 * @author Jackson Morgan
 */
public class MenuCtrl extends ViewCtrl {
    MainCtrl mainCtrl;
    
    public MenuCtrl(MainCtrl aParent, Window aWindow, Stage stage, GameModel gameModel) {
        super(aParent, aWindow, stage, gameModel);
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
        mainCtrl.loadGame();
    }
    
    
}
