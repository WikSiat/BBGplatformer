package com.bbgplatformer.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class StartButton extends Button {
	
	private Sound clickSound;
	
	private static final int BUTTON_WIDTH = 239;
	private static final int BUTTON_HEIGHT = 87;
	
	private static final int BUTTON_X = 275;
	private static final int BUTTON_Y = 103;
	
	public StartButton(IClickCallback callback) {
		super(new ButtonStyle());
		init(callback);
	}

	private void init(final IClickCallback callback) {
		clickSound = Gdx.audio.newSound(Gdx.files.internal("startSound.ogg"));
		
		this.setX(BUTTON_X);
		this.setY(BUTTON_Y);
		
		this.setWidth(BUTTON_WIDTH);
		this.setHeight(BUTTON_HEIGHT);
		
		this.setDebug(true);
		
		this.addListener(new ClickListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				clickSound.play();
				callback.onClick();
				return super.touchDown(event, x, y, pointer, button);
			}
		});
	}
}
