package com.iluwatar.tictactoe;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class TicTacToeBaseScreen {
	
	private boolean active = false;
	
	public void dispose() {
	}
	
	public void render(SpriteBatch batch) {
	}
	
	public void resize(int width, int height) {
	}
	
	public void pause() {
	}
	
	public void resume() {
	}
	
	public boolean keyDown(int keycode) {
		return false;
	}
	
	public boolean keyUp(int keycode) {
		return false;
	}
	
	public boolean keyTyped(char character) {
		return false;
	}
	
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}
	
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}
	
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}
	
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}
	
	public boolean scrolled(int amount) {
		return false;
	}

	public boolean isActive() {
		return this.active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
}
