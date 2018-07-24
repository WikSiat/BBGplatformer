package com.bbgoplatformer.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.bbgplatformer.BBGplatformer;

public abstract class AbstractScreen implements Screen {

	protected BBGplatformer game;

	protected Stage stage;
	protected OrthographicCamera camera;

	protected SpriteBatch batch;

	public AbstractScreen(BBGplatformer game) {
		this.game = game;
		createCamera();
		stage = new Stage(new StretchViewport(BBGplatformer.WIDTH, BBGplatformer.HEIGHT,camera));
		batch = new SpriteBatch();
		Gdx.input.setInputProcessor(stage);
		init();
	}

	protected abstract void init();

	private void createCamera() {
		camera = new OrthographicCamera();
		camera.setToOrtho(false, BBGplatformer.WIDTH, BBGplatformer.HEIGHT);
		camera.update();
	}

	@Override
	public void render(float delta) {
		clearScreen();
		camera.update();
		batch.setProjectionMatrix(camera.combined);
	}

	private void clearScreen() {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}

	@Override
	public void show() {

	}

	@Override
	public void resume() {
		game.setPaused(false);

	}

	@Override
	public void pause() {
		game.setPaused(true);
	}

	@Override
	public void hide() {

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		game.dispose();
		batch.dispose();
		stage.dispose();
	}
}
