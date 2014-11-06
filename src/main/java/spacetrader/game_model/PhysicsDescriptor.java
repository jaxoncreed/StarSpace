package spacetrader.game_model;

import org.jbox2d.dynamics.*;
import org.jbox2d.common.Vec2;
import org.jbox2d.collision.shapes.CircleShape;

public class PhysicsDescriptor {

    private BodyDef bodyDef;
    private FixtureDef fixtureDef;

    public PhysicsDescriptor() {
        bodyDef = new BodyDef();
        bodyDef.active            = true; 
        // bodyDef.allowSleep     = 
        bodyDef.angle             = 0.0f;
        bodyDef.angularDamping    = 0.0f;
        bodyDef.angularVelocity   = 0.0f;
        // bodyDef.awake          = 
        bodyDef.bullet            = false;
        bodyDef.fixedRotation     = false;
        // bodyDef.gravityScale   = 
        bodyDef.linearDamping     = 0.0f;
        bodyDef.linearVelocity    = new Vec2(0.0f, 0.0f);
        bodyDef.position          = new Vec2(0.0f, 0.0f);
        bodyDef.type              = BodyType.DYNAMIC;
        // bodyDef.userData       = 

        fixtureDef = new FixtureDef();

        fixtureDef.density        = 1.0f;
        // fixtureDef.filter      =
        fixtureDef.friction       = 0.2f;
        // fixtureDef.isSensor    =
        fixtureDef.restitution    = 0.2f;
        fixtureDef.shape          = new CircleShape();
        fixtureDef.shape.m_radius = 1.0f;
        // fixtureDef.userData    =
    }

    public BodyDef getBodyDef() {
        return bodyDef;
    }

    public FixtureDef getFixtureDef() {
        return fixtureDef;
    }

    public Position getPosition() {
        return new Position(bodyDef.position.x, bodyDef.position.y);
    }

    public void setPosition(Position pos) {
        bodyDef.position.x = (float)pos.x;
        bodyDef.position.y = (float)pos.y;
    }

    public double getAngle() {
        return bodyDef.angle;
    }

}