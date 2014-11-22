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
import spacetrader.MultiKeyPressEventHandler;
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
        private static JavaFXMainApplication mainApp;
        @Override
        public void start(Stage primaryStage) throws Exception {
            Pane pane=mainApp.getWindow().getPane();
            Scene scene=new Scene(pane);
            primaryStage.setScene(scene);
            mainApp.mainCtrl.init();
            mainApp.getWindow().setKeyHandle(()->{
                //Do nothing in the key handler by default
            });
            MultiKeyPressEventHandler handler=new MultiKeyPressEventHandler((MultiKeyPressEventHandler.MultiKeyEvent event) -> {
                mainApp.getWindow().keyHandle(event);//Make the key handler for the window trigger on events
            });
            primaryStage.show();

        }
        public static void setApp(JavaFXMainApplication app){
            mainApp=app;
        }
    }
    @Override
    public void init() {
       JavaFXApp.setApp(this);
    }

    @Override
    public void run(String[] args) {
        JavaFXApp.launch(JavaFXApp.class,args);
    }

    @Override
    public void close() {
        Platform.exit();
    }
    public JavaFXWindow getWindow(){
        return (JavaFXWindow)window;
    }
}
