
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
import spacetrader.Window;

/**
 *
 * @author Jackson Morgan
 */
public class MenuView implements Initializable {
    public Window window;
    public MenuCtrl menuCtrl;
   
    @FXML
    Button start;
    @FXML
    Pane background;
    @FXML
    Button exit;

    public MenuView() {};
    
    public MenuView(Window aWindow, MenuCtrl aMenuCtrl) {
        window = aWindow;
        menuCtrl = aMenuCtrl;
    }

    void renderMainMenu() {
        FXMLLoader loader = new FXMLLoader((getClass().getResource("MainMenu.fxml"))) ;
        loader.setController(this);
        try {
            window.loadFXML(loader);
        } catch (IOException ex) {
            Logger.getLogger(MenuView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        start.setOnAction((ActionEvent event) -> menuCtrl.newGame());
        exit.setOnAction((ActionEvent event) -> menuCtrl.closeApplication());
    }
    
    
}
