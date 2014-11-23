package spacetrader.game_model;

import java.io.Serializable;
import spacetrader.Interactable;

public class Planet implements Serializable, Positionable, Interactable {

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

    @Override
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
    public void interact(Ship ship, GameModel gm) {
        //TODO: Switch to marketplace
        System.out.println("Interaction with Planet.");
    }
}