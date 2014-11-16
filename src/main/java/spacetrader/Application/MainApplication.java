/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacetrader.Application;

import spacetrader.MainCtrl;
import spacetrader.Window.Window;

/**
 *
 * @author Tyler Allen <tallen40@gatech.edu>
 */
public abstract class MainApplication {
    private ApplicationType type;
    protected Window window;
    protected MainCtrl mainCtrl;
    public MainApplication(Window window,MainCtrl ctrl){
        this.window=window;
        this.mainCtrl=ctrl;
    }
    public Window getWindow(){
        return window;
    }
    public abstract void init();
    public abstract void run(String[] args);
    public abstract void close();
}
