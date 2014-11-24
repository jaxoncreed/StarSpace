package spacetrader.controlship;

import spacetrader.game_model.gameLogic.Position;
import spacetrader.game_model.system.JumpPoint;
import spacetrader.game_model.system.Planet;
import spacetrader.game_model.player.Player;
import spacetrader.menu.*;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import spacetrader.Window.Window;
import spacetrader.game_model.*;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TimelineBuilder;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.util.Duration;

import javafx.scene.*;
import javafx.scene.canvas.*;
import javafx.scene.paint.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import javafx.stage.Stage;

import spacetrader.AbstractView;
import spacetrader.MultiKeyPressEventHandler;
import spacetrader.Window.JavaFXWindow;

/**
 *
 * @author Jackson Morgan
 */
public class ControlShipView extends AbstractView implements Initializable {

    // TODO: Move images into their own class
    // public static final Image PLAYER_SHIP = new Image("lasher_ff.png");
    public JavaFXWindow window;
    public ControlShipCtrl shipCtrl;
    public Pane curPane;

    private static final int FRAMES_PER_SECOND = 60;

    private Position camera;
    private Player player;
    private Ship playerShip;
    @FXML
    private Canvas canvas;
    private GraphicsContext gc;

    private boolean shouldAccelerate;
    private boolean shouldTurnRight;
    private boolean shouldDecelerate;
    private boolean shouldTurnLeft;

    private String interactionMessage = "";

    public ControlShipView() {
    }

    ;

    public ControlShipView(JavaFXWindow window, ControlShipCtrl shipCtrl) {
        this.window = window;
        this.shipCtrl = shipCtrl;
        FXMLLoader loader = new FXMLLoader((getClass().getResource("ControlShip.fxml"))) ;
        loader.setController(this);
        try {
            curPane = loader.load();
            window.loadFXML(curPane);
        } catch (IOException ex) {
            Logger.getLogger(MenuView.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.player = GameModel.get().getPlayer();
        this.playerShip = player.getShip();
        this.camera = new Position(playerShip.getPosition());
        
        this.gc=canvas.getGraphicsContext2D();
        // Prepare game loop
        final Duration oneFrameAmt = Duration.millis(1000 / FRAMES_PER_SECOND);
        EventHandler eventHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Perform frame-sensitive controls
                if (shouldAccelerate) {
                    shipCtrl.playerAccelerate();
                }
                if (shouldTurnLeft) {
                    shipCtrl.playerTurnLeft();
                }
                if (shouldDecelerate) {
                    shipCtrl.playerDecelerate();
                }
                if (shouldTurnRight) {
                    shipCtrl.playerTurnRight();
                }

                // Simulate a timestep
                shipCtrl.update();

                // Render the frame
                renderPilotingShip();

            }
        };

        // Prepare the keyboard
        shouldAccelerate = false;
        shouldTurnRight = false;
        shouldDecelerate = false;
        shouldTurnLeft = false;
        window.setKeyHandle((MultiKeyPressEventHandler.MultiKeyEvent event) -> {
            if (event.isPressed(KeyCode.W)) {
                shouldAccelerate = true;
            }if(event.isPressed(KeyCode.A)){
                shouldTurnLeft = true;
            }if(event.isPressed(KeyCode.S)){
                shouldDecelerate=true;
            }if(event.isPressed(KeyCode.D)){
                this.shouldTurnRight=true;
            }if(event.isPressed(KeyCode.E)){
                shipCtrl.performInteraction();
            }
        });  
        
        final KeyFrame oneFrame = new KeyFrame(oneFrameAmt, eventHandler);

    TimelineBuilder.create ().cycleCount(Animation.INDEFINITE)
        .keyFrames(oneFrame)
        .build()
        .play();
    }

    private double temp = 0;

    public void renderPilotingShip() {
        // Camera smoothly follows ship
        camera.average(new Position(playerShip.getPosition().x - window.getWidth()/2 + 50, playerShip.getPosition().y - window.getHeight()/2 + 50), 0.05);

        // Clear the frame
        gc.clearRect(0,0,window.getWidth(),window.getHeight());
        
        // Draw the jump points
        for (JumpPoint j : player.getSystem().getJumpPoints()) {
            gc.setFill(Color.BLUE);
            gc.fillOval(j.getFromPosition().x - camera.x - 5, j.getFromPosition().y - camera.y - 5, 10, 10);
            gc.setFill(Color.WHITE);
            gc.fillText("To " + j.getTargetSystem().getName(), j.getFromPosition().x - camera.x - 5, j.getFromPosition().y - camera.y - 5);
        }
        
        //Draw Planets
        for (Planet p : player.getSystem().getPlanets()) {
            gc.setFill(Color.YELLOW);
            gc.fillOval(p.getPos().x - camera.x - 5, p.getPos().y - camera.y - 5, 200, 200);
        }

        // Draw the ship's body
        gc.setFill(Color.GREEN);
        gc.fillOval(playerShip.getPosition().x - camera.x, playerShip.getPosition().y - camera.y, 100, 100);

        // Draw the ship's heading
        gc.setFill(Color.RED);
        gc.fillOval(playerShip.getPosition().x - camera.x + 50*Math.sin(playerShip.getAngle() + Math.PI/2) + 45, playerShip.getPosition().y - camera.y + 50*Math.cos(playerShip.getAngle() + Math.PI/2) + 45, 10, 10);

        
        if (interactionMessage != "") {
            gc.setFill(Color.RED);
            gc.fillText("Press E: " + interactionMessage, 10, 10);            
        }
        temp++;
    }

    public void setInteractionMessage(String interactionMessage) {
        this.interactionMessage = interactionMessage;
    }

    @Override
        public void render() {
        renderPilotingShip();
    }
    
    @Override
        public void initialize(URL url, ResourceBundle rb) {

    }

    void remove() {
        curPane=null;
    }

    @Override
        public void hide() {
        
    }

}
