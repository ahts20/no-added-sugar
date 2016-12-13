package main;

import java.awt.Checkbox;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;

import GameStates.GameStateManager;

public class Player extends Avatar implements KeyListener {
	private static String P1Xdirection = "";
	private static String P1Ydirection = "";
	private static String P2Xdirection = "";
	private static String P2Ydirection = "";
	private Integer iD;
	private CopyOnWriteArrayList<Block> blocks;

	public void init(float X, float Y, int playerNum) {
//		X = (Main.width / 2) - (playerWidth / 2);
//		Y = (Main.height / 2) - (playerHeight / 2);
		this.X = X;
		this.Y = Y;
		//Java insists on this being static for some unknown reason.
		this.iD = playerNum;
		System.out.println(iD);
		
		loadImage loader = new loadImage();
		try {
			spriteSheet = loader.LoadImageFrom("/SpriteSheet(2).png");
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

	@Override
	public void keyPressed(KeyEvent e) {
		String Xdirection = "";
		String Ydirection = "";
		// Set player direction according to the key presses.
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_D) {
			P1Xdirection = "RIGHT";
			// System.out.println("Set X direction to Right: " +
			// this.Xdirection);

		}
		if (key == KeyEvent.VK_A) {
			P1Xdirection = "LEFT";
		}
		if (key == KeyEvent.VK_W) {
			P1Ydirection = "UP";
		}
		if (key == KeyEvent.VK_S) {
			P1Ydirection = "DOWN";
		}

		if (key == KeyEvent.VK_R) {
			World.resetWorld();
			gsm.states.push(new LevelLoader(gsm, "Not", "map2"));
			gsm.states.peek().init();
		}
		if (key == KeyEvent.VK_UP) {
			P2Ydirection = "UP";
		}
		
		
//		P1Xdirection = Xdirection;
//		P1Ydirection = Ydirection;
//		P2Xdirection = Xdirection;
//		P2Ydirection = Ydirection;
//		System.out.println("iD: " + this.iD + " xDirection: " + Xdirection);
//		//Set direction to specific player
//		if (iD == 1){
//			System.out.println("REACHED SETTER!!");
//			this.P1Xdirection = Xdirection;
//			this.P1Ydirection = Ydirection;
//		}
//		if (iD == 2){
//			this.P2Xdirection = Xdirection;
//			this.P2Ydirection = Ydirection;
//		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// Stop moving when player stops pressing the button.
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_D) {
			this.P1Xdirection = "faceright";
			// System.out.println("Facing right");
		}
		if (key == KeyEvent.VK_A) {
			this.P1Xdirection = "faceleft";
		}
		if (key == KeyEvent.VK_W) {
			this.P1Ydirection = "faceup";
		}
		if (key == KeyEvent.VK_S) {
			this.P1Ydirection = "facedown";
		}
		//Overwrite to prevent constant walking.#
		//System.out.println(iD);
		this.P2Xdirection = "default";
		this.P2Ydirection = "default";
	}

	@Override
	public void keyTyped(KeyEvent k) {

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
		// System.out.println("Current X cord: " + this.X + " Current direction:
		// " + this.Xdirection);

		g.drawString("Score: " + String.valueOf(score), (int) this.X, (int) this.Y - 30);
	}

	private void checkGoldTouch(CopyOnWriteArrayList<Block> blocks) {
		for (Block i : blocks) {
			// System.out.println("Checking " + i);
			if (i.gold && isTouching(i.x, i.y, i.width, i.height)) {
				i.changeGoldToFloor();
				this.score += 10;
			}
		}
	}
	
	protected void movePlayer(CopyOnWriteArrayList<Block> blocks) {
		// Make player move in desired direction.
		String Xdirection = "";
		String Ydirection = "";

		for (int i = 1; i <3 ; i++){
			if (i == 1){
				Xdirection = P1Xdirection;
				Ydirection = P1Ydirection;
			}
			if (i == 2){
				Xdirection = P2Xdirection;
				Ydirection = P2Ydirection;
			}
			//System.out.println("\nCurrent cords: " + X + "  " + Y);
			//System.out.println("Direction: "+ Xdirection + Ydirection);
			if (Xdirection == "RIGHT") {
				if (detectTouchingWall(blocks)) {
					Xdirection = "LEFT";
					this.X -= this.speed;
				} else {
					this.X += this.speed;
					this.status = "faceright";
					if(detectTouchingDoor(blocks)){
						World.resetWorld();
						gsm.states.push(new LevelLoader(gsm, "Not", "map2"));
						gsm.states.peek().init();
					}
				}

			}
			if (Xdirection.equals("LEFT")) {
				if (detectTouchingWall(blocks)) {
					Xdirection.equals("RIGHT");
					this.X += this.speed;
				} else {
					this.X -= this.speed;
					this.status = "faceleft";
					if(detectTouchingDoor(blocks)){
						World.resetWorld();
						gsm.states.push(new LevelLoader(gsm, "Not", "map2"));
						gsm.states.peek().init();
					}
				}

			}
			if (Ydirection == "UP") {
				if (detectTouchingWall(blocks)) {
					Ydirection = "DOWN";
					this.Y += this.speed;
				} else {
					this.Y -= this.speed;
					this.status = "faceup";
					if(detectTouchingDoor(blocks)){
						World.resetWorld();
						gsm.states.push(new LevelLoader(gsm, "Not", "map2"));
						gsm.states.peek().init();
					}
				}
			}
			if (Ydirection == "DOWN") {
				if (detectTouchingWall(blocks)) {
					Ydirection = "UP";
					this.Y += this.speed;
				} else {
					this.Y += this.speed;
					this.status = "facedown";
					if(detectTouchingDoor(blocks)){
						World.resetWorld();
						gsm.states.push(new LevelLoader(gsm, "Not", "map2"));
						gsm.states.peek().init();
					}
				}
			}
		}
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
