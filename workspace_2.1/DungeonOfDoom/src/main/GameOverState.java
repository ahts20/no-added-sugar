package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import GameStates.GameState;
import GameStates.GameStateManager;

public class GameOverState extends GameState {
	//Classes declared
	GameStateButton quit;
	MouseInput mi;
	//String messages declared
	String message = "Contgratulations";
	String message2 = "Please Try Again";
	String message3 = "And Do Not Forget To View Your HighScore";
	public GameOverState(GameStateManager gsm) {
		super(gsm);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init() {
		mi = new MouseInput();
		quit = new GameStateButton((Main.width / 2) - (GameStateButton.width / 2), Main.height - 150, new QuitState(gsm), "QUIT");
		
	}

	@Override
	public void update() {
		mi.update();
		quit.update();

	}

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
		g.drawString(message, x1 + (Main.width / 2) - 300, 200);
		g.setColor(Color.ORANGE);
		g.drawString(message2, x2 + (Main.width / 2) - 250, 350);
		g.setColor(Color.WHITE);
		g.drawString(message3, x3 + (Main.width / 2) - 100, 500);
		
		quit.render(g);
		g.clipRect(0,  0, Main.width, Main.height);
		
	}

}
