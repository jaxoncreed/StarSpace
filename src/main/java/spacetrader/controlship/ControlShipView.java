/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacetrader.controlship;

import java.io.IOException;
import spacetrader.controlship.graphicsrender.javafxrenderer.JavaFXPlanetRenderer;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.Event;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import spacetrader.AbstractView;
import spacetrader.MultiKeyPressEventHandler;
import spacetrader.MultiKeyPressEventHandler.MultiKeyEventHandler;
import spacetrader.Window.JavaFXWindow;
import spacetrader.controlship.graphicsrender.javafxrenderer.JavaFXBackgroundRender;
import spacetrader.controlship.graphicsrender.javafxrenderer.JavaFXShipRender;
import spacetrader.game_model.GameModel;
import spacetrader.game_model.system.*;
import spacetrader.game_model.system.Planet;
import spacetrader.game_model.player.Player;
import spacetrader.game_model.gameLogic.Position;
import spacetrader.game_model.Ship;
import spacetrader.game_model.system.StarSystem;
import spacetrader.game_model.positioncontainer.Bounds;
import spacetrader.game_model.positioncontainer.Bounds;

/**
 *
 * @author Tyler Allen <tallen40@gatech.edu>
 */
public class ControlShipView extends AbstractView implements Initializable {
    private Player player;
    private StarSystem system;
    private JavaFXWindow window;
    private Pane curPane;
    @FXML
    Canvas canvas;
    private int PIXELS_PER_DISTANCE=400;
    private double canvasWidth,canvasHeight;
    private Bounds bounds;
    private double view_size=50;
    private Position camera;

    private ControlShipCtrl controller;
    
