/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacetrader.controlship;

import javafx.event.Event;
import javafx.event.EventType;
import javafx.scene.layout.Pane;
import spacetrader.game_model.Player;
import spacetrader.game_model.StarSystem;

/**
 *
 * @author Tyler Allen <tallen40@gatech.edu>
 */
public class RealTimeShipControl extends Pane{
    private Player player;
    private StarSystem system;
    private class DrawEvent extends Event {
        public DrawEvent(EventType<? extends Event> eventType) {
            super(eventType);
        }
    }
    public RealTimeShipControl(){
        this.addEventHandler(EventType.ROOT, null);
    }
    public void render(){
        
    }
}
