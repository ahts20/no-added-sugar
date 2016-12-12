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

public abstract class Avatar {
	/*
	It is wise to declare it because if you don't declare one then when changing 
	the class it will get a different one generated automatically and the 
	serialisation will stop working.
	*/
	private static final long serialVersionUID = 1L;
	
	public static int playerWidth = 40;
	public static int playerHeight = 40;
	public int height = 60;
	public int width = 60;
	protected float X;
	protected float Y;
	protected static String Xdirection;
	protected static String Ydirection;
	protected float speed = 5;
	public String status = "facedown";
	protected int score = 0;
	
	protected GameStateManager gsm;



	public static Rectangle render;
	
	public boolean isChanging = false;

	protected BufferedImage spriteSheet = null;
	public static BufferedImage[] p = new BufferedImage[12];

	public void init() {	
		this.Xdirection = "";
		this.Ydirection = "";
	}

	public void update(CopyOnWriteArrayList<Block> blocks) {
		// make player change its co-ordinates.
		movePlayer(blocks);

	}


	protected boolean isTouching(int x, int y, int width, int height) {
		//Checking current in target space
		if (this.X >= x && this.X <= x + width) {
			if (this.Y >= y && this.Y <= y + height) {
				return true;
			} 
		}
		//Checking target in current space.
		if (x >= this.X && x <= this.X + this.width) {
			if (y >= this.Y && y <= this.Y + this.height) {
				return true;
			}
		}
		return false;
	}

	public void render(Graphics g, int i) {
}

	protected void movePlayer(CopyOnWriteArrayList<Block> blocks) {
		// Make player move in desired direction.
		if (this.Xdirection == "RIGHT") {
			if (detectTouchingWall(blocks)) {
				this.Xdirection = "LEFT";
				X -= this.speed;
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
		if (this.Xdirection.equals("LEFT")) {
			if (detectTouchingWall(blocks)) {
				this.Xdirection.equals("RIGHT");
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
		if (this.Ydirection == "UP") {
			if (detectTouchingWall(blocks)) {
				this.Ydirection = "DOWN";
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
		if (this.Ydirection == "DOWN") {
			if (detectTouchingWall(blocks)) {
				this.Ydirection = "UP";
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
	   

	// getters
	public double getX() {
		return this.X;
	}

	public double getY() {
		return this.Y;
	}

	public int getScore() {
		return this.score;
	}
	

	// Setters
	public void setX(float x) {
		this.X = x;
	}

	public void setY(float y) {
		this.Y = y;
	}
	
	public void setScore(int s){
		this.score = s;
	}

	
	public boolean isChanging() {
		return isChanging;
	}
	public void setIsChanging(boolean isChanging){
		this.isChanging = isChanging;
	}
	
}