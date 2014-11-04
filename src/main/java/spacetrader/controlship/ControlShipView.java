
package spacetrader.controlship;

import spacetrader.menu.*;
import java.io.IOException;
import java.net.URL;
import java.util.List;
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
import spacetrader.game_model.Planet;

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
    @FXML
    Button saveButton;
    
    
    List<Planet> planets;
    Planet tempPlanet;
    
    
    public ControlShipView() {};
    
    public ControlShipView(Window aWindow, ControlShipCtrl aShipCtrl) {
        window = aWindow;
        shipCtrl = aShipCtrl;
    }

    void renderPilotingShip() {
        FXMLLoader loader = new FXMLLoader((getClass().getResource("ControlShip.fxml"))) ;
        loader.setController(this);
        try {
            curPane = loader.load();
            window.loadFXML(curPane);
        } catch (IOException ex) {
            Logger.getLogger(ControlShipView.class.getName()).log(Level.SEVERE, null, ex);
        }
        trade.setOnAction((ActionEvent event)-> shipCtrl.newTrade());
        travel.setOnAction((ActionEvent event)->{
            travel();
        });
        //Adding event listener for save button
        saveButton.setOnAction((ActionEvent event) -> shipCtrl.saveGame());
        planets=shipCtrl.getPlanets();
        tempPlanet=shipCtrl.getPlanet();
        planets.remove(shipCtrl.getPlanet());
        updateList();
        setPlanet(tempPlanet);
    }
    void updateList(){
        list.getChildren().clear();
        for(Planet p:planets){
            Button b=new Button();
            b.setText(p.getName());
            b.setOnAction((ActionEvent event)->{
                selectPlanet(p);
            });
            list.getChildren().add(b);
        }
    }
    private void selectPlanet(Planet p){
        tempPlanet=p;
    }
    private void setPlanet(Planet p){
        planets.remove(p);
        planets.add(tempPlanet);
        shipCtrl.setPlanet(p);
    }
    private void travel(){
        setPlanet(tempPlanet);
        updateList();
        name.setText(shipCtrl.getPlanet().getName());
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

    void remove() {
        window.clearFXML(curPane);
        curPane=null;
    }
    
    
}
