package spacetrader.requestresource;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import spacetrader.Ctrl;
import spacetrader.MainCtrl;
import spacetrader.ViewCtrl;
import spacetrader.Window;
import spacetrader.createcharacter.CreateCharacterView;

/**
 *
 * @author Michael Lane
 */
public class RequestResourceCtrl extends ViewCtrl {
    private RequestResourceView view;
    private MainCtrl mainCtrl;

    public RequestResourceCtrl(MainCtrl parent, Window aWindow) {
        super(parent, aWindow);
        view = new RequestResourceView(window, this);
        mainCtrl = parent;
    }
    
}
