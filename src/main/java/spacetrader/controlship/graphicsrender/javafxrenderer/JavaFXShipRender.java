/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacetrader.controlship.graphicsrender.javafxrenderer;

import javafx.scene.paint.Color;
import spacetrader.game_model.gameLogic.Position;
import spacetrader.game_model.Ship;

/**
 *
 * @author Tyler Allen <tallen40@gatech.edu>
 */
public class JavaFXShipRender extends JavaFXRenderer{
    private Position position;
    private int sizex,sizey;
    private Ship ship;
    public JavaFXShipRender(Ship ship,Position normal){
        position=normal;
        sizex=20;
        sizey=20;
        this.ship=ship;
    }
    @Override
    public void init() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void draw() {
        this.graphics.setFill(Color.BLUE);
        double xpoint = position.x*scale;
        double ypoint = position.y*scale;

        drawRotatedImage(sprites.LASHER_FF, ship.getAngle() - Math.PI/2, xpoint, ypoint);
//        this.graphics.strokeOval(xpoint, ypoint, ship.getRadius(), ship.getRadius());
    }

    @Override
    public void end() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
