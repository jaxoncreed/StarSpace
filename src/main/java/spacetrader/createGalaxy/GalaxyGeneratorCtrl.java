package spacetrader.createGalaxy;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import spacetrader.MainCtrl;
import spacetrader.ViewCtrl;
import spacetrader.Window.Window;
import spacetrader.game_model.GameModel;

import javafx.stage.Stage;
import spacetrader.CtrlViewTypes;
import spacetrader.ViewCtrlFactory;
import spacetrader.Window.JavaFXWindow;
import spacetrader.galaxygenerators.GalaxyGenerator;
import spacetrader.galaxygenerators.GeneratorConfigParser;
import spacetrader.galaxygenerators.JumpPointsGenerator;
import spacetrader.galaxygenerators.PlanetGenerator;
import spacetrader.galaxygenerators.StarSystemGenerator;
import spacetrader.game_model.system.Galaxy;
import spacetrader.game_model.system.JumpPoint;
import spacetrader.game_model.graph.Graph;

/**
 * To generate a galaxy, simply instantiate this class, and call the generate() 
 * method on the instantiation. The generated galaxy, complete with StarSystems, 
 * Planets, and JumpPoints, will be available through the passed in GameModel.
 * 
 * @author fsanchez
 */
public class GalaxyGeneratorCtrl extends ViewCtrl {
    MainCtrl mainCtrl;
    GalaxyGenerator generator;
    JumpPointsGenerator jumpPointGenerator;
    private static final String CONFIG_XML_FILE = "generator_config.xml";

    public GalaxyGeneratorCtrl(MainCtrl aParent, Window window) {
        super(aParent, window);
        try {
            view = ViewCtrlFactory.getView(CtrlViewTypes.CreateGalaxy, window, this);
            mainCtrl = aParent;
            System.out.println();
            GeneratorConfigParser parser;
            parser = new GeneratorConfigParser(CONFIG_XML_FILE);
            parser.createGenerators();
            generator = parser.getGalaxyGenerators().get(0);
            StarSystemGenerator sysGen = parser.getStarSystemGenerators().get(0);
            PlanetGenerator planetGen = parser.getPlanetGenerators().get(0);
            sysGen.setPlanetGenerator(planetGen);
            generator.setStarSystemGenerator(sysGen);
            jumpPointGenerator = parser.getJumpPointsGenerators().get(0);
        } catch (Exception ex) {
            Logger.getLogger(GalaxyGeneratorCtrl.class.getName()).log(Level.SEVERE, null, ex);
            mainCtrl.switchViews(CtrlViewTypes.MainMenu);
        }
        
    }
    
    @Override
    public void startView() {
        view.render();
        generate();
        mainCtrl.switchViews(CtrlViewTypes.ControlShip);
    }

    @Override
    public void stopView() {
        view.hide();
    }
    
    public void generate() {
        Galaxy gax = generator.generate();
        jumpPointGenerator.setGalaxy(gax);
        jumpPointGenerator.generate();
        List<JumpPoint> edges = jumpPointGenerator.getJumpPointList();
        gax.setJumpPoints(new Graph(edges, null));
        GameModel.get().setGalaxy(gax);
        GameModel.get().getPlayer().getShip().setSystem(gax.getSystems().get(0));

    }
}
