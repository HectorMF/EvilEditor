package com.perfectplay.org;

import com.artemis.World;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.perfectplay.org.graphics.SpriteLayer;
import com.perfectplay.org.systems.BackgroundRenderSystem;
import com.perfectplay.org.systems.PhysicsSystem;
import com.perfectplay.org.systems.RegionSystem;
import com.perfectplay.org.systems.ScriptSystem;
import com.perfectplay.org.systems.SpatialGridSystem;
import com.perfectplay.org.systems.RenderSystem;
import com.perfectplay.org.utils.SpatialGrid;

public class Level extends World {
	
	//sprite render systems
	private RenderSystem renderSystem;
	private SpatialGridSystem spatialGridSystem;
	private PhysicsSystem physicsSystem;
	private RegionSystem regionSystem;
	private ScriptSystem scriptSystem;

	private int width,height;
	
	private SpatialGrid spatialGrid;
	
	private Camera camera;
	
	public Level(int width, int height, int bucketSize, SpriteBatch batch, Vector2 gravity, boolean doSleep){
		
		this.width = width;
		this.height = height;
		this.spatialGrid = new SpatialGrid(width, height, bucketSize);
		
		//can grab these systems if needed later
		physicsSystem = setSystem(new PhysicsSystem(new com.badlogic.gdx.physics.box2d.World(gravity, doSleep)), true);
		regionSystem = setSystem(new RegionSystem(), true);
		renderSystem = setSystem(new RenderSystem(batch), true);
		spatialGridSystem = setSystem(new SpatialGridSystem(spatialGrid), true);
		scriptSystem = setSystem(new ScriptSystem(), false);
	}
	
	public void addSpriteLayer(String name, SpriteLayer layer){
		renderSystem.addLayer(name, layer);
	}
	
	@Override
	public void initialize(){
		super.initialize();
	}
	public SpatialGrid getSpatialGrid(){
		return spatialGrid;
	}
	public static Level loadLevel(){
		return null;
	}
	
	public void setCamera(Camera camera){
		this.camera = camera;
	}
	
	private void updateSpatialGrid(){
		spatialGrid.activateBucketsOnScreen(
				(int)(camera.position.x-(camera.viewportWidth/2)), 
				(int)(camera.position.y-(camera.viewportHeight/2)), 
				(int)camera.viewportWidth, 
				(int)camera.viewportHeight);
	}
	
	public static void saveLevel(Level level){
		
	}
	
	public void render(){
		renderSystem.process();
	}
	
	@Override
	public void process(){
		super.process();
		updateSpatialGrid();
		spatialGridSystem.process();
		physicsSystem.process();
		regionSystem.process();
	}

}
