package main;

import javax.swing.JFrame;

import Generators.MouseInput;
import Managers.GameLoop;
import MovableObjects.Player1;
import MovableObjects.Player2;

public class GameWindow extends JFrame{
	/*
	It is wise to declare it because if you don't declare one then when changing 
	the class it will get a different one generated automatically and the 
	serialisation will stop working.
	*/
	private static final long serialVersionUID = 1L;
	
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
