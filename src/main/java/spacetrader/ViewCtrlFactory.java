/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacetrader;

import spacetrader.Window.Window;
import spacetrader.controlship.ControlShipCtrl;
import spacetrader.controlship.ControlShipViewFactory;
import spacetrader.createGalaxy.CreateGalaxyCtrl;
import spacetrader.createGalaxy.CreateGalaxyViewFactory;
import spacetrader.createcharacter.CreateCharacterCtrl;
import spacetrader.createcharacter.CreateCharacterViewFactory;
import spacetrader.loadGame.LoadGameCtrl;
import spacetrader.loadGame.LoadGameViewFactory;
import spacetrader.saveGame.SaveGameCtrl;
import spacetrader.maketrade.MakeTradeCtrl;
import spacetrader.maketrade.MakeTradeViewFactory;
import spacetrader.menu.MainMenuViewFactory;
import spacetrader.menu.MenuCtrl;
import spacetrader.saveGame.SaveGameViewFactory;
import spacetrader.viewGalaxyMap.GalaxyMapCtrl;
import spacetrader.viewGalaxyMap.GalaxyMapViewFactory;

/**
 *
 * @author Tyler Allen <tallen40@gatech.edu>
 */
public class ViewCtrlFactory {
    public static ViewCtrl getViewCtrl(CtrlViewTypes type,MainCtrl ctrl,Window win){
        switch(type){
            case CharacterCreator:  return new CreateCharacterCtrl(ctrl,win);
            case MainMenu:          return new MenuCtrl(ctrl,win);
            case SaveGame:          return new SaveGameCtrl(ctrl,win);
            case LoadGame:          return new LoadGameCtrl(ctrl,win);
            case Trade:             return new MakeTradeCtrl(ctrl,win);
            case CreateGalaxy:      return new CreateGalaxyCtrl(ctrl,win);
            case ControlShip:       return new ControlShipCtrl(ctrl,win);
            case GalaxyMap:         return new GalaxyMapCtrl(ctrl,win);
        }
        return null;
    }
    public static AbstractView getView(CtrlViewTypes type,Window win,Ctrl ctrl){
        switch(type){
            case MainMenu:          return new MainMenuViewFactory().getView(win, ctrl);
            case CharacterCreator:  return new CreateCharacterViewFactory().getView(win, ctrl);
            case SaveGame:          return new SaveGameViewFactory().getView(win, ctrl);
            case LoadGame:          return new LoadGameViewFactory().getView(win, ctrl);
            case Trade:             return new MakeTradeViewFactory().getView(win, ctrl);
            case CreateGalaxy:    return new CreateGalaxyViewFactory().getView(win, ctrl);
            case ControlShip:       return new ControlShipViewFactory().getView(win, ctrl);
            case GalaxyMap:         return new GalaxyMapViewFactory().getView(win, ctrl);
        }
        return null;
    }
}
