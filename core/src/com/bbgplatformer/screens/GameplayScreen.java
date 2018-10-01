package com.bbgplatformer.screens;

import java.util.Vector;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.bbgplatformer.BBGplatformer;
import com.bbgplatformer.entities.MarshmallowMonsterEnemy;
import com.bbgplatformer.entities.Player;
import com.bbgplatformer.staticobjects.Background;
import com.bbgplatformer.staticobjects.HpImage;
import com.bbgplatformer.staticobjects.Platform;

public class GameplayScreen extends AbstractScreen {

	private Texture platformTexture;
	private Vector<Platform> platforms;
	private int gravity;
	private Player player;
	private Background bg1;
	private Background bg2;
	private HpImage[] hpImage;
	private int hp;
	private MarshmallowMonsterEnemy monster;
	private Music bgMusic;

	private final int CAMERA_Y = 300;

	public int platformAmount;

	private Preferences platformPrefs;
	private Integer prefsLinePointer;

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
		platformTexture = new Texture("grassDirt.png");
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
		platforms = new Vector<Platform>();
		prefsLinePointer = new Integer(0);

		platformPrefs = Gdx.app.getPreferences(MapGeneratorScreen.PLATFORMS_PREFS_NAME); // load amount of platforms to render
		platformAmount = platformPrefs.getInteger(prefsLinePointer.toString());

		int x = 0; // variable where we will store x of platform we're loading in each iteration
		int y = 0; // variable where we will store y of platform we're loading in each iteration

		for (int i = 0; i < platformAmount; i++) {
			prefsLinePointer++;
			x = platformPrefs.getInteger(prefsLinePointer.toString());
			prefsLinePointer++;
			y = platformPrefs.getInteger(prefsLinePointer.toString());
			platforms.addElement(new Platform(platformTexture, x, y));
			stage.addActor(platforms.get(i));
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
		monster = new MarshmallowMonsterEnemy(600, 182);
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
		if (monster.doesDieFromPlayer(player)) {
			monster.remove();
		} else if (monster.doesColideWithPlayer(player)) {
			System.out.println("colide");
		}
	}

	private void subtractHp() {
		hp--;
		hpImage[hp].remove();
	}

	@Override
	public void dispose() {
		platformTexture.dispose();
	}

}
