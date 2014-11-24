package spacetrader.game_model.system;

import java.io.Serializable;
import spacetrader.game_model.GameModel;
import spacetrader.game_model.Ship;
import spacetrader.game_model.gameLogic.Inventory;
import spacetrader.game_model.gameLogic.Market;
import spacetrader.game_model.gameLogic.Position;
import spacetrader.game_model.interactable.InteractableObject;
import spacetrader.game_model.interactable.InteractionType;

public class Planet implements Serializable, InteractableObject {

    //#todo need to implement id system
private Position pos;
    private int techLevel;
    private String government;
    private String name;
    private Market market;
    private double interactionRange;

	public Planet(Position pos, System system, int techLevel, String government,
		String name) {
		this.name = name;
		this.pos = pos;
		this.techLevel = techLevel;
		this.government = government;
                this.interactionRange = 200;
                market=new Market(new Inventory(10),this);
	}

    public Planet(String name, Position pos) {
        this.name=name;
        this.pos=pos;
        market=new Market(new Inventory(10),this);

    }
    public void setTechLevel(int lvl) {
        this.techLevel = lvl;
    }
    public int getTechLevel() {
        return techLevel;
    }
    
    public Market getMarket() {
        return market;
    }

	public Position getPos() {
		return pos;
	}

	public void setPos(Position pos) {
		this.pos = pos;
	}

    public String getName(){
        return name;
    }

    public Position getPosition() {
        return pos;
    }
    
    public double getInteractionRange() {
        return interactionRange;
    }

    @Override
    public String getInteractionMessage() {
        return "Trade with planet.";
    }
    @Override
    public InteractionType getType() {
        return InteractionType.Trade;
    }
}
