package main;

import java.awt.Graphics;
import java.awt.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import GameStates.GameState;
import GameStates.GameStateManager;

public class HighScoreState extends GameState{
	
	GameStateButton back;
	MouseInput mi;
	
	public ArrayList<String> lines = new ArrayList<String>();
	
	public HighScoreState(GameStateManager gsm) {
		super(gsm);

	}
	
	@Override
	public void init() {
		mi = new MouseInput();
		back = new GameStateButton((Main.width / 2) - (GameStateButton.width / 2), (Main.height - 200), new MenuState(gsm), gsm, "BACK");
		
	
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader("res/score.txt"));
			String line = null;
			while ((line = reader.readLine()) != null) {
				lines.add(line);

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

	@Override
	public void update() {
		mi.update();
		back.update();

	    
	}

	@Override
	public void render(Graphics g) {
		mi.render(g);
		back.render(g);
		int highest = Integer.parseInt(lines.get(0));
		
		for(int i = 1; i < lines.size(); i++){
			if(Integer.parseInt(lines.get(i)) > highest){
				highest = Integer.parseInt(lines.get(i));
			}
		}
		
		g.drawString(String.valueOf(highest), 400, 200);
		
		g.clipRect(0,  0, Main.width, Main.height);
		
	}
	
	public ArrayList<String> getLines(){
		return lines;
	}

}
