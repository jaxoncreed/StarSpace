package spacetrader;

import spacetrader.game_model.*;

public interface Interactable {

	public double getInteractionRange();
    public String getInteractionMessage();
    public void interact(Ship ship, GameModel gm);

}