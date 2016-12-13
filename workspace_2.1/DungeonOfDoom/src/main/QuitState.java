package main;
import java.awt.Graphics;
import GameStates.GameState;
import GameStates.GameStateManager;
/**
* QuitState called when the Quit button is clicked on in the MenuState.
* Uses GameStateButton class to allow the user to change the game states when clicked on the buttons.
* Uses MouseInput class to allow user to use the mouse for interaction.
* (Extends GameState to use the init(), update() and render() functions which connect to the GameLoop and JFrame respectively.)
*
* @version 1.0
* @release 13/12/2016
* @See QuitState.java
*/
public class QuitState extends GameState{
	//Classes Declared
	GameStateButton yes;
	GameStateButton no;
	MouseInput mi;
	/**
	 * Constructor. Sets the field values.
	 * @param GameStateManager
	 * 		calls the GameStateManager class
	 */
	public QuitState(GameStateManager gsm) {
		super(gsm);
	}
	/**
	 * Part of GameLoop, Initialises the declared classes and fields.
	 * Specifies the GameStates to change to, for each GameStateButton.
	 * @see GameStates.GameState#init()
	 */
	@Override
	public void init() {
		mi = new MouseInput();
		yes = new GameStateButton(300, 200, "Yes");
		no = new GameStateButton((Main.width  - GameStateButton.width) - 300, 200, new MenuState(gsm), "No");
	}
	/**
	 * Part of GameLoop, Updates the declared classes and fields (60 FPS).
	 * Keeps track of mouse position.
	 * @see GameStates.GameState#update()
	 */
	@Override
	public void update() {
		mi.update();
		yes.update();
		no.update();
		
		if(yes.isHeldOver()){
			if(yes.isPressed()){
				System.exit(1);
			}
		}
	}
	/**
	 * Part of GameLoop, Sets the graphics for JFrame.
	 * Draws the buttons on the Screen.
	 * @see GameStates.GameState#render(java.awt.Graphics)
	 */
	@Override
	public void render(Graphics g) {
		mi.render(g);
		yes.render(g);
		no.render(g);
		g.clipRect(0,  0, Main.width, Main.height);
	}

}