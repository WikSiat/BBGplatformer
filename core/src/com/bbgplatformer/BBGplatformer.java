package com.bbgplatformer;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

public class BBGplatformer extends ApplicationAdapter {

	public final static String GAME_NAME = "platformGame";

	public final static int WIDTH = 800, HEIGHT = 480;

	private boolean isPaused;

	@Override
	public void create() {
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}

	@Override
	public void dispose() {
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
