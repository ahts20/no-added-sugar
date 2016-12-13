package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.net.MalformedURLException;
import java.net.URL;

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
	public static final int width = 250;
	public static final int height = 60;
	private String buttonMessage;
	private boolean isHeldOver;
	
	public GameStateButton(int xPos, int yPos, String buttonMessage){
		this.xPos = xPos;
		this.yPos = yPos;
		this.buttonMessage = buttonMessage;		
		setBounds(xPos, yPos, width, height);
	}
	
	
	public GameStateButton(int xPos, int yPos, GameState gamestate, GameStateManager gsm, String buttonMessage){
		this.gs = gamestate;
		this.gsm = gsm;
		this.xPos = xPos;
		this.yPos = yPos;
		this.buttonMessage = buttonMessage;		
		setBounds(xPos, yPos, width, height);
	}
	
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
				gsm.states.push(gs);
				gsm.states.peek().init();
				isHeldOver = false;
				MouseInput.pressed = false;
			}
		}
	}
	
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
		FontMetrics metrics = g.getFontMetrics(font);
		int x = (width - metrics.stringWidth(buttonMessage)) / 2;
		g.drawString(buttonMessage, x + xPos, yPos + height / 2);
	}
	
	public boolean isHeldOver(){
		return isHeldOver;
	}
	
	public boolean isPressed(){
		return MouseInput.pressed;
	}
	
}
