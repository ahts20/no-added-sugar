package main;
import javax.swing.JFrame;

import Generators.MouseInput;
import Managers.GameLoop;
import MovableObjects.Player1;
import MovableObjects.Player2;
/**
 * GameWindow class extends JFrame and adds GameLoop to the JFrame
 * JFrame is an in-built function which creates a window that contains modifiable decorations
 * 
 * @author anonymous
 * @version 1.0
 * @release 14/12/2016
 * @See GameWindow.java
 */
public class GameWindow extends JFrame{
	/*
	It is wise to declare it because if you don't declare one then when changing 
	the class it will get a different one generated automatically and the 
	serialisation will stop working.
	*/
	private static final long serialVersionUID = 1L;
	/**
	 * Constructor. Sets the field values. 
	 * Contains window decorations and key and mouse listeners.
	 * Added when the GameWindow is initialises in the main class.
	 * @param title
	 * 		
	 * @param width
	 * @param height
	 */
	public GameWindow(String title, int width, int height){
		add(new GameLoop(width, height));
		//Continue setting up the game window.
		setTitle(title);
		setSize(width, height);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setUndecorated(true);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		//click release
		addMouseListener(new MouseInput());
		//move dragMove
		addMouseMotionListener(new MouseInput());
		addKeyListener(new Player1());
		addKeyListener(new Player2());
		
		setVisible(true);
	}
}
