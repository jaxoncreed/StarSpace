/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spacetrader.controlship;

import spacetrader.shared.Util;
import spacetrader.game_model.*;
import spacetrader.PhysicsSimulator;
import java.util.List;
import spacetrader.MainCtrl;
import spacetrader.ViewCtrl;
import spacetrader.Window;
import spacetrader.game_model.Planet;
import spacetrader.game_model.StarSystem;
import spacetrader.game_model.GameModel;
import javafx.stage.Stage;

/**
 *
 * @author Jackson Morgan
 */
public class ControlShipCtrl extends ViewCtrl {
    ControlShipView view;
    MainCtrl mainCtrl;
    private GameModel gameModel;
    private Player player;
    private Ship playerShip;

    protected Stage stage;
 
    public ControlShipCtrl(MainCtrl aParent, Window aWindow, Stage stage, GameModel gameModel) {
        super(aParent, aWindow, stage, gameModel);
        view = new ControlShipView(aWindow, this, stage, gameModel);
        mainCtrl = aParent;

        player = gameModel.getPlayer();
        playerShip = player.getShip();

    }

    public void update() {
        spacetrader.PhysicsSimulator.simulate(player.getSystem());
    }


    @Override
    public void startView() {
        view.renderPilotingShip();
    }

    @Override
    public void stopView() {
        view.remove();
    }
    
    public void saveGame() {
        mainCtrl.saveGame();
    }

    
    
}
