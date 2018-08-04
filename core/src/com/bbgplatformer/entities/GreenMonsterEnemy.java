package com.bbgplatformer.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class GreenMonsterEnemy extends AbstractEnemy {

	private final static String LEFT_TEXTURE_PATH = "monsterLeft.png";
	private final static String RIGHT_TEXTURE_PATH = "monsterRight.png";

	private final static int WIDTH = 35;
	private final static int HEIGHT = 48;

	public boolean shouldGoRight;


	public GreenMonsterEnemy(int startingX, int startingY, int range) {
		super(startingX, startingY, range);
	}

	protected void init(int startingX, int startingY, int range) {
		this.startingX = startingX;
		this.startingY = startingY;
		this.range = range;

		shouldGoRight = false;

		this.rightDrawable = new TextureRegionDrawable(new TextureRegion(new Texture(RIGHT_TEXTURE_PATH)));
		this.leftDrawable = new TextureRegionDrawable(new TextureRegion(new Texture(LEFT_TEXTURE_PATH)));
		this.setDrawable(leftDrawable);

		this.setOrigin(WIDTH / 2, HEIGHT / 2);
		this.setSize(WIDTH, HEIGHT);

		this.setX(startingX);
		this.setY(startingY);
	}

	public void move() {

		if (this.getX() == startingX - range / 2) {
			shouldGoRight = true;
		} else if (this.getX() == startingX + range / 2) {
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
