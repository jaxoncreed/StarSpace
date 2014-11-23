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
public class Bounds extends PositionContainer{
    private Position minx,miny,maxx,maxy;
    private Position origin;
    private Position far;
    public Bounds(Position minx,Position miny,Position maxx,Position maxy){
        this.minx=minx;
        this.miny=miny;
        this.maxx=maxx;
        this.maxy=maxy;
        origin=new Position(minx.x,miny.y);
        far=new Position(maxx.x,maxy.y);
    }
    public double getMinX(){
        return origin.x;
    }
    public double getMinY(){
        return origin.y;
    }
    public double getMaxX(){
        return far.x;
    }
    public double getMaxY(){
        return far.y;
    }
    public Position normalize(Position p){
        Position temp=new Position(p.x,p.y);
        temp.sub(origin);
        return temp;
    }
    public double getDistanceX(){
        return getMaxX()-getMinX();
    }
    public double getDistanceY(){
        return getMaxY()-getMinY();
    }
    public void setOffsetNear(Position p){
        origin.sub(p);
    }
    public void setOffsetFar(Position p){
        far.add(p);
    }

    @Override
    public boolean contains(Position p) {
        return false;
    }
    
}
