package spacetrader.game_model;

public class Planet {

    //#todo need to implement id system
	private Position pos;
	private System system;
	private int techLevel;
	private String government;
	private String name;
        private Market market;

	public Planet(Position pos, System system, int techLevel, String government,
		String name) {
		this.name = name;
		this.pos = pos;
		this.system = system;
		this.techLevel = techLevel;
		this.government = government;
                market=new Market(new Inventory(10),this);
	}

    public Planet(String name, Position pos) {
        this.name=name;
        this.pos=pos;
        market=new Market(new Inventory(10),this);

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

	public System getSystem() {
		return system;
	}
   
    public String getName(){
        return name;
    }
}