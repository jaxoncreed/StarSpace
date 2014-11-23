package spacetrader.galaxygenerators;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.List;
import spacetrader.MainCtrl;
import spacetrader.ViewCtrl;
import spacetrader.Window.Window;
import spacetrader.game_model.GameModel;

import javafx.stage.Stage;
import spacetrader.game_model.Galaxy;
import spacetrader.game_model.JumpPoint;
import spacetrader.game_model.graph.DirectedEdge;
import spacetrader.game_model.graph.Graph;

/**
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
        Galaxy gax = generator.generate();
        jumpPointGenerator.setGalaxy(gax);
        jumpPointGenerator.generate();
        List<JumpPoint> edges = jumpPointGenerator.getJumpPointList();
        gax.setJumpPoints(new Graph(edges, null));
        gameModel.setGalaxy(gax)
;        
    }
}
