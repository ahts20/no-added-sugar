package main;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import GameStates.GameState;
import GameStates.GameStateManager;

public class MenuState extends GameState {
	
	GameStateButton play;
	GameStateButton quit;
	MouseInput mi;
	loadImage loader;
	BufferedImage backTitle;
	
	public MenuState(GameStateManager gsm) {
		super(gsm);
	}

	@Override
	public void init() {
		mi = new MouseInput();
		loader = new loadImage();
		try {
			backTitle = loader.LoadImageFrom("/dooomFINAL.png");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		play = new GameStateButton(Main.width / 3 +150, 200, new LevelLoader(gsm), gsm, "PLAY");
		quit = new GameStateButton(Main.width / 3 +150, 400, new LevelLoader(gsm), gsm, "QUIT");
	}

	@Override
	public void update() {
		mi.update();
		play.update();
		quit.update();
		
	}

	@Override
	public void render(Graphics g) {
//		Font fnt0 = new Font("arial", Font.BOLD, 50);
//		g.setFont(fnt0);
		g.setColor(Color.white);
		g.drawImage(backTitle, Main.width/4, 30, null);
//		g.drawString("Dungeon Of Dooom", Main.width/3, 100);
		Font fnt1 = new Font("arial", Font.BOLD, 20);
		g.setFont(fnt1);
		play.render(g);
		quit.render(g);
		g.clipRect(0,  0, Main.width, Main.height);
	}
}
