package main;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;

public class Player implements KeyListener, ImageObserver{
	//Set player variables.
	public static int playerWidth = 20;
	public static int playerHeight = 20;
	public static float X = (Main.width / 2)  - (playerWidth / 2);
	public static float Y = (Main.height / 2) - (playerHeight / 2);
	public static String direction = "";
	public static float speed = 20;
	

	
//	private BufferedImage image = new BufferedImage(Width, Height, BufferedImage.TYPE_INT_RGB);
	private BufferedImage spriteSheet = null;
	private BufferedImage[] p=new BufferedImage[9];
	public void init(){
		ImageLoader loader = new ImageLoader();
		try {
			spriteSheet = loader.loadImage("/SpriteSheet1.png");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SpriteSheet ss = new SpriteSheet(spriteSheet);
//		System.out.println(ss);
//		private int i;
		for (int i=1;i<=8;i++){
			p[i] = ss.grabImage(i, 1, 145/8, 32);
		}
		
		
	}
	private static boolean moving = false;
	

	
	//Method which takes in the graphics environment and adds the player as a rectangle.
	public void drawPlayer(Graphics g,int i){
		
		g.drawImage(p[i], (int) X, (int) Y, null);
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
	public void movePlayer(){
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
	@Override
	public boolean imageUpdate(Image arg0, int arg1, int arg2, int arg3, int arg4, int arg5) {
		// TODO Auto-generated method stub
		return false;
	}

}
