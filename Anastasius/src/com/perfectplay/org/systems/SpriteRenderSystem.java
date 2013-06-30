package com.perfectplay.org.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.perfectplay.org.components.SpriteRender;
import com.perfectplay.org.components.Transform;

public class SpriteRenderSystem extends EntityProcessingSystem {
	@Mapper ComponentMapper<Transform> transforms;
	@Mapper ComponentMapper<SpriteRender> sprites;
	
	private SpriteBatch batch;
	@SuppressWarnings("unchecked")
	public SpriteRenderSystem(SpriteBatch batch) {
		super(Aspect.getAspectForAll(Transform.class, SpriteRender.class));
		this.batch = batch;
	}

	@Override
	protected void process(Entity e) {
		Transform t = transforms.get(e);
		SpriteRender s = sprites.get(e);
		s.getSprite().update(world.delta*1000);

		s.getSprite().draw(batch, t.getX(), t.getY(), t.getOriginX(),t.getOriginY(), t.getWidth(), 
							t.getHeight(), t.getScaleX(), t.getScaleY(), t.getRotation(), t.getHorizontalFlip(), t.getVerticalFlip());
	}
}