package com.bbgplatformer.entities;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public abstract class AbstractEnnemy extends Image {
	
	protected Drawable leftDrawable, rightDrawable;
	
	public AbstractEnnemy() {
		super();
		init();
	}
	
	protected abstract void init();
	
	protected void moveLeft() {
		this.setDrawable(leftDrawable);
		Action moveLeftAction = Actions.moveBy(-2, 0);
		this.addAction(moveLeftAction);
	}

	protected void moveRight() {
		this.setDrawable(rightDrawable);
		Action moveRightAction = Actions.moveBy(2, 0);
		this.addAction(moveRightAction);
	}
	
	protected Rectangle getBounds() {
		return new Rectangle((int) this.getX(), (int) this.getY(), (int) this.getWidth(), (int) this.getHeight());
	}
	
	public boolean doesColideWithPlayer(Player player) {
		return this.getBounds().overlaps(player.getBounds());
	}
}
