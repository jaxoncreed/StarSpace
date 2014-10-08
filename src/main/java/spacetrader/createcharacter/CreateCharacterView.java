/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacetrader.createcharacter;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import spacetrader.Window;

/**
 *
 * @author Jackson Morgan
 */
public class CreateCharacterView implements Initializable {
    public Window window;
    public CreateCharacterCtrl createCharacterCtrl;
    
    @FXML
    Button pilotingNegative;
    @FXML
    Button pilotingPositive;
    @FXML
    Button navigationNegative;
    @FXML
    Button navigationPositive;
    @FXML
    Button engineeringNegative;
    @FXML
    Button engineeringPositive;
    @FXML
    Button charismaNegative;
    @FXML
    Button charismaPositive;
    @FXML
    Button cancel;
    @FXML
    Label points;
    @FXML
    Label pilotingPoints;
    @FXML
    Label charismaPoints;
    @FXML
    Label engineeringPoints;
    @FXML
    Label navigationPoints;


    @FXML
    Button done;

    @FXML
    TextField name;
    
    CreateCharacterView(Window window, CreateCharacterCtrl ccc) {
        this.window = window;
        createCharacterCtrl = ccc;
    }

    void renderCharacterCreator() {
        FXMLLoader loader = new FXMLLoader((getClass().getResource("CharacterCreator.fxml"))) ;
        loader.setController(this);
        try {
            window.loadFXML(loader.load());
        } catch (IOException ex) {
            Logger.getLogger(CreateCharacterView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void removeCharacterCreator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }

}
