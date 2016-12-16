package MovableObjects;
import java.awt.event.KeyEvent;
import java.util.concurrent.CopyOnWriteArrayList;
import GameStates.GameOverState;
import GameStates.LevelLoader;
import Generators.Block;
import Generators.World;
import Managers.GameStateManager;
/**
 * This class inherits method from the player class, and implements
 * methods specific to the player2 instance (i.e. specific 
 * key events).
 * Ideally both player1 and player2 would be instances of 
 * the player class, although java displayed a large bug when
 * trying to make the Xdirection and Ydirection variables 
 * non-static.
 *  
 * @author anonymous
 * @see Player
 * 		This class inherits from Player.
 * @see Bot
 * 		This class interacts with the Bot class.
 * @see Block
 * 		This class interacts with the Bot class to determine
 * 		whether it is touching objects in game (e.g. gold or walls).
 * @see World
 * 		This class is coordinated by the World class,
 * 		which determines when to update the player's logic and
 * 		add itself to the graphics screen.
 */
public class Player2 extends Player {
	/**
	 * This method is called when a keyboard key is pressed.
	 * This method sets the player's direction variables to
	 * the appropriate direction.
	 * 
	 * @param e
	 * 		e is a KeyEvent variable.
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_RIGHT) {
			P2Xdirection = "RIGHT";
		}
		if (key == KeyEvent.VK_LEFT) {
			P2Xdirection = "LEFT";
		}
		if (key == KeyEvent.VK_UP) {
			P2Ydirection = "UP";
		}
		if (key == KeyEvent.VK_DOWN) {
			P2Ydirection = "DOWN";
		}		
		if (key == KeyEvent.VK_ESCAPE) {
			System.exit(1);
		}	
	}
	/**
	 * This method is called when a key is released.
	 * It changes the player's direction variables from
	 * the movement coordinating values (e.g. "UP", "DOWN")
	 * so the player stops moving when the button is released.
	 * 
	 * @param e
	 * 		e is a KeyEvent variable.
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_RIGHT) {
			Player.P2Xdirection = "faceright";
		}
		if (key == KeyEvent.VK_LEFT) {
			Player.P2Xdirection = "faceleft";
		}
		if (key == KeyEvent.VK_UP) {
			Player.P2Ydirection = "faceup";
		}
		if (key == KeyEvent.VK_DOWN) {
			Player.P2Ydirection = "facedown";
		}
	}
	/**
	 * An unused method from the KeyListener class.
	 */
	@Override
	public void keyTyped(KeyEvent k) {
	
	}
	/**
	 *  This method makes the player move in desired direction,
	 *  as dictated by the player's direction variable values.
	 *  The method also takes into account wall collisions and 
	 *  closed/hidden door collisions, and moves the player backward
	 *  when this occurs - to prevent players from walking out of the map.
	 *  
	 *  If the player walks into an open door (which is opened once enough
	 *  gold in the room has been collected) then the next game state is 
	 *  pushed to the stack.
	 *  
	 * @param blocks 
	 *  	This ArrayList contains all block instances in the game.
	 */
	protected void movePlayer(CopyOnWriteArrayList<Block> blocks) {

		String Xdirection = "";
		String Ydirection = "";

		int extraPushback = 15;
		Xdirection = P2Xdirection;
		Ydirection = P2Ydirection;

		if (Xdirection == "RIGHT") {
			if (detectTouchingWall(blocks)|| detectTouchingHiddenDoor(blocks)) {
				Xdirection = "LEFT";
				this.X -= this.speed+extraPushback;
			} else {
				this.X += this.speed;
				this.status = "faceright";
				if(detectTouchingDoor(blocks)){
					counter ++;
					touching = true;
	
					if(maps[counter].equals("$")){
						GameStateManager.states.push(new GameOverState(gsm));
						GameStateManager.states.peek().init();
					} else {
						World.resetWorld();
						GameStateManager.states.push(new LevelLoader(gsm, "Not", maps[counter]));
						GameStateManager.states.peek().init();
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
						GameStateManager.states.push(new GameOverState(gsm));
						GameStateManager.states.peek().init();
					} else {
						World.resetWorld();
						GameStateManager.states.push(new LevelLoader(gsm, "Not", maps[counter]));
						GameStateManager.states.peek().init();
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
						GameStateManager.states.push(new GameOverState(gsm));
						GameStateManager.states.peek().init();
					} else {
						World.resetWorld();
						GameStateManager.states.push(new LevelLoader(gsm, "Not", maps[counter]));
						GameStateManager.states.peek().init();
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
						GameStateManager.states.push(new GameOverState(gsm));
						GameStateManager.states.peek().init();
					} else {
						World.resetWorld();
						GameStateManager.states.push(new LevelLoader(gsm, "Not", maps[counter]));
						GameStateManager.states.peek().init();
					}

				}
			}
		}
	}
}
