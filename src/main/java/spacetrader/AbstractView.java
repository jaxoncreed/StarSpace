/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacetrader;

/**
 *
 * @author Jackson
 */
public abstract class AbstractView {

    public static final int SCREEN_WIDTH = 1280;
    public static final int SCREEN_HEIGHT = 720; 

    public abstract void render();
    public abstract void hide();
    
}
