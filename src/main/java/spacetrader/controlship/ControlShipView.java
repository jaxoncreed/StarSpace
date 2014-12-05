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
import spacetrader.controlship.graphicsrender.javafxrenderer.*;
import spacetrader.game_model.GameModel;
import spacetrader.game_model.system.*;
import spacetrader.game_model.system.Planet;
import spacetrader.game_model.player.Player;
import spacetrader.game_model.gameLogic.Position;
import spacetrader.game_model.Ship;
import spacetrader.game_model.system.StarSystem;
import spacetrader.game_model.positioncontainer.Bounds;
import spacetrader.game_model.positioncontainer.BoxCut;

/**s
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
    private double view_size=500;
    private boolean turnLeft=false;
    private boolean turnRight=false;
    private boolean accelerate=false;
    private boolean decelerate=false;
    private ControlShipCtrl controller;
    
    private Timeline timeline;
    private AnimationTimer timer;

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
    }

    private int animationStep = 0;
    public void draw() {
        animationStep++;
        // Set system
        system=player.getSystem();

        // Background
        JavaFXBackgroundRender bg = new JavaFXBackgroundRender(canvasWidth,canvasHeight);
        bg.setGraphicsContext(canvas.getGraphicsContext2D());
        bg.setScale(PIXELS_PER_DISTANCE);
        bg.setOffset(player.getPosition());
        bg.draw();

        // Calculate area to render
        Position neg=new Position(player.getShip().getPosition().x,player.getShip().getPos().y);
        neg.sub(new Position((this.canvasWidth/PIXELS_PER_DISTANCE)/2+10,(this.canvasHeight/PIXELS_PER_DISTANCE)/2+10));
        Position pos=new Position(player.getShip().getPosition().x,player.getShip().getPos().y);
        pos.add(new Position((this.canvasWidth/PIXELS_PER_DISTANCE)/2+10,(this.canvasHeight/PIXELS_PER_DISTANCE)/2+10));
        BoxCut box=new BoxCut(neg,pos);





        // Star
        JavaFXStarRenderer st = new JavaFXStarRenderer(new BoxCut(neg,pos).normalize(new Position(0, 0)));
        st.setRandomStarSeed(system.getPosition().x * system.getPosition().y);
        st.setScale(PIXELS_PER_DISTANCE);
        st.setGraphicsContext(canvas.getGraphicsContext2D());
        st.draw();

        // Planets
        /*
        for(Planet p:system.getNearbyPlanets(new BoxCut(neg,pos))){
            JavaFXPlanetRenderer pr = new JavaFXPlanetRenderer(p,new BoxCut(neg,pos).normalize(p.getPos()),50*PIXELS_PER_DISTANCE,50*PIXELS_PER_DISTANCE);
            pr.setScale(PIXELS_PER_DISTANCE);
            pr.setGraphicsContext(canvas.getGraphicsContext2D());
            pr.draw();
        }
        */
        for (Planet p : system.getPlanets()) {
            JavaFXPlanetRenderer pr = new JavaFXPlanetRenderer(p,new BoxCut(neg,pos).normalize(p.getPos()),50*PIXELS_PER_DISTANCE,50*PIXELS_PER_DISTANCE);
            pr.setScale(PIXELS_PER_DISTANCE);
            pr.setGraphicsContext(canvas.getGraphicsContext2D());
            pr.draw();            
        }
        // Jump Points
        for(JumpPoint j : system.getJumpPoints() ) {
            JavaFXJumpPointRenderer jr = new JavaFXJumpPointRenderer(j,new BoxCut(neg,pos).normalize(j.getPos()));
            jr . setScale(PIXELS_PER_DISTANCE);
            jr . setGraphicsContext(canvas.getGraphicsContext2D());
            jr . draw(animationStep);
        }

        // Ship
        Ship s=player.getShip();
        JavaFXShipRender playrend=new JavaFXShipRender(s,new BoxCut(neg,pos).normalize(s.getPosition()));
        playrend.setGraphicsContext(canvas.getGraphicsContext2D());
        playrend.setScale(PIXELS_PER_DISTANCE);
        playrend.draw();

        // Foreground
        JavaFXForegroundRender fg = new JavaFXForegroundRender(canvasWidth,canvasHeight);
        fg.setGraphicsContext(canvas.getGraphicsContext2D());
        fg.setScale(PIXELS_PER_DISTANCE);
        fg.setOffset(player.getPosition());
        fg.draw();
    }

    public void handleMutliKey(MultiKeyPressEventHandler.MultiKeyEvent event){
        if (event.isPressed(KeyCode.ESCAPE)) {
            controller.switchToGameMenu();
        }
        turnRight=event.isPressed(KeyCode.D);
        turnLeft=event.isPressed(KeyCode.A);
        accelerate=event.isPressed(KeyCode.W);
        decelerate=event.isPressed(KeyCode.S);
        if(event.isPressed(KeyCode.E)){
            this.controller.performInteraction();
        }
        if(event.isPressed(KeyCode.M)){
            this.controller.switchToGalaxyView();
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    // public void stop() {
        // timeline.stop();
    // }

    @Override
    public void render() {
        window.setKeyHandle((MultiKeyPressEventHandler.MultiKeyEvent event)->{
            handleMutliKey(event);
        });
        PIXELS_PER_DISTANCE=(int)(window.getWidth()/view_size);
        bounds=system.getBounds();
        bounds.setOffsetNear(new Position(600,600));
        bounds.setOffsetFar(new Position(600,600));
        canvasWidth=window.getWidth();
        canvasHeight=window.getHeight();
        canvas.setWidth(canvasWidth);
        canvas.setHeight(canvasHeight);
        
        ControlShipView tt=this;
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                controller.update();
                if(turnLeft){
                    controller.playerTurnLeft();
                }
                if(turnRight){
                    controller.playerTurnRight();
                }
                if(accelerate){
                    controller.playerAccelerate();
                }
                if(decelerate){
                    controller.playerDecelerate();
                }
                if (!(accelerate || decelerate)) {
                    controller.playerLinearBrake();
                }
                if (!(turnLeft || turnRight)) {
                    controller.playerAngularBrake();
                }
                tt.draw();
            }
        };
        timeline = new Timeline(new KeyFrame(Duration.seconds(1/60)));
        timeline.play();
        timer.start();
    }

    @Override
    public void hide() {
        timeline.pause();
        timer.stop();
        window.clearFXML(curPane);
    }
}
