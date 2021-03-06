package MovableObjects;
import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;
import Generators.Block;
import Generators.SpriteSheet;
import Generators.World;
import Generators.loadImage;
/**
 * This class inherits from Avatar. It contains all methods
 * shared between the players. Class Player1 and Player2 inherit from this class.
 * Ideally both player would be instances of this class, but there was a bug from java.
 * 
 * @author anonymous
 * 
 * @see Avatar
 * @see Player1
 * @see Player2
 * 
 */
public abstract class Player extends Avatar implements KeyListener {

	protected static String P1Xdirection = "";
	protected static String P1Ydirection = "";
	protected static String P2Xdirection = "";
	protected static String P2Ydirection = "";
	protected CopyOnWriteArrayList<Block> blocks;

	public static boolean touching = false;
	
	public World world = new World(gsm);

	public static int counter = 0;
	public String[] maps = {"", "map2", "map3", "map4", "$"};

	/**
	 * This method initialises the attributes for the player class.
	 * 
	 * @param X
	 * 		The starting X coordinate.
	 * @param Y
	 * 		The starting Y coordinate.
	 */
	public void init(float X, float Y) {
	
		this.X = X;
		this.Y = Y;

		loadImage loader = new loadImage();
		try {
			spriteSheet = loader.LoadImageFrom("/SpriteSheet(3).png");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Assign animation images to the array p array.
		SpriteSheet ss = new SpriteSheet(spriteSheet);
		int i = 0;
		for (int k = 0; k < 4; k++) {
			for (int j = 0; j < 4; j++) {
				Avatar.p[i] = ss.grabImage(j, k, height, width);
				i++;
			}
		}
	}
	/**
	 * Method. Coordinates the player's logic and updates the player's interpretation of block
	 * locations. Checks if the player touches gold moves the player in the desired direction 
	 * and checks if the player is out of bounds.
	 * 
	 * @param blocks
	 * 		A list of all blocks in the game.
	 * 
	 * @see World
	 * 		Calls this method to update all logic in the game on each frame.
	 * 
	 * @see checkGoldTouch() 
	 * 		Coordinates all logic for when the player touches gold.
	 * 
	 * @see movePlayer()
	 * 		moves the player in the desired location.
	 * 
	 * @see checkIfOutside()
	 * 		Checks if the player is out of bounds (i.e. off the map).
	 * 
	 * @see resetPosition()
	 * 		Moves the player back to a location on the map.
	 */
	public void update(CopyOnWriteArrayList<Block> blocks) {
		//update pointer to blocks, so it can be used when the player gets pushed 
		//know where walls are. 
		this.blocks = blocks;
		checkGoldTouch(blocks);
		movePlayer(blocks);
		if (checkIfOutside(blocks))
			resetPosition(blocks);

	}

	/**
	 * Resets the player's location back onto the map.
	 * It works by finding the closest rectangle (floor) 
	 * block to the player's location and setting the player's
	 * coordinates to this block.
	 * 
	 * @param blocks
	 * 		A list of all blocks in the game.
	 * @see update()
	 * 		Calls this method.
	 */
	private void resetPosition(CopyOnWriteArrayList<Block> blocks) {
	
		Block first = blocks.get(0);
		double temp;
		double closestDist = calculateDist(first.x, first.y, this.X, this.Y);
		Block closest = blocks.get(0);
		for (Block i : blocks){
			temp = calculateDist(i.x, i.y, this.X, this.Y);
			if (i.rectangle && (temp < closestDist)){
				closestDist = temp;
				closest = i;
			}
		}
		//x-40 because players are twice the width of blocks.
		this.X = closest.x-40;
		this.Y = closest.y;
		
	}
	/**
	 * This method checks to see if the player is out of bounds.
	 * It does this by checking all blocks to see if the player is in contact with 
	 * any that it should be in contact with.
	 * 
	 * @param blocks
	 */
	private boolean checkIfOutside(CopyOnWriteArrayList<Block> blocks) {

		boolean outSide = true;
		for (Block i : blocks){
			if (isTouching(i.x, i.y, i.width, i.height) && (i.rectangle || i.gold || i.wall || (i.door && i.isVisible)) ){
				outSide = false;
				break;
			}
				
		}
		return outSide;
	}
	/**
	 * This method updates the game graphics with the player's
	 * correct image (in respect to direction) at the correct 
	 * coordinates.
	 * 
	 * @param g
	 * 		The graphics object which is displayed to the screen.
	 * 
	 * @see World
	 * 		Calls this method to coordinate updates the the screen.
	 */
	public void render(Graphics g) {

		int i = 0;
		
		if (status == "facedown") {
			i = 3;
		} else if (status == "faceleft") {
			i = 0;
		} else if (status == "faceright") {
			i = 1;
		} else if (status == "faceup") {
			i = 2;
		}

		// Draw the player to the graphics object
		g.drawImage(Avatar.p[i], (int) this.X, (int) this.Y, null);
		g.drawString("Score: " + String.valueOf(score), (int) this.X, (int) this.Y - 30);
	}
	/**
	 * Method which coordinates the logic involved with checking if a 
	 * player touches gold. This involves updating the player's score 
	 * and changing that gold into a floor object.
	 * 
	 * @param blocks
	 * 		A list of all block elements in the game.
	 * @see isTouching()
	 * 		Used to detect if the player is touching the gold object.
	 * @see .changeGoldToFloor()
	 * 		Used to change the gold element to a floor block.
	 */
	private void checkGoldTouch(CopyOnWriteArrayList<Block> blocks) {

		for (Block i : blocks) {
			if (i.gold && isTouching(i.x, i.y, i.width, i.height)) {
				i.changeGoldToFloor();
				this.score += 10;
			}
		}
	}
	/**
	 * A method used to move the player. It is implemented in the
	 * children classes (Player1 and Player2).
	 */
	protected void movePlayer(CopyOnWriteArrayList<Block> blocks) {
	
	}
	/**
	 * A method to coordinate the logic when the player is hit by the
	 * bot. The method move the player in the desired  direction by 
	 * the desired amount and stops if a wall is encountered.
	 * 
	 * It does this by splitting the distance into segments of 5 pixels. At each 
	 * segment it moves the player and checks to see if a wall is touched.
	 * If it is, then the player is moved back one step and the process ends there.
	 * 
	 * @param distance
	 * 		The distance required to move.
	 * @param direction
	 * 		The desired direction.
	 * @see Bot.java
	 * 		This class calls this method when it somes into contact with the player.
	 * @see moveCords()
	 * 		Uses this method to move the player.
	 */
	public void getKnocked (int distance, String direction){
		int step = 5;
		for (int i = 0; i <= distance; i += step){
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
	/**
	 * This method moves the player coordinates by the specified amount in the correct direction.
	 * 
	 * @param distance
	 * 		The distance to move the player.
	 * 
	 * @param direction
	 * 		The direction to move the player.
	 * 
	 * @see getKnocked()
	 * 		Called by this method when the player is knocked by the bot.
	 */
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
}
