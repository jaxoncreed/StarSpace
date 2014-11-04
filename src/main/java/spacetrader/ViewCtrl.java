/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spacetrader;

import spacetrader.game_model.GameModel;

/**
 *
 * @author Jackson Morgan
 */
public abstract class ViewCtrl extends SubCtrl {
    protected Window window;
    protected AbstractView view;
    protected GameModel gameModel;

    public ViewCtrl(Ctrl parent, Window aWindow, GameModel gameModel) {
        super(parent);
        window = aWindow;
        this.gameModel = gameModel;
    }
    
    public void startView() {
        view.render();
    };
    public void stopView() {
        view.hide();
    }
}
