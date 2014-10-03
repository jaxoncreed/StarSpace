/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spacetrader;

/**
 *
 * @author Jackson Morgan
 */
public abstract class SubCtrl extends Ctrl {
    private Ctrl parent;
    
    public SubCtrl(Ctrl aParent) {
        parent = aParent;
    }
    
    public Ctrl getParent() {
        return parent;
    }
}
