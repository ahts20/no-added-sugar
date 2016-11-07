package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.AbstractAction;
import javax.swing.Timer;

public class Logic {
	//Collects the player as an object.
	public Player player = new Player();
	
	//The run logic methods runs the main program.
	public void runLogic(){
		//Create an action listener which acts as a tick/processor for each frame.
		ActionListener listener = new AbstractAction(){
			
			public void actionPerformed(ActionEvent e) {
				player.configure();
			}
		};
		
		Timer timer = new Timer(100, listener);
		timer.start();
	}

}
