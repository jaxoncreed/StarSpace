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
public abstract class ViewCtrl extends SubCtrl {
    private Window window;
    
    public ViewCtrl(Ctrl parent, Window aWindow) {
        super(parent);
        window = aWindow;
    }
    
    public abstract void startView();
    public abstract void stopView();
}
