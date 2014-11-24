/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacetrader.controlship.graphicsrender.javafxrenderer;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import spacetrader.game_model.system.JumpPoint;
import spacetrader.game_model.gameLogic.Position;

/**
 *
 * @author Tyler Allen <tallen40@gatech.edu>
 */
public class JavaFXStarRenderer extends JavaFXRenderer{
    private Position position;
    public JavaFXStarRenderer(Position normalize) {
        this.position = normalize;
    }
    
    
    
    @Override
    public void draw() {
        double haloSize = 2048.0;
        graphics.drawImage(sprites.STAR_01, position.x*scale - sprites.STAR_01.getWidth()/2, position.y*scale - sprites.STAR_01.getHeight()/2);
        graphics.drawImage(sprites.STAR_HALO, position.x*scale - haloSize/2, position.y*scale - haloSize/2, haloSize, haloSize);
//        graphics.drawImage(sprites.STAR_HALO, position.x*scale - haloSize/4, position.y*scale - haloSize/4, haloSize/2, haloSize/2);
    }

    @Override
    public void init() {
    }

    @Override
    public void end() {
    }
    
}