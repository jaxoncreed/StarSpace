package spacetrader.Window;

import java.io.IOException;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import spacetrader.Application.ApplicationType;

/**
 *
 * @author Jackson Morgan
 */
public abstract class Window {
    private int SCREEN_WIDTH = 1280;
    private int SCREEN_HEIGHT = 720; 
    private ApplicationType type;
    public abstract void init();
    public abstract void close();
    public void setWidth(int width){
        SCREEN_WIDTH=width;
    }
    public void setHeight(int height){
        SCREEN_HEIGHT=height;
    }
    public ApplicationType getType(){
        return type;
    }
    public int getWidth(){
        return SCREEN_WIDTH;
    }
    public int getHeight(){
        return SCREEN_HEIGHT;
    }
}
