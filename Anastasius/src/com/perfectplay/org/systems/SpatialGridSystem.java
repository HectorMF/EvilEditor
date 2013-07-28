package com.perfectplay.org.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.artemis.annotations.Mapper;
import com.artemis.utils.ImmutableBag;
import com.perfectplay.org.components.Transform;
import com.perfectplay.org.utils.SpatialGrid;

public class SpatialGridSystem extends EntitySystem{
	@Mapper ComponentMapper<Transform> transforms;
	
	private SpatialGrid spatialGrid;
	@SuppressWarnings("unchecked")
	public SpatialGridSystem(SpatialGrid spatialGrid) {
		super(Aspect.getAspectForAll(Transform.class));
		this.spatialGrid = spatialGrid;
	}

	@Override
	protected void inserted(Entity e) {
		super.inserted(e);
		if(transforms.get(e).getBuckets() == null)
		spatialGrid.insertEntity(e, transforms.get(e));
	}
	
	@Override
    protected final void processEntities(ImmutableBag<Entity> entities) {
		for (int i = 0, s = entities.size(); s > i; i++) {
        	process(entities.get(i));
    	}
    }
	
	@Override
	protected void removed(Entity e) {
		super.removed(e);
		//spatialGrid.removeEntity(e);
	}
	
	protected void process(Entity e) {
		Transform transform = transforms.get(e);
		if(transform.isDirty()){
			if(transform.hasDelegates()){
				transform.getDelegateForEntity(e).onMove(e, transform);
			}
			spatialGrid.updateEntity(e,transform);
			transforms.get(e).setDirty(false);
		}
	}

	@Override
	protected boolean checkProcessing() {
		return true;
	}
}
