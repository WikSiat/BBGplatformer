package com.bbgoplatformer.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.bbgplatformer.BBGplatformer;
import com.bbgplatformer.entities.Player;
import com.bbgplatformer.staticobjects.Background;
import com.bbgplatformer.staticobjects.Platform;

public class GameplayScreen extends AbstractScreen {

	private Texture platformImage;
	private Platform[] platforms;
	private int numberOfPlatforms;
	private int gravity;
	private Player player;
	private Background bg1;
	private Background bg2;

	public GameplayScreen(BBGplatformer game) {
		super(game);
	} 

	protected void init() {
		assetsInit();
		bgInit();
		playerInit();
		platformInit();
		gravity = -20;
	}
	private void assetsInit() {
		platformImage = new Texture("t.jpg");
	}

	private void bgInit() {
		bg1 = new Background();
		bg2 = new Background();
		Background.setStartingBackgroundPosition(bg1,bg2);
		stage.addActor(bg1);
		stage.addActor(bg2);
	}

	private void playerInit() {
		player = new Player();
		stage.addActor(player);
	}

	private void platformInit() {
		numberOfPlatforms = 200;
		int x = 0;
		int y = 150;

		platforms = new Platform[numberOfPlatforms];

		for (int i = 0; i < numberOfPlatforms; i++) {
			platforms[i] = new Platform(platformImage, x, y);

			platforms[i].setX(x);
			platforms[i].setY(y);
			stage.addActor(platforms[i]);
			x += platformImage.getWidth();
		}
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
		Background.bgUpdate(player, bg1, bg2);
		handleInput();
		gravityUpdate();
		stage.act();

	}

	private void handleInput() {
		if (Gdx.input.isKeyPressed(Keys.A) && player.getX() > 0) {
			player.moveLeft();
		}
		if (Gdx.input.isKeyPressed(Keys.D)) {
			player.moveRight();
		}
		if (Gdx.input.justTouched() || Gdx.input.isKeyJustPressed(Keys.SPACE)) {
			player.jump();
		}
	}

	private void gravityUpdate() {
		float grav = player.jumpVelocity * Gdx.graphics.getDeltaTime();
		player.setY(player.getY() + grav);

		camera.position.set((player.getX() + player.getWidth() / 2), (player.getY() + player.getWidth() / 2), 0);
		if (player.getY() > 0) {
			player.jumpVelocity += gravity;
		} else {
			player.setPosition(Player.STARTING_X, Player.STARTING_Y);
			Background.setStartingBackgroundPosition(bg1,bg2);
		}
		for (Platform p : platforms) {
			if (player.isPlayerOnPlatform(p)) {
				player.canJump = true;
				player.jumpVelocity = 0;
				player.setY(p.getY() + p.getHeight());
			}
		}
	}

	@Override
	public void dispose() {
		batch.dispose();
		platformImage.dispose();
	}

}
