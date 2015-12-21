package com.mygdx.gravitytoy.vc;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;

public class ControlProcessor implements InputProcessor {
	
	private boolean clicked ;
	private Vector2 mouseClick ;
	
	protected ControlProcessor() {
		this.mouseClick = new Vector2() ;
	}
	
	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		mouseClick.set(screenX, screenY) ;
		clicked = true ;
		return false;
	}
	
	public Vector2 getMouseClick() {
		Vector2 ans = null ;
		if (clicked) {
			clicked = false ;
			ans = new Vector2(mouseClick) ;
		}
		return ans ;
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
