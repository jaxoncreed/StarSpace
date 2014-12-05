package spacetrader.viewGalaxyMap;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import spacetrader.AbstractView;
import spacetrader.MultiKeyPressEventHandler;
import spacetrader.Window.JavaFXWindow;
import spacetrader.game_model.GameModel;
import spacetrader.game_model.system.*;
import spacetrader.game_model.gameLogic.Position;
import spacetrader.game_model.system.StarSystem;
import spacetrader.game_model.positioncontainer.Bounds;
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
    private int view_size=400;

    public GalaxyMapView(JavaFXWindow win,GalaxyMapCtrl c){
        window=win;
        controller=c;
        FXMLLoader loader = new FXMLLoader((getClass().getResource("GalaxyMapView.fxml"))) ;
        loader.setController(this);
        try {
            curPane = loader.load();
            window.loadFXML(curPane);
        } catch (IOException ex) {
            Logger.getLogger(GalaxyMapView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private int animationStep = 0;
    public void draw() {
        List<StarSystem> systems=controller.getSystems();
        List<JumpPoint> jumpPoints=GameModel.get().getGalaxy().getJumpPoints();
        
        GraphicsContext gc=canvas.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, canvasWidth, canvasHeight);
        gc.drawImage(SpriteManager.DIGITAL_BG_ALPHA, 0, 0);
        for(JumpPoint j:jumpPoints){
            Position p1=camera.normalize(j.getFromNode().getPosition());
            Position p2=camera.normalize(j.getToNode().getPosition());
            if(GameModel.get().getPlayer().getJumpPath().contains(j)){
                gc.setStroke(Color.WHITE);
                gc.setLineWidth(3);
            }
            else{
                gc.setStroke(Color.web("#00e7ff", .5));
                gc.setLineWidth(1);
            }
            gc.strokeLine(p1.x*this.PIXELS_PER_DISTANCE, p1.y*this.PIXELS_PER_DISTANCE, p2.x*this.PIXELS_PER_DISTANCE, p2.y*this.PIXELS_PER_DISTANCE);
        }
        for(StarSystem sys:systems){
           //System.out.println(camera.normalize(sys.getPosition()));
           gc.setFill(Color.BLUE);
           double xDraw = (camera.normalize(sys.getPosition()).x-10)*this.PIXELS_PER_DISTANCE;
           double yDraw = (camera.normalize(sys.getPosition()).y-10)*this.PIXELS_PER_DISTANCE;
           double xHaloDraw = (camera.normalize(sys.getPosition()).x-10*2)*this.PIXELS_PER_DISTANCE;
           double yHaloDraw = (camera.normalize(sys.getPosition()).y-10*2)*this.PIXELS_PER_DISTANCE;
           double widthDraw = 20*PIXELS_PER_DISTANCE;
           double heightDraw = 20*PIXELS_PER_DISTANCE;
           gc.drawImage(SpriteManager.STAR_HALO, xHaloDraw, yHaloDraw, widthDraw*2, heightDraw*2);
           gc.drawImage(SpriteManager.getRandomStar(sys.getPosition().x * sys.getPosition().y), xDraw, yDraw, widthDraw, heightDraw);
           gc.drawImage(SpriteManager.STAR_HALO_CORE, xHaloDraw, yHaloDraw, widthDraw*2, heightDraw*2);
        }
        for(StarSystem sys:systems){
           gc.setStroke(Color.RED);
           gc.setLineWidth(1);
           gc.strokeText(sys.getName(),(camera.normalize(sys.getPosition()).x-10)*this.PIXELS_PER_DISTANCE, (camera.normalize(sys.getPosition()).y-10)*this.PIXELS_PER_DISTANCE);
        }
        for(StarSystem sys:systems){
           gc.setStroke(Color.WHITE);
           gc.setLineWidth(1);
           gc.strokeText(sys.getFaction().toString(),(camera.normalize(sys.getPosition()).x+10)*this.PIXELS_PER_DISTANCE, (camera.normalize(sys.getPosition()).y+10)*this.PIXELS_PER_DISTANCE);
        }

    }

    public void handleMutliKey(MultiKeyPressEventHandler.MultiKeyEvent event){
        if(event.isPressed(KeyCode.W)){
            camera.move(new Position(0,-2));
        }        
        if(event.isPressed(KeyCode.S)){
            camera.move(new Position(0,2));
        }
        if(event.isPressed(KeyCode.A)){
            camera.move(new Position(-2,0));
        }
        if(event.isPressed(KeyCode.D)){
            camera.move(new Position(2,0));
        }
    }
    private double ox;
    private double oy;
    private boolean dragInProgress=false;
    public void handleMouse(MouseEvent e){
        if(e.isPrimaryButtonDown()){
            double x=e.getSceneX()/PIXELS_PER_DISTANCE;
            double y=e.getSceneY()/PIXELS_PER_DISTANCE;
            Position p=new Position(x,y);
            StarSystem selected=this.controller.findNearbyStarSystem(camera.deNormalize(p));
            System.out.println((selected));
            System.out.println(selected.getPosition());
            this.controller.findPath(selected);
        }
        if(!e.isSecondaryButtonDown()&&dragInProgress){
            dragInProgress=false;
            double tx=e.getSceneX()-ox;
            double ty=e.getSceneY()-oy;
            camera.move(new Position(tx,ty));
        }
        if(e.isSecondaryButtonDown()&&e.isDragDetect()){
            dragInProgress=true;
        }
        else if(e.isSecondaryButtonDown()&&!dragInProgress){
            ox=e.getSceneX();
            oy=e.getSceneY();
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
        window.setMouseHandle((MouseEvent e)->{
            handleMouse(e);
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
