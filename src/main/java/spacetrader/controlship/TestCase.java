/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacetrader.controlship;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * Test class inorder to not run the whole application to run test cases on the Real time view. I don't know why there is no test case folder or how to add one. 
 * @author Tyler Allen <tallen40@gatech.edu>
 */
public class TestCase extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        RealTimeShipView pane=new RealTimeShipView(new RealTimeShipController());
        Scene scene=new Scene(pane);
        scene.setOnKeyPressed((KeyEvent e)->{
            pane.handleKey(e);
        });
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
