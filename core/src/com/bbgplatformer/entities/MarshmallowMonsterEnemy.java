package com.bbgplatformer.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class MarshmallowMonsterEnemy extends AbstractEnemy {

	private final static String LEFT_TEXTURE_PATH = "marshmallowMonsterLeft.png";
	private final static String RIGHT_TEXTURE_PATH = "marshmallowMonsterRight.png";

	private final static int WIDTH = 40;
	private final static int HEIGHT = 43;

	public boolean shouldGoRight;

	public MarshmallowMonsterEnemy(int startingX, int startingY, int range) {
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

	public boolean doesColideWithPlayer(Player player) {
		if (this.getBounds().overlaps(player.getBounds())) {
			return true;
		} else {
			return false;
		}
	}

	public boolean doesDieFromPlayer(Player player) {
		if (doesColideWithPlayer(player) && (this.getY() + this.getHeight()) / 1.1 < player.getY()) {
			return true;
		} else {
			return false;
		}
	}

}
