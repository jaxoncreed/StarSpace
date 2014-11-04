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
import spacetrader.Window;
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

    public CreateCharacterCtrl(MainCtrl aParent, Window window, Stage stage, GameModel gameModel) {
        super(aParent, window, stage, gameModel);
        view = new CreateCharacterView(window, this);
        mainCtrl = aParent;
    }

    @Override
    public void startView() {
        view.renderCharacterCreator();
    }

    @Override
    public void stopView() {
        view.removeCharacterCreator();
    }

    void backout() {
        mainCtrl.mainMenu();
    }

    void creationDone(Skillset skill) {
        //TODO: Save the player
        mainCtrl.generateUniverse();
    }
    
}
