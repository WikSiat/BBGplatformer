package com.bbgplatformer.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.bbgplatformer.BBGplatformer;

public class DesktopLauncher {
	public static void main(String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.resizable = false;
		config.title = BBGplatformer.GAME_NAME;
		config.width = BBGplatformer.WIDTH;
		config.height = BBGplatformer.HEIGHT;
		new LwjglApplication(new BBGplatformer(), config);
	}
}
