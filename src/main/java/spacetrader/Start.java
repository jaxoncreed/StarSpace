/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacetrader;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.stage.Stage;

/**
 *
 * @author Jackson Morgan
 */
public class Start extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Window window = new Window();

	    stage.setWidth(AbstractView.SCREEN_WIDTH);
	    stage.setHeight(AbstractView.SCREEN_HEIGHT);

        MainCtrl mainCtrl = new MainCtrl(stage, window);
        mainCtrl.mainMenu();
    }

    public static void main(String[] args) {
        launch(args);
    }
}