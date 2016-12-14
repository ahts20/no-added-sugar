package GameStates;
import java.awt.Graphics;
/**
 * Abstract class to be extended in the game states 
 * Specifies the implemented methods init(), update(), render() extended in the game states
 * Specifies the constructor to be extended in the game states 
 * Manages the stack used in the GameStateManger to push the states used to the top 
 * 
 * @author anonymous
 * @version 1.0
 * @release 14/12/2016
 * @See GameState.java
 */
public abstract class GameState {
	//Declared classes
	public GameStateManager gsm;
	/**
	 * Constructor. Sets the field values.
	 * @param gsm
	 * 		calls the GameStateManager class
	 */
	public GameState(GameStateManager gsm) {
		this.gsm = gsm;
	}
	
	public abstract void init();

	public abstract void update();

	public abstract void render(Graphics g);

}
