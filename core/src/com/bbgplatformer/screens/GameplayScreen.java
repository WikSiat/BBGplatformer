package com.bbgplatformer.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.bbgplatformer.BBGplatformer;
import com.bbgplatformer.entities.MarshmallowMonsterEnemy;
import com.bbgplatformer.entities.Player;
import com.bbgplatformer.staticobjects.Background;
import com.bbgplatformer.staticobjects.HpImage;
import com.bbgplatformer.staticobjects.Platform;

public class GameplayScreen extends AbstractScreen {

	private Texture platformImage;
	private Platform[] platforms;
	private int gravity;
	private Player player;
	private Background bg1;
	private Background bg2;
	private HpImage[] hpImage;
	private int hp;
	private MarshmallowMonsterEnemy monster;
	private Music bgMusic;

	private final int CAMERA_Y = 300;

	public final int PLATFORMS_AMOUNT = 720;

	public GameplayScreen(BBGplatformer game) {
		super(game);
	}

	protected void init() {
		assetsInit();
		bgInit();
		playerInit();
		platformInit();
		hpInit();
		musicInit();
		monstersInit();
		gravity = -20;
	}

	private void assetsInit() {
		platformImage = new Texture("grassDirt.png");
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
		int x = 0;
		int y = 150;
		
		// am[x] - am[x-1] = amount of single platforms in one bigger with index x
		int[] am = {36,50,100,101,103,150,152,220,250,280,360,400,420,440,510,550,590,620,720};

		platforms = new Platform[PLATFORMS_AMOUNT];

		for (int i = 0; i < am[0]; i++) {
			platforms[i] = new Platform(platformImage, x, y);

			platforms[i].setX(x);
			platforms[i].setY(y);
			stage.addActor(platforms[i]);
			x += platformImage.getWidth();
		}
		x += 96;
		y += 64;
		for (int i = am[0]; i < am[1]; i++) {
			platforms[i] = new Platform(platformImage, x, y);

			platforms[i].setX(x);
			platforms[i].setY(y);
			stage.addActor(platforms[i]);
			x += platformImage.getWidth();
		}
		x += 130;
		y = 150;
		for (int i = am[1]; i < am[2]; i++) {
			platforms[i] = new Platform(platformImage, x, y);

			platforms[i].setX(x);
			platforms[i].setY(y);
			stage.addActor(platforms[i]);
			x += platformImage.getWidth();
		}
		x += 96;
		y += 55;
		for (int i = am[2]; i < am[3]; i++) {
			platforms[i] = new Platform(platformImage, x, y);

			platforms[i].setX(x);
			platforms[i].setY(y);
			stage.addActor(platforms[i]);
			x += platformImage.getWidth();
		}
		x += 150;
		y += 64;
		for (int i = am[3]; i < am[4]; i++) {
			platforms[i] = new Platform(platformImage, x, y);

			platforms[i].setX(x);
			platforms[i].setY(y);
			stage.addActor(platforms[i]);
			x += platformImage.getWidth();
		}
		x += 100;
		y += 40;
		for (int i = am[4]; i < am[5]; i++) {
			platforms[i] = new Platform(platformImage, x, y);

			platforms[i].setX(x);
			platforms[i].setY(y);
			stage.addActor(platforms[i]);
			x += platformImage.getWidth();
		}
		x += 64;
		y -= 40;
		for (int i = am[5]; i < am[6]; i++) {
			platforms[i] = new Platform(platformImage, x, y);

			platforms[i].setX(x);
			platforms[i].setY(y);
			stage.addActor(platforms[i]);
			x += platformImage.getWidth();
		}
		x += 110;
		y = 150;
		for (int i = am[6]; i < am[7]; i++) {
			platforms[i] = new Platform(platformImage, x, y);

			platforms[i].setX(x);
			platforms[i].setY(y);
			stage.addActor(platforms[i]);
			x += platformImage.getWidth();
		}
		x += 90;
		y += 60;
		for (int i = am[7]; i < am[8]; i++) {
			platforms[i] = new Platform(platformImage, x, y);

			platforms[i].setX(x);
			platforms[i].setY(y);
			stage.addActor(platforms[i]);
			x += platformImage.getWidth();
		}
		x += 90;
		y += 60;
		for (int i = am[8]; i < am[9]; i++) {
			platforms[i] = new Platform(platformImage, x, y);

			platforms[i].setX(x);
			platforms[i].setY(y);
			stage.addActor(platforms[i]);
			x += platformImage.getWidth();
		}
		x += 240;
		y = 150;
		for (int i = am[9]; i < am[10]; i++) {
			platforms[i] = new Platform(platformImage, x, y);

			platforms[i].setX(x);
			platforms[i].setY(y);
			stage.addActor(platforms[i]);
			x += platformImage.getWidth();
		}
		x += 90;
		y += 70;
		for (int i = am[10]; i < am[11]; i++) {
			platforms[i] = new Platform(platformImage, x, y);

			platforms[i].setX(x);
			platforms[i].setY(y);
			stage.addActor(platforms[i]);
			x += platformImage.getWidth();
		}
		x += 90;
		y += 64;
		for (int i = am[11]; i < am[12]; i++) {
			platforms[i] = new Platform(platformImage, x, y);

			platforms[i].setX(x);
			platforms[i].setY(y);
			stage.addActor(platforms[i]);
			x += platformImage.getWidth();
		}
		x += 90;
		y += 55;
		for (int i = am[12]; i < am[13]; i++) {
			platforms[i] = new Platform(platformImage, x, y);

			platforms[i].setX(x);
			platforms[i].setY(y);
			stage.addActor(platforms[i]);
			x += platformImage.getWidth();
		}
		x += 90;
		y += 50;
		for (int i = am[13]; i < am[14]; i++) {
			platforms[i] = new Platform(platformImage, x, y);

			platforms[i].setX(x);
			platforms[i].setY(y);
			stage.addActor(platforms[i]);
			x += platformImage.getWidth();
		}
		x += 180;
		y = 180;
		for (int i = am[14]; i < am[15]; i++) {
			platforms[i] = new Platform(platformImage, x, y);

			platforms[i].setX(x);
			platforms[i].setY(y);
			stage.addActor(platforms[i]);
			x += platformImage.getWidth();
		}
		x += 96;
		y += 60;
		for (int i = am[15]; i < am[16]; i++) {
			platforms[i] = new Platform(platformImage, x, y);

			platforms[i].setX(x);
			platforms[i].setY(y);
			stage.addActor(platforms[i]);
			x += platformImage.getWidth();
		}
		x += 96;
		y += 60;
		for (int i = am[16]; i < am[17]; i++) {
			platforms[i] = new Platform(platformImage, x, y);

			platforms[i].setX(x);
			platforms[i].setY(y);
			stage.addActor(platforms[i]);
			x += platformImage.getWidth();
		}
		x += 200;
		y  = 150;
		for (int i = am[17]; i < am[18]; i++) {
			platforms[i] = new Platform(platformImage, x, y);

			platforms[i].setX(x);
			platforms[i].setY(y);
			stage.addActor(platforms[i]);
			x += platformImage.getWidth();
		}
	}

