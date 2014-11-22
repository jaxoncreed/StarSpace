/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacetrader.controlship;

import spacetrader.AbstractView;
import spacetrader.Ctrl;
import spacetrader.ViewFactory;
import spacetrader.Window.Window;

/**
 *
 * @author Tyler Allen <tallen40@gatech.edu>
 */
public class ControlShipViewFactory extends ViewFactory{

    @Override
    public AbstractView getView(Window win, Ctrl ctrl) {
        switch(win.getType()){
            case JavaFX: return new ControlShipView(win,(ControlShipCtrl)ctrl);
        }
        return null;
    }
    
}
