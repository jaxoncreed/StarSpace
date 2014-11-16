/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacetrader.Application;

import spacetrader.MainCtrl;
import spacetrader.Window.JavaFXWindow;
import spacetrader.Window.Window;
import spacetrader.Window.WindowFactory;

/**
 *
 * @author Tyler Allen <tallen40@gatech.edu>
 */
public class ApplicationFactory {
    public static MainApplication getApplication(ApplicationType type,MainCtrl ctrl,Window window){
        switch(type){
            case JavaFX: return new JavaFXMainApplication(window,ctrl);
        }
        return null;
    }
}
