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
	
	public void init (){
		this.Xdirection = "";
		this.Ydirection = "";
		X = (Main.width / 2) - (playerWidth / 2);
		Y = (Main.height / 2) - (playerHeight / 2);
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
				this.p[i] = ss.grabImage(j, k, height, width);
				i++;
			}
		}
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// Set player direction according to the key presses.
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_D) {
			this.Xdirection = "RIGHT";
			//System.out.println("Set X direction to Right: " + this.Xdirection);
			
		}
		if (key == KeyEvent.VK_A) {
			this.Xdirection = "LEFT";
		}
		if (key == KeyEvent.VK_W) {
			this.Ydirection = "UP";
		}
		if (key == KeyEvent.VK_S) {
			this.Ydirection = "DOWN";
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
			this.Xdirection = "faceright";
			//System.out.println("Facing right");
		}
		if (key == KeyEvent.VK_A) {
			this.Xdirection = "faceleft";
		}
		if (key == KeyEvent.VK_W) {
			this.Ydirection = "faceup";
		}
		if (key == KeyEvent.VK_S) {
			this.Ydirection = "facedown";
		}

	}

	@Override
	public void keyTyped(KeyEvent k){
		
	}
	public void update(CopyOnWriteArrayList<Block> blocks) {

		// Check for gold collision and update score and gold.
		checkGoldTouch(blocks);
		// make player change its co-ordinates.
		movePlayer(blocks);

	}
	
	public void render(Graphics g, int i) {

		// Draw the player to the graphics object
		g.drawImage(this.p[i], (int) this.X-30, (int) this.Y-30, null);
		//System.out.println("Current X cord: " + this.X + " Current direction: " + this.Xdirection);
		
		g.drawString("Score: " + String.valueOf(score), (int) this.X-30, (int) this.Y-30);
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
	
}
