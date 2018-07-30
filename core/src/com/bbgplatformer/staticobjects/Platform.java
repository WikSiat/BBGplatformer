package com.bbgplatformer.staticobjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Platform extends Image {

	private float x;
	private float y;
	
	public Platform(Texture texture, float x, float y) {
		super(texture);
		this.setSize(texture.getWidth(), texture.getWidth());
		this.x = x;
		this.y = y;
	}

	public Rectangle getBounds() {
		return new Rectangle((int) this.getX(), (int) this.getY(), (int) this.getWidth(), (int) this.getHeight());
	}
	/*
	 * 
	 * getters and setters
	 */
	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

}
