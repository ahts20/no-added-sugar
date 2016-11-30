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
	
	public boolean rectangle, wall, gold;
	
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
			g.setColor(Color.YELLOW);
			g.drawRect((int)x, (int)y, width, height);
		}
		
	}
	public void changeToFloor(){
		//Changes block type to floor - e.g. when it was gold and gets picked up.
		this.rectangle = true;
		this.gold = false;
		this.wall = false;
	}
	public enum BlockType{
		RECTANGLE,
		WALL,
		GOLD
	}
	
	

}
