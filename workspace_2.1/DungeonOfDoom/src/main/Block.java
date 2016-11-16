package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Block extends Rectangle{
	private BlockType blocktype;
	private int x, y;
	private int width;
	private int height;
	private boolean isAlive;
	
	private boolean rectangle, wall, gold;
	
	public Block(int x, int y) {
		setBounds((int)x, (int)y, width, height);
		this.x = x;
		this.y = y;
	}
	
	public Block(int x, int y, int blockSize, BlockType blocktype){
		setBounds((int)x, (int)y, width, width);
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
	
	public enum BlockType{
		RECTANGLE,
		WALL,
		GOLD
	}


}
