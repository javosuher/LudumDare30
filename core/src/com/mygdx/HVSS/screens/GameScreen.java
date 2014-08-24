package com.mygdx.HVSS.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.HVSS.Assets;
import com.mygdx.HVSS.Globals;
import com.mygdx.HVSS.actors.BackgroundActor;
import com.mygdx.HVSS.actors.ShipActor;

public class GameScreen extends AbstractScreen {
	private Stage stage;
	private Actor background, ship;

	public GameScreen(SpriteBatch batch) {
		super(batch);
	}
	
	@Override
	public void show() {
		stage = new Stage(new FitViewport(Globals.width, Globals.height), batch);
		background = new BackgroundActor();
		ship = new ShipActor();
		ship.setPosition(10, Globals.height / 2 - Assets.ship.getHeight() / 2);
		
		stage.addActor(background);
		stage.addActor(ship);
		Gdx.input.setInputProcessor(stage);
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		System.out.println("FPS: " + Gdx.graphics.getFramesPerSecond());
		
		stage.act(delta);
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, true);
	}
	
	@Override
	public void dispose() {
		stage.dispose();
	}	
}
