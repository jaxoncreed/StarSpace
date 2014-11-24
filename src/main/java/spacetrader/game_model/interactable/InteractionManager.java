/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacetrader.game_model.interactable;

import java.util.ArrayList;
import java.util.HashMap;
import spacetrader.game_model.Ship;
import spacetrader.game_model.gameLogic.Position;

/**
 *
 * @author Tyler Allen <tallen40@gatech.edu>
 */
public class InteractionManager {

    public interface InteractAction {

        public void interact(InteractableObject obj);
    }

    private class NullInteraction implements InteractableObject {

        @Override
        public double getInteractionRange() {
            return -1;
        }

        @Override
        public String getInteractionMessage() {
            return "";
        }

        @Override
        public Position getPos() {
            return new Position(-1, -1);
        }

        @Override
        public InteractionType getType() {
            return InteractionType.Null;
        }
    }
    private ArrayList<InteractableObject> objectList;
    private HashMap<InteractionType, InteractAction> funcMap;

    public InteractionManager(ArrayList<InteractableObject> object) {
        objectList = object;        funcMap = new HashMap();
        funcMap.put(InteractionType.Null, (InteractableObject obj) -> {

        });
    }

    public InteractableObject getInteraction(Ship ship) {
        for (InteractableObject object : objectList) {
            if(object.getPos().distTo(ship.getPosition())<object.getInteractionRange()){
                return object;
            }
        }
        return new NullInteraction();
    }

    public void interact(Ship ship) {
        InteractableObject object = getInteraction(ship);
        System.out.println(object.getType());
        funcMap.get(object.getType()).interact(object);
    }

    public void setInteractFunction(InteractionType type, InteractAction action) {
        funcMap.put(type, action);
    }

    public String getInteractionMessage(Ship ship) {
        InteractableObject object = getInteraction(ship);
        return object.getInteractionMessage();
    }
}
