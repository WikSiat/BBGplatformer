package com.bbgplatformer;

import com.badlogic.gdx.Game;
import com.bbgplatformer.screens.SplashScreen;

public class BBGplatformer extends Game {

	public final static String GAME_NAME = "platformGame";

	public final static int WIDTH = 800, HEIGHT = 480;

	private boolean isPaused;

	@Override
	public void create() {
		this.setScreen(new SplashScreen(this));
	}

	/*
	 * getters and setters
	 */

	public boolean isPaused() {
		return isPaused;
	}

	public void setPaused(boolean isPaused) {
		this.isPaused = isPaused;
	}

}
