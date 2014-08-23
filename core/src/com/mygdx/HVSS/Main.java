package com.mygdx.HVSS;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Main extends Game {
	private SpriteBatch batch;

	@Override
	public void create() {
		batch = new SpriteBatch();
		Globals.main = this;
		Screens.initialize(batch);
		setScreen(Screens.GAMESCREEN);
	}
	
	@Override
	public void dispose() {
		batch.dispose();
	}
}
