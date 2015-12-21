package com.mygdx.gravitytoy.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.gravitytoy.vc.Toy;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.backgroundFPS = 60 ;
		config.foregroundFPS = 60 ;
		config.resizable = true ;
		config.height = 800 ;
		config.width = 800 ;
		new LwjglApplication(new Toy(), config);
	}
}
