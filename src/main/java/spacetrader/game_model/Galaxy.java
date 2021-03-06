package spacetrader.game_model;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

/**
 * Presently only a single galaxy will ever be instantiated.
 */
public class Galaxy implements Serializable {

	private Map<Position,StarSystem> systems;
	private Position pos;
	private double width;
	private double height;

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

}