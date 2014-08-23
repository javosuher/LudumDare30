package com.mygdx.HVSS.actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.HVSS.Assets;

public class ShipActor extends Actor {
	private TextureRegion regionShip;
	
	public ShipActor() {
		regionShip = new TextureRegion(Assets.ship, Assets.ship.getWidth(), Assets.ship.getHeight());
		setSize(Assets.ship.getWidth(), Assets.ship.getHeight());
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
	}
}
