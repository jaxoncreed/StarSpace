/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacetrader;

import java.util.ArrayList;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.stage.Stage;
import spacetrader.game_model.Inventory;
import spacetrader.game_model.Item;
import java.util.List;

/**
 *
 * @author Jackson Morgan
 */
public class Start extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Window window = new Window();
        MainCtrl mainCtrl = new MainCtrl(stage, window);
        List<Item> items = new ArrayList<>();
        items.add(new Item("Block", 5, 3));
        items.add(new Item("Brick", 5, 3));
        items.add(new Item("Barrak", 5, 3));
        items.add(new Item("Bloop", 5, 3));
        mainCtrl.makeTrade(new Inventory(items, 10));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
