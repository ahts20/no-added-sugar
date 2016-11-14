package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Player implements KeyListener{

	public static int playerWidth = 20;
	public static int playerHeight = 20;
	private static float X = (Main.width / 2)  - (playerWidth / 2);
	private static float Y = (Main.height / 2) - (playerHeight / 2);
	private static String Xdirection = "";
	private static String Ydirection = "";
	private static float speed = 5;

	public void init() {
		
	}

	public void update() {
		//If player reaches the boundaries stop moving.
		if(X >= Main.width - 100 || X <= -10){
			Xdirection = "";
		}
		if(Y >= Main.height - 100 || Y <= -10){
			Ydirection = "";
		}
		//make player move and refresh screen.
		movePlayer();
	}

	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		g.drawRect((int)X, (int)Y, playerWidth, playerHeight);
	}
	
	//Move player.
	private void movePlayer(){
		//Make player move in desired direction.
		if(Xdirection == "RIGHT"){
			X += speed;
		}
		if(Xdirection == "LEFT"){
			X -= speed; 
		}
		if(Ydirection == "UP"){
			Y -= speed;
		}
		if(Ydirection == "DOWN"){
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
			Xdirection = "RIGHT";
		}
		if(key == KeyEvent.VK_A){
			Xdirection = "LEFT";
		}
		if(key == KeyEvent.VK_W){
			Ydirection = "UP";
		}
		if(key == KeyEvent.VK_S){
			Ydirection = "DOWN";
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		//Stop moving when player stops pressing the button.
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_D){
			Xdirection = "";
		}
		if(key == KeyEvent.VK_A){
			Xdirection = "";
		}
		if(key == KeyEvent.VK_W){
			Ydirection = "";
		}
		if(key == KeyEvent.VK_S){
			Ydirection = "";
		}
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
