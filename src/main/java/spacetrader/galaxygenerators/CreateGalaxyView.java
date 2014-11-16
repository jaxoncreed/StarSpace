package spacetrader.galaxygenerators;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import spacetrader.Window.Window;
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
import spacetrader.Window.JavaFXWindow;

/**
 *
 * @author fsanchez
 */
public class CreateGalaxyView {
    JavaFXWindow window;
    GalaxyGeneratorCtrl createGalaxyCtrl;
    Pane pane;
    
    public CreateGalaxyView(JavaFXWindow window, GalaxyGeneratorCtrl ccc) {
        this.window = window;
        createGalaxyCtrl = ccc;
        pane = new Pane();
    }
    
    public void renderGalaxyCreator() {
        try {
            window.loadFXML(pane);
        } catch (IOException ex) {
            Logger.getLogger(CreateGalaxyView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void removeGalaxyCreator() {
        window.clearFXML(pane);
    }
}
