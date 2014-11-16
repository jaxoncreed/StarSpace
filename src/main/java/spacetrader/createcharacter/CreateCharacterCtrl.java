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

/**
 *
 * @author Jackson Morgan
 */
public class CreateCharacterCtrl extends ViewCtrl {
    CreateCharacterView view;
    MainCtrl mainCtrl;
    GameModel gameModel;

    public CreateCharacterCtrl() {
        
    }

    @Override
    public void startView() {
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
