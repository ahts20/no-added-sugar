package main;

import javax.swing.JFrame;

import GameStates.GameLoop;

public class GameWindow extends JFrame{
	
	
	
	public GameWindow(String title, int width, int height){
		add(new GameLoop(width, height));
		//Continue setting up the game window.
		setTitle(title);
		setSize(width, height);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		addKeyListener(new Player());
		
		setVisible(true);
	}
}
