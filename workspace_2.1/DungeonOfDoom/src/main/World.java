package main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.concurrent.CopyOnWriteArrayList;
import main.Block.BlockType;
import GameStates.GameStateManager;

public class World{

	public Player player;
	public Block block;
	private GameStateManager gsm;

	private static BufferedImage map;
	public static loadImage loader;
	
	private String worldName;
	
	public static CopyOnWriteArrayList<Block> blocks = new CopyOnWriteArrayList<Block>();

	public World(GameStateManager gsm){
		this.gsm = gsm;
	}
	
	public World(String worldName, GameStateManager gsm) {
		this.gsm = gsm;
	}

	public void init() {
		loader = new loadImage();
		player = new Player();
		player.init();
	}

	public void update() {
		player.update(blocks);
		if (allGoldPickedUp()) {
			for (Block i : blocks) {
				if (i.door)
					i.isVisible = true;
			}
		}
	}

	private boolean allGoldPickedUp() {
		boolean allGoldDetected = true;
		for (Block i : blocks) {
			if (i.gold == true)
				allGoldDetected = false;
		}
		return allGoldDetected;
	}

	public void render(Graphics g) {
		for (Block i : blocks) {
			i.render(g);
		}
		if (player.status == "facedown") {
			player.render(g, 2);
		} else if (player.status == "faceleft") {
			player.render(g, 0);
		} else if (player.status == "faceright") {
			player.render(g, 1);
		} else if (player.status == "faceup") {
			player.render(g, 3);
		}
	}

	public void generate(String world_name) {
		// Generates the world from the map PNG image.
		map = null;
		// Syntactic sugarrrr - match with block height and width to avoid gaps.
		int blockSize = 25;
		try {
			map = loader.LoadImageFrom("/" + world_name + ".png");
		} catch (Exception e) {
			e.printStackTrace();
		}
		// Iterate through each pixel in the image.
		for (int x = 0; x < 50; x++) {
			for (int y = 0; y < 50; y++) {
				int mapColours = map.getRGB(x, y);

				switch (mapColours & 0xFFFFFF) {
				// If Grey set as floor/RECTANGLE
				case 0x808080:
					blocks.add(new Block(x * blockSize, y * blockSize, blockSize, BlockType.RECTANGLE));
					break;
				// If Black set as Wall.
				case 0x000000:
					blocks.add(new Block(x * blockSize, y * blockSize, blockSize, BlockType.WALL).isSolid(true));
					break;
				// If Yellow set as Gold.
				case 0xffff00:
					blocks.add(new Block(x * blockSize, y * blockSize, blockSize, BlockType.GOLD));
					break;
				// If Blue set as Door.
				case 0x0080FF:
					blocks.add(new Block(x * blockSize, y * blockSize, blockSize, BlockType.DOOR));
					break;
				}
			}
		}
	}
	public void addPlayer(Player player) {
		this.player = player;
	}
	
	public static void resetWorld(){
		blocks.clear();		
	}
	
	public void changeToWorld(String wn, String mn){
	
		if(wn != worldName){
			resetWorld();
			gsm.states.push(new LevelLoader(gsm, wn, mn));
			gsm.states.peek().init();
		} else {
			System.out.println("ALREADY IN THE WORLD");
		}
	}	
}
