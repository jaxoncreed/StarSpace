/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacetrader.buyship;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import spacetrader.AbstractView;
import spacetrader.Window;
import spacetrader.game_model.Item;
import spacetrader.maketrade.MakeTradeCtrl;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import spacetrader.controlship.ControlShipView;

/**
 *
 * @author fsanchez
 */
public class BuyShipView implements Initializable {
    public Window window;
    public BuyShipCtrl buyShipCtrl;
    public Pane curPane;

    @FXML
    Button finishButton;
    @FXML
    Button buy1;
    @FXML
    Button buy2;
    @FXML
    Button buy3;
    @FXML
    Button buy4;
    @FXML
    Label money;
    
    public BuyShipView(Window window, BuyShipCtrl ctrl) {
        this.window = window;
        buyShipCtrl = ctrl;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void renderBuyShip(int woolongs) {
        FXMLLoader loader = new FXMLLoader((getClass().getResource("BuyShip.fxml")));
        loader.setController(this);
        try {
            curPane = loader.load();
            window.loadFXML(curPane);
        }
        catch (IOException ex) {
            Logger.getLogger(BuyShipView.class.getName()).log(Level.SEVERE, null, ex);
        }
        finishButton.setOnAction((ActionEvent event)->{
            buyShipCtrl.shipControl();
        });
        buy1.setOnAction((ActionEvent event)->{buyShipCtrl.buy1();});
        buy2.setOnAction((ActionEvent event)->{buyShipCtrl.buy2();});
        buy3.setOnAction((ActionEvent event)->{buyShipCtrl.buy3();});
        buy4.setOnAction((ActionEvent event)->{buyShipCtrl.buy4();});
        updateMoneyLabel();
    }
    public void removeBuyShip() {
        window.clearFXML(curPane);
        curPane = null;
    }
    public void updateMoneyLabel() {
        money.setText("$" + buyShipCtrl.getPlayerWoolongs());
    }
}
