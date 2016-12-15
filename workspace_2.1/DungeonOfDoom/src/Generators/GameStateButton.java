package Generators;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;

import Managers.GameState;
import Managers.GameStateManager;
/**
* GameStateButton called when the buttons are clicked on in the MenuState, HighScoreState, QuitState and GameOverState.
* Uses GameState class to specify the new Game State when clicked on the button.
* (Extends Rectangle to use the setBound() function to see if the mouse position is over the button).
*
* @version 1.0
* @release 13/12/2016
* @See GameStateButton.java
*/
public class GameStateButton extends Rectangle{
	/*
	It is wise to declare it because if you don't declare one then when changing 
	the class it will get a different one generated automatically and the 
	serialisation will stop working.
	*/
	private static final long serialVersionUID = 1L;
	//
	private GameState gs;
	//Integer variables, made final to ensure they won't changes.
	private final int xPos;
	private final int yPos;
	public static final int width = 250;
	public static final int height = 60;
	//String to specify the name of the buttons.
	private String buttonMessage;
	//Boolean to see if the Mouse Position is over the Button Position.
	private boolean isHeldOver;
	/**
	 * Constructor. Sets the field values. Used for buttons that will not change state (Exit).
	 * @param xPos
	 * 		Specifies the x position of the button.
	 * @param yPos
	 * 		Specifies the y position of the button.
	 * @param buttonMessage
	 * 		Specifies the name that will appear over the button.
	 */
	public GameStateButton(int xPos, int yPos, String buttonMessage){
		this.xPos = xPos;
		this.yPos = yPos;
		this.buttonMessage = buttonMessage;		
		setBounds(xPos, yPos, width, height);
	}
	/**
	 * Constructor. Sets the field values. Used for buttons that will change state (HighScore, Credits).
	 * @param xPos
	 * 		Specifies the x position of the button.
	 * @param yPos
	 * 		Specifies the y position of the button.
	 * @param gamestate
	 * 		Specifies which game state we would like to change to when the button is clicked on.
	 * @param buttonMessage
	 * 		Specifies the name that will appear over the button.
	 */
	public GameStateButton(int xPos, int yPos, GameState gamestate, String buttonMessage){
		this.gs = gamestate;
		this.xPos = xPos;
		this.yPos = yPos;
		this.buttonMessage = buttonMessage;		
		setBounds(xPos, yPos, width, height);
	}
	/**
	 * Part of GameLoop, Updates the declared classes and fields (60 FPS).
	 * Checks the mouse position and checks if it is over the button and whether it is pressed or not.
	 * @see Managers.GameState#update()
	 */
	public void update(){
		if(MouseInput.mouse != null){
			if(getBounds().contains(MouseInput.mouse)){
				isHeldOver = true;
			} else {
				isHeldOver = false;
			}
		}
		
		if(gs != null){
			if(isHeldOver && isPressed()){
				GameStateManager.states.push(gs);
				GameStateManager.states.peek().init();
				isHeldOver = false;
				MouseInput.pressed = false;
			}
		}
	}
	/**
	 * Part of GameLoop, Sets the graphics for JFrame.
	 * Sets the colour of the button for when the mouse is over the button.
	 * Sets the colour of the button for when the mouse is not over the button.
	 * Draws the buttons on the screen.
	 * @see Managers.GameState#render(java.awt.Graphics)
	 * @param g
	 * The graphics object which is displayed to the screen.
	 */
	public void render(Graphics g){
		Font font = new Font("8BIT WONDER", Font.PLAIN, 20);
		g.setFont(font);
		
		if(isHeldOver == true){
			g.setColor(Color.RED);
			g.drawRect(xPos, yPos, width, height);
		} else {
			g.setColor(Color.GRAY);
			g.drawRect(xPos, yPos, width, height);
		}
		//Centres the graph
		FontMetrics metrics = g.getFontMetrics(font);
		int x = (width - metrics.stringWidth(buttonMessage)) / 2;
		g.drawString(buttonMessage, x + xPos, yPos + height / 2);
	}
	/**
	 * Getter for the isHeldOver boolean.
	 * @return isHeldOver
	 * 		returns isHeldOver boolean depending on its current state.
	 */
	public boolean isHeldOver(){
		return isHeldOver;
	}
	/**
	 * Getter for the isPressed boolean.
	 * @return isPressed
	 * 		returns isPressed boolean depending on its current state.
	 */
	public boolean isPressed(){
		return MouseInput.pressed;
	}
	
}
