package main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import java.util.concurrent.CopyOnWriteArrayList;

import main.Block.BlockType;
import GameStates.GameStateManager;

public class World{

	public Player player1, player2;
	public Bot bot;
	public Block block;
	private GameStateManager gsm;
	private LevelLoader ll;
	
	public static ArrayList<Integer> linesP1 = new ArrayList<Integer>();
	public static ArrayList<Integer> linesP2 = new ArrayList<Integer>();
	
	public boolean change = false;
	public boolean changeP2 = false;	
	//Used by enoughGoldPickedUp() to know how much gold was originally in place.
	private int totalGold;

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
		

		player2 = new Player2();
		player1 = new Player1();
		
	
		
		player2.init(500,600);
		player1.init(300, 600);

		bot = new Bot();
		bot.init(player1, player2, 400, 200);
	}


	public void update() {
		player1.update(blocks);
		player2.update(blocks);
		checkGoldTakenAndOpenDoor();

		bot.update(blocks);
		savePlayer1Score();
		savePlayer2Score();
		player1.touching = false;
		
		if(!linesP1.isEmpty() && change == false){
			player1.setScore(linesP1.get(linesP1.size()-1));
			change = true;
		}
		
		if(!linesP2.isEmpty() && changeP2 == false){
			player2.setScore(linesP2.get(linesP2.size()-1));
			changeP2 = true;
		}
	
	}
	private void checkGoldTakenAndOpenDoor(){
		if (enoughGoldPickedUp()) {
			for (Block i : blocks) {
				if (i.door)
					i.isVisible = true;
			}
		}
	}
	private boolean enoughGoldPickedUp() {
		int minimumPercentToOpenDoor = 50;
		int currentGold = returnCurrentGold();
		//System.out.println(currentGold + "  " + this.totalGold);
		if(currentGold == 0)
			return true;
		return (Math.abs((currentGold/(float) this.totalGold)*100) < minimumPercentToOpenDoor);
	}
	private int returnCurrentGold() {
		int gold = 0;
		for (Block i : blocks) {
			if (i.gold == true)
				gold ++;
		}
		return gold;
	}
	public void render(Graphics g) {
		for (Block i : blocks) {
			i.render(g);
		}

		player1.render(g);
		player2.render(g);
		
		if (bot.botState == "facedown") {
			bot.render(g, 6);
		} else if (bot.botState == "faceleft") {
			bot.render(g, 4);
		} else if (bot.botState == "faceright") {
			bot.render(g, 5);
		} else if (bot.botState == "faceup") {
			bot.render(g, 7);
		}
//		bot.render(g);
	}

	public void generate(String world_name) {
		// Generates the world from the map PNG image.
		map = null;
		// Syntactic sugarrrr - match with block height and width to avoid gaps.
		int blockSize = 26;
		try {
			map = loader.LoadImageFrom("/" + world_name + ".png");
		} catch (Exception e) {
			e.printStackTrace();
		}
		// Iterate through each pixel in the image.
		for (int x = 0; x < 50; x++) {
			for (int y = 0; y < 30; y++) {
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
		//Save total gold as an attribute, so it can be used to 
		//detect is enough gold is collected.
		this.totalGold = returnCurrentGold();
	}
	public void addPlayer(Player player) {
		this.player1 = player;
	}
	
	public static void resetWorld(){
		blocks.clear();		
	}
	
	public void savePlayer1Score(){
		if(player1.touching == true){
			try(FileWriter fw = new FileWriter("res/scorePlayer1.txt", true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter out = new PrintWriter(bw))
			{
		out.println(player1.score);

		linesP1.add(player1.score);
		change = false;
		
		out.close();
			} catch (Exception e){
				e.printStackTrace();
			}			

		}
	}
	
	public void savePlayer2Score(){
		if(player2.touching == true){
			try(FileWriter fw = new FileWriter("res/scorePlayer2.txt", true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter out = new PrintWriter(bw))
			{
		out.println(player2.score);

		linesP2.add(player2.score);
		changeP2 = false;
		

		out.close();
			} catch (Exception e){
				e.printStackTrace();
			}
		}
	}
	
}
