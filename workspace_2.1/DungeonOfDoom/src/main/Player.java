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

public class Player extends Rectangle implements KeyListener {
	/*
	It is wise to declare it because if you don't declare one then when changing 
	the class it will get a different one generated automatically and the 
	serialisation will stop working.
	*/
	private static final long serialVersionUID = 1L;
	
	public static int playerWidth = 40;
	public static int playerHeight = 40;
	private float X = (Main.width / 2) - (playerWidth / 2);
	private float Y = (Main.height / 2) - (playerHeight / 2);
	private static String Xdirection = "";
	private static String Ydirection = "";
	private static float speed = 5;
	public String status = "facedown";
	private int score = 0;
	
	private GameStateManager gsm;

	// Dimensions of Square/Screen following player.
	private int renderDistanceW = 20;
	private int renderDistanceH = 20;

	public static Rectangle render;
	
	public boolean isChanging = false;

	private BufferedImage spriteSheet = null;
	public static BufferedImage[] p = new BufferedImage[12];

	public void init() {	
		loadImage loader = new loadImage();
		try {
			spriteSheet = loader.LoadImageFrom("/SpriteSheet.png");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Assign animation images to the array p array.
		SpriteSheet ss = new SpriteSheet(spriteSheet);
		int i = 0;
		for (int k = 0; k < 3; k++) {
			for (int j = 0; j < 4; j++) {
				p[i] = ss.grabImage(j, k, 80 / 4, 60 / 3);
				i++;
			}
		}

		// Draw large rectangle following player.
		render = new Rectangle((int) X - ((renderDistanceW * 20) / 2), (int) Y - ((renderDistanceH * 20) / 2),
				renderDistanceW * 20, renderDistanceH * 20);
	}

	public void update(CopyOnWriteArrayList<Block> blocks) {

		// Update the large rectangle coordinates.
		render = new Rectangle((int) X - ((renderDistanceW * 20) / 2), (int) Y - ((renderDistanceH * 20) / 2),
				renderDistanceW * 20, renderDistanceH * 20);

		// If player reaches the boundaries stop moving.
		if (X >= Main.width - 100 || X <= -10) {
			Xdirection = "";
		}
		if (Y >= Main.height - 100 || Y <= -10) {
			Ydirection = "";
		}
		// Update player bounds
		setBounds((int) x, (int) y, width, height);
		// Check for gold collision and update score and gold.
		checkGoldTouch(blocks);
		// make player change its co-ordinates.
		movePlayer(blocks);

	}

	private void checkGoldTouch(CopyOnWriteArrayList<Block> blocks) {
		for (Block i : blocks) {
			// System.out.println("Checking " + i);
			if (i.gold && isTouching(i.x, i.y, i.width, i.height)) {
				i.changeGoldToFloor();
				score += 10;
			}
		}
	}

	private boolean isTouching(int x, int y, int width, int height) {
		if (this.X >= x && this.X <= x + width) {
			if (this.Y >= y && this.Y <= y + height) {
				return true;
			}
		}
		return false;
	}

	public void render(Graphics g, int i) {

		// Draw the player to the graphics object
		g.drawImage(p[i], (int) X, (int) Y, null);
		
		g.drawString("Score: " + String.valueOf(score), (int) X, (int) Y);
	}

	// Move player.
	private void movePlayer(CopyOnWriteArrayList<Block> blocks) {
		// Make player move in desired direction.
		if (Xdirection == "RIGHT") {
			if (detectTouchingWall(blocks)) {
				Xdirection = "LEFT";
				X -= speed;
			} else {
				X += speed;
				status = "faceright";
				if(detectTouchingDoor(blocks)){
					World.resetWorld();
			        gsm.states.push(new LevelLoader(gsm, "Not", "map2"));
			        gsm.states.peek().init();
				}
			}
			
		}
		if (Xdirection == "LEFT") {
			if (detectTouchingWall(blocks)) {
				Xdirection = "RIGHT";
				X += speed;
			} else {
				X -= speed;
				status = "faceleft";
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
				Y += speed;
			} else {
				Y -= speed;
				status = "faceup";
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
				Y += speed;
			} else {
				Y += speed;
				status = "facedown";
				if(detectTouchingDoor(blocks)){
					World.resetWorld();
			        gsm.states.push(new LevelLoader(gsm, "Not", "map2"));
			        gsm.states.peek().init();
				}
			}
		}
	}

	private boolean detectTouchingWall(CopyOnWriteArrayList<Block> blocks) {
		for (Block i : blocks) {
			if ((i.wall) && isTouching(i.x, i.y, i.width, i.height))

				return true;
		}
		return false;
	}
	
	private boolean detectTouchingDoor(CopyOnWriteArrayList<Block> blocks) {
		for (Block i : blocks) {
			if ((i.door) && isTouching(i.x, i.y, i.width, i.height))
		
		        return true;
		}
		return false;
	}
	   

	// getters
	public double getX() {
		return X;
	}

	public double getY() {
		return Y;
	}

	public double getScore() {
		return this.score;
	}

	// Setters
	public void setX(float x) {
		X = x;
	}

	public void setY(float y) {
		Y = y;
	}

	// Control and use player input.
	@Override
	public void keyPressed(KeyEvent e) {
		// Set player direction according to the key presses.
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_D) {
			Xdirection = "RIGHT";
			
		}
		if (key == KeyEvent.VK_A) {
			Xdirection = "LEFT";
		}
		if (key == KeyEvent.VK_W) {
			Ydirection = "UP";
		}
		if (key == KeyEvent.VK_S) {
			Ydirection = "DOWN";
		}
		
		if (key == KeyEvent.VK_R) {
		   World.resetWorld();
	       gsm.states.push(new LevelLoader(gsm, "Not", "map2"));
	       gsm.states.peek().init();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// Stop moving when player stops pressing the button.
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_D) {
			Xdirection = "faceright";
		}
		if (key == KeyEvent.VK_A) {
			Xdirection = "faceleft";
		}
		if (key == KeyEvent.VK_W) {
			Ydirection = "faceup";
		}
		if (key == KeyEvent.VK_S) {
			Ydirection = "facedown";
		}

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	public boolean isChanging() {
		return isChanging;
	}
	public void setIsChanging(boolean isChanging){
		this.isChanging = isChanging;
	}
	
}
