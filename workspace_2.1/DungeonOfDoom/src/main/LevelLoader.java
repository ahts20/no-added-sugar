package main;

import java.awt.Graphics;
import java.util.Timer;
//import java.util.TimerTask;

import GameStates.GameState;
import GameStates.GameStateManager;

public class LevelLoader extends GameState{
	
	public static World world;
	public static Player player = new Player();
	
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
		if(worldName == null){
			worldName = "null";
			map_name = "map";
		}
		
		world = new World(worldName, gsm);
		world.addPlayer(new Player());
		world.init();
	
		world.generate(map_name);
		
//		t.schedule(new TimerTask() {
//		    @Override
//		    public void run() {
//		       world.resetWorld();
//		       gsm.states.push(new LevelLoader(gsm, "Not", "map2"));
//		       gsm.states.peek().init();
//		    }
//		}, 4000, 10000);
		
	}
	
	
	
	@Override
	public void update() {
		if(player.isChanging == true){
			System.out.println("HEY");
			player.setIsChanging(false);
		}
		
		world.update();

	}

	@Override
	public void render(Graphics g) {
		world.render(g);
	}

	
}
