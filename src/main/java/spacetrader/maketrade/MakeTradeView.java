package spacetrader.maketrade;

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
import spacetrader.Window.Window;
import spacetrader.game_model.gameLogic.Item;
import spacetrader.maketrade.MakeTradeCtrl;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import spacetrader.Window.JavaFXWindow;
import spacetrader.controlship.ControlShipView;
import spacetrader.game_model.Faction;
import spacetrader.game_model.Ship;

/**
 *
 * @author Jackson Morgan
 */
public class MakeTradeView extends AbstractView implements Initializable {
    public JavaFXWindow window;
    public MakeTradeCtrl makeTradeCtrl;
    public Pane curPane;
    List<Item> itemsP;
    List<Item> itemsM;
    @FXML
    Button finishButton1;
    @FXML
    Button finishButton2;
    @FXML
    VBox yourItemsContainer;
    @FXML
    VBox theirItemsContainer;
    @FXML
    Label money1;
    @FXML
    Label money2;
    @FXML
    SplitPane splitpane;
    @FXML
    Pane anchor;
    @FXML
    Button buy1;
    @FXML
    Button buy2;
    @FXML
    Button buy3;
    @FXML
    Button buy4;
    @FXML
    Button buyacc;
    @FXML
    Button buyspeed;
    @FXML
    Button buyhp;
    @FXML
    Button buylas;
    @FXML
    Label shipAcc;
    @FXML
    Label shipSpeed;
    @FXML
    Label shipHealth;
    @FXML
    Label shipName;
    @FXML
    Label lasers;
    @FXML
    Button finishButton3;    
    @FXML
    Label money3;
    
    public MakeTradeView(JavaFXWindow window, MakeTradeCtrl ctrl) {
        this.window = window;
        this.makeTradeCtrl = ctrl;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
    @Override
    public void hide() {
        window.clearFXML(curPane);
        curPane = null;
    }

    public void render() {
        itemsP=(ArrayList<Item>) makeTradeCtrl.getItemsPlayer();
        itemsM=(ArrayList<Item>) makeTradeCtrl.getItemsStore();
        FXMLLoader loader = new FXMLLoader((getClass().getResource("MakeTrade.fxml"))) ;
        loader.setController(this);
        try {
            curPane = loader.load();
            window.loadFXML(curPane);
        } catch (IOException ex) {
            Logger.getLogger(MakeTradeView.class.getName()).log(Level.SEVERE, null, ex);
        }
        buy1.setOnAction((ActionEvent event)->{makeTradeCtrl.buy1();
        updateShipLabels(makeTradeCtrl.buyUpgrade("yeah"));
        updateMoneyLabel();});
        buy2.setOnAction((ActionEvent event)->{makeTradeCtrl.buy2();
        updateShipLabels(makeTradeCtrl.buyUpgrade("yeah"));
        updateMoneyLabel();});
        buy3.setOnAction((ActionEvent event)->{makeTradeCtrl.buy3();
        updateShipLabels(makeTradeCtrl.buyUpgrade("yeah"));
        updateMoneyLabel();});
        buy4.setOnAction((ActionEvent event)->{makeTradeCtrl.buy4();
        updateShipLabels(makeTradeCtrl.buyUpgrade("yeah"));
        updateMoneyLabel();});
        buyacc.setOnAction((ActionEvent event)->{updateShipLabels(makeTradeCtrl.buyUpgrade("a"));
        updateMoneyLabel();});
        buyspeed.setOnAction((ActionEvent event)->{updateShipLabels(makeTradeCtrl.buyUpgrade("s"));
        updateMoneyLabel();});
        buyhp.setOnAction((ActionEvent event)->{updateShipLabels(makeTradeCtrl.buyUpgrade("hp"));
        updateMoneyLabel();});
        buylas.setOnAction((ActionEvent event)->{updateShipLabels(makeTradeCtrl.buyUpgrade("las"));
        updateMoneyLabel();});
        updateMoneyLabel();
        updateShipLabels(makeTradeCtrl.buyUpgrade("yeah"));
        finishButton1.setOnAction((ActionEvent event)->{
            makeTradeCtrl.returnToControlShip();
        });
        finishButton2.setOnAction((ActionEvent event)->{
            makeTradeCtrl.returnToControlShip();
        });
        finishButton3.setOnAction((ActionEvent event)->{
            makeTradeCtrl.returnToControlShip();
        });
        splitpane.setPrefWidth(window.getWidth());
        splitpane.setPrefHeight(window.getHeight());
        anchor.setPrefWidth(window.getWidth());
        anchor.setPrefHeight(window.getHeight());

        updateLists();
    }
    public void updateLists(){
        itemsP=makeTradeCtrl.getItemsPlayer();
        itemsM=makeTradeCtrl.getItemsStore();

        yourItemsContainer.getChildren().clear();
        for(Item i:itemsP){
            HBox h=new HBox(); 
            Button b=new Button();
            b.setText(i.getName()+" ("+makeTradeCtrl.getPlayerItemAmount(i)+"): ₩"+
                    makeTradeCtrl.getCost(i));
            b.setOnAction((ActionEvent event)->{
                makeTradeCtrl.sell(i, 1);
                b.setText(i.getName()+" ("+makeTradeCtrl.getPlayerItemAmount(i)+"): ₩"+
                    makeTradeCtrl.getCost(i));
                updateLists();
            });
            h.getChildren().add(b);
            yourItemsContainer.getChildren().add(h);
        }
        theirItemsContainer.getChildren().clear();
        for(Item i:itemsM){
            HBox h=new HBox(); 
            Button b=new Button();
            b.setText(i.getName()+" ("+makeTradeCtrl.getStoreItemAmount(i) + "): ₩"+
                    makeTradeCtrl.getCost(i));
            b.setOnAction((ActionEvent event)->{
                makeTradeCtrl.buy(i, 1);
                b.setText(i.getName()+" ("+makeTradeCtrl.getStoreItemAmount(i) + "): ₩"+
                    makeTradeCtrl.getCost(i));
                updateLists();
            });
            h.getChildren().add(b);
            theirItemsContainer.getChildren().add(h);
        }
        money1.setText("₩"+makeTradeCtrl.getPlayerMoney());
        money2.setText("₩"+makeTradeCtrl.getPlayerMoney());

    }
    public void updateMoneyLabel() {
        money2.setText("₩" + makeTradeCtrl.getPlayerMoney());
        money1.setText("₩" + makeTradeCtrl.getPlayerMoney());
        money3.setText("₩" + makeTradeCtrl.getPlayerMoney());
    }
    public void updateShipLabels(Ship ship) {
        shipAcc.setText("Acceleration: " + Math.round(ship.linearThrust*1000) + "Gm/s/s");
        shipSpeed.setText("Top Speed: " + Math.round(ship.maxLinearSpeed*100) + "Gm/s");
        shipHealth.setText("Health: " + ship.maxHealth + "HP");
        lasers.setText("Laser Power: " + ship.laserBeams + "YW"); 
        shipName.setText("Ship: " + ship.name);
    }
        
    public void removeMakeTrade() {
    }
}