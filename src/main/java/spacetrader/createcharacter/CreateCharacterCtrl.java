/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacetrader.createcharacter;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import spacetrader.MainCtrl;
import spacetrader.ViewCtrl;
import spacetrader.Window.Window;
import spacetrader.game_model.player.Skillset;
import spacetrader.menu.MenuView;
import spacetrader.game_model.GameModel;

import javafx.stage.Stage;
import spacetrader.AbstractView;
import spacetrader.CtrlViewTypes;
import spacetrader.ViewCtrlFactory;
import spacetrader.game_model.Faction;
import spacetrader.game_model.player.Player;
import spacetrader.game_model.Ship;
import spacetrader.game_model.ShipDef;

/**
 *
 * @author Jackson Morgan
 */
public class CreateCharacterCtrl extends ViewCtrl {
    MainCtrl mainCtrl;
    GameModel gameModel;

    public CreateCharacterCtrl(MainCtrl parent,Window aWindow) {
        super(parent,aWindow);
        mainCtrl=parent;
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
        mainCtrl.switchViews(CtrlViewTypes.MainMenu);
    }

    void creationDone(Skillset skill) {
        Player player=new Player(skill.getName(),Faction.Space_People);
        player.setShip(new Ship(new ShipDef()));
        GameModel.get().setPlayer(player);
        GameModel.get().getPlayer().setSkillset(skill);
        mainCtrl.switchViews(CtrlViewTypes.CreateGalaxy);
    }
    
}
