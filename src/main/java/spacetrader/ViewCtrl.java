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
    protected Window window;
    protected AbstractView view;
    
    public ViewCtrl(Ctrl parent, Window aWindow) {
        super(parent);
        window = aWindow;
    }
    
    public void startView() {
        view.render();
    };
    public void stopView() {
        view.hide();
    }
}
