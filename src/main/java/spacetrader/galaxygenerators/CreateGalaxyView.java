package spacetrader.galaxygenerators;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import spacetrader.Window;

/**
 *
 * @author fsanchez
 */
public class CreateGalaxyView {
    Window window;
    GalaxyGeneratorCtrl createGalaxyCtrl;
    
    public CreateGalaxyView(Window window, GalaxyGeneratorCtrl ccc) {
        this.window = window;
        createGalaxyCtrl = ccc;
    }
}
