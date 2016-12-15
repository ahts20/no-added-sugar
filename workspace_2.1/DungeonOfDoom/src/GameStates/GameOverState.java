package GameStates;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import Generators.GameStateButton;
import Generators.MouseInput;
import main.Main;
import Managers.GameState;
import Managers.GameStateManager;
/**
* GameOverState class called when the game is started.
* Uses QuitState classes to give user further interaction.
* Uses GameStateButton class to allow the user to change the game states when clicked on the buttons.
* Uses MouseInput class to allow user to use the mouse for interaction.
* (Extends GameState to use the init(), update() and render() functions which connect to the GameLoop and JFrame respectively.)
*
* @version 1.0
* @release 14/12/2016
* @See GameOverState.java
*/
public class GameOverState extends GameState {
	//Classes declared
	GameStateButton quit;
	MouseInput mi;
	//String messages declared
	String message = "Contgratulations";
	String message2 = "Please Try Again";
	String message3 = "And Do Not Forget To View Your HighScore";
	/**
	 * Constructor. Sets the field values.
	 * @param GameStateManager
	 * 		calls the GameStateManager class
	 */
	public GameOverState(GameStateManager gsm) {
		super(gsm);
	}
	/**
	 * Part of GameLoop, Initialises the declared classes and fields.
	 * Specifies the GameStates to change to, for the quit GameStateButton.
	 * @see Managers.GameState#init()
	 */
	@Override
	public void init() {
		mi = new MouseInput();
		quit = new GameStateButton((main.Main.width / 2) - (GameStateButton.width / 2), main.Main.height - 150, new QuitState(gsm), "QUIT");
		
	}
	/**
	 * Part of GameLoop, Updates the declared classes and fields (60 FPS)
	 * Updates the mouse position and keep track of the GameStateButton
	 * @see Managers.GameState#update()
	 */
	@Override
	public void update() {
		mi.update();
		quit.update();

	}
	/**
	 * Part of GameLoop, Sets the graphics for JFrame.
	 * Draws the buttons on the Screen.
	 * Draws the mouse on the screen.
	 * @see Managers.GameState#render(java.awt.Graphics)
	 * @param g
		 * 	The graphics object which is displayed to the screen.
	 */
	@Override
	public void render(Graphics g) {
		//Loads new Font
		Font font = new Font("8BIT WONDER", Font.PLAIN, 30);
		g.setFont(font);
		//Centres the Font
		FontMetrics metrics = g.getFontMetrics(font);
		int x1 = (600 - metrics.stringWidth(message)) / 2;
		int x2 = (500 - metrics.stringWidth(message2)) / 2;
		int x3 = (200 - metrics.stringWidth(message3)) / 2;
		g.setColor(Color.RED);
		g.drawString(message, x1 + (main.Main.width / 2) - 300, 200);
		g.setColor(Color.ORANGE);
		g.drawString(message2, x2 + (main.Main.width / 2) - 250, 350);
		g.setColor(Color.WHITE);
		g.drawString(message3, x3 + (main.Main.width / 2) - 100, 500);
		
		quit.render(g);
		g.clipRect(0,  0, main.Main.width, main.Main.height);
		
	}

}
