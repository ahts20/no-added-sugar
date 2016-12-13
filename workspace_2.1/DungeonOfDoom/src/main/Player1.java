package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.CopyOnWriteArrayList;

public class Player1 extends Player implements KeyListener {
	@Override
	public void keyPressed(KeyEvent e) {
		// Set player direction according to the key presses.
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_D) {
			P1Xdirection = "RIGHT";

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
		
		
		if (key == KeyEvent.VK_ESCAPE) {
			System.exit(1);
		}
		
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
		//Overwrite to prevent constant walking.
	}

	@Override
	public void keyTyped(KeyEvent k) {

	}
	
	protected void movePlayer(CopyOnWriteArrayList<Block> blocks) {
		// Make player move in desired direction.
		String Xdirection = "";
		String Ydirection = "";


		Xdirection = P1Xdirection;
		Ydirection = P1Ydirection;

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
				this.Y -= this.speed;
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
