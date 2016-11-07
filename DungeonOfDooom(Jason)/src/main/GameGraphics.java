package main;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class GameGraphics extends JPanel{
	//Set the player as an object.
	public Player player = new Player();;
	
	public void paintComponent(Graphics g){
		//Setup the graphics environment.
		super.paintComponent(g);
		this.setBackground(Color.BLACK);
		g.setColor(Color.WHITE);
		//Make the player draw itself.
		player.init();
//		while (true){
			for (int i=0; i<=8;i++){
				player.drawPlayer(g,i);
			}
//		}
	}

}
