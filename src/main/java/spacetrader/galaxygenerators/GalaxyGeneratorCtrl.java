package spacetrader.galaxygenerators;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.List;
import spacetrader.MainCtrl;
import spacetrader.ViewCtrl;
import spacetrader.Window;
import spacetrader.game_model.*;

import javafx.stage.Stage;
import spacetrader.game_model.Galaxy;
import spacetrader.game_model.JumpPoint;
import spacetrader.game_model.graph.DirectedEdge;
import spacetrader.game_model.graph.Graph;

/**
 * To generate a galaxy, simply instantiate this class, and call the generate() 
 * method on the instantiation. The generated galaxy, complete with StarSystems, 
 * Planets, and JumpPoints, will be available through the passed in GameModel.
 * 
 * @author fsanchez
 */
public class GalaxyGeneratorCtrl extends ViewCtrl {
    CreateGalaxyView view;
    MainCtrl mainCtrl;
    GalaxyGenerator generator;
    JumpPointsGenerator jumpPointGenerator;
    private GameModel gameModel;
    
    private static final String CONFIG_XML_FILE = "generator_config.xml";

    public GalaxyGeneratorCtrl(MainCtrl aParent, Window window, Stage stage, GameModel gameModel) throws Exception {
        super(aParent, window, stage, gameModel);
        view = new CreateGalaxyView(window, this);
        mainCtrl = aParent;
        GeneratorConfigParser parser = new GeneratorConfigParser(CONFIG_XML_FILE);
        parser.createGenerators();
                
        generator = parser.getGalaxyGenerators().get(0);
        StarSystemGenerator sysGen = parser.getStarSystemGenerators().get(0);
        PlanetGenerator planetGen = parser.getPlanetGenerators().get(0);
        sysGen.setPlanetGenerator(planetGen);
        generator.setStarSystemGenerator(sysGen);
        jumpPointGenerator = parser.getJumpPointsGenerators().get(0);
        this.gameModel = gameModel;
    }
    
    @Override
    public void startView() {
        view.renderGalaxyCreator();
        gameModel.setGalaxy(generator.generate());
        mainCtrl.controlShip();
    }

    @Override
    public void stopView() {
        view.removeGalaxyCreator();
    }
    
    public void generate() {
        // Generate the galaxy
        Galaxy gax = generator.generate();
        gameModel.setGalaxy(gax);
        jumpPointGenerator.setGameModel(gameModel);
        jumpPointGenerator.generate();
        List<JumpPoint> edges = jumpPointGenerator.getJumpPointList();
        gax.setJumpPoints(new Graph(edges, null));
        gameModel.setGalaxy(gax);

        // Put player in a system
        StarSystem starSystem = null;
        for (StarSystem s : gax.getSystems()) {
            if (s.getJumpPoints().size() > 0) {
                starSystem = s;
            }
        }
        starSystem.addShip(gameModel.getPlayer().getShip());
        gameModel.getPlayer().setSystem(starSystem);
        System.out.println("HELLO WORLD BEN YES HELLO");
        System.out.println(gameModel.getPlayer().getSystem());
        spacetrader.PhysicsSimulator.setSystem(gameModel.getPlayer().getSystem());
    }
}