package com.mygdx.HVSS.screens;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.HVSS.Assets;
import com.mygdx.HVSS.Globals;
import com.mygdx.HVSS.Timer;
import com.mygdx.HVSS.actors.BackgroundActor;
import com.mygdx.HVSS.actors.PortalActor;
import com.mygdx.HVSS.actors.SamuelActor;
import com.mygdx.HVSS.actors.ShipActor;

public class GameScreen extends AbstractScreen {
	private Stage stage;
	private Actor background, ship, portal;
	private SamuelActor samuel1, samuel2, samuel3;
	private int limit;
	private Timer timer;
	private Random random;
	private int score;
	
	public GameScreen(SpriteBatch batch) {
		super(batch);
	}
	
	@Override
	public void show() {
		stage = new Stage(new FitViewport(Globals.width, Globals.height), batch);
		background = new BackgroundActor();
		ship = new ShipActor();
		ship.setPosition(10, Globals.height / 2 - Assets.ship.getHeight() / 2);
		portal = new PortalActor();
		portal.setPosition(Globals.width - 10 - Assets.portal.getWidth() / 2, Globals.height / 2 - Assets.portal.getHeight() / 2);
		
		limit = Globals.height - Assets.samuel.getHeight();
		random = new Random();
		samuel1 = new SamuelActor();
		samuel2 = new SamuelActor();
		samuel3 = new SamuelActor();
		samuel1.setPosition(Globals.width, random.nextInt(limit));
		samuel2.setPosition(Globals.width, random.nextInt(limit));
		samuel3.setPosition(Globals.width, random.nextInt(limit));
		samuel1.update();
		
		stage.addActor(background);
		stage.addActor(portal);
		stage.addActor(samuel1);
		stage.addActor(samuel2);
		stage.addActor(samuel3);
		stage.addActor(ship);
		Gdx.input.setInputProcessor(stage);
		
		timer = new Timer(5);
		timer.start();
		
		Assets.font.setColor(Color.MAGENTA);
		Assets.font.setScale(2);
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		System.out.println("FPS: " + Gdx.graphics.getFramesPerSecond());
		
		SamuelsUpdate();
		
		stage.act(delta);
		stage.draw();
		
		batch.begin();
		Assets.font.draw(batch, timer.getTime(), Globals.width / 2 - 70, Globals.height);
		batch.end();
	}
	
	private void collisions() {
		
	}
	
	private void SamuelsUpdate() {
		if(samuel1.getUpdate()) {
			if(!samuel2.getUpdate() && samuel1.getX() < Globals.width / 2) {
				samuel2.setPosition(Globals.width, random.nextInt(limit));
				samuel2.update();
			}
			else if(samuel1.getX() < 0 - Assets.samuel.getWidth() / 2) {
				samuel1.disable();
			}
		}
		if(samuel2.getUpdate()) {
			if(!samuel3.getUpdate() && samuel2.getX() < Globals.width / 2) {
				samuel3.setPosition(Globals.width, random.nextInt(limit));
				samuel3.update();
			}
			else if(samuel2.getX() < 0 - Assets.samuel.getWidth() / 2) {
				samuel2.disable();
			}
		}
		if(samuel3.getUpdate()) {
			if(!samuel1.getUpdate() && samuel3.getX() < Globals.width / 2) {
				samuel1.setPosition(Globals.width, random.nextInt(limit));
				samuel1.update();
			}
			else if(samuel3.getX() < 0 - Assets.samuel.getWidth() / 2) {
				samuel3.disable();
			}
		}
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
