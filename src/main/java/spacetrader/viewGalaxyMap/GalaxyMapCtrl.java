/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacetrader.viewGalaxyMap;

import java.util.ArrayList;
import java.util.List;
import spacetrader.Ctrl;
import spacetrader.CtrlViewTypes;
import spacetrader.MainCtrl;
import spacetrader.ViewCtrl;
import spacetrader.Window.Window;
import spacetrader.game_model.GameModel;
import spacetrader.game_model.gameLogic.Position;
import spacetrader.game_model.graph.Edge;
import spacetrader.game_model.graph.Node;
import spacetrader.game_model.player.Player;
import spacetrader.game_model.system.Galaxy;
import spacetrader.game_model.system.JumpPoint;
import spacetrader.game_model.system.StarSystem;

/**
 *
 * @author Tyler Allen <tallen40@gatech.edu>
 */
public class GalaxyMapCtrl extends ViewCtrl{

    MainCtrl mainCtrl;
    Galaxy  galaxy;
    Player  player;
    double minDist=10;
    public GalaxyMapCtrl(Ctrl parent, Window window) {
        super(parent, window);
        mainCtrl=(MainCtrl) parent;
        this.view=new GalaxyMapViewFactory().getView(window, this);
        galaxy=GameModel.get().getGalaxy();
        player=GameModel.get().getPlayer();
    }
    public void exitToControlShip(){
        mainCtrl.switchViews(CtrlViewTypes.ControlShip);
    }
    public List<StarSystem> getSystems(){
        return galaxy.getSystems();
    }
    public StarSystem findNearbyStarSystem(Position p){
        StarSystem min=galaxy.getSystems().get(0);
        for(StarSystem sys:galaxy.getSystems()){
            if(p.distTo(sys.getPosition())<this.minDist){
                if(min==null){
                    min=sys;
                }else if(p.distTo(sys.getPosition())<p.distTo(min.getPosition())){
                    min=sys;
                }
            }
        }
        return min;
    }
    public List<JumpPoint> findPath(StarSystem target){
        return player.calculatePath(target);
    }
  
}
