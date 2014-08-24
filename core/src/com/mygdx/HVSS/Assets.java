package com.mygdx.HVSS;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public final class Assets {
	public final static Texture background = new Texture(Gdx.files.internal("Images/background.png"));
	public final static Texture ship = new Texture(Gdx.files.internal("Images/ship.png"));
	public final static Texture shipS = new Texture(Gdx.files.internal("Images/shipS.png"));
	public final static Texture samuel = new Texture(Gdx.files.internal("Images/samuel.png"));
	public final static Texture portal = new Texture(Gdx.files.internal("Images/portal.png"));
	
	public final static Sound ag = Gdx.audio.newSound(Gdx.files.internal("Sounds/ag.wav"));
	public final static Sound run = Gdx.audio.newSound(Gdx.files.internal("Sounds/run.wav"));
	public final static Sound win = Gdx.audio.newSound(Gdx.files.internal("Sounds/win.wav"));
	public final static Sound lose = Gdx.audio.newSound(Gdx.files.internal("Sounds/lose.wav"));
	public final static Sound portalS = Gdx.audio.newSound(Gdx.files.internal("Sounds/portal.wav"));
}
