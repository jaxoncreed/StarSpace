/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacetrader.Window;

import java.io.IOException;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import static spacetrader.Application.ApplicationType.JavaFX;
import spacetrader.MultiKeyPressEventHandler;

/**
 *
 * @author Tyler Allen <tallen40@gatech.edu>
 */
public class JavaFXWindow extends Window{
    public interface JavaFXKeyHandler extends Handler{
        public void handle(MultiKeyPressEventHandler.MultiKeyEvent e);
    }
    private Pane windowPane;
    public JavaFXWindow(){
        this.type=JavaFX;
        windowPane=new Pane();
    }
    public void keyHandle(MultiKeyPressEventHandler.MultiKeyEvent e){
        ((JavaFXKeyHandler)this.handleKey).handle(e);
    }
    public void loadFXML(Pane pane) throws IOException {
        windowPane.getChildren().add(pane);
    }
    public void clearFXML(Pane pane) {
        windowPane.getChildren().remove(pane);
    }
    public Pane getPane(){
        return windowPane;
    }
    @Override
    public void init() {
    }

    @Override
    public void close() {
        
    }
}
