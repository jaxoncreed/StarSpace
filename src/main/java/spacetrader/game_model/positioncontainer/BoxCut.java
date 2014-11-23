/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacetrader.game_model.positioncontainer;

import spacetrader.game_model.gameLogic.Position;

/**
 *
 * @author Tyler Allen <tallen40@gatech.edu>
 */
public class BoxCut extends PositionContainer{
    private Position origin,far;
    private Position farRelative;
    public BoxCut(Position origin,Position far){
        this.origin=origin;
        this.far=far;
        this.farRelative=new Position(far);
        this.farRelative.sub(origin);
    }
    @Override
    public boolean contains(Position p) {
        Position temp=new Position(p);
        temp.sub(this.farRelative);
        return temp.x>=0&&temp.y>=0;
    }
    
}
