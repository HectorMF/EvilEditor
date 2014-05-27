package com.perfectplay.org.components;

import java.util.ArrayList;

import com.artemis.Component;
import com.artemis.Entity;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.perfectplay.org.systems.PhysicsSystem;
import com.perfectplay.org.utils.EntityBodyMapper;
import com.perfectplay.org.utils.Pixel;

public class RigidBody extends Component {
	private Body rigidBody;
	
	private ArrayList<FixtureDef> fixtures;

	public RigidBody(Entity entity, BodyType type) {
		super();
		fixtures = new ArrayList<FixtureDef>();
		if (EntityBodyMapper.getInstance().hasBody(entity)) {
			rigidBody = EntityBodyMapper.getInstance().getBody(entity);
		} else {
			BodyDef bodDef = new BodyDef();
			rigidBody = PhysicsSystem.createBody(bodDef);
			EntityBodyMapper.getInstance().registerBody(entity, rigidBody);
		}
		rigidBody.setType(type);
		rigidBody.setUserData(entity);
	}

	public void addFixture(FixtureDef fixtureDef) {
		rigidBody.createFixture(fixtureDef);
		fixtures.add(fixtureDef);
	}

	public ArrayList<FixtureDef> getFixtures() {
		return fixtures;
	}

	public static FixtureDef createPolygonFixture(Vector2[] vertices,
			float density, float restitution, float friction) {
		PolygonShape shape = new PolygonShape();
		shape.set(vertices);
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.density = density;
		fixtureDef.restitution = restitution;
		fixtureDef.friction = friction;
		return fixtureDef;
	}

	public static FixtureDef createCircleFixture(float radius,
			Vector2 position, float density, float restitution, float friction) {
		CircleShape shape = new CircleShape();
		shape.setRadius(radius);
		shape.setPosition(position);
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.density = density;
		fixtureDef.restitution = restitution;
		fixtureDef.friction = friction;
		return fixtureDef;
	}

	public static FixtureDef createBoxFixture(float width, float height,
			Vector2 position, float angle, float density, float restitution,
			float friction) {
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(Pixel.toMeter(width / 2), Pixel.toMeter(height / 2),
				Pixel.toMeter(position), (float) Math.toRadians(angle));
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.density = density;
		fixtureDef.restitution = restitution;
		fixtureDef.friction = friction;
		return fixtureDef;
	}

	public Vector2 getVelocity() {
		return rigidBody.getLinearVelocity();
	}

	public void setLinearVelocity(Vector2 velocity) {
		rigidBody.setLinearVelocity(velocity.x, velocity.y);
	}

	public void setLinearDamping(float damping) {
		rigidBody.setLinearDamping(damping);
	}

	public Body getBody() {
		return rigidBody;
	}

}
