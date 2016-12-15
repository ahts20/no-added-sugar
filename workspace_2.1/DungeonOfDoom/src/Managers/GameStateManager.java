package Managers;
import java.awt.Graphics;
import java.util.Stack;
/**
* GameStateManager uses stack to push the desired game state to the top of the stack to be used
* Uses GameState class for the implemented methods init(), update(), render()
* GameStateManager is used in the GameLoop for the implemented GameState methods to be updated in the Loop
*
* @version 1.0
* @release 14/12/2016
* @See GameStateManager.java
*/
public class GameStateManager {
	// Stack allows you to push states
	public static Stack<GameState> states;
	/**
	 * Constructor. Sets the field values.
	 * Initialises the Stack
	 * Pushes the initial game state (MenuState) on top of the stack 
	 */
	public GameStateManager() {
		states = new Stack<GameState>();
		states.push(new GameStates.MenuState(this));
	}
	/**
	 * Updates the game state on top of the stack
	 * Called in Game Loop to be updated (60 FPS)
	 */
	public void update() {
		states.peek().update();
	}
	/**
	 * Renders the game graphics of the game state on top of the stack
	 * Called in Game Loop to be updated (60 FPS)
	 * @param g
	 * 		The graphics object which is displayed to the screen.
	 */
	public void render(Graphics g) {
		states.peek().render(g);
	}
	/**
	 * Initialises the game state on top of the stack 
	 * Initialises the declared classes and fields of the game state on top of the stack
	 */
	public void init() {
		states.peek().init();
	}
}