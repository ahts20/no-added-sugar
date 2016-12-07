package main;

import java.awt.EventQueue;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

public class Main{
	
	//Collect screen sizes and set as global variables.
	private static GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	public static int width = gd.getDisplayMode().getWidth();
	public static int height = gd.getDisplayMode().getHeight();
	
	//Set variables for controlling other classes
	public static GameWindow gw;
	public static Logic logic = new Logic();

	
	public static void main(String[] args){
		System.out.println(width);
		System.out.println(height);
		//Set up a runnable environment.
	   EventQueue.invokeLater(new Runnable() {
            public void run() {
            	//When it runs create a game window (frame/shell/box).
                gw = new GameWindow("Dungeon", width, height);
                //Run the game logic.
                logic.runLogic();
            }
        });
	}
}
