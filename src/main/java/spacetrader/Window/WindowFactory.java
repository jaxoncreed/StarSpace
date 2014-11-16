/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacetrader.Window;

import spacetrader.Application.ApplicationType;

/**
 *
 * @author Tyler Allen <tallen40@gatech.edu>
 */
public class WindowFactory {
    public static Window getWindow(ApplicationType type){
        switch(type){
            case JavaFX:return new JavaFXWindow();
        }
        return null;
    }
}
