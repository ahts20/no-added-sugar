package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.CopyOnWriteArrayList;

public class Player1 extends Player implements KeyListener {
	/**
	 * This class inherits method from the player class, and implements
	 * methods specific to the player1 instance (i.e. specific 
	 * key events).
	 * Ideally both player1 and player2 would be instances of 
	 * the player class, although java displayed a large bug when
	 * trying to make the Xdirection and Ydirection variables 
	 * non-static.
	 *  
	 * @author James
	 * 
	 * @see Player
	 * 	This class inherits from Player.
	 * @see Bot
	 * 	This class interacts with the Bot class.
	 * @see Block
	 * 	This class interacts with the Bot class to determine
	 * 	whether it is touching objects in game (e.g. gold or
	 * 	walls).
	 * @see World
	 * 	This class is coordinated by the World class,
	 * 	which determines when to update the player's logic and
	 * 	add itself to the graphics screen.
	 */
	
	@Override
	public void keyPressed(KeyEvent e) {
		/**
		 * This method is called when a keyboard key is pressed.
		 * This method sets the player's direction variables to
		 * the appropriate direction.
		 * 
		 * @param e
		 * 	e is a KeyEvent variable.
		 */
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
		if (key == KeyEvent.VK_ESCAPE) {
			System.exit(1);
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		/**
		 * This method is called when a key is released.
		 * It changes the player's direction variables from
		 * the movement coordinating values (e.g. "UP", "DOWN")
		 * so the player stops moving when the button is released.
		 * 
		 * @param e
		 * 	e is a KeyEvent variable.
		 */
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_D) {
			this.P1Xdirection = "faceright";
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
		int extraPushback = 15;
		
		if (Xdirection == "RIGHT") {
			if (detectTouchingWall(blocks) || detectTouchingHiddenDoor(blocks)) {
				Xdirection = "LEFT";
				this.X -= this.speed+extraPushback;
			} else {
				this.X += this.speed;
				this.status = "faceright";
				if(detectTouchingDoor(blocks)){
					counter ++;
					touching = true;

				
					if(maps[counter].equals("$")){
						gsm.states.push(new GameOverState(gsm));
						gsm.states.peek().init();
					} else {
						World.resetWorld();
						gsm.states.push(new LevelLoader(gsm, "Not", maps[counter]));
						gsm.states.peek().init();
					}


				}
			}
		}
		if (Xdirection.equals("LEFT")) {
			if (detectTouchingWall(blocks)|| detectTouchingHiddenDoor(blocks)) {
				Xdirection.equals("RIGHT");
				this.X += this.speed+extraPushback;
			} else {
				this.X -= this.speed;
				this.status = "faceleft";
				if(detectTouchingDoor(blocks)){
					counter ++;
					touching = true;
	
					if(maps[counter].equals("$")){
						gsm.states.push(new GameOverState(gsm));
						gsm.states.peek().init();
					} else {
						World.resetWorld();
						gsm.states.push(new LevelLoader(gsm, "Not", maps[counter]));
						gsm.states.peek().init();
					}

				}
			}
		}
		if (Ydirection == "UP") {
			if (detectTouchingWall(blocks)|| detectTouchingHiddenDoor(blocks)) {
				Ydirection = "DOWN";
				this.Y += this.speed+extraPushback;
			} else {
				this.Y -= this.speed;
				this.status = "faceup";
				if(detectTouchingDoor(blocks)){
					counter ++;
					touching = true;
				
					if(maps[counter].equals("$")){
						gsm.states.push(new GameOverState(gsm));
						gsm.states.peek().init();
					} else {
						World.resetWorld();
						gsm.states.push(new LevelLoader(gsm, "Not", maps[counter]));
						gsm.states.peek().init();
					}

				}
			}
		}
		if (Ydirection == "DOWN") {
			if (detectTouchingWall(blocks)|| detectTouchingHiddenDoor(blocks)) {
				Ydirection = "UP";
				this.Y -= this.speed+extraPushback;
			} else {
				this.Y += this.speed; 
				this.status = "facedown";
				if(detectTouchingDoor(blocks)){
					counter ++;
					touching = true;
				
					if(maps[counter].equals("$")){
						gsm.states.push(new GameOverState(gsm));
						gsm.states.peek().init();
					} else {
						World.resetWorld();
						gsm.states.push(new LevelLoader(gsm, "Not", maps[counter]));
						gsm.states.peek().init();
					}

				}
			}
		}
	}
}
