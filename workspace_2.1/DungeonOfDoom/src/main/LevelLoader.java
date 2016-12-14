package main;

import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
//import java.util.TimerTask;




import GameStates.GameState;
import GameStates.GameStateManager;

public class LevelLoader extends GameState{
	
	public static World world;
	public static Player player;
	private String worldName;
	private String map_name;
	
	Timer t = new Timer();
	
	public LevelLoader(GameStateManager gsm){
		super(gsm);
	}
	
	public LevelLoader(GameStateManager gsm, String worldName, String map_name) {
		super(gsm);
		this.worldName = worldName;
		this.map_name = map_name;
	}

	@Override
	public void init() {

		world = new World(worldName, gsm);
		world.addPlayer(player);
		world.init();
		
		if(worldName == null){
			worldName = "null";
			map_name = "map";
		} 
		

	
		world.generate(map_name);
		
	}
	
	
	
	@Override
	public void update() {
		world.update();
	}

	@Override
	public void render(Graphics g) {
		world.render(g);
	}

	
}
