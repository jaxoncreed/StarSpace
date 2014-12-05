/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacetrader.game_model.positioncontainer;

import java.util.ArrayList;
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
    public Bounds(ArrayList<Position> list){
        this.minx=list.get(0);
        this.miny=list.get(0);
        this.maxx=list.get(0);
        this.maxy=list.get(0);
        for(Position p:list){
            this.minx=(p.x<this.minx.x)?p:this.minx;
            this.miny=(p.y<this.miny.y)?p:this.miny;
            this.maxx=(p.x>this.maxx.x)?p:this.maxx;
            this.maxy=(p.y>this.maxy.y)?p:this.maxy;
        }     
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
    public Position getOrigin(){
        return origin;
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
        return new BoxCut(this.origin,this.far).contains(p);
    }

    public Position distanceOutOf(Position point) {
        Position pos1=new Position(point);
        Position o2=new Position(origin);
        pos1.sub(o2);
        System.out.println("Distance to origin:"+o2);
        double x=0,y=0;
        if(pos1.x<0){
            x=pos1.x;
        }
        if(pos1.y<0){
            y=pos1.y;
        }
        pos1=new Position(point);
        o2=new Position(far);
        pos1.sub(o2);
        System.out.println("Distance to far:"+pos1);

        if(pos1.x>0){
            x=pos1.x;
        }
        if(pos1.y>0){
            y=pos1.y;
        }
        return new Position(x,y);
    }

    public Position getFar() {
        return new Position(far);
    }
    
}
