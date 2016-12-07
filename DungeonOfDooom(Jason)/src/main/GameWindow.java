package main;

import javax.swing.JFrame;

public class GameWindow extends JFrame{
	
	//Collect game graphics as a variable instance.
	public GameGraphics gg = new GameGraphics();
	
	public GameWindow(String title, int width, int height){
		//Add graphics to the game window.
		add(gg);
		//Continue setting up the game window.
		setTitle(title);
		setSize(width, height);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Add key listener
		addKeyListener(new Player());
		
		setVisible(true);
	}
}
