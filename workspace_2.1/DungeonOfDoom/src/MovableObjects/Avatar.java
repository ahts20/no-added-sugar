package MovableObjects;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.concurrent.CopyOnWriteArrayList;
import Generators.Block;
import Managers.GameStateManager;
/**
 * This class is the parent class to the Bot and the Player class.
 * It contains all shared methods and attributes.
 * 
 * The Xdirection and Ydirection attributes are static because java
 * displayed a strange bug, where there attributes would reset to 
 * default values if they were non static, despite accessing them in a
 * non static way.
 * 
 * @author anonymous
 * @see Bot.java
 * 		Bot class is child of Avatar.
 * @see Player.java
 * 		Player class is child of Avatar.
 * 
*/
public abstract class Avatar {
	
	public static int playerWidth = 40;
	public static int playerHeight = 40;
	public int height = 45;
	public int width = 45;
	protected float X;
	protected float Y;
	protected float speed = 5;
	public String status = "facedown";
	public int score;
	
	protected GameStateManager gsm;

	public static Rectangle render;
	
	public boolean isChanging = false;

	protected BufferedImage spriteSheet = null;
	public static BufferedImage[] p = new BufferedImage[16];

	/**
	 * Method which detects whether an object represented by the parameters
	 * overlaps with the current object's space.
	 * 
	 * @return true/false
	 * 		Returns true if the objects overlap.
	 * 		Returns false if the objects do not overlap.
	 */
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

	/**
	 * Method used to detect whether the object is touching a wall object.
	 * 
	 * @param blocks
	 * 		A variable which contains all elements of the blocks in the current game.
	 * 
	 * @return true/false
	 * 		Returns true if the object of Avatar is touching any walls in the game.
	 * 		Returns false if this is not the case.
	 */
	protected boolean detectTouchingWall(CopyOnWriteArrayList<Block> blocks) {
		for (Block i : blocks) {
			if ((i.wall) && isTouching(i.x, i.y, i.width, i.height))
				return true;
		}
		return false;
	}
	/**
	 * Method used to detect whether the Avatar object is touching a door object.
	 * 
	 * @param blocks
	 * 		A variable which contains all elements of the blocks in the current game.
	 * 
	 * @return true/false
	 * 		Returns true if the object of Avatar is touching any doors in the game.
	 * 		Returns false if this is not the case.
	 */
	protected boolean detectTouchingDoor(CopyOnWriteArrayList<Block> blocks) {
		for (Block i : blocks) {
			if ((i.door) && isTouching(i.x, i.y, i.width, i.height) && i.isVisible)
		        return true;
		}
		return false;
	}
	/**
	 * Method used to detect whether the object is touching a door object which is not visible.
	 * Doors start as being hidden (i.e. not visible) at the start of the room. When enough gold 
	 * is collected they become visible.
	 * 
	 * @param blocks
	 * 		A variable which contains all elements of the blocks in the current game.
	 * 
	 * @return true/false
	 * 		Returns true if the object of Avatar is touching any walls in the game.
	 * 		Returns false if this is not the case.
	 */
	protected boolean detectTouchingHiddenDoor(CopyOnWriteArrayList<Block> blocks) {
		for (Block i : blocks) {
			if ((i.door && !i.isVisible) && isTouching(i.x, i.y, i.width, i.height))
		        return true;
		}
		return false;
	}
	/**
	 * This method calculates the distance between the coordinates passed in.
	 * It is based on the mathematical formula c^2 = a^2 + b^2.
	 * 
	 * @see Bot
	 * 		The current method is called by this class to find the closest player
	 * 		to the bot's location.
	 * @see Player
	 * 		This method is called by this class to find the closest block to the player
	 * 		so the player can be repositioned on the map if he escapes.
	 * 
	 * @return
	 * 		The current method returns the distance between the two variables.
	 */
	protected double calculateDist(double x, double y, float x2, float y2) {
		double X = Math.abs(x - x2);
		double Y = Math.abs(y - y2);
		double dist = Math.pow(X, 2);
		dist += Math.pow(Y, 2);
		return dist;
	}
	/**
	 * This method moves the player coordinates by the 
	 * specified amount in the correct direction.
	 * 
	 * @param distance
	 * 		The distance to move the player.
	 * 
	 * @param direction
	 * 		The direction to move the player.
	 * 
	 * @see getKnocked()
	 * 		Called by this method when the player is knocked by 
	 * 		the bot.
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
