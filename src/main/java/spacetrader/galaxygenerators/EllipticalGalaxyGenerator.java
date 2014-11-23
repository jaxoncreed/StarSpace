package spacetrader.galaxygenerators;

//import spacetrader.Util;
import spacetrader.shared.Util;
import spacetrader.game_model.Galaxy;
import spacetrader.game_model.Position;
import spacetrader.game_model.StarType;
import spacetrader.game_model.StarSystem;
import java.util.List;
import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.distribution.UniformRealDistribution;
import org.apache.commons.math3.distribution.BinomialDistribution;

//#todo account for min distance between systems

/**
 * @author Michael Lane
 */
public class EllipticalGalaxyGenerator extends GalaxyGenerator {

	private Double _xSD;
	private Double _ySD;
	private Double _xBound;
	private Double _yBound;
	private Double _tiltRads;


    @Override
	public Galaxy generate() {

		NormalDistribution xDistr = new NormalDistribution(0, _xSD);
		NormalDistribution yDistr = new NormalDistribution(0, _ySD);
		Galaxy gax = new Galaxy(width, height);
		starSystemGenerator.setGalaxy(gax);
		for (int i = 0; i < getNumSystems(); i++) {
                       
			// randomly generate position
			float x = (float) xDistr.sample();
			float y = (float) yDistr.sample();
			Position pos = new Position(x, y);

			// calculate the distance from pos to all other StarSystems already generated
			boolean tooClose = false;
            List<StarSystem> systems = gax.getSystems();
            int size = systems.size();
			for (int j = 0; j < size && !tooClose; j++) {
                StarSystem system = systems.get(j);
				tooClose = pos.distTo(system.getPosition()) < minSystemDist;
			}

			// if pos is too far away from the origin
			if (
				1 > 		   (Math.pow(_xBound, 2)*Math.pow(_yBound, 2)) /
			//  ---------------------------------------------------------------------------
				(Math.pow(y, 2)*Math.pow(_xBound, 2) + Math.pow(x, 2)*Math.pow(_yBound, 2))
			
			// or if this position is too close to StarSystem already generated 
			|| tooClose) {
				// then loop again
				i--;			

			// otherwise make the system in all its glory
			} else {
                _tiltRads = Util.sampleFromUniformReal(0, Math.PI);
				pos.rotate(_tiltRads);
				// SET PROPERTIES FOR STARSYSTEM GENERATION HERE
				starSystemGenerator.setName("System " + i);
				starSystemGenerator.setPosition(pos);
				
				gax.addSystem(starSystemGenerator.generate());
			}
		}
		return gax;
	}

	public final void setXSD(Double xSD) {
		if (xSD <= 0) {
			throw new IllegalArgumentException("xSD must be positive");
		}
		_xSD = xSD;
	}

	public final void setYSD(Double ySD) {

		if (ySD <= 0) {
			throw new IllegalArgumentException("ySD must be positive");
		}
		_ySD = ySD;
	}

	public final void setXBound(Double xBound) {

		if (xBound <= 0) {
			throw new IllegalArgumentException("xBound must be positive");
		}
		_xBound = xBound;
	}

	public final void setYBound(Double yBound) {

		if (yBound <= 0) {
			throw new IllegalArgumentException("yBound must be positive");
		}
		_yBound = yBound;
	}

	public final void setTiltRads(Double tiltRads) {

		_tiltRads = tiltRads;
	}
}