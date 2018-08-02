package com.bbgplatformer.staticobjects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class HpLabel extends Label {

	private static final LabelStyle LABEL_STYLE = new LabelStyle(new BitmapFont(), Color.BLACK);

	public static final int WIDTH = 30;
	public static final int HEIGHT = 30;

	public static final int STARTING_X = -370;
	public static final int STARTING_Y = 450;

	public HpLabel() {
		super("", LABEL_STYLE);
		this.setOrigin(WIDTH / 2, HEIGHT / 2);
		this.setSize(WIDTH, HEIGHT);
		this.setPosition(STARTING_X, STARTING_Y);
	}

}
