/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spacetrader.controlship;

import spacetrader.Interactable;
import spacetrader.shared.Util;
import spacetrader.game_model.*;
import spacetrader.PhysicsSimulator;
import java.util.List;
import javafx.scene.Group;
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
    private Interactable interactionEntity;
    private Planet planet;

    protected Stage stage;
 
    public ControlShipCtrl(MainCtrl aParent, Window aWindow, Stage stage, GameModel gameModel) {
        super(aParent, aWindow, stage, gameModel);
        view = new ControlShipView(aWindow, this, stage, gameModel);
        mainCtrl = aParent;

        player = gameModel.getPlayer();
        playerShip = player.getShip();
        interactionEntity = null;
    }

    public void update() {

        spacetrader.PhysicsSimulator.simulate();
 
        // Update interaction opportunities
        interactionEntity = null;
        view.setInteractionMessage("");
        StarSystem curSystem = player.getSystem();
        for (JumpPoint j : curSystem.getJumpPoints()) {
            double interactionRange = playerShip.getInteractionRange() + j.getInteractionRange();
            double distance = j.getPos().distTo(player.getPosition());
            if (distance <= interactionRange) {
                view.setInteractionMessage(j.getInteractionMessage());
                interactionEntity = j;
            }
        }
        for (Planet p : curSystem.getPlanets()) {
            double interactionRange = playerShip.getInteractionRange() + p.getInteractionRange();
            double distance = p.getPos().distTo(player.getPosition());
            if (distance <= interactionRange) {
                view.setInteractionMessage(p.getInteractionMessage());
                interactionEntity = p;
                planet = p;
            }
        }
    }

    public void performInteraction() {
        if (interactionEntity != null) {
            if (interactionEntity instanceof Planet) {
                mainCtrl.makeTrade(planet.getMarket().getCargo());
            }
            interactionEntity.interact(playerShip, gameModel);
        }
    }

    public void playerAccelerate() {
        playerShip.accelerate();
    }

    public void playerTurnLeft() {
        playerShip.turnLeft();
    }

    public void playerDecelerate() {
        playerShip.decelerate();
    }

    public void playerTurnRight() {
        playerShip.turnRight();
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
