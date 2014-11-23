/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacetrader.game_model.graph;

import java.util.Comparator;

/**
 *
 * @author Tyler Allen <tallen40@gatech.edu>
 */
public abstract class Heurstic<N extends Node> implements Comparator<N>{
    
    protected boolean toRecalc;
    protected boolean isConsistent;
    protected boolean isAdmissible;
        
    public abstract double calculate(N n1,N n2);
    @Override
    public int compare(N t, N t1) {
        return (int) calculate(t,t1);
    }
    
    public boolean toRecalc() {
        return toRecalc;
    }
    
    public boolean isConsistent() {
        return isConsistent;
    }
    
    public boolean isAdmissible() {
        return isAdmissible;
    }
    
    public void setConsistent(boolean isConsistent) {
        this.isConsistent = isConsistent;
    }
    
    public void setAdmissible(boolean isAdmissible) {
        this.isAdmissible = isAdmissible;
    }
}
