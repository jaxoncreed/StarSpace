/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacetrader;

import spacetrader.Window.Window;

/**
 *
 * @author Tyler Allen <tallen40@gatech.edu>
 */
public abstract class ViewFactory {
    public abstract AbstractView getView(Window win,Ctrl ctrl);
}
