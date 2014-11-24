/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacetrader;

import java.util.EnumSet;
import java.util.Set;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 *
 * @author Tyler Allen <tallen40@gatech.edu>
 */
public class MultiKeyPressEventHandler implements EventHandler<KeyEvent>{
    private final Set<KeyCode> buffer=EnumSet.noneOf(KeyCode.class);
    private final MultiKeyEvent mkEvent=new MultiKeyEvent();
    private final MultiKeyEventHandler multiKeyEventHandler;
    public MultiKeyPressEventHandler(MultiKeyEventHandler handler){
        multiKeyEventHandler=handler;
    }
    @Override
    public void handle(KeyEvent event) {
        final KeyCode code = event.getCode();
        if (KeyEvent.KEY_PRESSED.equals(event.getEventType())) {
            buffer.add(code);
            multiKeyEventHandler.handle(mkEvent);
        }else if (KeyEvent.KEY_RELEASED.equals(event.getEventType())) {
            buffer.remove(code);
            multiKeyEventHandler.handle(mkEvent);
        }
        event.consume();
    }
    public interface MultiKeyEventHandler {
        void handle(final MultiKeyEvent event);
    }
    public class MultiKeyEvent {
        public boolean isPressed(final KeyCode key){
            return buffer.contains(key);
        }
    }
    
}
