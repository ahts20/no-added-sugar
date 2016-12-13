package main;
import java.awt.Graphics;
import GameStates.GameState;
import GameStates.GameStateManager;

public class QuitState extends GameState{

	GameStateButton yes;
	GameStateButton no;
	MouseInput mi;

	public QuitState(GameStateManager gsm) {
		super(gsm);
	}

	@Override
	public void init() {
		mi = new MouseInput();
		yes = new GameStateButton(300, 200, "Yes");
		no = new GameStateButton((Main.width  - GameStateButton.width) - 300, 200, new MenuState(gsm), gsm, "No");
	}

	@Override
	public void update() {
		mi.update();
		yes.update();
		no.update();
		
		if(yes.isHeldOver()){
			if(yes.isPressed()){
				System.exit(1);
			}
		}
	}
	

	@Override
	public void render(Graphics g) {
		mi.render(g);
		yes.render(g);
		no.render(g);
		g.clipRect(0,  0, Main.width, Main.height);
	}

}