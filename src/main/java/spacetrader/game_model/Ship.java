package spacetrader.game_model;

import java.io.Serializable;
import org.jbox2d.dynamics.Body;
import org.jbox2d.common.Vec2;

/**
 * Ship model!
 */
public class Ship implements Tradeable, Serializable {

    private final String name;
    private double basePrice;
    private int firePower;
    private final Inventory cargo;
    private int health;
    private int maxHealth;
    private StarSystem system;
    private Body physicsBody;
    private PhysicsDescriptor physicsDescriptor;
    private double linearAcceleration;
    private double maxLinearSpeed;
    private double angularAcceleration;
    private double maxAngularSpeed;

    public Ship(String name) {
        this.basePrice = 1000;
        this.name = name;
        this.firePower = 10;
        this.cargo = new Inventory();
        this.physicsDescriptor = new PhysicsDescriptor();
        this.health = 100;
        this.maxHealth = 100;
        this.system = null;
        this.physicsBody = null;
        this.linearAcceleration = 1.0;
        this.maxLinearSpeed = 10.0;
        this.angularAcceleration = 1.0;
        this.maxAngularSpeed = 10.0;
    }
        
    public void jump(JumpPoint jumpPoint) {
        system.removeShip(this);
        jumpPoint.getTargetSystem().addShip(this);
        this.setSystem(jumpPoint.getTargetSystem());
        this.setPosition(jumpPoint.getTargetPos());
    }

    public void setSystem(StarSystem system) {
        this.system = system;
    }

    public StarSystem getSystem() {
        return system;
    }

    public boolean addItem(Item i) {
        return cargo.add(i);
    }
    
    public boolean removeItem(Item i) {
        return cargo.remove(i);
    }
    
    @Override
    public double getBasePrice() {
        return basePrice;
    }
    
    public String getName() {
        return name;
    }
    
    public Inventory getCargo() {
        return cargo;
    }
    
    public int getFirePower() {
        return firePower;
    }

    public void setCargoSize(int size) {
        cargo.setMaxSize(size);
    }
    
    public void setFirePower(int power) {
        firePower = power;
    }
    
    public boolean addToCargo(Item i) {
        return cargo.add(i);
    }
    
    public void setBasePrice(double value) {
        basePrice = value;
    }
    
    public void setPosition(Position pos){
        physicsDescriptor.setPosition(pos);
    }
    
    public Position getPosition() {
        if (physicsBody != null) {
            Vec2 pos = physicsBody.getWorldCenter();
            return new Position(pos.x, pos.y);
        }
        return physicsDescriptor.getPosition();
    }
    
    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }
    
    public void setHealth(int health) {
        this.health = health; 
    }
    
    public void incrementHealth(int delta) {
        this.health += delta;
    }

    public PhysicsDescriptor getPhysicsDescriptor() {
        return physicsDescriptor;
    }

    public void setPhysicsBody(Body body) {
        this.physicsBody = body;
    }

    public void accelerate() {
        if (physicsBody != null) {
            double angle = physicsBody.getAngle();
            Position force = new Position(linearAcceleration, 0.0);
            force.rotate(angle);
            Vec2 centerOfMass = physicsBody.getLocalCenter();
            physicsBody.applyForce(force.toVec2(), centerOfMass);
        }
    }
    
    public void turnLeft() {
        if (physicsBody != null) {
            physicsBody.applyTorque((float)(-angularAcceleration));
        }
    }
    
    public void decelerate() {
        if (physicsBody != null) {
            double angle = physicsBody.getAngle();
            Position force = new Position(-linearAcceleration, 0.0);
            force.rotate(angle);
            Vec2 centerOfMass = physicsBody.getLocalCenter();
            physicsBody.applyForce(force.toVec2(), centerOfMass);
        }
    }
    
    public void turnRight() {
        if (physicsBody != null) {
            physicsBody.applyTorque((float)angularAcceleration);
        }
    }

}