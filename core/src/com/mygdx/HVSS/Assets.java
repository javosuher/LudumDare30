package com.mygdx.HVSS;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public final class Assets {
	public final static Texture background = new Texture(Gdx.files.internal("background.png"));
	public final static Texture ship = new Texture(Gdx.files.internal("ship.png"));
	public final static Texture shipS = new Texture(Gdx.files.internal("shipS.png"));
	public final static Texture samuel = new Texture(Gdx.files.internal("samuel.png"));
	public final static Texture portal = new Texture(Gdx.files.internal("portal.png"));
	
	public final static Sound ag = Gdx.audio.newSound(Gdx.files.internal("ag.wav"));
	public final static Sound run = Gdx.audio.newSound(Gdx.files.internal("run .wav"));
	public final static Sound win = Gdx.audio.newSound(Gdx.files.internal("win.wav"));
	public final static Sound lose = Gdx.audio.newSound(Gdx.files.internal("lose.wav"));
	public final static Sound portalS = Gdx.audio.newSound(Gdx.files.internal("portal.wav"));
}
