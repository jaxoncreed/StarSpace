/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacetrader.createGalaxy;

import spacetrader.Ctrl;
import spacetrader.CtrlViewTypes;
import spacetrader.MainCtrl;
import spacetrader.ViewCtrl;
import spacetrader.Window.Window;
import spacetrader.galaxygenerators.EllipticalGalaxyGenerator;
import spacetrader.galaxygenerators.GalaxyGenerator;
import spacetrader.game_model.system.Galaxy;
import spacetrader.game_model.GameModel;

/**
 *
 * @author Tyler Allen <tallen40@gatech.edu>
 */
public class CreateGalaxyCtrl extends ViewCtrl{

    public CreateGalaxyCtrl(Ctrl parent, Window window) {
        super(parent, window);
        GalaxyGenerator generator=new EllipticalGalaxyGenerator();
        Galaxy gal=generator.generate();
        GameModel.get().setGalaxy(gal);
        GameModel.get().getPlayer().setSystem(gal.getSystems().get(0));
        ((MainCtrl)parent).switchViews(CtrlViewTypes.ControlShip);
    }
}
