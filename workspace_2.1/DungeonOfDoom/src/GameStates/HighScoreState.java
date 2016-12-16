package GameStates;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import Generators.GameStateButton;
import Generators.MouseInput;
import Managers.GameState;
import Managers.GameStateManager;
/**
* HighScoreState called when the High Score button is clicked in the Menu State.
* Uses MenuState class to allow the user further interaction
* Uses GameStateButton class to allow the user to change the game states when clicked on the buttons.
* Uses MouseInput class to allow user to use the mouse for interaction.
* (Extends GameState to use the init(), update() and render() functions which connect to the GameLoop and JFrame respectively.)
*
* @author anonymous
* @version 1.0
* @release 13/12/2016
* @See HighScoreState.java
*/
public class HighScoreState extends GameState{
	//Classes Declared
	GameStateButton back;
	MouseInput mi;
	//Array storing the score
	ArrayList<String> linesP1 = new ArrayList<String>();
	ArrayList<String> linesP2 = new ArrayList<String>();
	/**
	 * Constructor. Sets the field values.
	 * @param GameStateManager
	 * 		calls the GameStateManager class
	 */
	public HighScoreState(GameStateManager gsm) {
		super(gsm);

	}
	/**
	 * Part of GameLoop, Initialises the declared classes and fields.
	 * Specifies the GameStates to change to, for each GameStateButton.
	 * Adds score to the array.
	 * @exception IOException
	 * 		calls the exception when the text file is not found
	 * @see Managers.GameState#init()
	 */
	@Override
	public void init() {
		mi = new MouseInput();
		back = new GameStateButton((main.Main.width / 2) - (GameStateButton.width / 2), (main.Main.height - 200), new MenuState(gsm), "BACK");
	
		BufferedReader readerPlayer1 = null;
		BufferedReader readerPlayer2 = null;
		try {
			readerPlayer1 = new BufferedReader(new FileReader("res/scorePlayer1.txt"));
			readerPlayer2 = new BufferedReader(new FileReader("res/scorePlayer2.txt"));
			String lineP1 = null;
			String lineP2 = null;
			while ((lineP1 = readerPlayer1.readLine()) != null) {
				linesP1.add(lineP1);
			}
			while ((lineP2 = readerPlayer2.readLine()) != null) {
				linesP2.add(lineP2);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	/**
	 * Part of GameLoop, Updates the declared classes and fields (60 FPS)
	 * Updates the mouse position and keep track of the GameStateButton
	 * @see Managers.GameState#update()
	 */
	@Override
	public void update() {
		mi.update();
		back.update();

	    
	}
	/**
	 * Part of GameLoop, Sets the graphics for JFrame.
	 * Draws the buttons on the Screen.
	 * Draws the highest score on the Screen. 
	 * Draws the mouse on the screen.
	 * @see Managers.GameState#render(java.awt.Graphics)
	 * @param g
		 * 	The graphics object which is displayed to the screen.
	 */
	@Override
	public void render(Graphics g) {
		//Declares integers
		int highestP1 = 0;
		int highestP2 = 0;
		//Loads new Font
		Font font = new Font("8BIT WONDER", Font.PLAIN, 20);
		g.setFont(font);
		
		for(int i = 1; i < linesP1.size(); i++){
			if(Integer.parseInt(linesP1.get(i)) > highestP1){
				highestP1 = Integer.parseInt(linesP1.get(i));
			}
		}
		
		for(int i = 1; i < linesP2.size(); i++){
			if(Integer.parseInt(linesP2.get(i)) > highestP2){
				highestP2 = Integer.parseInt(linesP2.get(i));
			}
		}
		g.setColor(Color.GRAY);
		if(!linesP1.isEmpty()){
			g.drawString("Player 1 Score", 300, 200);
			g.drawString(String.valueOf(highestP1), 400, 300);
		} else {
			g.drawString("No Score Yet", 300, 300);
		}
		
		if(!linesP2.isEmpty()){
			g.drawString("Player 2 Score", 800, 200);
			g.drawString(String.valueOf(highestP2), 900, 300);
		} else {
			g.drawString("No Score Yet", 800, 300);
		}
		
		
		g.clipRect(0,  0, main.Main.width, main.Main.height);
		
		mi.render(g);
		back.render(g);
	}
}
