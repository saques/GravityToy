package com.mygdx.gravitytoy.vc;

import java.util.List;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.gravitytoy.simulation.Particle;
import com.mygdx.gravitytoy.simulation.Universe;

public class Toy extends ApplicationAdapter {
	private Camera camera ;
	private ShapeRenderer renderer ;
	@SuppressWarnings("unused")
	private Viewport port ;
	private ControlProcessor input ;
	private Universe universe = Universe.getInstance();
	
	@Override
	public void create () {
		camera = new OrthographicCamera() ;
		renderer = new ShapeRenderer() ;
		port = new StretchViewport(Gdx.graphics.getWidth(), 
								   Gdx.graphics.getHeight(),camera);
		input = new ControlProcessor() ;
		Gdx.input.setInputProcessor(input);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		List<ParticleInformation> toDraw = update() ;
		camera.update();
	    renderer.begin(ShapeType.Filled);
		renderer.setColor(Color.WHITE);
	    Matrix4 mat = camera.combined.cpy();
	    mat.setToOrtho2D(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	    renderer.setProjectionMatrix(mat);
		for (ParticleInformation pi : toDraw) {
			renderer.circle(pi.getPosition().x, pi.getPosition().y, pi.getRadius());
		}
	    renderer.end();
	}
	
	private List<ParticleInformation> update(){
		List<ParticleInformation> ans = null ;
		Vector2 mousePosition = input.getMouseClick() ;
		if (mousePosition != null) {
			universe.addParticle(new Particle(mousePosition,100));
		}
		ans = universe.update(Gdx.graphics.getDeltaTime()) ;
		return ans ;
	}
}
