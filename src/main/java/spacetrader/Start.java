/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacetrader;

import spacetrader.Window.Window;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.stage.Stage;
import spacetrader.Application.ApplicationFactory;
import spacetrader.Application.ApplicationType;
import spacetrader.Application.MainApplication;
import spacetrader.Window.JavaFXWindow;
import spacetrader.Window.WindowFactory;

/**
 *
 * @author Jackson Morgan
 */
public class Start {
    public static void main(String[] args) {
        ApplicationType type=ApplicationType.JavaFX;
        Window window=WindowFactory.getWindow(type);
        MainCtrl ctrl=new MainCtrl(window);
        MainApplication app=ApplicationFactory.getApplication(type, ctrl, window);
        app.init();
        app.run(args);
    }
}