/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacetrader.galaxygenerators;

import java.util.Comparator;

/**
 *
 * @author Tyler Allen <tallen40@gatech.edu>
 */
public abstract class Heurstic<N extends Node> implements Comparator<N>{
    public abstract int calculate(N n1,N n2);
    @Override
    public int compare(N t, N t1) {
        return calculate(t,t1);
    }
}
