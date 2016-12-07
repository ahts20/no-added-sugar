package my.mm.generator;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import my.mm.MoveableObjects.Player;
import my.mm.main.Assets;
import my.mm.managers.LightManager;
import my.mm.managers.LightSource;
import my.project.gop.main.Vector2F;

public class Block extends Rectangle{
	
	//IMAGES
	private BufferedImage block;
	private BufferedImage stoneWall;
	private BufferedImage brickWall;
	private BufferedImage topBrickWall;
	private BufferedImage topBrickWallLeft;
	private BufferedImage topBrickWallRight;
	
	//BOOLEANS
	private boolean isSolid;
	private boolean isAlive;
	private boolean droped = false;
	
	//FLOATS / INTS
	private float lightLevel = 1f;
	private float shadowLevel = 0.5f;
	private int BlockSize = 40;
	
	//LISTS
	LightSource ls;
	LightManager lm;
	Player player;
	private BlockType blocktype;
	public Vector2F pos = new Vector2F();
	
	public Block(Vector2F pos) {
		isAlive = true;
		setBounds((int)pos.xpos, (int)pos.ypos, BlockSize, BlockSize);
		this.pos = pos;
	}
	
	public Block(Vector2F pos, BlockType blocktype){
		isAlive = true;
		setBounds((int)pos.xpos, (int)pos.ypos, BlockSize, BlockSize);
		this.pos = pos;
		this.blocktype = blocktype;
		init();
	}
	
	public void init(){    
		switch(blocktype){
		case STONE_1:
			stoneWall = Assets.getStone_1();
			break;
		case STONE_2:
			topBrickWall = Assets.getStone_2();
			break;
		case STONE_LEFT:
			topBrickWallLeft = Assets.getStone_left();
			break;
		case STONE_RIGHT:
			topBrickWallRight = Assets.getStone_right();
			break;
		case WALL_1:
			brickWall = Assets.getWall_1();
			break;
		}
	}
	
	public void tick(double deltaTime){
		
		if(isAlive){
			setBounds((int)pos.xpos, (int)pos.ypos, BlockSize, BlockSize);
		}
		
		if(lightLevel > 0.250f && lightLevel < 0.50f){
			lightLevel = 0.250f;
		}
		
	}
	
	public void renderBlockLightLevel(Graphics2D g){
		
		if(lightLevel > 0 && lightLevel <= 1.0){
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, lightLevel));
			g.setColor(Color.BLACK);
			g.fillRect((int)pos.getWorldLocation().xpos, (int)pos.getWorldLocation().ypos, BlockSize, BlockSize);
			g.setColor(Color.WHITE);
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
		}
			
	}
	

	
	public void render(Graphics2D g){
		if(isAlive){
			if(block != null || stoneWall != null || brickWall != null || topBrickWall != null || topBrickWallLeft != null || topBrickWallRight != null){
						
				g.drawImage(block, (int)pos.getWorldLocation().xpos, (int)pos.getWorldLocation().ypos, BlockSize, BlockSize, null);	
				g.drawImage(stoneWall, (int)pos.getWorldLocation().xpos, (int)pos.getWorldLocation().ypos, BlockSize, BlockSize, null);		
				g.drawImage(topBrickWall,(int)pos.getWorldLocation().xpos, (int)pos.getWorldLocation().ypos, BlockSize, BlockSize, null);		
				g.drawImage(brickWall, (int)pos.getWorldLocation().xpos, (int)pos.getWorldLocation().ypos, BlockSize, BlockSize, null);	
				g.drawImage(topBrickWallLeft, (int)pos.getWorldLocation().xpos, (int)pos.getWorldLocation().ypos, BlockSize, BlockSize, null);	
				g.drawImage(topBrickWallRight, (int)pos.getWorldLocation().xpos, (int)pos.getWorldLocation().ypos, BlockSize, BlockSize, null);	

				
			} else {
				g.fillRect((int)pos.getWorldLocation().xpos, (int)pos.getWorldLocation().ypos, BlockSize, BlockSize);
			}
				
			if(isSolid){
//				g.drawRect((int)pos.getWorldLocation().xpos, (int)pos.getWorldLocation().ypos, BlockSize, BlockSize);
			}
		}
	}
	
	public static int randomNumber(int min, int max){

		int randomNum = (int) (min +(Math.random() * (max - min)));
		
		return randomNum;
		
	}
		
	public enum BlockType{
		STONE_1,
		STONE_2,
		STONE_LEFT,
		STONE_RIGHT,
		WALL_1,
		APPLE
	}

	public boolean isSolid() {
		return isSolid;
	}
	
	public Block isSolid(boolean isSolid){
		this.isSolid = isSolid;
		return this;
	}
	
	public boolean isAlive(){
		return isAlive;
	}
	
	//Boolean to set isAlive to either true or false
	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}
	
	public Vector2F getBlockLocation() {
		return pos;
	}
	
	//ADDING AND REMOVING SHADOWS
	public void addShadow(float amount){
		if(lightLevel < 1.0){
			lightLevel += amount;
		}
	}
	
	public void removeShadow(float amount){
		if(lightLevel > 0.001000){
			lightLevel -= amount;
		}
	}
	
	public float getLightLevel() {
		return lightLevel;
	}
	
	public void setLightLevel(float lightLevel) {
		this.lightLevel = lightLevel;
	}
}
