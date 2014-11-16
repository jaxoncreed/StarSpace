/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacetrader.Application;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import spacetrader.MainCtrl;
import spacetrader.Window.JavaFXWindow;
import spacetrader.Window.Window;

/**
 *
 * @author Tyler Allen <tallen40@gatech.edu>
 */
public class JavaFXMainApplication extends MainApplication{

    public JavaFXMainApplication(Window window, MainCtrl ctrl) {
        super(window, ctrl);
    }
    public static class JavaFXApp extends Application{
        private static JavaFXWindow  window;
        private static MainCtrl  mainCtrl;
        @Override
        public void start(Stage primaryStage) throws Exception {
            Pane pane=window.getPane();
            Scene scene=new Scene(pane);
            primaryStage.setScene(scene);
            mainCtrl.init();
            primaryStage.show();
        }
        public static void setCtrl(MainCtrl ctrl){
            mainCtrl=ctrl;
        }
        public static void setWindow(JavaFXWindow win){
            window=win;
        }
    }
    @Override
    public void init() {
        JavaFXApp.setCtrl(mainCtrl);
        JavaFXApp.setWindow((JavaFXWindow)window);
    }

    @Override
    public void run(String[] args) {
        JavaFXApp.launch(JavaFXApp.class,args);
    }

    @Override
    public void close() {
        Platform.exit();
    }
    
}
