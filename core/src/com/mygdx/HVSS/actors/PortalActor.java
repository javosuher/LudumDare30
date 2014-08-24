package com.mygdx.HVSS.actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.HVSS.Assets;

public class PortalActor extends Actor {
	private final int FRAMES =  2;
	private TextureRegion currentPortal;
	private TextureRegion[] portalFrames;
	private Animation portalAnimation;
	private float stateTime;
	
	private Rectangle bounds;
	
	public PortalActor() {
		TextureRegion[][] tmp = TextureRegion.split(Assets.portal, Assets.portal.getWidth() / FRAMES, Assets.portal.getHeight());
		portalFrames = new TextureRegion[FRAMES];
		portalFrames[0] = tmp[0][0];
		portalFrames[1] = tmp[0][1];
		portalAnimation = new Animation(0.15f, portalFrames);
		stateTime = 0f;
		
		currentPortal = portalFrames[0];
		setSize(Assets.portal.getWidth() / FRAMES, Assets.portal.getHeight());
		bounds = new Rectangle(getX(), getY(), getWidth(), getHeight());
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		Color color = getColor();
		batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
		batch.draw(currentPortal, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
	}

	@Override
	public void act(float delta) {
		super.act(delta);
		
		stateTime += delta;
		currentPortal = portalAnimation.getKeyFrame(stateTime, true);
	}	
	
	public Rectangle getBounds() {
		return bounds;
	}
	public void setBounds(float x, float y) {
		bounds.x = x;
		bounds.y = y;
	}
}
