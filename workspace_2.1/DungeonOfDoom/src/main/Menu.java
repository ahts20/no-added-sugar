package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JFrame;

import GameStates.GameState;
import GameStates.GameStateManager;

public class Menu extends GameState {

	GameStateButton play;
	GameStateButton quit;
	MouseInput mi;
	
	public Menu(GameStateManager gsm) {
		super(gsm);
	}

	@Override
	public void init() {
		mi = new MouseInput();
		play = new GameStateButton(Main.width / 3 + 150, 150, new LevelLoader(gsm), gsm, "PLAY");
		quit = new GameStateButton(Main.width / 3 + 150, 350, new LevelLoader(gsm), gsm, "QUIT");
	}


	@Override
	public void update() {
		mi.update();
		play.update();
		quit.update();
	}


	@Override
	public void render(Graphics g) {
		Font fnt0 = new Font("arial", Font.BOLD, 50);
		g.setFont(fnt0);
		g.setColor(Color.white);
		g.drawString("Dungeon Of Dooom", Main.width/3, 100);
		play.render(g);
		quit.render(g);
		g.clipRect(0,  0, Main.width, Main.height);
	}
}
