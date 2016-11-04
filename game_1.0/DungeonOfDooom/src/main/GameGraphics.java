package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.JPanel;

import main.Block.BlockType;

public class GameGraphics extends JPanel{
	//Set the player as an object.
	public Player player = new Player();
	public static loadImage loader = new loadImage();
	private Block block;
	
	public static CopyOnWriteArrayList<Block> blocks = new CopyOnWriteArrayList<Block>();
	private static BufferedImage map;
	
	public void paintComponent(Graphics g){
		//Setup the graphics environment.
		super.paintComponent(g);
		this.setBackground(Color.BLACK);
		g.setColor(Color.WHITE);
		//Make the player draw itself.
		player.drawPlayer(g);
		//block = new Block(new Vector2F(50,50), BlockType.RECTANGLE, g);
		generate(g);
		//Cinema Effect
		/*g.setColor(Color.BLACK);
		g.fillRect(0, 0, Main.width, Main.height / 6);
		g.fillRect(0, Main.height - Main.height / 6, Main.width, Main.height / 6);*/
		
	}
	
	public static void generate(Graphics g){
		
		map = null;
	
		try{
			map = loader.LoadImageFrom("/map.png");
		}catch(Exception e){
			e.printStackTrace();
		}
		
		for(int x = 0; x < 50; x++){
			for(int y = 0; y < 50; y++){
				int mapColours = map.getRGB(x, y);
				
				switch(mapColours & 0xFFFFFF){
				case 0x808080:
					blocks.add(new Block(new Vector2F(x*20, y*20), BlockType.RECTANGLE, g));
					break;
				}
			}
		}
		
	}

}
