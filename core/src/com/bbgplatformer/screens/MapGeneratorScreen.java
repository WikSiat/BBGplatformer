package com.bbgplatformer.screens;

import java.util.Vector;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.bbgplatformer.BBGplatformer;
import com.bbgplatformer.staticobjects.Platform;

public class MapGeneratorScreen extends AbstractScreen {

	public static final String PREFS_NAME = "BBGplatformer.prefs";

	private int cameraX, cameraY;
	private int buttonX, buttonY;
	private int button2X, button2Y;
	private int platformX, platformY;

	private int platformAmount;

	private Button pButton, pButton2;

	private Vector<Platform> platforms;

	private Texture platformTexture;

	private Preferences prefs;

	private Integer index;

	public MapGeneratorScreen(BBGplatformer game) {
		super(game);
	}

	@Override
	protected void init() {
		platformAmount = new Integer(0);
		index = new Integer(0);
		prefsInit();
		buttonInit();
		platformInit();
		cameraInit();
	}

	private void buttonInit() {
		pButton = new Button(new ButtonStyle());
		buttonX = 700;
		buttonY = 430;

		pButton.setX(buttonX);
		pButton.setY(buttonY);

		pButton.setWidth(100);
		pButton.setHeight(50);

		pButton.setDebug(true);
		pButton.addListener(new ClickListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				platforms.add(new Platform(platformTexture, platformX, platformY));
				stage.addActor(platforms.get(platformAmount));
				platformAmount++;
				return super.touchDown(event, x, y, pointer, button);
			}
		});

		stage.addActor(pButton);

		// ----------------------------------------------------------------------------------------------//

		pButton2 = new Button(new ButtonStyle());
		button2X = 700;
		button2Y = 380;

		pButton2.setX(button2X);
		pButton2.setY(button2Y);

		pButton2.setWidth(100);
		pButton2.setHeight(50);

		pButton2.setDebug(true);
		pButton2.addListener(new ClickListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				prefs.clear();
				prefs.putInteger(index.toString(), platformAmount);
				prefs.flush();
				for (int i = 0; i < platforms.size(); i++) {
					index++;
					prefs.putInteger(index.toString(), (int) platforms.elementAt(i).x);
					prefs.flush();
					index++;
					prefs.putInteger(index.toString(), (int) platforms.elementAt(i).y);
					prefs.flush();
				}
				return super.touchDown(event, x, y, pointer, button);
			}
		});

		stage.addActor(pButton2);
	}

	private void platformInit() {
		platforms = new Vector<Platform>();
		platformX = 0;
		platformY = 150;
		platformTexture = new Texture("grassDirt.png");
	}

	private void cameraInit() {
		cameraY = 300;
		cameraX = 400;
	}

	private void prefsInit() {
		prefs = Gdx.app.getPreferences(PREFS_NAME);
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
		handleInput();
		camera.position.set(cameraX, cameraY, 0);
		stage.act();
	}

	private void handleInput() {
		// moving camera//
		if (Gdx.input.isKeyPressed(Keys.A)) {
			cameraX -= 5;
			pButton.moveBy(-5, 0);
			pButton2.moveBy(-5, 0);
		}
		if (Gdx.input.isKeyPressed(Keys.D)) {
			cameraX += 5;
			pButton.moveBy(5, 0);
			pButton2.moveBy(5, 0);
		}
		if (Gdx.input.isKeyPressed(Keys.S)) {
			cameraY -= 5;
			pButton.moveBy(0, -5);
			pButton2.moveBy(0, -5);
		}
		if (Gdx.input.isKeyPressed(Keys.W)) {
			cameraY += 5;
			pButton.moveBy(0, 5);
			pButton2.moveBy(0, 5);
		}
		// moving camera//
		// moving platforms//
		if (Gdx.input.isKeyPressed(Keys.UP)) {
			platforms.get(platformAmount - 1).y += 2;
		}
		if (Gdx.input.isKeyPressed(Keys.DOWN)) {
			platforms.get(platformAmount - 1).y -= 2;
		}
		if (Gdx.input.isKeyPressed(Keys.LEFT)) {
			platforms.get(platformAmount - 1).x -= 2;
		}
		if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
			platforms.get(platformAmount - 1).x += 2;
		}
		// moving platforms//
	}

	@Override
	public void dispose() {
		super.dispose();
		platformTexture.dispose();
	}
}
