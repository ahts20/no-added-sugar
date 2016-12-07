package main;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class GameGraphics extends JPanel{
	//Set the player as an object.
	public Player player = new Player();;
	private int frame=1;
	private int frameDelay=0;
	
	
	public void paintComponent(Graphics g){
		//Setup the graphics environment.
		super.paintComponent(g);
		this.setBackground(Color.BLACK);
		g.setColor(Color.WHITE);
		//Make the player draw itself.
		player.init();
		if (frame != 8){
			frame++;
		}
		else{
			frame=1;
		}
		player.drawPlayer(g,frame);
	}

}
