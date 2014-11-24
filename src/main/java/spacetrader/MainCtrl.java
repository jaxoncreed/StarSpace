package spacetrader;

import static spacetrader.CtrlViewTypes.MainMenu;
import spacetrader.Window.Window;

/**
 * The overall centralized controller. Manages the other controllers
 * @author Jackson Morgan
 */
public class MainCtrl extends Ctrl{
    private Window window;
    private ViewCtrl currentViewCtrl;
    public static interface CloseOperator{
        public void op();
    }
    private CloseOperator close;
    /**
     * Constructor
     * 
     * @param aWindow: A class that will be the window for the project
     */
    public MainCtrl(Window aWindow){
        window = aWindow;
    }
    public void init(){
        currentViewCtrl=ViewCtrlFactory.getViewCtrl(MainMenu,this,window);
        currentViewCtrl.startView();
    }
    public void switchViews(CtrlViewTypes type){
        currentViewCtrl.stopView();
        currentViewCtrl=ViewCtrlFactory.getViewCtrl(type,this,window);
        currentViewCtrl.startView();
    }
    public void setClose(CloseOperator op){
        close=op;
    }
    public void close(){
        close.op();
    }
}