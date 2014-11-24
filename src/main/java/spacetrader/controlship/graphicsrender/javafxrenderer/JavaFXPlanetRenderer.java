/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacetrader.controlship.graphicsrender.javafxrenderer;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import spacetrader.game_model.system.Planet;
import spacetrader.game_model.gameLogic.Position;

/**
 *
 * @author Tyler Allen <tallen40@gatech.edu>
 */
public class JavaFXPlanetRenderer extends JavaFXRenderer{
    private Planet planet;
    private int sizex,sizey;
    private Position position;
    public JavaFXPlanetRenderer(Planet p,Position normalize,int sizex,int sizey) {
        this.sizex=sizex;
        this.sizey=sizey;
        this.position=normalize;
        this.planet=p;
    }
    @Override
    public void draw() {
        double pointX = (position.x*scale-sprites.ARID_01.getWidth()/2);
        double pointY = (position.y*scale-sprites.ARID_01.getHeight()/2);
        drawRotatedImage(sprites.ARID_01, -planet.getPosition().toAngle() - Math.PI, pointX, pointY);
    }

    @Override
    public void init() {
    }

    @Override
    public void end() {
    }
    
}
