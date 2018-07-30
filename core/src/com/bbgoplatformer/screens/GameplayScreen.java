package com.bbgoplatformer.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.bbgplatformer.BBGplatformer;
import com.bbgplatformer.entities.Player;
import com.bbgplatformer.staticobjects.Platform;

public class GameplayScreen extends AbstractScreen {

	private Texture platformImage;
	private Platform[] platforms;
	private int numberOfPlatforms;
	private int gravity;
	private Player player;
	private Image[] bgImages;
	private int bgIndex1;

	private final static int BG1_STARTING_X = -370;
	private final static int BG2_STARTING_X = 1230;
	private final static int BG_STARTING_Y = -130;

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
		bgImages = new Image[2];
		bgImages[0] = new Image(new Texture("tlo.jpg"));
		bgImages[1] = new Image(new Texture("tlo.jpg"));
		setStartingBackgroundPossition();
		stage.addActor(bgImages[0]);
		stage.addActor(bgImages[1]);
	}

	private void setStartingBackgroundPossition() {
		bgIndex1 = 1;
		bgImages[0].setX(BG1_STARTING_X);
		bgImages[0].setY(BG_STARTING_Y);
		bgImages[1].setX(BG2_STARTING_X);
		bgImages[1].setY(BG_STARTING_Y);
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
		bgUpdate();
		handleInput();
		gravityUpdate();
		stage.act();

	}

	// renders looping background that can scroll infinitely and will adjust to player's moves
	private void bgUpdate() {
		if (player.getX() == (BG2_STARTING_X + bgIndex1 * 1600 - 430) && player.isTurnedRight) {
			switch (bgIndex1 % 2) {
			case 0:
				bgImages[1].setX(BG2_STARTING_X + bgIndex1 * 1600);
				break;
			case 1:
				bgImages[0].setX(BG2_STARTING_X + bgIndex1 * 1600);
				break;
			default:
				break;
			}
			bgIndex1++;
		} else if (player.getX() == (BG2_STARTING_X + (bgIndex1 - 1) * 1600 - 430) && !player.isTurnedRight) {
			bgIndex1--;
			if (bgIndex1 == 0) {
				setStartingBackgroundPossition();
			} else {
				switch (bgIndex1 % 2) {
				case 0:
					bgImages[1].setX(BG1_STARTING_X + (bgIndex1 - 1) * 1600);
					break;
				case 1:
					bgImages[0].setX(BG1_STARTING_X + (bgIndex1 - 1) * 1600);
					break;
				default:
					break;
				}
			}
		}
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
			player.setX(Player.STARTING_X);
			player.setY(Player.STARTING_Y);
			setStartingBackgroundPossition();
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
