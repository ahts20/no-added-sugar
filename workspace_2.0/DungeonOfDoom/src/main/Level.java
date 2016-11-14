package main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.concurrent.CopyOnWriteArrayList;

import GameStates.GameState;
import GameStates.GameStateManager;

public class Level extends GameState{

	public Player player;
	public Block block;
	private static BufferedImage map;
	public static loadImage loader; 
	//Array to hold all blocks in game.
	public static CopyOnWriteArrayList<Block> blocks = new CopyOnWriteArrayList<Block>();
	public int ScreenX, ScreenY;
	public Level(GameStateManager gsm) {
		super(gsm);
	}

	@Override
	public void init() {
		player = new Player();
		block = new Block(50,50, "RECTANGLE");
		loader = new loadImage();
		generate("map");
	}

	@Override
	public void update() {
		player.update();
	}

	@Override
	public void render(Graphics g) {
		block.render(g);
		player.render(g);
		for (Block i : blocks){
			i.render(g);
		}
	}
	public static void generate(String world_name){

		map = null;

		try{
			map = loader.LoadImageFrom("/"+world_name+".png");
		}catch(Exception e){
			e.printStackTrace();
		}

		for(int x = 0; x < 50; x++){
			for(int y = 0; y < 50; y++){
				int mapColours = map.getRGB(x, y);

				switch(mapColours & 0xFFFFFF){
				case 0x808080:
					blocks.add(new Block(x*20,y*20, "RECTANGLE"));
					break;
				case 0x000000:
					blocks.add(new Block(x*20, y*20, "WALL"));
					break;
				}
			}

		}
	}
}
