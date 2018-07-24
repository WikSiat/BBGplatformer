package com.bbgoplatformer.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.bbgplatformer.BBGplatformer;

public class SplashScreen extends AbstractScreen {

	private Texture splashTexture;

	public SplashScreen(final BBGplatformer game) {
		super(game);

		Timer.schedule(new Task() {
			@Override
			public void run() {
				game.setScreen(new MainMenuScreen(game));
			}
		}, 1);
	}

	@Override
	public void render(float delta) {
		super.render(delta);
		batch.begin();
		batch.draw(splashTexture, 0, 0);
		batch.end();
	}

	@Override
	protected void init() {
		splashTexture = new Texture("logo.jpg");
	}
}
