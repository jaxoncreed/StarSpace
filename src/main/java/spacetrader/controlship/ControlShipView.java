
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

    private final int framesPerSecond = 60;

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

        this.player = gameModel.getPlayer();
        this.playerShip = player.getShip();
        this.camera = new Position(playerShip.getPosition());

        int canvasSize = 800;

        root = new Group();
        scene = new Scene(root, canvasSize, canvasSize, Color.BLACK);
        canvas = new Canvas(canvasSize,canvasSize);
        gc = canvas.getGraphicsContext2D();

        root.getChildren().add(canvas);

        stage.setScene(scene);
        stage.show();

        final Duration oneFrameAmt = Duration.millis(1000/60);
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

        // sets the game world's game loop (Timeline)
        TimelineBuilder.create()
        .cycleCount(Animation.INDEFINITE)
        .keyFrames(oneFrame)
        .build()
        .play();
    }

    private double temp = 0;

    public void renderPilotingShip() {
        // Camera smoothly follows ship
        camera.average(playerShip.getPosition());

        gc.clearRect(0,0,800,800);
        gc.setFill(Color.BLUE);
        gc.fillOval(400,400,Math.sin(temp/100.0)*400,Math.cos(temp/100.0)*400);
        gc.setFill(Color.RED);
        temp++;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    void remove() {
        curPane=null;
    }


}
