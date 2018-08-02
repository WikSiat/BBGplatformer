package com.bbgoplatformer.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.bbgplatformer.BBGplatformer;
import com.bbgplatformer.entities.Player;
import com.bbgplatformer.staticobjects.Background;
import com.bbgplatformer.staticobjects.HpImage;
import com.bbgplatformer.staticobjects.HpLabel;
import com.bbgplatformer.staticobjects.Platform;

public class GameplayScreen extends AbstractScreen {

	private Texture platformImage;
	private Platform[] platforms;
	private int numberOfPlatforms;
	private int gravity;
	private Player player;
	private Background bg1;
	private Background bg2;
	private HpImage hpImage;
	private HpLabel hpLabel;
	private int hp;

	public GameplayScreen(BBGplatformer game) {
		super(game);
	}

	protected void init() {
		assetsInit();
		bgInit();
		playerInit();
		platformInit();
		hpInit();
		gravity = -20;
	}

	private void hpInit() {
		hp = 3;

		hpImage = new HpImage();
		stage.addActor(hpImage);

		hpLabel = new HpLabel();
		stage.addActor(hpLabel);
	}

	private void subtractHp() {
		hp--;
	}

	private void assetsInit() {
		platformImage = new Texture("t.jpg");
	}

	private void bgInit() {
		bg1 = new Background();
		bg2 = new Background();
		Background.setStartingBackgroundPosition(bg1, bg2);
		stage.addActor(bg1);
		stage.addActor(bg2);
	}

	private void playerInit() {
		player = new Player();
		stage.addActor(player);
	}

	private void platformInit() {
		numberOfPlatforms = 20;
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

		batch.begin();

		batch.end();
	}

	private void update() {
		Background.bgUpdate(player, bg1, bg2);
		handleInput();
		gravityUpdate();
		hpUpdate();
		stage.act();

	}

	private void hpUpdate() {
		hpImage.setPosition(player.getX() - 340, player.getY() + 235);
		hpLabel.setPosition(player.getX() - 370, player.getY() + 235);
		hpLabel.setText(Integer.toString(hp));
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
			subtractHp();
			if (hp == 0) {
				Gdx.app.exit();
			}
			player.setPosition(Player.STARTING_X, Player.STARTING_Y);
			Background.setStartingBackgroundPosition(bg1, bg2);
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
		platformImage.dispose();
	}

}
