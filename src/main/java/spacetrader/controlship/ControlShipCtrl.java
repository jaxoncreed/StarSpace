/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spacetrader.controlship;

import spacetrader.game_model.system.JumpPoint;
import spacetrader.game_model.player.Player;
import spacetrader.game_model.interactable.InteractableObject;
import spacetrader.shared.Util;
import spacetrader.game_model.*;
import spacetrader.PhysicsSimulator;
import java.util.List;
import spacetrader.MainCtrl;
import spacetrader.ViewCtrl;
import spacetrader.Window.Window;
import spacetrader.game_model.system.Planet;
import spacetrader.game_model.system.StarSystem;
import spacetrader.game_model.GameModel;
import javafx.stage.Stage;
import spacetrader.CtrlViewTypes;
import spacetrader.ViewCtrlFactory;
import spacetrader.game_model.interactable.InteractionManager;
import spacetrader.game_model.interactable.InteractionManager.InteractAction;
import spacetrader.game_model.interactable.InteractionType;

/**
 *
 * @author Jackson Morgan
 */
public class ControlShipCtrl extends ViewCtrl {
    MainCtrl mainCtrl;
    private Player player;
    private Ship playerShip;
    private InteractionManager interactionManager;
    protected Stage stage;
 
    public ControlShipCtrl(MainCtrl aParent, Window aWindow) {
        super(aParent, aWindow);
                
        player = GameModel.get().getPlayer();
        playerShip = player.getShip();
        System.out.println(playerShip);
        PhysicsSimulator.setSystem(playerShip.getSystem());
        mainCtrl = aParent;
        interactionManager=new InteractionManager(playerShip.getSystem().getInteractableObjects());
        InteractAction tradeAction=(InteractableObject obj)->{
            ViewCtrlFactory.setMarket(((Planet)obj).getMarket());
            mainCtrl.switchViews(CtrlViewTypes.Trade);
        };
        InteractAction travelAction=(InteractableObject obj)->{
            //Do the jumpPoint Travel stuff
            GameModel.get().getPlayer().getShip().setPosition(((JumpPoint)obj).getToPosition());
            GameModel.get().getPlayer().getShip().setSystem(((JumpPoint)obj).getTargetSystem());
            PhysicsSimulator.setSystem(((JumpPoint)obj).getTargetSystem());
        };
        InteractAction nullAction=(InteractableObject obj)->{
            
        };
        interactionManager.setInteractFunction(InteractionType.Trade, tradeAction);
        interactionManager.setInteractFunction(InteractionType.Travel, travelAction);
        interactionManager.setInteractFunction(InteractionType.Null, nullAction);

        view = ViewCtrlFactory.getView(CtrlViewTypes.ControlShip, window, this);
    }

    public void update() {
        spacetrader.PhysicsSimulator.simulate();
    }
    public String getInteractionMessage(){
        return this.interactionManager.getInteractionMessage(playerShip);
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

    public void playerAngularBrake() {
        playerShip.angularBrake();
    }

    public void playerLinearBrake() {
        playerShip.linearBrake();
    }

    @Override
    public void startView() {
        view.render();
    }

    @Override
    public void stopView() {
        view.hide();
    }
    
    public void saveGame() {
        //mainCtrl.saveGame();
    }

    void performInteraction() {
        this.interactionManager.interact(playerShip);
    }
    void switchToMarket(){
        Planet p=GameModel.get().getPlayer().getSystem().getPlanets().get(0);
        ViewCtrlFactory.setMarket(p.getMarket());
        this.mainCtrl.switchViews(CtrlViewTypes.Trade);
    }
  
    void switchToGameMenu() {
        mainCtrl.switchViews(CtrlViewTypes.GameMenu);
    }
}