package main;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;



import GameStates.GameLoop;

public class Main{
	
	private static GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	public static int width = gd.getDisplayMode().getWidth();
	public static int height = gd.getDisplayMode().getHeight();

	public static GameWindow gw;
	
	public static void main(String[] args) {
	
		gw = new GameWindow("Dungeon", width, height);
		
	}
	
}
