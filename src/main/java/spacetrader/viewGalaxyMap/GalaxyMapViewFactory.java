/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacetrader.viewGalaxyMap;

import spacetrader.AbstractView;
import spacetrader.Ctrl;
import spacetrader.MainCtrl;
import spacetrader.ViewFactory;
import spacetrader.Window.JavaFXWindow;
import spacetrader.Window.Window;

/**
 *
 * @author Tyler Allen <tallen40@gatech.edu>
 */
public class GalaxyMapViewFactory extends ViewFactory{

    @Override
    public AbstractView getView(Window win, Ctrl ctrl) {
        switch(win.getType()){
            case JavaFX:return new GalaxyMapView((JavaFXWindow)win,(GalaxyMapCtrl)ctrl);
        }
        return null;
    }
    
}
