package GameStates;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import Generators.GameStateButton;
import Generators.MouseInput;
import Generators.loadImage;
import main.Main;
import Managers.GameState;
import Managers.GameStateManager;
/**
* MenuState class, called when the game is started.
* Uses HighScoreState and QuitState classes to give user further interaction.
* Uses GameStateButton class to allow the user to change the game states when clicked on the buttons.
* Uses MouseInput class to allow user to use the mouse for interaction.
* (Extends GameState to use the init(), update() and render() functions which connect to the GameLoop and JFrame respectively.)
*
* @version 1.0
* @release 13/12/2016
* @See MenuState.java
*/
public class MenuState extends GameState {
	//Classes Declared
	GameStateButton play;
	GameStateButton quit;
	GameStateButton highScore;
	MouseInput mi;
	loadImage loader;
	//Header Image
	BufferedImage backTitle,logo;
	/**
	 * Constructor. Sets the field values.
	 * @param gsm
	 * 		calls the GameStateManager class
	 */
	public MenuState(GameStateManager gsm) {
		super(gsm);
	}
	/**
	 * Part of GameLoop, Initialises the declared classes and fields.
	 * Specifies the GameStates to change to, for each GameStateButton.
	 * @exception IOException
	 * 		calls the exception when no header image found
	 * @see Managers.GameState#init()
	 */
	@Override
	public void init() {
		mi = new MouseInput();
		
		loader = new loadImage();
		try {
			backTitle = loader.LoadImageFrom("/dooomFINAL.png");
			logo = loader.LoadImageFrom("/LOGO1.png");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		play = new GameStateButton((Main.width / 2) - (GameStateButton.width / 2), 200, new LevelLoader(gsm), "PLAY");
		highScore = new GameStateButton((Main.width / 2) - (GameStateButton.width / 2), 350, new HighScoreState(gsm), "HIGH SCORE");
		quit = new GameStateButton((Main.width / 2) - (GameStateButton.width / 2), 500, new QuitState(gsm), "QUIT");
		
	}
	/**
	 * Part of GameLoop, Updates the declared classes and fields (60 FPS)
	 * Keeps track of mouse position
	 * @see Managers.GameState#update()
	 */
	@Override
	public void update() {
		mi.update();
		play.update();
		highScore.update();
		quit.update();
		
		if(quit.isHeldOver()){
			if(quit.isPressed()){
				System.exit(1);
			}
		}
		
	}
	/**
	 * Part of GameLoop, Sets the graphics for JFrame
	 * Draws the buttons on the Screen and sets their colour
	 * Draws the mouse on the screen.
	 * @see Managers.GameState#render(java.awt.Graphics)
	 * @param g
	 * 		The graphics object which is displayed to the screen.
	 */
	@Override
	public void render(Graphics g) {
		//Loads new Font
		Font font1 = new Font("Bloodthirsty", Font.PLAIN, 20);
		
		g.setColor(Color.pink);
		g.setFont(font1);
		g.drawString("Long long ago, there was a poor city.", Main.width-420, 200);
		g.drawString("There was a dungeon outside the city, with", Main.width-420, 225);
		g.drawString("countless treasures inside. Anyone who tried", Main.width-420, 250);
		g.drawString("to steal from it were never seen again...", Main.width-420, 275);
		//g.drawString("steal from it was never seen again...", Main.width-420, 300);
		g.drawString("Although...", Main.width-420, 325);
		g.drawString("The Yorik brothers decided to try their luck", Main.width-420, 350);
		g.drawString("in this dungeon of wealth. Despite the rumours", Main.width-420, 375);
		g.drawString("of a gostly guard.", Main.width-420, 400);
		g.drawString("This is a cooperative game!", Main.width-420, 450);
		g.drawString("Player1 use 'wasd' to control directions;", Main.width-420, 500);

		g.drawString("Player2 uses 'arrows' to control directions;", Main.width-420, 525);
		g.drawString("If you and your buddy collect 50% golden", Main.width-420, 550);
		g.drawString(" sword, the stone door will be opened that", Main.width-420, 575);
		g.drawString("leads you to the next room. On the third and", Main.width-420, 600);
		g.drawString("final room you can exit the final door to", Main.width-420, 625);
		g.drawString("win the game.", Main.width-420, 650);
		g.drawString("By the Way, avoid the ghost, it will hurt you!", Main.width-420, 675);

		g.setColor(Color.white);
		g.drawImage(backTitle, Main.width/4, 30, null);
		g.drawImage(logo, Main.width/4+240, Main.height-150, null);
		
		play.render(g);
		highScore.render(g);
		quit.render(g);
		g.clipRect(0,  0, Main.width, Main.height);
	}
}
