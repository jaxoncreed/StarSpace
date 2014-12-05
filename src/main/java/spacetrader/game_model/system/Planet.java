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
    private StarSystem system;

	public Planet(Position pos, StarSystem system, int techLevel, String government,
		String name) {
		this.name = name;
		this.pos = pos;
		this.techLevel = techLevel;
		this.government = government;
        this.interactionRange = 50;
        this.system = system;
        market=new Market(new Inventory(10),this);
	}

    public Planet(String name, Position pos) {
        this.name=name;
        this.pos=pos;
        this.interactionRange = 100;

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
    
    public void setMarket(Market market) {
        this.market = market;
    }
    
    public void setSystem(StarSystem system) {
        this.system = system;
    }
    
    public StarSystem getSystem() {
        return system;
    }
}
