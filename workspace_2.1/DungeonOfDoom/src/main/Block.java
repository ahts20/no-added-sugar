package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Block extends Rectangle{
	private BlockType blocktype;
	private int x, y;
	
	private boolean isAlive;
	
	private boolean rectangle, wall;
	
	public Block(int x, int y) {
		setBounds((int)x, (int)y, 40, 40);
		this.x = x;
		this.y = y;
	}
	
	public Block(int x, int y, BlockType blocktype){
		setBounds((int)x, (int)y, 40, 40);
		this.x = x;
		this.y = y;
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
		}
	}
	
	public void update() {
		setBounds((int)x, (int)y, 40, 40);
	}
	
	public void render(Graphics g){
		if(rectangle == true){
			g.setColor(Color.RED);
			g.drawRect((int)x, (int)y, 40, 40);
			//System.out.println("HEY");
		}
			
		if(wall == true){
			g.setColor(Color.WHITE);
			g.drawRect((int)x, (int)y, 40, 40);
		}
		
	}
	
	public enum BlockType{
		RECTANGLE,
		WALL
	}


}
