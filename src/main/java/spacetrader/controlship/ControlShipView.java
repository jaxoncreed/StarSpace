
package spacetrader.controlship;

import spacetrader.menu.*;
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
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import spacetrader.Window;

/**
 *
 * @author Jackson Morgan
 */
public class ControlShipView implements Initializable {
    public Window window;
    public ControlShipCtrl shipCtrl;
    public Pane curPane;
   
    @FXML
    Button travel;   
    @FXML
    Button switchLOD;
    @FXML
    VBox list;
    @FXML
    HBox actions;
    @FXML 
    Label name; 
    @FXML 
    Button trade;
    
    public ControlShipView() {};
    
    public ControlShipView(Window aWindow, ControlShipCtrl aMenuCtrl) {
        window = aWindow;
        shipCtrl = aMenuCtrl;
    }

    void renderMainMenu() {
        FXMLLoader loader = new FXMLLoader((getClass().getResource("ControlShip.fxml"))) ;
        loader.setController(this);
        try {
            curPane = loader.load();
            window.loadFXML(curPane);
        } catch (IOException ex) {
            Logger.getLogger(ControlShipView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

    void removeMainMenu() {
        window.clearFXML(curPane);
    }
    
    
}
