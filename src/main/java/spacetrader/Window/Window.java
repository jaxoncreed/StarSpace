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
    public static interface Handler{
        public void handle();
    } 
    protected Handler handleKey;
    private int SCREEN_WIDTH = 1280;
    private int SCREEN_HEIGHT = 720; 
    protected ApplicationType type;
    public abstract void init();
    public abstract void close();
    public void setKeyHandle(Handler key){
        handleKey=key;
    }
    public void keyHandle(){
        handleKey.handle();
    }
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
