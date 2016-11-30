package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Block extends Rectangle{
	private BlockType blocktype;
	public int x, y;
	public int width;
	public int height;
	private boolean isAlive;
	public boolean isVisible;
	
	public boolean rectangle, wall, gold, door;
	
	public Block(int x, int y) {
		setBounds((int)x, (int)y, width, height);
		this.x = x;
		this.y = y;
	}
	
	public Block(int x, int y, int blockSize, BlockType blocktype){
		setBounds((int)x, (int)y, width, height);
		this.x = x;
		this.y = y;
		this.width = blockSize;
		this.height = blockSize;
		this.blocktype = blocktype;
		init();
	}
	
	public void init(){
		switch(blocktype){
		case RECTANGLE:
			rectangle = true;
			break;
		case WALL:
			wall = true;
			break;
		case GOLD:
			gold = true;
			break;
		case DOOR:
			door = true;
			isVisible = false;
			break;
		}
	}
	
	public void update() {
		setBounds((int)x, (int)y, width, height);
	}
	
	public void render(Graphics g){
		if(rectangle == true){
			g.setColor(Color.RED);
			g.drawRect((int)x, (int)y, width, height);
			//System.out.println("HEY");
		}
			
		if(wall == true){
			g.setColor(Color.WHITE);
			g.drawRect((int)x, (int)y, width, height);
		}
		if(gold == true){
			g.drawImage(Player.p[8], (int)x, (int)y, width, height, null);
//			g.setColor(Color.YELLOW);
//			g.drawRect((int)x, (int)y, width, height);
		}
		if(door == true && isVisible == false){
			g.setColor(Color.WHITE);
			g.drawRect((int)x, (int)y, width, height);
		}
		if(door == true && isVisible == true){
			g.setColor(Color.BLUE);
			g.drawRect((int)x, (int)y, width, height);
			
		}
		
	}
	public void changeGoldToFloor(){
		//Changes block type to floor - e.g. when it was gold and gets picked up.
		this.rectangle = true;
		this.gold = false;
		this.wall = false;
	}
	public enum BlockType{
		RECTANGLE,
		WALL,
		GOLD,
		DOOR
	}
	
	

}
