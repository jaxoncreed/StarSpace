package spacetrader;

import java.io.IOException;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

/**
 *
 * @author Jackson Morgan
 */
public class Window extends Pane {
    
    public void loadFXML(FXMLLoader xml) throws IOException {
        this.getChildren().add(xml.load());
    }

    void closeApplication() {
        Platform.exit();
    }
}
