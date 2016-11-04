package main;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class Player implements KeyListener{
	//Set player variables.
	public static int playerWidth = 20;
	public static int playerHeight = 20;
	private static float X = (Main.width / 2)  - (playerWidth / 2);
	private static float Y = (Main.height / 2) - (playerHeight / 2);
	private static String direction = "";
	private static float speed = 20;
	
	//Method which takes in the graphics environment and adds the player as a rectangle.
	public void drawPlayer(Graphics g){
		g.drawRect((int)X, (int)Y, playerWidth, playerHeight);
	}
	//Run player configuration logic. 
	public void configure(){
		//If player reaches the boundaries stop moving.
		if(X >= Main.width - 100 || X <= -10){
			direction = "";
		}
		if(Y >= Main.height - 100 || Y <= -10){
			direction = "";
		}
		//make player move and refresh screen.
		movePlayer();
		Main.gw.gg.repaint();
	}
	
	//Move player.
	private void movePlayer(){
		//Make player move in desired direction.
		if(direction == "RIGHT"){
			X += speed;
		}
		if(direction == "LEFT"){
			X -= speed; 
		}
		if(direction == "UP"){
			Y -= speed;
		}
		if(direction == "DOWN"){
			Y += speed; 
		}
		
	}
	
	//getters
	public float getX(){
		return X;
	}
	public float getY(){
		return Y;
	}
	//Setters
	public void setX(float x){
		X = x;
	}
	public void setY(float y){
		Y = y;
	}
	 // Control and use player input.
	@Override
	public void keyPressed(KeyEvent e) {
		//Set player direction according to the key presses.
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_D){
			direction = "RIGHT";
		}
		if(key == KeyEvent.VK_A){
			direction = "LEFT";
		}
		if(key == KeyEvent.VK_W){
			direction = "UP";
		}
		if(key == KeyEvent.VK_S){
			direction = "DOWN";
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		//Stop moving when player stops pressing the button.
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_D){
			direction = "";
		}
		if(key == KeyEvent.VK_A){
			direction = "";
		}
		if(key == KeyEvent.VK_W){
			direction = "";
		}
		if(key == KeyEvent.VK_S){
			direction = "";
		}
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
