


public class PhysicsDescriptor {

	private BodyDef bodyDef;
	private FixtureDef fixtureDef;

	public PhysicsDescriptor() {
		bodyDef	= new BodyDef();
		// bodyDef.active				= 
		// bodyDef.allowSleep			= 
		bodyDef.angle				= 0.0f;
		bodyDef.angularDampening	= 0.0f;
		bodyDef.angularVelocity		= 0.0f;
		// bodyDef.awake				= 
		bodyDef.bullet				= false;
		bodyDef.fixedRotation		= false;
		// bodyDef.gravityScale		= 
		bodyDef.linearDampening		= new Vec2(0.0f, 0.0f);
		bodyDef.linearVelocity		= new Vec2(0.0f, 0.0f);
		bodyDef.position			= new Vec2(0.0f, 0.0f);
		bodyDef.type				= BodyType.DYNAMIC;
		// bodyDef.userData			= 

		fixtureDef = new FixtureDef();

		fixtureDef.density		= 1.0f;
		// fixtureDef.filter		=
		fixtureDef.friction		= 0.2f;
		// fixtureDef.isSensor		=
		fixtureDef.restitution	= 0.2f;
		fixtureDef.shape		= new CircleShape()
		fixtureDef.shape.m_radius = 1.0f;
		// fixtureDef.userData		=



	}

}