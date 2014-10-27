/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacetrader.controlship.graphicsrender.javafxrenderer;

import javafx.scene.canvas.GraphicsContext;
import spacetrader.controlship.graphicsrender.GraphicsRender;

/**
 *
 * @author Tyler Allen <tallen40@gatech.edu>
 */
public abstract class JavaFXRenderer extends GraphicsRender{
    protected GraphicsContext graphics;
    protected int scale;
    public void setGraphicsContext(GraphicsContext gc){
        graphics=gc;
    }
    public void setScale(int s){
        scale=s;
    }
}
