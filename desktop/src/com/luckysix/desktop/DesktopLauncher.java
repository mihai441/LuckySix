package com.luckysix.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.luckysix.luckysix;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title="Lucky Six";
        config.height=luckysix.HEIGHT;
        config.width=luckysix.WIDTH;
		new LwjglApplication(new luckysix(), config);
	}
}
