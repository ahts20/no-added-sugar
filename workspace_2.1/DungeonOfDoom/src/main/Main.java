package main;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.io.File;
/**
 * Main class is the entry point into the program
 * Instantiates GameWindow which extends the JFrame to be used across the program
 * GameWindow also adds GameLoop into the JFrame which is the Heart of the program
 * Uses GraphicsEnvironment to calculate the size of the screen to allow platform specific dimensions
 * 
 * @author anonymous
 * @version 1.0
 * @release 14/12/2016
 * @See Main.java
 */
public class Main {
	//Declares and initialises the Graphics Environment to be used across different platforms
	private static GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	public static int width = gd.getDisplayMode().getWidth();
	public static int height = gd.getDisplayMode().getHeight();
	//Declares GameWindow to be initialised (JFrame)
	public static GameWindow gw;
	/**
	 * Main method is the first method to be called when game starts
	 * Adds new font to the Graphics Environments
	 * Initialises GameWindow class which in return adds GameLoop, heart of the program to JFrame
	 *   
	 * @param args
	 * 		args contains the supplied command-line arguments as an array of String objects.
	 * @exception e
	 * 		prints error if no font found
	 */
	public static void main(String[] args) {
		gw = new GameWindow("Dungeon", width, height);
		fontLoader("res/8BIT");
	}
	
	private static void fontLoader(String fontNamePath){
		try {
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(fontNamePath+".ttf")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
