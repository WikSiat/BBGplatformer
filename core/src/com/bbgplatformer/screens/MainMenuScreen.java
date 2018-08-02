package com.bbgplatformer.screens;

import com.badlogic.gdx.Gdx;
import com.bbgplatformer.BBGplatformer;
import com.bbgplatformer.ui.ExitButton;
import com.bbgplatformer.ui.IClickCallback;
import com.bbgplatformer.ui.StartButton;

public class MainMenuScreen extends AbstractScreen {

	private StartButton startButton;
	private ExitButton exitButton;

	public MainMenuScreen(final BBGplatformer game) {
		super(game);
	}

	@Override
	protected void init() {
		startButtonInit();
		exitButtonInit();
	}
	
	@Override
	public void render(float delta) {
		super.render(delta);
		stage.act();
		stage.draw();
	}

	private void startButtonInit() {
		startButton = new StartButton(new IClickCallback() {
			public void onClick() {
				game.setScreen(new GameplayScreen(game));
			}
		});

		stage.addActor(startButton);
	}
	
	private void exitButtonInit() {
		exitButton = new ExitButton(new IClickCallback() {
			public void onClick() {
				Gdx.app.exit();
			}
		});

		stage.addActor(exitButton);
	}

}
