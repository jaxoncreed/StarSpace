package spacetrader;

import spacetrader.Window.Window;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import spacetrader.maketrade.MakeTradeCtrl;
import javafx.scene.Scene;
import javafx.stage.Stage;
import spacetrader.createcharacter.CreateCharacterCtrl;
import spacetrader.menu.MenuCtrl;
import spacetrader.controlship.ControlShipCtrl;
import spacetrader.galaxygenerators.GalaxyGeneratorCtrl;
import spacetrader.game_model.*;

/**
 * The overall centralized controller. Manages the other controllers
 * @author Jackson Morgan
 */
public class MainCtrl extends Ctrl{
    private Window window;
    private ViewCtrl currentViewCtrl;
    private GalaxyGeneratorCtrl galaxyGenerator;
    public MainCtrl(Window aWindow) {
        window=aWindow;
        
    }
    public void init(){
        currentViewCtrl=
    }
    private void switchViews(ViewCtrl newViewCtrl) {
        currentViewCtrl.stopView();
        newViewCtrl.startView();
        currentViewCtrl = newViewCtrl;
    }

}
