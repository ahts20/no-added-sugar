package my.mm.main;

import java.awt.image.BufferedImage;

import my.project.gop.main.SpriteSheet;
import my.project.gop.main.loadImageFrom;

public class Assets {
	
	SpriteSheet blocks = new SpriteSheet();
	SpriteSheet stoneWall = new SpriteSheet();
	SpriteSheet brickWall = new SpriteSheet();
	SpriteSheet topBrickWall = new SpriteSheet();
	SpriteSheet topBrickWallLeft = new SpriteSheet();
	SpriteSheet topBrickWallRight = new SpriteSheet();
	
	public static SpriteSheet player = new SpriteSheet();
	public static SpriteSheet playerRight = new SpriteSheet();
	public static SpriteSheet playerLeft = new SpriteSheet();
	public static SpriteSheet playerUp = new SpriteSheet();
	public static SpriteSheet playerDown = new SpriteSheet();
	public static SpriteSheet playerIdle = new SpriteSheet();
	
	public static BufferedImage stone_1;
	public static BufferedImage stone_2;
	public static BufferedImage stone_left;
	public static BufferedImage stone_right;
	public static BufferedImage wall_1;
	public static BufferedImage apple;
	public static BufferedImage cursor_pressed;
	public static BufferedImage cursor_released;
	public static BufferedImage button_notover;
	public static BufferedImage button_over;
	
	public void init(){
		blocks.setSpriteSheet(loadImageFrom.LoadImageFrom(Main.class, "spritesheet.png"));
		stoneWall.setSpriteSheet(loadImageFrom.LoadImageFrom(Main.class, "stone_wall.png"));
		topBrickWall.setSpriteSheet(loadImageFrom.LoadImageFrom(Main.class, "top_brick_wall.png"));
		topBrickWallLeft.setSpriteSheet(loadImageFrom.LoadImageFrom(Main.class, "top_brick_wall_left.png"));
		topBrickWallRight.setSpriteSheet(loadImageFrom.LoadImageFrom(Main.class, "top_brick_wall_right.png"));
		brickWall.setSpriteSheet(loadImageFrom.LoadImageFrom(Main.class, "brick_wall.png"));
		
		player.setSpriteSheet(loadImageFrom.LoadImageFrom(Main.class, "NewPlayerSheet.png"));
		playerRight.setSpriteSheet(loadImageFrom.LoadImageFrom(Main.class, "spritesheet_right.png"));
		playerLeft.setSpriteSheet(loadImageFrom.LoadImageFrom(Main.class, "spritesheet_left.png"));
		playerUp.setSpriteSheet(loadImageFrom.LoadImageFrom(Main.class, "spritesheet_up.png"));
		playerDown.setSpriteSheet(loadImageFrom.LoadImageFrom(Main.class, "spritesheet_down.png"));
		playerIdle.setSpriteSheet(loadImageFrom.LoadImageFrom(Main.class, "spritesheet_idle.png"));
		
		stone_1 = stoneWall.getTile(0, 0, 254, 254);
		stone_2 = topBrickWall.getTile(0, 0, 254, 254);
		stone_left = topBrickWallLeft.getTile(0, 0, 254, 254);
		stone_right = topBrickWallRight.getTile(0, 0, 254, 254);
		wall_1 = brickWall.getTile(0, 0, 254, 254);
		
		apple = blocks.getTile(0, 16, 8, 8);
		
		cursor_released = player.getTile(96, 8, 8, 8);
		cursor_pressed = player.getTile(104, 8, 8, 8);
		button_notover = player.getTile(0, 48, 32, 16);
		button_over = player.getTile(32, 48, 32, 16);
	}
	
	public static BufferedImage getStone_1() {
		return stone_1;
	}
	
	public static BufferedImage getStone_2(){
		return stone_2;
	}
	
	public static BufferedImage getStone_left(){
		return stone_left;
	}
	
	public static BufferedImage getStone_right(){
		return stone_right;
	}
	
	public static BufferedImage getWall_1(){
		return wall_1;
	}
	
	public static BufferedImage getApple(){
		return apple;
	}
	
	public static BufferedImage getCursor_pressed(){
		return cursor_pressed;
	}
	
	public static BufferedImage getCursor_released(){
		return cursor_pressed;
	}
	
	public static BufferedImage getButton_notover() {
		return button_notover;
	}
	
	public static BufferedImage getButton_over() {
		return button_over;
	}
	
}
