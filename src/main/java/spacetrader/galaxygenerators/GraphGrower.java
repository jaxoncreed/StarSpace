/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacetrader.galaxygenerators;

import java.util.ArrayList;
import java.util.Collection;
import java.util.PriorityQueue;

/**
 *
 * @author Tyler Allen <tallen40@gatech.edu>
 * @param <N>
 */
public class GraphGrower <N extends Node>{
    private PriorityQueue<N> neighbors;
    private ArrayList<N> set;
    private Heurstic<N> heurstic;
    GraphGrower(Heurstic h){
        heurstic=h;
        neighbors=new PriorityQueue(h);
        set=new ArrayList();
    }
    public void addNeighbor(Collection<N> n){
        neighbors.addAll(n);
    }
    public void addNeighbor(N n){
        neighbors.add(n);
    }
    public void setHeurstic(Heurstic<N> h){
        heurstic=h;
    }
    public N getNext(){
        return neighbors.peek();
    }
    public N popNext(){
        return neighbors.poll();
    }
    public void addToSet(N n){
        set.add(n);
    }
    public void update(){
        addToSet(popNext());
    }
}
