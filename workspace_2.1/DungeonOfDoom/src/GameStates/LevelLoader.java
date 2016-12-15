package GameStates;
import java.awt.Graphics;

import Generators.World;
import Managers.GameState;
import Managers.GameStateManager;
import MovableObjects.Player;
/**
* LevelLoader called when the Play button is clicked on in the MenuState.
* Uses the World class to initialise all the methods needed to play the game
* (Extends GameState to use the init(), update() and render() functions which connect to the GameLoop and JFrame respectively.)
*
* @version 1.0
* @release 13/12/2016
* @See LevelLoader.java
*/
public class LevelLoader extends GameState{
	//Classes Declared
	public static World world;
	public static Player player;
	//Strings declared used to load the appropriate map
	private String worldName;
	private String map_name;
	/**
	 * Constructor 1. Sets the field values. Used when play is pressed in MenuState
	 * @param GameStateManager
	 * 		calls the GameStateManager class
	 */
	public LevelLoader(GameStateManager gsm){
		super(gsm);
	}
	/**
	 * Constructor 2. Sets the field values.
	 * @param GameStateManager
	 * 		calls the GameStateManager class
	 * @param worldName
	 * 		worldName String variable specifying the name of the world
	 * @param map_name
	 * 		map_name String variable specifying which map to load
	 */
	public LevelLoader(GameStateManager gsm, String worldName, String map_name) {
		super(gsm);
		this.worldName = worldName;
		this.map_name = map_name;
	}	
	/**
	 * Part of GameLoop, Initialises the declared classes and fields.
	 * Initialises the World class and generates the map 
	 * @see Managers.GameState#init()
	 * @see World.generate()
	 */
	@Override
	public void init() {
		world = new World(gsm);
		world.init();
		
		if(worldName == null){
			worldName = "null";
			map_name = "map";
		} 
		world.generate(map_name);
	}
	/** 
	 * Part of GameLoop, Updates the declared classes and fields (60 FPS).
	 * @see Managers.GameState#update()
	 */
	@Override
	public void update() {
		world.update();
	}
	/**
	 * Part of GameLoop, Sets the graphics for JFrame.
	 * Initialises the world graphics
	 * @see Managers.GameState#render(java.awt.Graphics)
	 * @param g
 	 * 		The graphics object which is displayed to the screen.
	 */
	@Override
	public void render(Graphics g) {
		world.render(g);
	}
}
