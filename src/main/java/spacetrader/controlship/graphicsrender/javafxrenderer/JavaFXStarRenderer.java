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
import javafx.scene.image.Image;

/**
 *
 * @author Tyler Allen <tallen40@gatech.edu>
 */
public class JavaFXStarRenderer extends JavaFXRenderer{
    private Position position;
    private double seed;
    public JavaFXStarRenderer(Position normalize) {
        this.position = normalize;
    }
    
    public void setRandomStarSeed(double seed) {
        this.seed = seed;
    }
    
    @Override
    public void draw() {
        double haloSize = 3500.0;
        double coreSize = 2000.0;
        Image starImage = SpriteManager.getRandomStar(seed);
        graphics.drawImage(starImage, position.x*scale - starImage.getWidth()/2, position.y*scale - starImage.getHeight()/2);
        graphics.drawImage(sprites.STAR_HALO, position.x*scale - haloSize/2, position.y*scale - haloSize/2, haloSize, haloSize);
        graphics.drawImage(sprites.STAR_HALO_CORE, position.x*scale - coreSize/4, position.y*scale - coreSize/4, coreSize/2, coreSize/2);
    }

    @Override
    public void init() {
    }

    @Override
    public void end() {
    }
    
}
