/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spacetrader.menu;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import spacetrader.MainCtrl;
import spacetrader.ViewCtrl;
import spacetrader.Window.Window;
import spacetrader.game_model.GameModel;

import spacetrader.CtrlViewTypes;
import spacetrader.GameSaver;
import spacetrader.ViewCtrlFactory;

/**
 *
 * @author Jackson Morgan
 */
public class MenuCtrl extends ViewCtrl {
    private MainCtrl mainCtrl;
    public MenuCtrl(MainCtrl aParent, Window aWindow) {
        super(aParent, aWindow);
        view = ViewCtrlFactory.getView(CtrlViewTypes.MainMenu, window, this);
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
    public void loadGame() {
        //TODO: Make a view to dynamically load a game
        //mainCtrl.switchViews(CtrlViewTypes.LoadGame);
        System.out.println("It is here");
        GameSaver saver = new GameSaver(GameModel.get());
        try {
            saver.loadGame();
        } catch (IOException ex) {
            Logger.getLogger(MenuCtrl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MenuCtrl.class.getName()).log(Level.SEVERE, null, ex);
        }
        mainCtrl.switchViews(CtrlViewTypes.ControlShip);
    }

    void startGame() {
        mainCtrl.switchViews(CtrlViewTypes.CharacterCreator);
    }
}
