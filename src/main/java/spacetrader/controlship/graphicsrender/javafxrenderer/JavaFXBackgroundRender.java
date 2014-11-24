/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacetrader.controlship.graphicsrender.javafxrenderer;

import javafx.scene.paint.Color;
import spacetrader.game_model.gameLogic.Position;

/**
 *
 * @author Tyler Allen <tallen40@gatech.edu>
 */
public class JavaFXBackgroundRender extends JavaFXRenderer{
    private double x,y;
    private Position offset = new Position(0, 0);
    private static Position[] stars;
    static {
        stars = new Position[50];
        for (int i = 0; i < stars.length; i++) {
            stars[i] = new Position(Math.random(), Math.random());
        }
    }
    
    private static final double STAR_RADIUS = 1.0;
    
    public JavaFXBackgroundRender(double x,double y){
        this.x=x;
        this.y=y;
        
     }

    public void setOffset(Position offset) {
        this.offset = offset;
    }

    @Override
    public void init() {
    }

    @Override
    public void draw() {
        // fill black
        graphics.setFill(Color.BLACK);
        graphics.fillRect(0, 0, x, y);

        // parallax background images
        graphics.drawImage(sprites.BACKGROUND_4, -offset.x/20, -offset.y/20);
        graphics.drawImage(sprites.BACKGROUND_1, -offset.x/10, -offset.y/10);
        
        // parallax stars
//        graphics.setFill(Color.WHITE);
//        for (Position s : stars) {
//            graphics.fillOval((s.x*4*x - offset.x) % (x + STAR_RADIUS) - STAR_RADIUS, (s.y*4*y - offset.y) % (y + STAR_RADIUS) - STAR_RADIUS, STAR_RADIUS*2, STAR_RADIUS*2);
//        }
    }

    @Override
    public void end() {
    }
    
}
