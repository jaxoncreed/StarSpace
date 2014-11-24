package spacetrader;

import org.jbox2d.callbacks.*;
import org.jbox2d.collision.*;
import org.jbox2d.collision.shapes.*;
import org.jbox2d.common.*;
import org.jbox2d.dynamics.*;
import org.jbox2d.pooling.*;
import org.jbox2d.profile.*;

import spacetrader.game_model.*;

public class PhysicsSimulator {

	private static final float TIME_STEP = 1.0f/60.0f;
	private static final int VELOCITY_ITERATIONS = 10;
	private static final int POSITION_ITERATIONS = 10;

	private static final Vec2 GRAVITY = new Vec2(0.0f, 0.0f);
	private static World world;
        private static StarSystem system = null;
            
	public static void setSystem(StarSystem s) {
            if (system != null) {
		for (Ship ship : system.getShips()) {
			ship.disablePhysicalSimulation();
		}                
            }
            system = s;
            // Initialize world (no gravity)
            world = new World(GRAVITY);
            
		// Add all physics objects
            for (Ship ship : system.getShips()) {
		ship.enablePhysicalSimulation(world);
            }
	}

	public static void simulate() {
		world.step(TIME_STEP, VELOCITY_ITERATIONS, POSITION_ITERATIONS);
	}

}