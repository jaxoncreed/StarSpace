package spacetrader;

import spacetrader.maketrade.MakeTradeCtrl;
import javafx.scene.Scene;
import javafx.stage.Stage;
import spacetrader.createcharacter.CreateCharacterCtrl;
import spacetrader.game_model.Faction;
import spacetrader.game_model.Inventory;
import spacetrader.game_model.Player;
import spacetrader.menu.MenuCtrl;
import spacetrader.controlship.ControlShipCtrl;
import spacetrader.galaxygenerators.GalaxyGeneratorCtrl;
import spacetrader.game_model.Galaxy;
/**
 * The overall centralized controller. Manages the other controllers
 * @author Jackson Morgan
 */
public class MainCtrl extends Ctrl{
    private Window window;
    private Stage stage;
    private ViewCtrl currentViewCtrl;
    private GalaxyGeneratorCtrl galaxyGenerator;
    
    //TODO: allow player to be generated
    private Player player;
    private MenuCtrl menuCtrl;
    private CreateCharacterCtrl createCharacterCtrl;
    //TODO: think of better way to get galaxy generated
    private ControlShipCtrl controlShipCtrl;
    private MakeTradeCtrl makeTradeCtrl;
    private ControlShipCtrl controlShip;
    private Galaxy gax; 
    
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
        
        //TODO: fix later
        player = new Player("Bob", Faction.NoFaction);

        galaxyGenerator = new GalaxyGeneratorCtrl(this, window);
        currentViewCtrl = new MenuCtrl(this, window);
        menuCtrl = new MenuCtrl(this, window);
        makeTradeCtrl = new MakeTradeCtrl(this, window, player);
        createCharacterCtrl = new CreateCharacterCtrl(this, window);
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
        switchViews(createCharacterCtrl);
    }
    
    /**
     * Starts generating the universe while disabling other functions.
     */
    public void generateUniverse() {
        switchViews(galaxyGenerator);
    }

    public void makeTrade(Inventory inv) {
        currentViewCtrl.stopView();
        makeTradeCtrl.renderMarket(inv);
        currentViewCtrl = makeTradeCtrl;
    }
    
    public void controlShip(){
        controlShipCtrl = new ControlShipCtrl(this,window,gax.getSystems().get(0));
        switchViews(controlShipCtrl);
    }
    
    public void setGalaxy(Galaxy gax) {
        this.gax = gax;
    }
}
