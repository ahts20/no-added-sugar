package Generators;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import Generators.Block.BlockType;
import Managers.GameStateManager;
import MovableObjects.Bot;
import MovableObjects.Player;
/**
 * World class is called in the LevelLoader when the LevelLoader state is pushed on top of the stack.
 * Uses Player, Bot class to render and update the movable objects.
 * Uses Block class to create new Array to store the blocks and to call them in generate() function. 
 * Uses loadImage class to load the map images to be used in generate() method.
 * 
 * @author anonymous
 * @version 1.0
 * @release 15/12/2016
 * @See World.java
 */
public class World{
	//Declared classes
	//Made public for JUnit testing
	public Player player1, player2;
	public Bot bot;
	public Block block;
	private static loadImage loader;
	
	@SuppressWarnings("unused")
	private GameStateManager gsm;
	//Arrays holding the score collected by Players
	private static ArrayList<Integer> linesP1 = new ArrayList<Integer>();
	private static ArrayList<Integer> linesP2 = new ArrayList<Integer>();
	//Array holding the blocks loaded in generate() function
	public static CopyOnWriteArrayList<Block> blocks = new CopyOnWriteArrayList<Block>();
	//Booleans to check when to add score into array 
	private boolean change = false;
	private boolean changeP2 = false;	
	//Used by enoughGoldPickedUp() to know how much gold was originally in place.
	private int totalGold;
	//Map image to use as template to load the Block image onto
	//Uses the RGB values of the image to do so
	private static BufferedImage map;
	/**
	 * Constructor. Sets the field values.
	 * @param gsm
	 * 		calls the GameStateManager class
	 */
	public World(GameStateManager gsm){
		this.gsm = gsm;
	}
	/**
	 * Initialises loadImage, Player and Bot
	 * Used in LevelLoader to be initialised when LevelLoader is pushed on top of the stack in GameStateManager
	 */
	public void init() {
		loader = new loadImage();	

		player2 = new MovableObjects.Player2();
		player1 = new MovableObjects.Player1();
		
		
		player2.init(500,600);
		player1.init(300, 600);


		bot = new Bot();
		bot.init(player1, player2, 400, 200);
	}
	/**
	 * Updates Player and Bot (60FPS)
	 * Used in LevelLoader to be updated when LevelLoader is called
	 * Uses the arrays holding the score collected by Players (linesP1, linesP2)
	 * @see checkGoldTakenAndOpenDoor()
	 * 		Checks if enough gold has been picked up (50%) and opens the door to next Map
	 * @see savePlayer1Score()
	 * 		Saves Player1 score each time the Player1 leaves the Map (Back End)
	 * @see savePlayer2Score()
	 * 		Saves Player2 score each time the Player2 leaves the Map (Back End)
	 */
	public void update() {
		player1.update(blocks);
		player2.update(blocks);
		checkGoldTakenAndOpenDoor();

		bot.update(blocks);
		savePlayer1Score();
		savePlayer2Score();
		Player.touching = false;
		
		if(!linesP1.isEmpty() && change == false){
			player1.setScore(linesP1.get(linesP1.size()-1));
			change = true;
		}
		
		if(!linesP2.isEmpty() && changeP2 == false){
			player2.setScore(linesP2.get(linesP2.size()-1));
			changeP2 = true;
		}
	}
	/**
	 * Checks if enough gold has been picked up (50%) and opens the door to the next Map
	 * Uses Block class to check for the Block objects
	 * @see enoughGoldPickedUp()
	 * 		Checks how much gold has been collected, if more than 50% has been collected returns true
	 */
	private void checkGoldTakenAndOpenDoor(){
		if (enoughGoldPickedUp()) {
			for (Block i : blocks) {
				if (i.door)
					i.isVisible = true;
			}
		}
	}
	/**
	 * Checks how much gold has been collected
	 * @see returnCurrentGold
	 * 		Checks how much gold is on the screen, returns an integer number of the amount
	 * @return
	 * 		if more than 50% of gold has been collected returns true
	 */
	private boolean enoughGoldPickedUp() {
		int minimumPercentToOpenDoor = 50;
		int currentGold = returnCurrentGold();
		if(currentGold == 0)
			return true;
		return (Math.abs((currentGold/(float) this.totalGold)*100) < minimumPercentToOpenDoor);
	}
	/**
	 * Checks how much gold is on the screen
	 * @return
	 * 		returns an integer number of the amount of gold
	 */
	private int returnCurrentGold() {
		int gold = 0;
		for (Block i : blocks) {
			if (i.gold == true)
				gold ++;
		}
		return gold;
	}
	/**
	 * Renders Player, Bot and Block on the screen
	 * Used in LevelLoader to be rendered when LevelLoader is called
	 * Uses the array holding the blocks loaded in generate() method
	 * @param g
	 * 		The graphics object which is displayed to the screen.
	 */
	public void render(Graphics g) {
		for (Block i : blocks) {
			i.render(g);
		}

		player1.render(g);
		player2.render(g);
		
		bot.render(g);
	}
	/**
	 * Generates the map on the screen
	 * Uses the RGB values of the map image loaded to specify where the blocks will be loaded (0x808080)
	 * @param world_name
	 * 		Specifies the name of the map to be loaded
	 * @see loader.LoadImageFrom()
	 * 		Loads an image with the name specified in the parameter (world_name)
	 */
	public void generate(String world_name) {
		// Generates the world from the map PNG image.
		map = null;
		// Syntactic sugarrrr - match with block height and width to avoid gaps.
		int blockSize = 26;
		try {
			map = loader.LoadImageFrom("/" + world_name + ".png");
		} catch (Exception e) {
			e.printStackTrace();
		}
		// Iterate through each pixel in the image.
		for (int x = 0; x < 50; x++) {
			for (int y = 0; y < 30; y++) {
				int mapColours = map.getRGB(x, y);

				switch (mapColours & 0xFFFFFF) {
				// If Grey set as floor/RECTANGLE
				case 0x808080:
					blocks.add(new Block(x * blockSize, y * blockSize, blockSize, BlockType.RECTANGLE));
					break;
				// If Black set as Wall.
				case 0x000000:
					blocks.add(new Block(x * blockSize, y * blockSize, blockSize, BlockType.WALL));
					break;
				// If Yellow set as Gold.
				case 0xffff00:
					blocks.add(new Block(x * blockSize, y * blockSize, blockSize, BlockType.GOLD));
					break;
				// If Blue set as Door.
				case 0x0080FF:
					blocks.add(new Block(x * blockSize, y * blockSize, blockSize, BlockType.DOOR));
					break;
				}
			}
		}
		//Save total gold as an attribute, so it can be used to 
		//detect is enough gold is collected.
		this.totalGold = returnCurrentGold();
	}
	/**
	 * Used when the next Map is loaded to clear the array holding the blocks loaded in generate() method
	 */
	public static void resetWorld(){
		blocks.clear();		
	}
	/**
	 * Saves Player1 score each time the Player1 leaves the Map (Back End)
	 * Uses in built functions FileWriter, BufferedWriter and PrintWriter to access the text file holding the score
	 * Uses the array holding the score collected by Player1 to update the score
	 */
	@SuppressWarnings("static-access")
	public void savePlayer1Score(){
		if(player1.touching == true){
			try(FileWriter fw = new FileWriter("res/scorePlayer1.txt", true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter out = new PrintWriter(bw))
			{
		out.println(player1.score);

		linesP1.add(player1.score);
		change = false;
		
		out.close();
			} catch (Exception e){
				e.printStackTrace();
			}			
		}
	}
	/**
	 * Saves Player2 score each time the Player2 leaves the Map (Back End)
	 * Uses in built functions FileWriter, BufferedWriter and PrintWriter to access the text file holding the score
	 * Uses the array holding the score collected by Player2 to update the score
	 */
	@SuppressWarnings("static-access")
	public void savePlayer2Score(){
		if(player2.touching == true){
			try(FileWriter fw = new FileWriter("res/scorePlayer2.txt", true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter out = new PrintWriter(bw))
			{
		out.println(player2.score);

		linesP2.add(player2.score);
		changeP2 = false;
		
		out.close();
			} catch (Exception e){
				e.printStackTrace();
			}
		}
	}
	
}
