package com.mygdx.HVSS.actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.HVSS.Assets;
import com.mygdx.HVSS.Globals;

public class BackgroundActor extends Actor {
	private final int FRAMES =  10;
	private TextureRegion currentBackground;
	private TextureRegion[] backgroundFrames;
	private Animation backgroundAnimation;
	private float stateTime;
	
	public BackgroundActor() {
		TextureRegion[][] tmp = TextureRegion.split(Assets.background, Globals.width, Globals.height);
		backgroundFrames = new TextureRegion[FRAMES];
		int index = 0;
		for (int i = 0; i < FRAMES; i++)
			backgroundFrames[index++] = tmp[0][i];
		backgroundAnimation = new Animation(0.15f, backgroundFrames);
		stateTime = 0f;
		
		currentBackground = backgroundFrames[0];
		setSize(Globals.width, Globals.height);
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		Color color = getColor();
		batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
		batch.draw(currentBackground, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
	}

	@Override
	public void act(float delta) {
		super.act(delta);
		
		stateTime += delta;
		currentBackground = backgroundAnimation.getKeyFrame(stateTime, true);
	}
	
}
