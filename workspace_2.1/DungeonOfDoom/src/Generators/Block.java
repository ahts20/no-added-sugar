package Generators;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import MovableObjects.Player;
/**
* Block class draws the graphics on blocks wherever specified 
* Uses GameState class to use the implemented methods init(), update(), render()
* Is called in World which contains the generate() method to draw the blocks depending on the RGB values
* (Extends Rectangle to use the setBound() function to keep track of the each individual block position).
*
* @version 1.0
* @release 14/12/2016
* @See Block.java
*/
public class Block extends Rectangle {
	/*
	It is wise to declare it because if you don't declare one then when changing 
	the class it will get a different one generated automatically and the 
	serialisation will stop working.
	*/
	private static final long serialVersionUID = 1L;
	//Declared BlockType object
	private BlockType blocktype;
	//Declared Integers
	public int x, y;
	public int width;
	public int height;
	//Declared booleans
	public boolean isVisible;
	public boolean rectangle, wall, gold, door;
	private boolean isSolid;
	/**
	 * Constructor. Sets the field values. Used to specify the position of blocks
	 * @param x
	 * 		Specifies the x position of the block
	 * @param y
	 * 		Specifies the y position of the block
	 */
	public Block(int x, int y) {
		setBounds((int) x, (int) y, width, height);
		this.x = x;
		this.y = y;
	}
	/**
	 * Constructor. Sets the field values. 
	 * Used to specify the position of blocks, the size of blocks and the object type
	 * @param x
	 * 		Specifies the x position of the block
	 * @param y
	 * 		Specifies the y position of the block
	 * @param blockSize
	 * 		Specifies the size of the block
	 * @param blocktype
	 * 		Specifies the object used for each individual block (Wall, Gold, Ground)
	 */
	public Block(int x, int y, int blockSize, BlockType blocktype) {
		setBounds((int) x, (int) y, width, height);
		this.x = x;
		this.y = y;
		this.width = blockSize;
		this.height = blockSize;
		this.blocktype = blocktype;
		init();
	}
	/**
	 * Initialises the objects declared in the constructor 
	 * Called in World class to initialise the said objects
	 */
	public void init() {
		switch (blocktype) {
		case RECTANGLE:
			rectangle = true;
			break;
		case WALL:
			wall = true;
			break;
		case GOLD:
			gold = true;
			break;
		case DOOR:
			door = true;
			isVisible = false;
			break;
		}
	}
	/**
	 * Updates and keeps track of the bounds of the blocks
	 * Called in World class to update the bounds
	 */
	public void update() {
		setBounds((int) x, (int) y, width, height);
	}
	/**
	 * Renders the graphics and draws the image on the block depending on the object specified (rectangle)
	 * Called in the World class to render the images
	 * @param g
	 * 		The graphics object which is displayed to the screen.
	 */
	public void render(Graphics g) {
		if (rectangle == true) {
			g.drawImage(Player.p[10], (int) x, (int) y, width, height, null);
		}

		if (wall == true) {
			g.drawImage(Player.p[9], (int) x, (int) y, width, height, null);
		}
		if (gold == true) {
			g.drawImage(Player.p[8], (int) x, (int) y, width, height, null);
		}
		if (door == true && isVisible == false) {
			g.setColor(Color.WHITE);
			g.drawImage(Player.p[9], (int) x, (int) y, width, height, null);
		}
		if (door == true && isVisible == true) {
			g.drawImage(Player.p[10], (int) x, (int) y, width, height, null);
		}

	}
	/**
	 * changeGoldToFloor() method changes the gold image to floor image after gold is collected
	 * Uses the objects to change the image
	 */
	public void changeGoldToFloor() {
		// Changes block type to floor - e.g. when it was gold and gets picked
		// up.
		this.rectangle = true;
		this.gold = false;
		this.wall = false;
	}	
	/**
	 * Declares the object
	 * @author anonymous
	 */
	public enum BlockType {
		RECTANGLE, WALL, GOLD, DOOR
	}
	/**
	 * Getter for isSolid boolean 
	 */
	public boolean isSolid() {
		return isSolid;
	}
	/**
	 * Setter for isSolid boolean
	 * @param isSolid
	 * 		the boolean to change
	 * @return
	 * 		returns the isSolid boolean
	 */
	public Block isSolid(boolean isSolid){
		this.isSolid = isSolid;
		return this;
	}
	

}
