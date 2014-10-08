package spacetrader.maketrade;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import spacetrader.AbstractView;
import spacetrader.Window;
import spacetrader.game_model.Item;
import spacetrader.maketrade.MakeTradeCtrl;

/**
 *
 * @author Jackson Morgan
 */
public class MakeTradeView extends AbstractView implements Initializable {
    public Window window;
    public MakeTradeCtrl makeTradeCtrl;
    public Pane curPane;

    public MakeTradeView(Window window, MakeTradeCtrl ctrl) {
        this.window = window;
        this.makeTradeCtrl = ctrl;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @Override
    public void render() {

    }

    @Override
    public void hide() {

    }

    public void renderMakeTrade(Item[] items, int woolongs, Item[] items0) {
        System.out.println("Render Make Trade");
    }

    public void removeMakeTrade() {
        System.out.println("remove make trade");
    }
}