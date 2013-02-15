package com.iluwatar.tictactoe;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TicTacToeMenuScreen extends TicTacToeBaseScreen {
	
	private Texture menuTexture;
	private int x;
	private int y;
	private int dx;
	private int dy;
	
	TicTacToeMenuScreen() {
		menuTexture = new Texture(Gdx.files.internal("data/menu.png"));
		menuTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
	}

	@Override
	public void dispose() {
		menuTexture.dispose();
	}

	@Override
	public void render(SpriteBatch batch) {
		batch.draw(menuTexture, x, y, dx, dy);
	}
	
	@Override
	public void resize(int width, int height) {
		x = 0;
		y = 0;
		dx = width;
		dy = height;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		Gdx.app.exit();
		return false;
	}
	
}
