
package spacetrader.menu;

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
import spacetrader.Window.Window;

/**
 *
 * @author Jackson Morgan
 */
public class MenuView extends AbstractView implements Initializable {
    public Window window;
    public MenuCtrl menuCtrl;
    public Pane curPane;
   
    @FXML
    Button start;
    @FXML
    Pane background;
    @FXML
    Button exit;
    @FXML
    Button loadButton;

    public MenuView() {};
    
    public MenuView(Window aWindow, MenuCtrl aMenuCtrl) {
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
            Logger.getLogger(MenuView.class.getName()).log(Level.SEVERE, null, ex);
        }
        loadButton.setOnAction((ActionEvent event) -> menuCtrl.loadGame());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        start.setOnAction((ActionEvent event) -> menuCtrl.newGame());
        exit.setOnAction((ActionEvent event) -> menuCtrl.closeApplication());
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