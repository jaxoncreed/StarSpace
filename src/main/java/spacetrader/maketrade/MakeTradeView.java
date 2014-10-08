package spacetrader.menu;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import spacetrader.AbstractView;
import spacetrader.Window;
import spacetrader.game_model.Item;
import spacetrader.maketrade.MakeTradeCtrl;

/**
 *
 * @author Jackson Morgan
 */
public class MakeTradeView extends AbstractView implements Initializable {
    public Window window;
    public MenuCtrl menuCtrl;
    public Pane curPane;

    public MakeTradeView() {};

    public MakeTradeView(Window window, MakeTradeCtrl aThis) {
        System.out.println("make trade view");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @Override
    public void render() {

    }

    @Override
    public void hide() {

    }    

    public void renderMakeTrade(Item[] items, int woolongs, Item[] items0) {
        System.out.println("Render Make Trade");
    }

    public void removeMakeTrade() {
        System.out.println("remove make trade");
    }
}