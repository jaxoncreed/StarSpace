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
 * @author Ben Hirsch <tallen40@gatech.edu>
 */
public class JavaFXForegroundRender extends JavaFXRenderer{
    private double x,y;
    private Position offset = new Position(0, 0);
    private static Position[] stars;
    static {
        stars = new Position[500];
        for (int i = 0; i < stars.length; i++) {
            stars[i] = new Position(Math.random(), Math.random());
        }
    }
    
    private static final double STAR_RADIUS = 40.0;
    
    public JavaFXForegroundRender(double x,double y){
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
        // parallax stars
        graphics.setFill(Color.WHITE);
        for (int i = 0; i < stars.length; i++) {
            Position s = stars[i];
            double xPos = (s.x*8.0*x - offset.x*4.0) % (x*8.0 + STAR_RADIUS*2.0) - STAR_RADIUS;
            double yPos = (s.y*8.0*y - offset.y*4.0) % (y*8.0 + STAR_RADIUS*2.0) - STAR_RADIUS;
            if (xPos < 0)
                xPos = (x + STAR_RADIUS*2.0) + xPos;
            if (yPos < 0)
                yPos = (y + STAR_RADIUS*2.0) + yPos;
            switch (i % 4) {
                case 0:
                graphics.drawImage(sprites.STAR_0, xPos, yPos);
                break;
                case 1:
                graphics.drawImage(sprites.STAR_1, xPos, yPos);
                break;
                case 2:
                graphics.drawImage(sprites.STAR_2, xPos, yPos);
                break;
                case 3:
                graphics.drawImage(sprites.STAR_3, xPos, yPos);
                break;
            }
        }
    }

    @Override
    public void end() {
    }
    
}
