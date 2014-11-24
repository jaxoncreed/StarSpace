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
        temp.sub(this.origin);
        if(temp.x<0||temp.y<0){
            return false;
        }
        Position temp2=new Position(farRelative);
        temp2.sub(temp);
        return temp2.x>=0&&temp2.y>=0;
    }
    public Position normalize(Position p){
        Position pos=new Position(p.x,p.y);
        pos.sub(origin);
        return pos;
    }
    
}
