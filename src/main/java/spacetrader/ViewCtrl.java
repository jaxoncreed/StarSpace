/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spacetrader;

import spacetrader.game_model.GameModel;
import javafx.stage.Stage;

/**
 *
 * @author Jackson Morgan
 */
public abstract class ViewCtrl extends SubCtrl {
    protected Window window;
    protected AbstractView view;
    protected GameModel gameModel;
    protected Stage stage;

    public ViewCtrl(Ctrl parent, Window window, Stage stage, GameModel gameModel) {
        super(parent);
        this.window = window;
        this.gameModel = gameModel;
        this.stage = stage;
    }
    
    public void startView() {
        view.render();
    };
    public void stopView() {
        view.hide();
    }
}
