package com.bbgplatformer.staticobjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class HpImage extends Image {
	
	private static final int WIDTH = 30;
	private static final int HEIGHT = 30;

	public static final int STARTING_X = -370;
	public static final int STARTING_Y = 450;
	
	private static final String IMAGE = "hearth.png";
	
	public HpImage() {
		super(new Texture(IMAGE));
		this.setOrigin(WIDTH / 2, HEIGHT / 2);
		this.setSize(WIDTH, HEIGHT);
		this.setPosition(STARTING_X, STARTING_Y);
	}

}
