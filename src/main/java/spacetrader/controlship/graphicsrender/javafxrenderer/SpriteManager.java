/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacetrader.controlship.graphicsrender.javafxrenderer;

import javafx.scene.image.Image;
import java.util.Random;


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
    public static Image ARID_01;
    public static Image STAR_BLUE;
    public static Image STAR_ORANGE;
    public static Image STAR_RED;
    public static Image STAR_RED2;
    public static Image STAR_WHITE;
    public static Image STAR_YELLOW;
    public static Image STAR_HALO;
    public static Image STAR_HALO_CORE;
    public static Image DIGITAL_BG_ALPHA;
    public static Image MINIMAP_BG;
    public static Image MINIMAP_FRAME;

   
    public SpriteManager() {
    	LASHER_FF = new Image(getClass().getResourceAsStream("lasher_ff.png"));

    	BACKGROUND_1 = new Image(getClass().getResourceAsStream("background1.png"));
    	BACKGROUND_4 = new Image(getClass().getResourceAsStream("background4.png"));

    	STAR_0 = new Image(getClass().getResourceAsStream("star0.png"));
    	STAR_1 = new Image(getClass().getResourceAsStream("star1.png"));
    	STAR_2 = new Image(getClass().getResourceAsStream("star2.png"));
    	STAR_3 = new Image(getClass().getResourceAsStream("star3.png"));

    	WORMHOLE_CORONA_TRANS = new Image(getClass().getResourceAsStream("wormhole_corona_66a.png"));

    	ARID_01 = new Image(getClass().getResourceAsStream("arid01.png"));

        STAR_BLUE = new Image(getClass().getResourceAsStream("star_blue.png"));
        STAR_ORANGE = new Image(getClass().getResourceAsStream("star_orange.png"));
        STAR_RED = new Image(getClass().getResourceAsStream("star_red.png"));
        STAR_RED2 = new Image(getClass().getResourceAsStream("star_red2.png"));
        STAR_WHITE = new Image(getClass().getResourceAsStream("star_white.png"));
        STAR_YELLOW = new Image(getClass().getResourceAsStream("star_yellow.png"));

    	STAR_HALO = new Image(getClass().getResourceAsStream("star_halo.png"));
    	STAR_HALO_CORE = new Image(getClass().getResourceAsStream("star_halo_core.png"));

        DIGITAL_BG_ALPHA = new Image(getClass().getResourceAsStream("digital_bg_alpha.png"));

        MINIMAP_BG = new Image(getClass().getResourceAsStream("minimap_bg.png"));
        MINIMAP_FRAME = new Image(getClass().getResourceAsStream("minimap_frame.png"));
    }

    public static Image getRandomStar(double seed) {
        Image[] stars = {STAR_BLUE, STAR_ORANGE, STAR_RED, STAR_RED2, STAR_WHITE, STAR_YELLOW};
        Random generator = new Random((long)seed);
        double r = generator.nextDouble();
        int n = (int)(r * stars.length);
        return stars[n];
    }
    
}
