package main;

import java.awt.Graphics;
import GameStates.GameState;
import GameStates.GameStateManager;

public class Menu extends GameState {
	
	GameStateButton play;
	GameStateButton james;
	MouseInput mi;
	
	public Menu(GameStateManager gsm) {
		super(gsm);
	}

	@Override
	public void init() {
		mi = new MouseInput();
		play = new GameStateButton(Main.width / 2 - play.width / 2, Main.height / 2 - play.height / 2, new LevelLoader(gsm), gsm, "PLAY");
	}


	@Override
	public void update() {
		mi.update();
		play.update();
		
	}


	@Override
	public void render(Graphics g) {
		play.render(g);
		g.clipRect(0,  0, Main.width, Main.height);
	}
}
