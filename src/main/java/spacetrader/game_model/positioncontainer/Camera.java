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
public class Camera extends BoxCut{
    private BoxCut box;
    private double size,sizey;
    private Bounds bounds;
    public Camera(Position start,double size,double sizey){
        super(start,new Position(start.x+size,start.y+sizey));
        this.origin=start;
        this.size=size;
        this.sizey=sizey;
    }
    public void move(Position amount){
        this.origin.add(amount);
        Position off=new Position(this.origin);
        off.add(new Position(size,sizey));
        this.far=off;
        Position p=new Position(origin);
        //p.sub(bounds.getOrigin());
        if(!bounds.contains(this.origin)){
            System.out.println("Origin; Move Back Dist: "+bounds.distanceOutOf(this.origin));
            this.origin.add(bounds.distanceOutOf(this.origin).negate());
            off=new Position(this.origin);
            off.add(new Position(size,sizey));
            this.far=off;
        }
        System.out.println(bounds.getFar());
        System.out.println(far);
        if(!bounds.contains(this.far)){            
            System.out.println("Far; Move Back Dist: "+bounds.distanceOutOf(this.far));
            this.origin.add(bounds.distanceOutOf(this.far).negate());
            off=new Position(this.origin);
            off.add(new Position(size,sizey));
            this.far=off;
        }
    }
    public void setBounds(Bounds bounds){
        this.bounds=bounds;
    }
}
