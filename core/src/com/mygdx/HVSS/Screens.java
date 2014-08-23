package com.mygdx.HVSS;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.HVSS.screens.AbstractScreen;
import com.mygdx.HVSS.screens.GameScreen;

public final class Screens {
	public static AbstractScreen GAMESCREEN;
	
	public static void initialize(SpriteBatch batch) {
		GAMESCREEN = new GameScreen(batch);
	}
}
