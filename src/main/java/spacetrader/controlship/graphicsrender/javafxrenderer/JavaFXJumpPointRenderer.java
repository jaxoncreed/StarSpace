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
public class JavaFXJumpPointRenderer extends JavaFXRenderer{
    private JumpPoint jumpPoint;
    private Position position;
    public JavaFXJumpPointRenderer(JumpPoint j, Position normalize) {
        this.position = normalize;
        this.jumpPoint = j;
    }
    
    
    
    @Override
    public void draw() {
        draw(0.0);
    }
    
    public void draw(double step) {
        this.graphics.setFill(Color.BLACK);
        double centerRadius = 60.0;
        this.graphics.fillOval(position.x*scale - centerRadius, position.y*scale - centerRadius, centerRadius*2, centerRadius*2);
        drawRotatedImage(sprites.WORMHOLE_CORONA_TRANS, step/50, (position.x*scale-sprites.WORMHOLE_CORONA_TRANS.getWidth()/2), (position.y*scale-sprites.WORMHOLE_CORONA_TRANS.getHeight()/2));
        drawRotatedImage(sprites.WORMHOLE_CORONA_TRANS, -step/50, (position.x*scale-sprites.WORMHOLE_CORONA_TRANS.getWidth()/2), (position.y*scale-sprites.WORMHOLE_CORONA_TRANS.getHeight()/2));
    }

    @Override
    public void init() {
    }

    @Override
    public void end() {
    }
    
}
