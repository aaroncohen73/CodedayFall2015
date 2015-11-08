package com.code.day.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.code.day.Game;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 512;
		config.height = 448;
		config.resizable = false;
		config.title = "Please don't sue us Nintendo";
		new LwjglApplication(new Game(), config);
	}
}
