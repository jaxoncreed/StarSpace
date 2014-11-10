
package spacetrader.controlship;

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
import spacetrader.Window;
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
import javafx.scene.input.KeyEvent;

import javafx.stage.Stage;


/**
*
* @author Jackson Morgan
*/
public class ControlShipView implements Initializable {

    // TODO: Move images into their own class
    // public static final Image PLAYER_SHIP = new Image("lasher_ff.png");

    public Window window;
    public ControlShipCtrl shipCtrl;
    public Pane curPane;

    private static final int FRAMES_PER_SECOND = 60;
    private static final int SCREEN_WIDTH = 1280;
    private static final int SCREEN_HEIGHT = 720; 

    private Position camera;
    private Player player;
    private Ship playerShip;

    private Stage stage;
    private Group root;
    private Scene scene;
    private Canvas canvas;
    private GraphicsContext gc;

    public ControlShipView() {};

    public ControlShipView(Window window, ControlShipCtrl shipCtrl, Stage stage, GameModel gameModel) {
        this.window = window;
        this.shipCtrl = shipCtrl;

        // Prepare camera
        this.player = gameModel.getPlayer();
        this.playerShip = player.getShip();
       this.camera = new Position(playerShip.getPosition());

        // Prepare and show canvas
        root = new Group();
        scene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT, Color.BLACK);
        canvas = new Canvas(SCREEN_WIDTH,SCREEN_HEIGHT);
        gc = canvas.getGraphicsContext2D();
        root.getChildren().add(canvas);
        stage.setScene(scene);
        stage.show();

        // Prepare game loop
        final Duration oneFrameAmt = Duration.millis(1000/FRAMES_PER_SECOND);
        EventHandler eventHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Simulate a timestep
                shipCtrl.update();

                // Render the frame
                renderPilotingShip();

            }
        };
        final KeyFrame oneFrame = new KeyFrame(oneFrameAmt, eventHandler);

        TimelineBuilder.create()
        .cycleCount(Animation.INDEFINITE)
        .keyFrames(oneFrame)
        .build()
        .play();
    }

    private double temp = 0;

    public void renderPilotingShip() {
        // Camera smoothly follows ship
//        camera.average(new Position(playerShip.getPosition().x - 350, playerShip.getPosition().y - 350));

        // Testing the game loop at 60 FPS.  The final game WILL BE FRAME-LOCKED because of how the physics engine works.
        // That is, unless you want to multi-thread.  Please no.
        gc.clearRect(0,0,SCREEN_WIDTH,SCREEN_HEIGHT);

        gc.setFill(Color.BLUE);
        gc.fillOval(playerShip.getPosition().x - camera.x, playerShip.getPosition().y - camera.y, 100, 100);

        gc.setFill(Color.RED);
        gc.fillText((temp/60.0)+"", 10, 10);
                    shipCtrl.playerAccelerate();
        temp++;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Handles Key Presses
        window.getScene().setOnKeyPressed((KeyEvent t) -> {
            switch (t.getCode().toString()) {
                case "W":
                    shipCtrl.playerAccelerate();
                    System.out.println("shipCtrl.playerAccelerate()");
                    break;
                case "A":
                    shipCtrl.playerTurnLeft();
                    System.out.println("shipCtrl.playerTurnLeft()");
                    break;
                case "S":
                    shipCtrl.playerDecelerate();
                    System.out.println("shipCtrl.playerDecelerate()");
                    break;
                case "D":
                    shipCtrl.playerTurnRight();
                    System.out.println("shipCtrl.playerTurnRight()");
                    break;
            }
        });
        
        window.getScene().setOnKeyReleased((KeyEvent t) -> {
            
        });
    }

    void remove() {
        curPane=null;
    }


}









