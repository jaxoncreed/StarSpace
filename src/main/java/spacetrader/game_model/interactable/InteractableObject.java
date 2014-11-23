package spacetrader.game_model.interactable;

import spacetrader.game_model.*;
import spacetrader.game_model.gameLogic.Position;

public interface InteractableObject {
    public interface InteractOperation{
        public void interact();
    }
    public double getInteractionRange();
    public String getInteractionMessage();    
    public Position getPos();
    public InteractionType getType();
}