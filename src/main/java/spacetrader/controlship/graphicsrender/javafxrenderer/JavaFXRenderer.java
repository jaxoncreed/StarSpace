/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacetrader.controlship.graphicsrender.javafxrenderer;

import javafx.scene.canvas.GraphicsContext;
import spacetrader.controlship.graphicsrender.GraphicsRender;
import javafx.scene.image.Image;
import javafx.scene.transform.Rotate;

/**
 *
 * @author Tyler Allen <tallen40@gatech.edu>
 */
public abstract class JavaFXRenderer extends GraphicsRender{
    protected GraphicsContext graphics;
    protected int scale;
    public static SpriteManager sprites = new SpriteManager();
    
    public void setGraphicsContext(GraphicsContext gc){
        graphics=gc;
    }

    public void setScale(int s){
        scale=s;
    }

    protected void drawRotatedImage(Image image, double angle, double tlpx, double tlpy) {
        angle = -Math.toDegrees(angle);
        this.graphics.save(); // saves the current state on stack, including the current transform
        rotate(angle, tlpx + image.getWidth() / 2, tlpy + image.getHeight() / 2);
        this.graphics.drawImage(image, tlpx, tlpy);
        this.graphics.restore(); // back to original state (before rotation)
    }

    private void rotate(double angle, double px, double py) {
        Rotate r = new Rotate(angle, px, py);
        this.graphics.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
    }

}
