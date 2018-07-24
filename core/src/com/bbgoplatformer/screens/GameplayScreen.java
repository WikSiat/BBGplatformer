package com.bbgoplatformer.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.bbgplatformer.BBGplatformer;

public class GameplayScreen extends AbstractScreen{
	
	private Image[] bgImages;
	private int bgIndex1, bgIndex2;
	
	private final static int BG1_STARTING_X = -370;
	private final static int BG2_STARTING_X = 1230;
	private final static int BG_STARTING_Y = -130;
	
	public GameplayScreen(BBGplatformer game) {
		super(game);
	}

	@Override
	protected void init() {
		bgInit();
	}
	
	private void bgInit() {
		bgImages = new Image[2];
		bgImages[0] = new Image(new Texture("tlo.jpg"));
		bgImages[1] = new Image(new Texture("tlo.jpg"));
		setStartingBackgroundPossition();
		stage.addActor(bgImages[0]);
		stage.addActor(bgImages[1]);
	}

	private void setStartingBackgroundPossition() {
		bgIndex1 = 0;
		bgIndex2 = 1;
		bgImages[0].setX(BG1_STARTING_X);
		bgImages[0].setY(BG_STARTING_Y);
		bgImages[1].setX(BG2_STARTING_X);
		bgImages[1].setY(BG_STARTING_Y);
	}
	
	@Override
	public void render(float delta) {
		super.render(delta);
		update();
		batch.begin();
		stage.draw();
		batch.end();
	}

	private void update() {
		stage.act();
	}

}
