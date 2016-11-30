package GameStates;

import java.awt.Graphics;

public abstract class GameState {

	public GameStateManager gsm;
	
	public GameState(GameStateManager gsm) {
		this.gsm = gsm;
	}

	public abstract void init();
	public abstract void update();
	public abstract void render(Graphics g);
	
}
