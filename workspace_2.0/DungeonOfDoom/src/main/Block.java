package main;

import java.awt.Color;
import java.awt.Graphics;

public class Block {
	private String Type;
	private int x, y;
	
	public Block(int x, int y, String blocktype){
		this.x = x;
		this.y = y;
		this.Type = blocktype;
		
	}
//	public Block(int x, int y){
//		this.x = x;
//		this.y = y;
//	}
	public void render(Graphics g){
		switch(this.Type){
		case "RECTANGLE":
			System.out.println("Draw self" + x + y);
			g.setColor(Color.RED);
			g.drawRect((int)x, (int)y, 20, 20);
			break;
		case "WALL":
			g.setColor(Color.WHITE);
			g.drawRect((int)x, (int)y, 20, 20);
			break;
		}
	}

}
