package com.mygdx.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.mygdx.game.SkelliDungeon;

// This class handles the launching of the application. It sets the application to run
// at a frame rate of 60 frames per second so that the game doesn't look choppy
// while at the same time is not overly demanding on the computer.
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		// The Vsync feature is used to ensure that screen tearing does not occur.
		config.useVsync(true);
		// The window is set to have a width of 800 pixels and a height of 600 pixels to ensure
		// that the game window is able to properly fit on 16:9 displays as well as
		// displays of other aspect ratios
		config.setWindowedMode(800,600);
		new Lwjgl3Application((ApplicationListener) new SkelliDungeon(), config);
	}
}
