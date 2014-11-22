/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacetrader.createcharacter;

import spacetrader.AbstractView;
import spacetrader.Ctrl;
import spacetrader.ViewFactory;
import spacetrader.Window.JavaFXWindow;
import spacetrader.Window.Window;
import spacetrader.menu.MenuCtrl;
import spacetrader.menu.MenuView;

/**
 *
 * @author Tyler Allen <tallen40@gatech.edu>
 */
public class CreateCharacterViewFactory extends ViewFactory{
    @Override
    public AbstractView getView(Window win,Ctrl ctrl) {
        switch(win.getType()){
            case JavaFX: return new CreateCharacterView((JavaFXWindow)win,(CreateCharacterCtrl)ctrl);
        }
        return null;
    }
}
