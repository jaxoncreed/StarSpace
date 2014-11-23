package spacetrader.game_model;

import java.io.Serializable;
import org.jbox2d.common.Vec2;

public class Position implements Serializable {
	public double x;
	public double y;
	public static final Position origin = new Position(0,0);

	public Position(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public Position(Position that) {
		this.x = that.x;
		this.y = that.y;
	}


	public void add(Position that) {
		this.x += that.x;
		this.y += that.y;
	}

	public void sub(Position that){
		this.x-= that.x;
		this.y-= that.y;
	}

	public void rotate(double theta) {
		double r = distTo(origin);
		double alpha = toAngle();

		this.x = (double)(r * Math.cos(theta + alpha));
		this.y = (double)(r * Math.sin(theta + alpha));
	}

	public void average(Position that) {
		this.x = (this.x + that.x) / 2.0f;
		this.y = (this.y + that.y) / 2.0f;
	}

	public void average(Position that, double weight) {
		this.x = (this.x + that.x * weight) / (1.0f + weight);
		this.y = (this.y + that.y * weight) / (1.0f + weight);
	}

	/**
	* Reflects the vector about the input vector
	* @param n the vector perpendicular to the surface of the reflecting object
	*/
	public void reflectOff(Position n) {
		double len2 = n.x * n.x + n.y * n.y;
		double dot = x * n.x + y * n.y;
		this.x = x - 2*dot*n.x / len2;
		this.y = y - 2*dot*n.y / len2;
	}

	public double distTo(Position that) {
		return Math.sqrt((this.x - that.x) * (this.x - that.x) + (this.y - that.y) * (this.y - that.y));
	}
    
    /**
     * I (Michael) used this for testing/debugging purposes; it prolly won't be used
     * elsewhere.
     * @param that
     * @return 
     */
    public double manhattanDistTo(Position that) {
        double dx = Math.abs(that.x - this.x);
        double dy = Math.abs(that.y - this.y);
        return dx + dy;
    }

	public double angleTo(Position that) {
		return Math.atan2(this.y - that.y, this.x - that.x);
	}

	public double toAngle() {
		return Math.atan2(this.y, this.x);
	}

	public int relQuadrant(Position that) {
		if (this.x < that.x && this.y < that.y) return 1;
		if (this.x > that.x && this.y < that.y) return 2;
		if (this.x > that.x && this.y > that.y) return 3;
		if (this.x < that.x && this.y > that.y) return 4;
		return 0;
	}

	public boolean equals(Position that) {
		return (this.x == that.x && this.y == that.y);
	}

	public String toString() {
		String s = "(" + x + ", " + y + ")";
		return s;
	}

	public Vec2 toVec2() {
		return new Vec2((float)x, (float)y);
	}

}