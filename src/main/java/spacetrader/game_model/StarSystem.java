package spacetrader.game_model;

import java.io.Serializable;
import java.util.List;

import java.util.ArrayList;
import spacetrader.game_model.graph.Node;
import java.util.Map;
import java.util.HashMap;
import java.util.Objects;

public class StarSystem implements Node, Serializable, Positionable {

	private String name;
	private Position pos;
	private StarType starType;
	private List<Planet> planets;
	private double starMass;
	private Map<Node,JumpPoint> jumpPoints;
        private Faction faction;


	public Position getPosition() {
		return pos;
	}

	public StarSystem(String name, Position pos, StarType starType) {
		this.name = name;
		this.pos = pos;
		this.planets = new ArrayList<Planet>();
		this.jumpPoints = new HashMap<Node,JumpPoint>();
		this.starType = starType;
		this.faction = Faction.NoFaction;
	}

	public StarSystem(
		String name, 
		Position pos,
		StarType starType,
		double starMass,
		Faction faction) {

		this(name, pos, starType);
		setStarMass(starMass);
		this.faction = faction;
	}

	public double getX() {
		return pos.x;
	}

	public double getY() {
		return pos.y;
	}

	public void addPlanet(Planet planet) {
		planets.add(planet);
	}

	public JumpPoint addJumpPoint(StarSystem targetSys, Position pos, Position targetPos) {
        JumpPoint j = new JumpPoint(this, targetSys, pos, targetPos);
		this.asymmetricalAddJumpPoint(targetSys, j);
		targetSys.asymmetricalAddJumpPoint(this, j);
        return j;
        
	}
    public List<Planet> getPlanets() {
        return planets;
    }
        
	private void asymmetricalAddJumpPoint(StarSystem from, JumpPoint jumpPoint) {
		jumpPoints.put(from,jumpPoint);
	}
    public List<StarSystem> getNeighbors(){
        List<StarSystem> out=new ArrayList();
        jumpPoints.forEach((k,v) -> {
//                out.add(v.getTargetSystem());
        });
        return out;
    }

    public JumpPoint getJumpPoint(StarSystem s){
        return jumpPoints.get(s);
    }
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.name);
        hash = 59 * hash + Objects.hashCode(this.pos);
        return hash;
    }
	public void setFaction(Faction f){

		if (f == null) {
			throw new IllegalArgumentException("faction must be non-null");
		}
		faction=f;
	}


    public final void setStarMass(double starMass) {

    	if (starMass <= 0) {
    		throw new IllegalArgumentException("mass must be positive");
    	}
    	this.starMass = starMass;
    }

    public double getStarMass() {
    	return starMass;
    }
    public Faction getFaction(){
        return faction;
    }
    public StarType getStarType() {
        return starType;
    }
    public String getName() {
        return name;
    }
    
    public String toString() {
        return getName();
    }

//    @Override
//    public double getCostTo(Node node) {
//        if (!(node instanceof StarSystem)) {
//            return Double.POSITIVE_INFINITY;
//        }
//        
//        if (getJumpPoint((StarSystem) node) != null) {
//            return 1;
//        } else {
//            return Double.POSITIVE_INFINITY;
//        }
//    }
//
//    @Override
//    public List<Node> getSuccessors() {
//        List<Node> out=new ArrayList();
//        jumpPoints.forEach((k,v) -> {
//            out.add(v.getTargetSystem());
//        });
//        return out;
//    }
}