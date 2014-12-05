package spacetrader.viewGalaxyMap;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import spacetrader.controlship.graphicsrender.javafxrenderer.JavaFXPlanetRenderer;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
import spacetrader.controlship.RealTimeShipView;
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
import spacetrader.game_model.positioncontainer.Camera;

/**
 *
 * @author Tyler Allen <tallen40@gatech.edu>
 */
public class GalaxyMapView extends AbstractView implements Initializable {
    private JavaFXWindow window;
    private Pane curPane;
    @FXML
    Canvas canvas;

    private double PIXELS_PER_DISTANCE=400;
    private double canvasWidth,canvasHeight;
    private Bounds bounds;
    private GalaxyMapCtrl controller;
    private Camera camera;
    private Timeline timeline;
    private AnimationTimer timer;
    private int view_size=800;

    public GalaxyMapView(JavaFXWindow win,GalaxyMapCtrl c){
        window=win;
        controller=c;
        FXMLLoader loader = new FXMLLoader((getClass().getResource("GalaxyMapView.fxml"))) ;
        loader.setController(this);
        try {
            curPane = loader.load();
            window.loadFXML(curPane);
        } catch (IOException ex) {
            Logger.getLogger(RealTimeShipView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private int animationStep = 0;
    public void draw() {
        List<StarSystem> systems=controller.getSystems();
        GraphicsContext gc=canvas.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, canvasWidth, canvasHeight);
        for(StarSystem sys:systems){
           //System.out.println(camera.normalize(sys.getPosition()));
           gc.setFill(Color.BLUE);
           gc.fillOval((camera.normalize(sys.getPosition()).x-10)*this.PIXELS_PER_DISTANCE, (camera.normalize(sys.getPosition()).y-10)*this.PIXELS_PER_DISTANCE, 20*PIXELS_PER_DISTANCE, 20*PIXELS_PER_DISTANCE);
        }
    }

    public void handleMutliKey(MultiKeyPressEventHandler.MultiKeyEvent event){
        if(event.isPressed(KeyCode.W)){
            camera.move(new Position(0,1));
        }        
        if(event.isPressed(KeyCode.S)){
            camera.move(new Position(0,-1));
        }
        if(event.isPressed(KeyCode.A)){
            camera.move(new Position(-1,0));
        }
        if(event.isPressed(KeyCode.D)){
            camera.move(new Position(1,0));
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
        Galaxy gal=GameModel.get().getGalaxy();
        List<StarSystem> systems=gal.getSystems();
        ArrayList<Position> pos=new ArrayList();
        for(StarSystem sys:systems){
            pos.add(sys.getPosition());
        }
        bounds=new Bounds(pos);
        System.out.println(bounds.getFar());
        bounds.setOffsetFar(new Position(100,100));
        bounds.setOffsetNear(new Position(100,100));
        double max=window.getWidth()/this.view_size;//(bounds.getDistanceX()>bounds.getDistanceY())?window.getWidth()/bounds.getDistanceX():window.getHeight()/bounds.getDistanceY();
        PIXELS_PER_DISTANCE=(max);
        camera=new Camera(new Position(bounds.getOrigin()),this.view_size,window.getHeight()/PIXELS_PER_DISTANCE);
        camera.setBounds(bounds);

        System.out.println(max);
        canvasWidth=window.getWidth();
        canvasHeight=window.getHeight();
        canvas.setWidth(canvasWidth);
        canvas.setHeight(canvasHeight);
        GalaxyMapView tt=this;
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
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
