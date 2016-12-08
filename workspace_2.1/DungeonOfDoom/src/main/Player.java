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
	@Override
	public void keyPressed(KeyEvent e) {
		// Set player direction according to the key presses.
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_D) {
			this.Xdirection = "RIGHT";
			System.out.println("Set X direction to Right: " + this.Xdirection);
			
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
			System.out.println("Facing right");
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

		// Dimensions of Square/Screen following player.
	

		// Update player bounds
		//setBounds((int) x, (int) y, width, height);
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
				this.score += 10;
			}
		}
	}
}
