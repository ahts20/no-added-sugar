package main;

import java.awt.Checkbox;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.CopyOnWriteArrayList;

import GameStates.GameStateManager;

public abstract class Player extends Avatar implements KeyListener {
	protected static String P1Xdirection = "";
	protected static String P1Ydirection = "";
	protected static String P2Xdirection = "";
	protected static String P2Ydirection = "";
	protected CopyOnWriteArrayList<Block> blocks;

	public void init(float X, float Y, int playerNum) {
		this.X = X;
		this.Y = Y;
		
		loadImage loader = new loadImage();
		try {
			spriteSheet = loader.LoadImageFrom("/SpriteSheet(1).png");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Assign animation images to the array p array.
		SpriteSheet ss = new SpriteSheet(spriteSheet);
		int i = 0;
		for (int k = 0; k < 3; k++) {
			for (int j = 0; j < 4; j++) {
				this.p[i] = ss.grabImage(j, k, height, width);
				i++;
			}
		}
	}

	public void update(CopyOnWriteArrayList<Block> blocks) {
		//update pointer to blocks, so it can be used in the push to 
		//know where walls are. 
		this.blocks = blocks;
		// Check for gold collision and update score and gold.
		checkGoldTouch(blocks);
		// make player change its co-ordinates.
		movePlayer(blocks);

	}

	public void render(Graphics g, int i) {

		// Draw the player to the graphics object
		g.drawImage(this.p[i], (int) this.X, (int) this.Y, null);

		g.drawString("Score: " + String.valueOf(score), (int) this.X, (int) this.Y - 30);
	}

	private void checkGoldTouch(CopyOnWriteArrayList<Block> blocks) {
		for (Block i : blocks) {
			if (i.gold && isTouching(i.x, i.y, i.width, i.height)) {
				i.changeGoldToFloor();
				this.score += 10;
				try(FileWriter fw = new FileWriter("res/score.txt", true);
					BufferedWriter bw = new BufferedWriter(fw);
					PrintWriter out = new PrintWriter(bw))
				{
					out.close();
					
				} catch (Exception e){
					e.printStackTrace();
				}
			}
		}
	}
	
	protected void movePlayer(CopyOnWriteArrayList<Block> blocks) {
		//Implemented in Player1 and Player2 classes.
	}
	protected boolean detectTouchingWall(CopyOnWriteArrayList<Block> blocks) {
		for (Block i : blocks) {
			if ((i.wall) && isTouching(i.x, i.y, i.width, i.height))
				return true;
		}
		return false;
	}
	
	protected boolean detectTouchingDoor(CopyOnWriteArrayList<Block> blocks) {
		for (Block i : blocks) {
			if ((i.door) && isTouching(i.x, i.y, i.width, i.height))
		        return true;
		}
		return false;
	}
	public void getKnocked (int distance, String direction){
		//Break total distance down into smaller steps.
		int step = distance/10;
		for (int i = 0; i <= distance; i += 10){
			//Move if not touching a wall or door object.
			if(!detectTouchingWall(blocks) && !detectTouchingDoor(blocks)){
				moveCords(step, direction);
			}
			else{
				//If touching a wall from previous step move back by one.
				//Reverse direction
				switch(direction){
				case "RIGHT":
					direction = "LEFT";
					break;
				case "LEFT":
					direction = "RIGHT";
					break;
				case "UP":
					direction = "DOWN";
					break;
				case "DOWN":
					direction = "UP";
					break;
				}
				//Move back one step.
				moveCords(step, direction);
				break;
			}
		}
	}
	public void moveCords(int distance, String direction){
		if (direction.equals("RIGHT"))
			X = (this.X + distance);
		if (direction.equals("LEFT"))
			X = (this.X - distance);
		if (direction.equals("DOWN"))
			Y = (this.Y + distance);
		if (direction.equals("UP"))
			Y = (this.Y - distance);
	}
	public void setXDirection(String dir){
		this.P1Xdirection = dir;
	}
	public void setYDirection(String dir){
		this.P1Ydirection = dir;
}
}
