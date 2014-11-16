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
 * @author Jackson Morgan
 */
public class MakeTradeView extends AbstractView implements Initializable {
    public Window window;
    public MakeTradeCtrl makeTradeCtrl;
    public Pane curPane;
    List<Item> itemsP;
    List<Item> itemsM;
    @FXML
    Button finishButton;
    @FXML
    VBox yourItemsContainer;
    @FXML
    VBox theirItemsContainer;
    @FXML
    Label money;
    
    
    public MakeTradeView(Window window, MakeTradeCtrl ctrl) {
        this.window = window;
        this.makeTradeCtrl = ctrl;
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

    public void renderMakeTrade(List<Item> items, int woolongs, List<Item> items0) {
        itemsP=(ArrayList<Item>) items;
        itemsM=(ArrayList<Item>) items0;
        System.out.println("Render Make Trade");
        FXMLLoader loader = new FXMLLoader((getClass().getResource("MakeTrade.fxml"))) ;
        loader.setController(this);
        try {
            curPane = loader.load();
            window.loadFXML(curPane);
            System.out.println("Loaded");
        } catch (IOException ex) {
            Logger.getLogger(MakeTradeView.class.getName()).log(Level.SEVERE, null, ex);
        }
        finishButton.setOnAction((ActionEvent event)->{
            makeTradeCtrl.shipControl();
        });
        updateLists();
    }
    public void updateLists(){
        itemsP=makeTradeCtrl.getItemsPlayer();
        itemsM=makeTradeCtrl.getItemsStore();

        yourItemsContainer.getChildren().clear();
        for(Item i:itemsP){
            HBox h=new HBox(); 
            Button b=new Button();
            b.setText(i.getName()+" #"+makeTradeCtrl.getPlayerItemAmount(i));
            b.setOnAction((ActionEvent event)->{
                makeTradeCtrl.sell(i, 1);
                b.setText(i.getName()+" #"+makeTradeCtrl.getPlayerItemAmount(i));
                updateLists();
            });
            h.getChildren().add(b);
            yourItemsContainer.getChildren().add(h);
        }
        theirItemsContainer.getChildren().clear();
        for(Item i:itemsM){
            HBox h=new HBox(); 
            Button b=new Button();
            b.setText(i.getName()+" #"+makeTradeCtrl.getStoreItemAmount(i));
            b.setOnAction((ActionEvent event)->{
                makeTradeCtrl.buy(i, 1);
                b.setText(i.getName()+" #"+makeTradeCtrl.getStoreItemAmount(i));
                updateLists();
            });
            h.getChildren().add(b);
            theirItemsContainer.getChildren().add(h);
        }
        money.setText("$"+makeTradeCtrl.getPlayerMoney());

    }
    public void removeMakeTrade() {
        System.out.println("remove make trade");
    }
}