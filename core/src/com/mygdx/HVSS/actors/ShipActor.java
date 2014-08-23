package com.mygdx.HVSS.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.HVSS.Assets;
import com.mygdx.HVSS.Globals;

public class ShipActor extends Actor {
	private TextureRegion regionShip;
	private Rectangle bounds;
	
	public ShipActor() {
		regionShip = new TextureRegion(Assets.ship, Assets.ship.getWidth(), Assets.ship.getHeight());
		setSize(Assets.ship.getWidth(), Assets.ship.getHeight());
		bounds = new Rectangle(getX(), getY(), getWidth(), getHeight());
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		Color color = getColor();
		batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
		batch.draw(regionShip, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
	}

	@Override
	public void act(float delta) {
		super.act(delta);
		
		boolean PushOneButton = true;
		if(Gdx.input.isKeyPressed(Keys.RIGHT) || Gdx.input.isKeyPressed(Keys.D) && PushOneButton) {
			if(NotRightCollision(delta)) {
				setPosition(getX() + delta * Globals.SSPEED, getY());
				PushOneButton = false;
			}
		}
		else if(Gdx.input.isKeyPressed(Keys.LEFT) || Gdx.input.isKeyPressed(Keys.A)  && PushOneButton) {
			if(NotLeftCollision(delta)) {
				setPosition(getX() - delta * Globals.SSPEED, getY());
				PushOneButton = false;
			}
		}
		if(Gdx.input.isKeyPressed(Keys.UP) || Gdx.input.isKeyPressed(Keys.W)  && PushOneButton) {
			if(NotUpCollision(delta)) {
				setPosition(getX(), getY() + delta * Globals.SSPEED);
				PushOneButton = false;
			}
		}
		else if(Gdx.input.isKeyPressed(Keys.DOWN) || Gdx.input.isKeyPressed(Keys.S)  && PushOneButton) {
			if(NotDownCollision(delta)) {
				setPosition(getX(), getY() - delta * Globals.SSPEED);
				PushOneButton = false;
			}
		}
		
		setBounds(getX(), getY());
	}
	
	private boolean NotRightCollision(float delta) {
		boolean notCollision = true;
		if(getX() + getWidth() + Globals.SSPEED * delta >= Globals.width) {
			notCollision = false;
			setX(Globals.width - getWidth());
		}
		return notCollision;
	}
	private boolean NotLeftCollision(float delta) {
		boolean notCollision = true;
		if(getX() - Globals.SSPEED * delta <= 0) {
			notCollision = false;
			setX(0);
		}
		return notCollision;
	}
	private boolean NotUpCollision(float delta) {
		boolean notCollision = true;
		if(getY() + getHeight() + Globals.SSPEED * delta >= Globals.height) {
			notCollision = false;
			setY(Globals.height - getHeight());
		}
		return notCollision;
	}
	private boolean NotDownCollision(float delta) {
		boolean notCollision = true;
		if(getY() - Globals.SSPEED * delta <= 0) {
			notCollision = false;
			setY(0);
		}
		return notCollision;
	}
	
	
	public Rectangle getBounds() {
		return bounds;
	}
	public void setBounds(float x, float y) {
		bounds.x = x;
		bounds.y = y;
	}
}
