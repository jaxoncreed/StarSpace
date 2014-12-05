package spacetrader.game_model;

import spacetrader.PhysicsSimulator;
import spacetrader.game_model.gameLogic.Item;
import spacetrader.game_model.system.StarSystem;
import spacetrader.game_model.gameLogic.Inventory;
import spacetrader.game_model.gameLogic.Position;
import spacetrader.game_model.system.JumpPoint;
import java.io.Serializable;
import org.jbox2d.dynamics.Body;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
import spacetrader.game_model.interactable.InteractableObject;
import spacetrader.game_model.interactable.InteractionType;
import org.jbox2d.dynamics.*;

/**
 * Ship model!
 */
public class Ship implements Tradeable, Serializable,InteractableObject {

    public String      name;
    public double      basePrice;
    public int         firePower;
    public Inventory   cargo;
    public int         health;
    public int         maxHealth;
    public double      linearThrust;
    public double      maxLinearSpeed;
    public float       linearBrake;
    public double      angularThrust;
    public double      maxAngularSpeed;
    public float       angularBrake;
    public double      interactionRange;

    public StarSystem system;
    private transient Body physicsBody;
    private transient PhysicsDescriptor physicsDescriptor;

    public Ship (ShipDef shipDef) {
        this.name              = shipDef.name;
        this.basePrice         = shipDef.basePrice;
        this.firePower         = shipDef.firePower;
        this.cargo             = shipDef.cargo;
        this.health            = shipDef.health;
        this.maxHealth         = shipDef.maxHealth;
        this.linearThrust      = shipDef.linearThrust;
        this.maxLinearSpeed    = shipDef.maxLinearSpeed;
        this.linearBrake       = shipDef.linearBrake;
        this.angularThrust     = shipDef.angularThrust;
        this.maxAngularSpeed   = shipDef.maxAngularSpeed;
        this.angularBrake      = shipDef.angularBrake;
        this.interactionRange  = shipDef.interactionRange;

        this.cargo             = shipDef.cargo;
        this.physicsDescriptor = new PhysicsDescriptor();
        this.system            = null;
        this.physicsBody       = null;
    }
        
    public void jump(JumpPoint jumpPoint) {
        this.setSystem(jumpPoint.getTargetSystem());
        this.setPosition(jumpPoint.getTargetPos());
    }
    public void updateShip(ShipDef shipDef) {
        this.name              = shipDef.name;
        this.basePrice         = shipDef.basePrice;
        this.firePower         = shipDef.firePower;
        this.cargo             = shipDef.cargo;
        this.health            = shipDef.health;
        this.maxHealth         = shipDef.maxHealth;
        this.linearThrust      = shipDef.linearThrust;
        this.maxLinearSpeed    = shipDef.maxLinearSpeed;
        this.linearBrake       = shipDef.linearBrake;
        this.angularThrust     = shipDef.angularThrust;
        this.maxAngularSpeed   = shipDef.maxAngularSpeed;
        this.angularBrake      = shipDef.angularBrake;
    }


    public void setSystem(StarSystem system) {
        if (this.system != null) {
            system.removeShip(this);
        }
        system.addShip(this);
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
        return physicsDescriptor.getPosition();
    }

    public double getAngle() {
        if (physicsBody != null) {
            return physicsBody.getAngle();
        }
        return physicsDescriptor.getAngle();
    }
    
    public double getRadius() {
        return physicsDescriptor.getRadius();
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
    public void setPhysicsDescriptor(PhysicsDescriptor d) {
        this.physicsDescriptor = d;
    }

    public void setPhysicsBody(Body body) {
        this.physicsBody = body;
    }
    public Body getPhysicsBody() {
        return this.physicsBody;
    }

    public void accelerate() {
        if (physicsBody != null) {
            physicsBody.setLinearDamping(0.0f);
            double angle = physicsBody.getAngle();
            Position force = new Position(linearThrust, 0.0);
            force.rotate(angle);
            force.y = -force.y;
            Vec2 centerOfMass = physicsBody.getWorldCenter();
            physicsBody.applyForce(force.toVec2(), centerOfMass);
            if (Math.abs(physicsBody.getLinearVelocity().length()) > maxLinearSpeed) {
                linearBrake();
            }
        }
    }
    
    public void turnLeft() {
        if (physicsBody != null) {
            physicsBody.setAngularDamping(0.0f);
            physicsBody.applyTorque((float)(angularThrust));
            if (Math.abs(physicsBody.getAngularVelocity()) > maxAngularSpeed) {
                angularBrake();
            }
        }
    }
    
    public void decelerate() {
        if (physicsBody != null) {
            physicsBody.setLinearDamping(0.0f);
            double angle = physicsBody.getAngle();
            Position force = new Position(-linearThrust, 0.0);
            force.rotate(angle);
            force.y = -force.y;
            Vec2 centerOfMass = physicsBody.getWorldCenter();
            physicsBody.applyForce(force.toVec2(), centerOfMass);
            if (Math.abs(physicsBody.getLinearVelocity().length()) > maxLinearSpeed) {
                linearBrake();
            }
        }
    }
    
    public void turnRight() {
        if (physicsBody != null) {
            physicsBody.setAngularDamping(0.0f);
            physicsBody.applyTorque((float)(-angularThrust));
            if (Math.abs(physicsBody.getAngularVelocity()) > maxAngularSpeed) {
                angularBrake();
            }
        }
    }

    // private int cnt = 0;
    public void linearBrake() {
        // System.out.println("LinearBreak" + (cnt++));
        physicsBody.setLinearDamping(linearBrake);
    }

    public void angularBrake() {
        physicsBody.setAngularDamping(angularBrake);
    }
    
    public void physicsUpdate() {
        BodyDef physDef = physicsDescriptor.getBodyDef();
        physDef.angle = physicsBody.getAngle();
        physDef.position = physicsBody.getWorldCenter();
        physDef.linearDamping = physicsBody.getLinearDamping();
        physDef.angularDamping = physicsBody.getAngularDamping();
    }

    public void enablePhysicalSimulation(World world) {
        System.out.println(physicsDescriptor.getPosition());
        physicsBody = world.createBody(physicsDescriptor.getBodyDef());
        physicsBody.createFixture(physicsDescriptor.getFixtureDef());
    }

    public void disablePhysicalSimulation() {
        setPosition(new Position(physicsBody.getPosition().x, physicsBody.getPosition().y));
        physicsBody = null;
    }

    @Override
    public double getInteractionRange() {
        return this.interactionRange;
    }

    @Override
    public String getInteractionMessage() {
        return "";
    }

    @Override
    public Position getPos() {
        return this.getPosition();
    }

    @Override
    public InteractionType getType() {
        return InteractionType.Null;
    }
}