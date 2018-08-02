package com.bbgplatformer.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class GreenMonsterEnemy extends AbstractEnemy {

	private final static String LEFT_TEXTURE_PATH = "monsterLeft.png";
	private final static String RIGHT_TEXTURE_PATH = "monsterRight.png";

	private final static int WIDTH = 35;
	private final static int HEIGHT = 48;

	private final static int STARTING_Y = 210;
	private final static int STARTING_X = 800;

	public boolean shouldGoRight;

	private final static int RANGE = 400;

	public GreenMonsterEnemy() {
		super();
	}

	@Override
	protected void init() {
		shouldGoRight = false;

		this.rightDrawable = new TextureRegionDrawable(new TextureRegion(new Texture(RIGHT_TEXTURE_PATH)));
		this.leftDrawable = new TextureRegionDrawable(new TextureRegion(new Texture(LEFT_TEXTURE_PATH)));
		this.setDrawable(leftDrawable);

		this.setOrigin(WIDTH / 2, HEIGHT / 2);
		this.setSize(WIDTH, HEIGHT);

		this.setX(STARTING_X);
		this.setY(STARTING_Y);
	}

	public void move() {

		if (this.getX() == STARTING_X - RANGE / 2) {
			shouldGoRight = true;
		} else if (this.getX() == STARTING_X + RANGE / 2) {
			shouldGoRight = false;
		}
		// moves monster depending on previous if statements
		if (shouldGoRight) {
			this.moveRight();
		} else if (!shouldGoRight) {
			this.moveLeft();
		}
	}

}