	private void hpInit() {
		hp = 3;

		hpImage = new HpImage[3];

		for (int i = 0; i < hp; i++) {
			hpImage[i] = new HpImage();
			hpImage[i].setX(HpImage.STARTING_X + i * HpImage.WIDTH);
			hpImage[i].setX(HpImage.STARTING_Y);
			stage.addActor(hpImage[i]);
		}
	}

	private void musicInit() {
		bgMusic = Gdx.audio.newMusic(Gdx.files.internal("bgMusic.ogg"));
		bgMusic.setLooping(true);
		bgMusic.play();
	}

	private void monstersInit() {
		monster = new MarshmallowMonsterEnemy(600, 182, 400);
		stage.addActor(monster);
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
		handleInputUpdate();
		gravityUpdate();
		cameraUpdate();
		hpUpdate();
		monsterUpdate();
		stage.act();

	}

	private void handleInputUpdate() {
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

	private void cameraUpdate() {
		camera.position.set((player.getX() + player.getWidth() / 2), CAMERA_Y, 0);
	}

	private void hpUpdate() {
		for (int i = 0; i < hp; i++) {
			hpImage[i].setPosition(player.getX() + HpImage.STARTING_X + i * HpImage.WIDTH, Player.STARTING_Y + 365);
		}
	}

	private void monsterUpdate() {
		monster.move();
	}

	private void subtractHp() {
		hp--;
		hpImage[hp].remove();
	}

	@Override
	public void dispose() {
		platformImage.dispose();
	}

}
