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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import spacetrader.AbstractView;
import spacetrader.Window.JavaFXWindow;
import spacetrader.Window.Window;
import spacetrader.game_model.Skillset;

/**
 *
 * @author Jackson Morgan
 */
public class CreateCharacterView extends AbstractView implements Initializable {
    private JavaFXWindow window;
    private CreateCharacterCtrl createCharacterCtrl;
    private Pane curPane;
    
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
    
    private int pointsToAward;
    private Skillset skill;
    private int piloting,navigation,engineering,charisma;
    
    CreateCharacterView(JavaFXWindow window, CreateCharacterCtrl ccc) {
        this.window = window;
        createCharacterCtrl = ccc;
        pointsToAward = 5;
        piloting = navigation = engineering = charisma = 0;
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
    private void setup(){
        pointsToAward=5;
        piloting=navigation=charisma=engineering=0;
        points.setText(""+pointsToAward);
        pilotingPositive.setOnAction((ActionEvent event) -> {
            if(pointsToAward>0){
                pointsToAward--;
                piloting++;
            }
            points.setText(""+pointsToAward);
            pilotingPoints.setText(""+piloting);
        });
        pilotingNegative.setOnAction((ActionEvent event) -> {
            if(piloting>0){
                pointsToAward++;
                piloting--;
            }
            points.setText(""+pointsToAward);
            pilotingPoints.setText(""+piloting);

        });
        navigationPositive.setOnAction((ActionEvent event) -> {
            if(pointsToAward>0){
                pointsToAward--;
                navigation++;
            }
            points.setText(""+pointsToAward);            
            navigationPoints.setText(""+navigation);

        });
        navigationNegative.setOnAction((ActionEvent event) -> {
            if(navigation>0){
                pointsToAward++;
                navigation--;
            }
            points.setText(""+pointsToAward);
            navigationPoints.setText(""+navigation);

        });
        engineeringPositive.setOnAction((ActionEvent event) -> {
            if(pointsToAward>0){
                pointsToAward--;
                engineering++;
            }
            points.setText(""+pointsToAward);
            engineeringPoints.setText(""+engineering);

        });
        engineeringNegative.setOnAction((ActionEvent event) -> {
            if(engineering>0){
                pointsToAward++;
                engineering--;
            }
            points.setText(""+pointsToAward);
            engineeringPoints.setText(""+engineering);

        });
        charismaPositive.setOnAction((ActionEvent event) -> {
            if(pointsToAward>0){
                pointsToAward--;
                charisma++;
            }
            points.setText(""+pointsToAward);
            charismaPoints.setText(""+charisma);
        });
        charismaNegative.setOnAction((ActionEvent event) -> {
            if(charisma>0){
                pointsToAward++;
                charisma--;
            }
            points.setText(""+pointsToAward);
            charismaPoints.setText(""+charisma);
        });
        done.setOnAction((ActionEvent event) -> {
            skill=new Skillset();
            skill.setCharisma(charisma);
            skill.setEngineering(engineering);
            skill.setNavigation(navigation);
            skill.setPiloting(piloting);
            skill.setName(name.getText());
            System.out.println("Engineering:"+skill.getEngineering());
            System.out.println("Charisma:"+skill.getCharisma());
            System.out.println("Navigation:"+skill.getNavigation());
            System.out.println("Piloting:"+skill.getPiloting());
            System.out.println("Name:"+skill.getName());
            createCharacterCtrl.creationDone(skill);
        });    
        cancel.setOnAction((ActionEvent ever)->{
            createCharacterCtrl.backout();
        });

    }
    @Override
    public void render() {
        FXMLLoader loader = new FXMLLoader((getClass().getResource("CharacterCreator.fxml"))) ;
        loader.setController(this);
        try {
            curPane = loader.load();
            window.loadFXML(curPane);
        } catch (IOException ex) {
            Logger.getLogger(CreateCharacterView.class.getName()).log(Level.SEVERE, null, ex);
        }
        setup();
    }

    @Override
    public void hide() {
        window.clearFXML(curPane);
        curPane = null;
    }
}
