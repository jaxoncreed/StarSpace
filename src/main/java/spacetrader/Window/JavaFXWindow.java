/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacetrader.Window;

import java.io.IOException;
import javafx.scene.layout.Pane;

/**
 *
 * @author Tyler Allen <tallen40@gatech.edu>
 */
public class JavaFXWindow extends Window{
    private Pane windowPane;
    public JavaFXWindow(){
        windowPane=new Pane();
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
