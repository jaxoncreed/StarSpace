/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacetrader.createcharacter;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import spacetrader.MainCtrl;
import spacetrader.ViewCtrl;
import spacetrader.Window.Window;
import spacetrader.game_model.Skillset;
import spacetrader.menu.MenuView;
import spacetrader.game_model.GameModel;

import javafx.stage.Stage;
import spacetrader.AbstractView;
import spacetrader.CtrlViewTypes;
import spacetrader.ViewCtrlFactory;

/**
 *
 * @author Jackson Morgan
 */
public class CreateCharacterCtrl extends ViewCtrl {
    MainCtrl mainCtrl;
    GameModel gameModel;

    public CreateCharacterCtrl(MainCtrl parent,Window aWindow) {
        super(parent,aWindow);
        view=ViewCtrlFactory.getView(CtrlViewTypes.CharacterCreator, window, this);
    }

    @Override
    public void startView() {
        view.render();
    }

    @Override
    public void stopView() {
        view.hide();
    }

    void backout() {
    }

    void creationDone(Skillset skill) {
    }
    
}
