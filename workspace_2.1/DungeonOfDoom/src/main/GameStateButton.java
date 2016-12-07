package main;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import GameStates.GameState;
import GameStates.GameStateManager;

public class GameStateButton extends Rectangle{
	/*
	It is wise to declare it because if you don't declare one then when changing 
	the class it will get a different one generated automatically and the 
	serialisation will stop working.
	*/
	private static final long serialVersionUID = 1L;
	
	private GameStateManager gsm;
	private GameState gs;
	private int xPos;
	private int yPos;
	public static int width = 100;
	public static int height = 50;
	private String buttonMessage;
	private boolean isHeldOver;
	
	public GameStateButton(int xPos, int yPos, GameState gamestate, GameStateManager gsm, String buttonMessage){
		this.gs = gamestate;
		this.gsm = gsm;
		this.xPos = xPos;
		this.yPos = yPos;
		this.buttonMessage = buttonMessage;		
		setBounds(xPos, yPos, width, height);
	}
	
	public void update(){
		setBounds(xPos, yPos, width, height);
		if(MouseInput.mouse != null){
			if(getBounds().contains(MouseInput.mouse)){
				isHeldOver = true;
			} else {
				isHeldOver = false;
			}
		}
		
		if(gs != null){
			if(isHeldOver && isPressed()){
				gsm.states.push(gs);
				gsm.states.peek().init();
				isHeldOver = false;
				MouseInput.pressed = false;
			}
		}
	}
	
	public void render(Graphics g){
		Font fnt1 = new Font("arial", Font.BOLD, 30);
		g.setFont(fnt1);
		g.drawRect(xPos, yPos, width, height);
		g.drawString(buttonMessage, xPos + 20 , yPos + 30);
	}
	
	public boolean isHeldOver(){
		return isHeldOver;
	}
	
	public boolean isPressed(){
		return MouseInput.pressed;
	}
	
}
