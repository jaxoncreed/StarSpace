package spacetrader.requestresource;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import spacetrader.Ctrl;
import spacetrader.MainCtrl;
import spacetrader.ViewCtrl;
import spacetrader.Window.Window;
import spacetrader.createcharacter.CreateCharacterView;
import spacetrader.game_model.GameModel;

/**
 *
 * @author Michael Lane
 */
public class RequestResourceCtrl extends ViewCtrl {
    private RequestResourceView view;
    private MainCtrl mainCtrl;

//    public RequestResourceCtrl(MainCtrl parent, Window aWindow) {
//        super(parent, aWindow);
//        view = new RequestResourceView(window, this);
//        mainCtrl = parent;
//    }

    public RequestResourceCtrl(Ctrl parent, Window window, Stage stage, GameModel gameModel) {
        super(parent, window);
    }
    
}
