package com.bbgplatformer.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.bbgplatformer.staticobjects.Platform;

public class Player extends Image {

	private static final int WIDTH = 60;
	private static final int HEIGHT = 80;

	public static final int STARTING_X = 0;
	public static final int STARTING_Y = 150;

	public float jumpVelocity;
	public boolean canJump;

	private Texture leftTexture, rightTexture;
	private Drawable leftDrawable, rightDrawable;
	
	public boolean isTurnedRight;


	public Player() {
		super(new Texture("p1.jpg"));
		init();
		this.setOrigin(WIDTH / 2, HEIGHT / 2);
		this.setSize(WIDTH, HEIGHT);
		this.setPosition(STARTING_X, STARTING_Y);

	}

	private void init() {
		isTurnedRight = true;
		leftTexture = new Texture("p2.jpg");
		rightTexture = new Texture("p1.jpg");
		rightDrawable = new TextureRegionDrawable(new TextureRegion(rightTexture));
		leftDrawable = new TextureRegionDrawable(new TextureRegion(leftTexture));

	}

	public void moveLeft() {
		isTurnedRight = false;
		this.setDrawable(leftDrawable);
		Action moveLeftAction = Actions.moveBy(-5, 0);
		this.addAction(moveLeftAction);
	}

	public void moveRight() {
		isTurnedRight = true;
		this.setDrawable(rightDrawable);
		Action moveRightAction = Actions.moveBy(5, 0);
		this.addAction(moveRightAction);
	}


	public void jump() {
		if (canJump && jumpVelocity >= -100) {
			jumpVelocity += 400;
			canJump = false;
		}
	}

	public Rectangle getBounds() {
		return new Rectangle((int) this.getX(), (int) this.getY(), (int) this.getWidth(), (int) this.getHeight());
	}

	public boolean isPlayerOnPlatform(Platform p) {
		return this.jumpVelocity <= 0 && this.getBounds().overlaps(p.getBounds()) && !(this.getY() < p.getY());

	}
	

}