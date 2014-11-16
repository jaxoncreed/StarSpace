package spacetrader;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Group;
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
    private GameModel gameModel;
    private GameSaver gameSaver;
    private Group root;
    
    /**
     * Constructor
     * 
     * @param aStage: The stage that comes with the beginning of the project
     * @param aWindow: A class that will be the window for the project
     */
    public MainCtrl(Stage aStage, Window aWindow) throws Exception {
        // TODO: load option model and dynamically set window settings
        stage = aStage;
        window = aWindow;
        
        gameModel = new GameModel();

        // TODO: Make galaxy generator set galaxy
        Galaxy galaxy = new Galaxy(100, 100);

        StarSystem sol        = new StarSystem("Sol",        new Position(10, 10), StarType.DWARF);
        StarSystem andromeda  = new StarSystem("Andromeda",  new Position(30, 30), StarType.DWARF);
        StarSystem yourmomeda = new StarSystem("Yourmomeda", new Position(50, 50), StarType.DWARF);

        sol        .addJumpPoint(new Position(500, 500), andromeda,  new Position(300, 300));
        andromeda  .addJumpPoint(new Position(100, 100), sol,        new Position(300, 300));
        andromeda  .addJumpPoint(new Position(500, 500), yourmomeda, new Position(300, 300));
        yourmomeda .addJumpPoint(new Position(100, 100), andromeda,  new Position(300, 300));
        
        sol        .addPlanet(new Planet("Earth", new Position(-100, -200)));
        andromeda  .addPlanet(new Planet("Vulcan", new Position(-300, 200)));
        yourmomeda .addPlanet(new Planet("SoFat", new Position(500, -100)));
        yourmomeda .addPlanet(new Planet("SoDumb", new Position(-500, 100)));

        galaxy.addSystem(sol);
        galaxy.addSystem(andromeda);
        galaxy.addSystem(yourmomeda);

        gameModel.setGalaxy(galaxy);

        // TODO: Make character creator do this work and set the player
        player = new Player("Bob", Faction.NoFaction);
        player.setSystem(sol);

        sol.addShip(player.getShip());

        gameModel.setPlayer(player);

        spacetrader.PhysicsSimulator.setSystem(gameModel.getPlayer().getSystem());

        // Create controllers
        createCharacterCtrl = new CreateCharacterCtrl (this, window, stage, gameModel);
        currentViewCtrl     = new MenuCtrl            (this, window, stage, gameModel);
        galaxyGenerator     = new GalaxyGeneratorCtrl (this, window, stage, gameModel);
        makeTradeCtrl       = new MakeTradeCtrl       (this, window, stage, gameModel);
        menuCtrl            = new MenuCtrl            (this, window, stage, gameModel);
        controlShipCtrl         = null; // this is kind of a hack to make the buttons show up at startup
        // Create game saver
        gameSaver = new GameSaver(gameModel);


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
        if (controlShipCtrl == null) { // this is kind of a hack to make the buttons show up at startup
            controlShipCtrl = new ControlShipCtrl(this, window, stage, gameModel);
        }
        controlShipCtrl = new ControlShipCtrl(this, window, stage, gameModel);
        switchViews(controlShipCtrl);
    }
    
    public void saveGame() {
        gameSaver.saveGame();
    }
    
    public void loadGame() {
        //TODO: update so player and galaxy are combined
        GameModel tempModel;
        try {
            tempModel = gameSaver.loadGame();
            this.gax = tempModel.getGalaxy();
            this.player = tempModel.getPlayer();
            controlShipCtrl = new ControlShipCtrl(this, window, stage, tempModel);
            switchViews(controlShipCtrl);
        } catch (IOException ex) {
            System.out.println("Game could not be loaded. No such file.");
            Logger.getLogger(MainCtrl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            System.out.println("Game could not be loaded. Corrupted file.");
            Logger.getLogger(MainCtrl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
