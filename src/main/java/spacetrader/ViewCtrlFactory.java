/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacetrader;

import spacetrader.Window.Window;
import spacetrader.createcharacter.CreateCharacterCtrl;
import spacetrader.createcharacter.CreateCharacterViewFactory;
import spacetrader.menu.MainMenuViewFactory;
import spacetrader.menu.MenuCtrl;

/**
 *
 * @author Tyler Allen <tallen40@gatech.edu>
 */
public class ViewCtrlFactory {
    public static ViewCtrl getViewCtrl(CtrlViewTypes type,MainCtrl ctrl,Window win){
        switch(type){
            case CharacterCreator:return new CreateCharacterCtrl(ctrl,win);
            case MainMenu: return new MenuCtrl(ctrl,win);
        }
        return null;
    }
    public static AbstractView getView(CtrlViewTypes type,Window win,Ctrl ctrl){
        switch(type){
            case MainMenu: return new MainMenuViewFactory().getView(win, ctrl);
            case CharacterCreator: return new CreateCharacterViewFactory().getView(win, ctrl);

        }
        return null;
    }
}
