package com.iluwatar.tictactoe;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.math.Rectangle;
import com.iluwatar.tictactoe.TicTacToeUtils.TicTacToeScreen;

public class TicTacToeMenuScreen extends TicTacToeBaseScreen {
	
	private static final String LOG = TicTacToeGame.class.getSimpleName() + "." + TicTacToeUtils.getMethodName(0);
	
	private Texture menuTexture;
	private int x;
	private int y;
	private int dx;
	private int dy;
	private ScreenCallback callback;
	private float scaleX;
	private float scaleY;
	
	Rectangle easyRect = new Rectangle(10, 60, 235, 35);
	Rectangle easyRectScaled = new Rectangle(0, 0, 0, 0);
	Rectangle mediumRect = new Rectangle(10, 114, 235, 35);
	Rectangle mediumRectScaled = new Rectangle(0, 0, 0, 0);
	Rectangle hardRect = new Rectangle(10, 163, 235, 35);
	Rectangle hardRectScaled = new Rectangle(0, 0, 0, 0);
	Rectangle exitRect = new Rectangle(10, 216, 235, 35);
	Rectangle exitRectScaled = new Rectangle(0, 0, 0, 0);
	
	TicTacToeMenuScreen(ScreenCallback callback) {
		this.callback = callback;
		menuTexture = new Texture(Gdx.files.internal("data/menu.png"));
		menuTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
	}

	@Override
	public void dispose() {
		super.dispose();
		menuTexture.dispose();
	}

	@Override
	public void render() {
		batch.begin();
		batch.draw(menuTexture, x, y, dx, dy);
		batch.end();
	}
	
	@Override
	public void resize(int width, int height) {
		this.x = 0;
		this.y = 0;
		this.dx = width;
		this.dy = height;
		this.scaleX = (float)this.dx / 256;
		this.scaleY = (float)this.dy / 256;
		Gdx.app.log(LOG, "scaleX=" + this.scaleX + " scaleY=" + this.scaleY);
		this.easyRectScaled = scaleRectangle(this.easyRect, this.scaleX, this.scaleY);
		Gdx.app.log(LOG, "easyRectScaled " + this.easyRectScaled.toString());
		this.mediumRectScaled = scaleRectangle(this.mediumRect, this.scaleX, this.scaleY);
		this.hardRectScaled = scaleRectangle(this.hardRect, this.scaleX, this.scaleY);
		this.exitRectScaled = scaleRectangle(this.exitRect, this.scaleX, this.scaleY);
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		if (easyRectScaled.contains(screenX, screenY)) {
			Gdx.app.log(LOG, "easy rect hit");
			callback.endScreen(TicTacToeScreen.PLAY, 0);
		} else if (mediumRectScaled.contains(screenX, screenY)) {
			Gdx.app.log(LOG, "medium rect hit");
			callback.endScreen(TicTacToeScreen.PLAY, 1);
		} else if (hardRectScaled.contains(screenX, screenY)) {
			Gdx.app.log(LOG, "hard rect hit");
			callback.endScreen(TicTacToeScreen.PLAY, 2);
		} else if (exitRectScaled.contains(screenX, screenY)) {
			Gdx.app.log(LOG, "exit rect hit");
			callback.endScreen(TicTacToeScreen.EXIT);
		}
		return false;
	}
	
	private Rectangle scaleRectangle(Rectangle rect, float scaleX, float scaleY) {
		Rectangle scaled = new Rectangle();
		scaled.setX(rect.getX()*scaleX);
		scaled.setY(rect.getY()*scaleY);
		scaled.setWidth(rect.getWidth()*scaleX);
		scaled.setHeight(rect.getHeight()*scaleY);
		return scaled;
	}
	
}
