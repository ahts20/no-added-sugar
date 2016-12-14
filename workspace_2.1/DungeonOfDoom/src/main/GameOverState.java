package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import GameStates.GameState;
import GameStates.GameStateManager;

public class GameOverState extends GameState {

	public GameOverState(GameStateManager gsm) {
		super(gsm);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		Font font1 = new Font("Bloodthirsty", Font.PLAIN, 40);
		g.setColor(Color.pink);
		g.setFont(font1);
		g.drawString("!!! CHICKEN IN TROUSERS !!! ", 100, 100);
		
	}

}
