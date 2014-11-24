
package spacetrader.gameMenu;

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
import spacetrader.Window.Window;

/**
 *
 * @author Jackson Morgan
 */
public class GameMenuView extends AbstractView implements Initializable {
    public JavaFXWindow window;
    public GameMenuCtrl menuCtrl;
    public Pane curPane;
   
    @FXML
    Button save;
    @FXML
    Pane background;
    @FXML
    Button returnGame;
    @FXML
    Button menu;

    public GameMenuView() {};
    
    public GameMenuView(JavaFXWindow aWindow, GameMenuCtrl aMenuCtrl) {
        window = aWindow;
        menuCtrl = aMenuCtrl;
    }

    public void renderMainMenu() {
        FXMLLoader loader = new FXMLLoader((getClass().getResource("MainMenu.fxml"))) ;
        loader.setController(this);
        try {
            curPane = loader.load();
            window.loadFXML(curPane);
        } catch (IOException ex) {
            Logger.getLogger(GameMenuView.class.getName()).log(Level.SEVERE, null, ex);
        }
        menu.setOnAction((ActionEvent event)->{
            menuCtrl.mainMenu();
        });
        save.setOnAction((ActionEvent event)->{
            menuCtrl.saveGame();
        });
        returnGame.setOnAction((ActionEvent event)->{
            menuCtrl.retunToGame();
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void removeMainMenu() {
        window.clearFXML(curPane);
        curPane = null;
    }

    @Override
    public void render() {
        renderMainMenu();
    }

    @Override
    public void hide() {
        removeMainMenu();
    }
    
    
}