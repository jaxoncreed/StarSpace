package spacetrader.game_model.system;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import spacetrader.game_model.Positionable;
import spacetrader.game_model.gameLogic.Position;
import spacetrader.game_model.graph.Edge;
import spacetrader.game_model.graph.EuclideanHeuristic;
import spacetrader.game_model.graph.Graph;

/**
 * Presently only a single galaxy will ever be instantiated.
 */
public class Galaxy implements Serializable, Positionable {

    private Map<Position,StarSystem> systems;
    private Position pos;
    private double width;
    private double height;
    private Graph jumpPoints; 

	public Galaxy() {
		systems = new HashMap();
		pos = Position.origin;
	}

	public Galaxy(double width, double height) {
		this();
		setWidth(width);
		setHeight(height);
	}

	public void addSystem(StarSystem system) {
		systems.put(system.getPosition(), system);
	}

    public Map<Position,StarSystem> getHashMap(){
        return systems;
    }

	public List<StarSystem> getSystems() {
		return new ArrayList<StarSystem>(systems.values());
	}

	public void replaceSystems(List<StarSystem> systems) {
		this.systems = new HashMap();
		for (StarSystem system : systems) {
			addSystem(system);
		}
	}

	public final void setWidth(double width) {
		if (width <= 0) {
			throw new IllegalArgumentException("width must be positive; " + width + " given");
		}
		this.width = width;
	}

	public final void setHeight(double height) {

		if (height <= 0) {
			throw new IllegalArgumentException("height must be positive; " + height + " given");
		}
		this.height = height;
	}

	public double getWidth() {
		return width;
	}
	public double getHeight() {
		return height;
	}
    
    public Position getPosition(){
        return pos;
    }
    
    public void setJumpPoints(Graph jumpPoints) {
        this.jumpPoints = jumpPoints;
    }
    public ArrayList<JumpPoint> findPath(StarSystem start,StarSystem end){
        List<Edge> edges=this.jumpPoints.aStarGraphSearch(start, end, new EuclideanHeuristic());
        ArrayList<JumpPoint> jumpPoints=new ArrayList();
        for(Edge e:edges){
            jumpPoints.add((JumpPoint)e);
        }
        return jumpPoints;
    }
    public Map<StarSystem, Double> getDistancesTo(StarSystem system) {
        Map<StarSystem, Double> dists = new HashMap();
        Position pos = system.getPosition();
        Iterable<StarSystem> starSystems = systems.values();
        for (StarSystem sys : starSystems) {
            dists.put(sys, pos.distTo(sys.getPosition()));
        }
        return dists;
    }
}
