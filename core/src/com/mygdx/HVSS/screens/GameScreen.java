package com.mygdx.HVSS.screens;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
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
	private BackgroundActor background;
	private ShipActor ship;
	private PortalActor portal;
	private SamuelActor samuel1, samuel2, samuel3;
	private int limit;
	private Timer time;
	private Random random;
	private int score;
	private boolean gameOver;
	private boolean gameWin;
	
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
		portal.setPosition(-500, -500);
		
		limit = Globals.height - Assets.samuel.getHeight();
		random = new Random();
		samuel1 = new SamuelActor();
		samuel2 = new SamuelActor();
		samuel3 = new SamuelActor();
		samuel1.setPosition(Globals.width, random.nextInt(limit));
		samuel2.setPosition(Globals.width, random.nextInt(limit));
		samuel3.setPosition(Globals.width, random.nextInt(limit));
		samuel1.update();
		score = 0;
		
		stage.addActor(background);
		stage.addActor(portal);
		stage.addActor(samuel1);
		stage.addActor(samuel2);
		stage.addActor(samuel3);
		stage.addActor(ship);
		Gdx.input.setInputProcessor(stage);
		
		time = new Timer(2);
		time.start();
		
		gameOver = false;
		gameWin = false;
		
		Assets.font.setColor(Color.MAGENTA);
		Assets.font.setScale(2);
		Assets.run.play();
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		System.out.println("FPS: " + Gdx.graphics.getFramesPerSecond() + " ------ Score: " + score + " -------------- VelSam: " + Globals.SPEEDSAM);
		
		if(!gameOver && !gameWin)
			normalRender(delta);
		else if(gameOver)
			gameOverRender(delta);
		else if(gameWin)
			winRender(delta);
	}
	
	private void normalRender(float delta) {
		SamuelsUpdate();
		portalUpdate();
		loseUpdate();
		collisions();
		
		stage.act(delta);
		stage.draw();
		
		batch.begin();
		Assets.font.draw(batch, time.getTime(), Globals.width / 2 - 70, Globals.height);
		batch.end();
	}
	private void gameOverRender(float delta) {
		batch.begin();
		Assets.font.draw(batch, "YOU LOSE", Globals.width / 2 - 150, Globals.height / 2);
		batch.end();
	}
	private void winRender(float delta) {
		batch.begin();
		ship.draw(batch, 1);
		Assets.font.draw(batch, "YOU WIN", Globals.width / 2 - 140, Globals.height / 2);
		batch.end();
	}
	
	
	private void collisions() {
		if(samuel1.getBounds().overlaps(ship.getBounds())) {
			Assets.ag.play();
			samuel1.setPosition(-200, 0);
			score -= 3;
		}
		if(samuel2.getBounds().overlaps(ship.getBounds())) {
			Assets.ag.play();
			samuel2.setPosition(-200, 0);
			score -= 3;
		}
		if(samuel3.getBounds().overlaps(ship.getBounds())) {
			Assets.ag.play();
			samuel3.setPosition(-200, 0);
			score -= 3;
		}
		if(portal.getBounds().overlaps(ship.getBounds())) {
			Assets.portalS.play();
			Assets.win.loop();
			Assets.win.play();
			gameWin = true;
		}	
	}
	private void portalUpdate() {
		if(score >= Globals.MAXSCORE && !portal.getUpdate()) {
			portal.update();
			portal.setPosition(Globals.width, Globals.height / 2 - Assets.portal.getHeight() / 2);
		}
		if(portal.getUpdate()) {
			if(portal.getX() < 0 - Assets.portal.getWidth() / 2) {
				score -= Globals.MAXSCORE;
				portal.disable();
			}
		}
	}
	private void SamuelsUpdate() {
		if(samuel1.getUpdate()) {
			if(!samuel2.getUpdate() && samuel1.getX() < Globals.width / 2) {
				samuel2.setPosition(Globals.width, random.nextInt(limit));
				samuel2.update();
			}
			else if(samuel1.getX() < 0 - Assets.samuel.getWidth() / 2) {
				Globals.SPEEDSAM += 9;
				samuel1.disable();
				score++;
			}
		}
		if(samuel2.getUpdate()) {
			if(!samuel3.getUpdate() && samuel2.getX() < Globals.width / 2) {
				samuel3.setPosition(Globals.width, random.nextInt(limit));
				samuel3.update();
			}
			else if(samuel2.getX() < 0 - Assets.samuel.getWidth() / 2) {
				Globals.SPEEDSAM += 9;
				samuel2.disable();
				score++;
			}
		}
		if(samuel3.getUpdate()) {
			if(!samuel1.getUpdate() && samuel3.getX() < Globals.width / 2) {
				samuel1.setPosition(Globals.width, random.nextInt(limit));
				samuel1.update();
			}
			else if(samuel3.getX() < 0 - Assets.samuel.getWidth() / 2) {
				Globals.SPEEDSAM += 9;
				samuel3.disable();
				score++;
			}
		}
	}
	private void loseUpdate() {
		if(time.isOver()) {
			Assets.lose.loop();
			Assets.lose.play();
			gameOver = true;
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
