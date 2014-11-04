package spacetrader.galaxygenerators;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import spacetrader.MainCtrl;
import spacetrader.ViewCtrl;
import spacetrader.Window;
import spacetrader.game_model.GameModel;

import javafx.stage.Stage;

/**
 *
 * @author fsanchez
 */
public class GalaxyGeneratorCtrl extends ViewCtrl {
    CreateGalaxyView view;
    MainCtrl mainCtrl;
    GalaxyGenerator generator; 
    
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
}