    public ControlShipView(JavaFXWindow win,ControlShipCtrl c){
        FXMLLoader loader = new FXMLLoader((getClass().getResource("ControlShip.fxml"))) ;
        loader.setController(this);
        controller=c;
        player=GameModel.get().getPlayer();
        system=player.getSystem();
        window=win;
        try {
            curPane = loader.load();
            window.loadFXML(curPane);
        } catch (IOException ex) {
            Logger.getLogger(RealTimeShipView.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.camera = new Position(player.getShip().getPosition());
    }

    public void draw(){
        /*
        system=player.getSystem();
        JavaFXBackgroundRender bg=new JavaFXBackgroundRender(canvasWidth,canvasHeight);
        bg.setGraphicsContext(canvas.getGraphicsContext2D());
        bg.setScale(PIXELS_PER_DISTANCE);
        bg.draw();
        for(Planet p:system.getPlanets()){
            JavaFXPlanetRenderer pr=new JavaFXPlanetRenderer(p,bounds.normalize(p.getPos()),100,100);
            pr.setScale(PIXELS_PER_DISTANCE);
            pr.setGraphicsContext(canvas.getGraphicsContext2D());
            pr.draw();
        }
        Ship s=player.getShip();
        JavaFXShipRender playrend=new JavaFXShipRender(s,bounds.normalize(s.getPosition()));
        playrend.setGraphicsContext(canvas.getGraphicsContext2D());
        playrend.setScale(PIXELS_PER_DISTANCE);
        playrend.draw();
        canvas.setTranslateX(-(bounds.normalize(s.getPosition()).x*PIXELS_PER_DISTANCE-.5*window.getWidth()));
        canvas.setTranslateY(-(bounds.normalize(s.getPosition()).y*PIXELS_PER_DISTANCE-.5*window.getHeight()));
        */

        GraphicsContext gc = canvas.getGraphicsContext2D();
        // Camera smoothly follows ship
        camera.average(new Position(player.getShip().getPosition().x - AbstractView.SCREEN_WIDTH/2 + 50, player.getShip().getPosition().y - AbstractView.SCREEN_HEIGHT/2 + 50), 0.05);

        // Clear the frame
        gc.setFill(Color.BLACK);
        gc.clearRect(0,0,AbstractView.SCREEN_WIDTH,AbstractView.SCREEN_HEIGHT);
        
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
            gc.fillOval(p.getPos().x - camera.x - 5, p.getPos().y - camera.y - 5, 10, 10);
        }

        // Draw the ship's body
        gc.setFill(Color.GREEN);
        gc.fillOval(player.getShip().getPosition().x - camera.x, player.getShip().getPosition().y - camera.y, 100, 100);

        // Draw the ship's heading
        gc.setFill(Color.RED);
        gc.fillOval(player.getShip().getPosition().x - camera.x + 50*Math.sin(player.getShip().getAngle() + Math.PI/2) + 45, player.getShip().getPosition().y - camera.y + 50*Math.cos(player.getShip().getAngle() + Math.PI/2) + 45, 10, 10);

        
        // if (interactionMessage != "") {
        //     gc.setFill(Color.RED);
        //     gc.fillText("Press E: " + interactionMessage, 10, 10);            
        // }
    }
    public void handleKey(KeyEvent t){/*
        if (t.getCode() == KeyCode.D ) {
            Position p=new Position(1,0);
            canvas.setTranslateX(-(bounds.normalize(p).x*PIXELS_PER_DISTANCE-.5*anchor.getWidth()));
            canvas.setTranslateY(-(bounds.normalize(p).y*PIXELS_PER_DISTANCE-.5*anchor.getHeight()));
        } else if (t.getCode() == KeyCode.A ) {
            Position p=new Position(-1,0);
            canvas.setTranslateX(-(bounds.normalize(p).x*PIXELS_PER_DISTANCE-.5*anchor.getWidth()));
            canvas.setTranslateY(-(bounds.normalize(p).y*PIXELS_PER_DISTANCE-.5*anchor.getHeight()));

        }
        if (t.getCode() == KeyCode.W) {
            Position p=new Position(0,-1);
            canvas.setTranslateX(-(bounds.normalize(p).x*PIXELS_PER_DISTANCE-.5*anchor.getWidth()));
            canvas.setTranslateY(-(bounds.normalize(p).y*PIXELS_PER_DISTANCE-.5*anchor.getHeight()));

        } else if (t.getCode() == KeyCode.S) {
            Position p=new Position(0,1);
            canvas.setTranslateX(-(bounds.normalize(p).x*PIXELS_PER_DISTANCE-.5*anchor.getWidth()));
            canvas.setTranslateY(-(bounds.normalize(p).y*PIXELS_PER_DISTANCE-.5*anchor.getHeight()));
        }*/
    }

    public void handleMutliKey(MultiKeyPressEventHandler.MultiKeyEvent event){
        if(event.isPressed(KeyCode.D)){
            controller.playerTurnRight();
        }
        if(event.isPressed(KeyCode.A)){
            controller.playerTurnLeft();
        }
        if(event.isPressed(KeyCode.W)){
            controller.playerAccelerate();
        }
        if(event.isPressed(KeyCode.S)){
            controller.playerDecelerate();
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @Override
    public void render() {
        // Keyboard input
        window.setKeyHandle((MultiKeyPressEventHandler.MultiKeyEvent event)->{
            handleMutliKey(event);
            System.out.println("sub handle");
        });

        // System scale
        /*
        PIXELS_PER_DISTANCE=(int)(window.getWidth()/view_size);
        bounds=system.getBounds();
        bounds.setOffsetNear(new Position(50,50));
        bounds.setOffsetFar(new Position(50,50));
        canvasWidth=(bounds.getDistanceX())*PIXELS_PER_DISTANCE;
        canvasHeight=(bounds.getDistanceY())*PIXELS_PER_DISTANCE;
        canvas.setWidth((int)canvasWidth);
        canvas.setHeight(canvasHeight);
        */
        canvas.setWidth(AbstractView.SCREEN_WIDTH);
        canvas.setHeight(AbstractView.SCREEN_HEIGHT);
        
        // Game loop
        ControlShipView tt=this;
        AnimationTimer timer;
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                // UPDATE
                controller.update();
                // RENDER
                tt.draw();
            }
        };
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1/60)));
        timeline.play();
        timer.start();
    }

    @Override
    public void hide() {
        window.clearFXML(curPane);
    }
}
