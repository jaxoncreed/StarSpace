package spacetrader.maketrade;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    @FXML
    Button finishButton;
    @FXML
    VBox yourItemsContainer;
    @FXML
    VBox theirItemsContainer;
    
    
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

    public void renderMakeTrade(Item[] items, int woolongs, Item[] items0) {
        System.out.println("Render Make Trade");
        FXMLLoader loader = new FXMLLoader((getClass().getResource("MakeTrade.fxml"))) ;
        loader.setController(this);
        try {
            curPane = loader.load();
            window.loadFXML(curPane);
        } catch (IOException ex) {
            Logger.getLogger(MakeTradeView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void removeMakeTrade() {
        System.out.println("remove make trade");
    }
}