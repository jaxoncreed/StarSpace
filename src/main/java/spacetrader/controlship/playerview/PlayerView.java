/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacetrader.controlship.playerview;

import spacetrader.game_model.player.Player;
import spacetrader.game_model.gameLogic.Position;
import spacetrader.game_model.positioncontainer.BoxCut;

/**
 *
 * @author Tyler Allen <tallen40@gatech.edu>
 */
public class PlayerView {
    /*
    variables declaring the box of the player view.
    */
    private Position viewspaceOrigin,viewspaceFar;
    private BoxCut   boxView;
    /*
    Current position of the player. 
    */    
    Player player;
    
    public PlayerView(Position origin,Position far){
        viewspaceOrigin=origin;
        viewspaceFar=far;
        boxView=new BoxCut(viewspaceOrigin,viewspaceFar);
    }
    
}
