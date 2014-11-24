/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacetrader.controlship.graphicsrender.javafxrenderer;

import javafx.scene.image.Image;



/**
 *
 * @author benhirsch
 */
public class SpriteManager {
    public static Image LASHER_FF;
    public static Image BACKGROUND_1;
    public static Image BACKGROUND_4;
    public static Image STAR_0;
    public static Image STAR_1;
    public static Image STAR_2;
    public static Image STAR_3;
    public static Image WORMHOLE_CORONA_TRANS;
   
    public SpriteManager() {
    	LASHER_FF = new Image(getClass().getResourceAsStream("lasher_ff.png"));
    	BACKGROUND_1 = new Image(getClass().getResourceAsStream("background1.png"));
    	BACKGROUND_4 = new Image(getClass().getResourceAsStream("background4.png"));
    	STAR_0 = new Image(getClass().getResourceAsStream("star0.png"));
    	STAR_1 = new Image(getClass().getResourceAsStream("star1.png"));
    	STAR_2 = new Image(getClass().getResourceAsStream("star2.png"));
    	STAR_3 = new Image(getClass().getResourceAsStream("star3.png"));
    	WORMHOLE_CORONA_TRANS = new Image(getClass().getResourceAsStream("wormhole_corona_66a.png"));
    }
    
}
