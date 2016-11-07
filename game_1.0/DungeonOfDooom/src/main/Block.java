package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Block extends Rectangle{
	//Blocks build up the in game world.
	//Store coordinates of bloc.
	public Vector2F pos = new Vector2F();
	private BlockType blocktype;

	public Block(Vector2F pos, BlockType blocktype, Graphics g){
		//Consructor stores position.
		this.pos = pos;
		//If a rectangle it draws it self to the screen.
		switch(blocktype){
		case RECTANGLE:
			g.drawRect((int)pos.xpos, (int)pos.ypos, 20, 20);
			break;
		}
	}
	
	public enum BlockType{
		RECTANGLE
	}
	
}
