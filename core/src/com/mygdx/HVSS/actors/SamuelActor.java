package com.mygdx.HVSS.actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.HVSS.Assets;
import com.mygdx.HVSS.Globals;

public class SamuelActor extends Actor {
	private final int FRAMES =  2;
	private TextureRegion currentSamuel;
	private TextureRegion[] samuelFrames;
	private Animation samuelAnimation;
	private float stateTime;
	private boolean update;
	
	private Rectangle bounds;
	
	public SamuelActor() {
		TextureRegion[][] tmp = TextureRegion.split(Assets.samuel, Assets.samuel.getWidth() / FRAMES, Assets.samuel.getHeight());
		samuelFrames = new TextureRegion[FRAMES];
		samuelFrames[0] = tmp[0][0];
		samuelFrames[1] = tmp[0][1];
		samuelAnimation = new Animation(0.7f, samuelFrames);
		stateTime = 0f;
		
		currentSamuel = samuelFrames[0];
		setSize(Assets.samuel.getWidth() / FRAMES, Assets.samuel.getHeight());
		bounds = new Rectangle(getX(), getY(), getWidth(), getHeight());
		update = false;
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		Color color = getColor();
		batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
		batch.draw(currentSamuel, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
	}

	@Override
	public void act(float delta) {
		super.act(delta);
		
		stateTime += delta;
		currentSamuel = samuelAnimation.getKeyFrame(stateTime, true);
		
		if(update) {
			setPosition(getX() - Globals.SPEEDSAM * delta, getY());
			setBounds(getX(), getY());
		}
	}
	
	public void update() {
		update = true;
	}
	public void disable() {
		update = false;
	}
	public boolean getUpdate() {
		return update;
	}
	
	public Rectangle getBounds() {
		return bounds;
	}
	public void setBounds(float x, float y) {
		bounds.x = x;
		bounds.y = y;
	}
}
