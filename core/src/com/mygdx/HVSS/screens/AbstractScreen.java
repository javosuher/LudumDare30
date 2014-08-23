package com.mygdx.HVSS.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class AbstractScreen implements Screen {
	protected SpriteBatch batch;
	
	public AbstractScreen(SpriteBatch batch) {
		this.batch = batch;
	}

	@Override
	public void render(float delta) {}

	@Override
	public void resize(int width, int height) {}

	@Override
	public void show() {}

	@Override
	public void hide() {}

	@Override
	public void pause() {}

	@Override
	public void resume() {}

	@Override
	public void dispose() {}	
}
