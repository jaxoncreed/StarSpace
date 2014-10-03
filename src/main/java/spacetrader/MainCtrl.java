package spacetrader;

import javafx.scene.Scene;
import javafx.stage.Stage;
import spacetrader.menu.MenuCtrl;

/**
 * The overall centralized controller. Manages the other controllers
 * @author Jackson Morgan
 */
public class MainCtrl extends Ctrl{
    private Window window;
    private Stage stage;
    private ViewCtrl currentViewCtrl;
    
    private MenuCtrl menuCtrl;
    
    
    /**
     * Constructor
     * 
     * @param aStage: The stage that comes with the beginning of the project
     * @param aWindow: A class that will be the window for the project
     */
    public MainCtrl(Stage aStage, Window aWindow) {
        //TODO: load option model and dynamically set window settings
        stage = aStage;
        window = aWindow;
        currentViewCtrl = new MenuCtrl(this, window);
        menuCtrl = new MenuCtrl(this, window);
        stage.setScene(new Scene(window));
        stage.show();
    }
    
    public void closeApplication() {
        window.closeApplication();
    }
    
    /**
     * A helper method to handle switching views under normal circumstances
     * @param newView: The view to which the window should be switched
     */
    private void switchViews(ViewCtrl newViewCtrl) {
        currentViewCtrl.stopView();
        newViewCtrl.startView();
        currentViewCtrl = newViewCtrl;
    }

    /**
     * Starts the main menu while disabling other functions.
     */
    public void mainMenu() {
        switchViews(menuCtrl);
    }
    
    /**
     * Starts the character creator while disabling other functions.
     */
    public void createCharacter() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * Starts generating the universe while disabling other functions.
     */
    public void generateUniverse() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
