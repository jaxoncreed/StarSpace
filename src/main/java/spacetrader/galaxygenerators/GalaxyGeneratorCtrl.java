package spacetrader.galaxygenerators;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import spacetrader.MainCtrl;
import spacetrader.ViewCtrl;
import spacetrader.Window;

/**
 *
 * @author fsanchez
 */
public class GalaxyGeneratorCtrl extends ViewCtrl {
    CreateGalaxyView view;
    MainCtrl mainCtrl;
    EllipticalGalaxyGenerator generator;

    public GalaxyGeneratorCtrl(MainCtrl aParent, Window window) {
        super(aParent, window);
        view = new CreateGalaxyView(window, this);
        mainCtrl = aParent;
        generator = new EllipticalGalaxyGenerator("Fart Dust", 25, 1, 1, 3, 3, 1);
    }
    
    @Override
    public void startView() {
        view.renderGalaxyCreator();
        mainCtrl.setGalaxy(generator.generate());
        mainCtrl.controlShip();
    }

    @Override
    public void stopView() {
        view.removeGalaxyCreator();
    }
}
