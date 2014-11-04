/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacetrader.controlship.graphicsrender.javafxrenderer;

import javafx.scene.paint.Color;

/**
 *
 * @author Tyler Allen <tallen40@gatech.edu>
 */
public class JavaFXBackgroundRender extends JavaFXRenderer{
    private double x,y;
    public JavaFXBackgroundRender(double x,double y){
        this.x=x;
        this.y=y;
    }
    @Override
    public void init() {
    }

    @Override
    public void draw() {
        this.graphics.setFill(Color.BLACK);
        this.graphics.fillRect(0, 0, x, y);
    }

    @Override
    public void end() {
    }
    
}
