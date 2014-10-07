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
    
    public void loadFXML(Pane pane) throws IOException {
        this.getChildren().add(pane);
    }
    
    public void clearFXML(Pane pane) {
        this.getChildren().remove(pane);
    }

    void closeApplication() {
        Platform.exit();
    }
}
