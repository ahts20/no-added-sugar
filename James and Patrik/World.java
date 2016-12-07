package my.mm.generator;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArrayList;

import my.mm.MoveableObjects.Player;
import my.mm.gamestate.GameStateManager;
import my.mm.gamestates.MapmakerLevelLoader;
import my.mm.generator.Block.BlockType;
import my.mm.main.Assets;
import my.mm.main.Main;
import my.mm.managers.LightManager;
import my.mm.managers.SoundManager;
import my.mm.managers.SoundSource;
import my.project.gop.main.Vector2F;
import my.project.gop.main.loadImageFrom;

public class World {

	//VECTORS
	public static Vector2F map_pos = new Vector2F();
	
	//IMAGES
	private BufferedImage map;
	private BufferedImage apple;
	
	//STRINGS
	private String worldName;
	
	//INTIGERS
	private int world_width;
	private int world_height;
	private int blockSize = 32;
	public static int entityX, entityY;
	public static int lightX, lightY;
	
	//FLOATS
	public float distance;
	
	//LISTS
	public static CopyOnWriteArrayList<BlockEntity> blockents;
	public TileManager tiles;
	public BlockEntity ent;
	private LightManager lightManager;
	private static Player player;
	private SoundManager soundManager;
	
	//WORLD SPAWN
	private Block spawn;
	
	//BOOLEANS
	private boolean hasSize = false;
	public boolean hasGenerated;
	private GameStateManager gsm;
	
	
	public World(String worldName, GameStateManager gsm) {
		this.worldName = worldName;
		this.gsm = gsm;
		Vector2F.setWorldVariables(map_pos.xpos, map_pos.ypos);
	}

	public void init() {
		//arrays
		blockents = new CopyOnWriteArrayList<BlockEntity>();
		tiles = new TileManager(this);
		lightManager = new LightManager(tiles.getBlocks());
		lightManager.init();
		
		soundManager = new SoundManager();
		soundManager.init();
		
		map_pos.xpos = spawn.pos.getWorldLocation().xpos - player.getPos().xpos;
		map_pos.ypos = spawn.pos.getWorldLocation().ypos - player.getPos().ypos;
		
		if(player != null){
			player.init(this);;
		}
	}

	public void tick(double deltaTime) {
		
		//Vectors
		Vector2F.setWorldVariables(map_pos.xpos, map_pos.ypos);
		
		if(!player.hasSpawned()){
			spawn.tick(deltaTime);
		}
		
		tiles.tick(deltaTime);
		lightManager.tick();
		
		
		/*Arrays Tick*/
		if(!blockents.isEmpty()){
			for(BlockEntity ent : blockents){
				if(player.render.intersects(ent)){
					ent.tick(deltaTime);
					ent.setAlive(true);
				} else {
					ent.setAlive(false);
				}
			}
		}
	
		////////////////////////////////////////////////////////////
		
		if(player != null){
			player.tick(deltaTime);
		}
	}

	public void render(Graphics2D g) {
		tiles.render(g);
		
		if(!player.hasSpawned()){
			spawn.render(g);
		}
		
		/*Arrays Render*/
		if(!blockents.isEmpty()){
			for(BlockEntity ent : blockents){
				if(player.render.intersects(ent)){
					ent.render(g);
				} 
			}
		}
		
		for(Block block : TileManager.blocks){
			if(player.render.intersects(block)){
				block.renderBlockLightLevel(g);
			} 
		}
		////////////////////////////////////////////////////////////
		
		if(player != null){
			player.render(g);
		}
		
		lightManager.render(g);

		//CINEMA EFFECT
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Main.width, Main.height / 6);
		g.fillRect(0, 650, Main.width, Main.height / 6);
		g.setColor(Color.WHITE);	
		
		//DEBUG
		if(getPlayer().isDebugging()){
			g.drawString("[DEBUG]", 30, 20);

			g.drawString("[MapXpos] "+ map_pos.xpos, 30, 50);
			g.drawString("[MapYpos] "+ map_pos.ypos, 30, 80);
			
			g.drawString("[PlayerXpos] "+(getPlayer().getPos().xpos + map_pos.xpos), 330, 50);
			g.drawString("[PlayerYpos] "+(getPlayer().getPos().ypos + map_pos.ypos), 330, 80);
		
			g.drawString("[World Blocks] "+ getWorldBlocks().getBlocks().size(), 640, 50);
			g.drawString("[Loaded World Blocks] "+ getWorldBlocks().getLoadedBlocks().size(), 640, 80);
		}
	}
	
	public void generate(String world_image_name) {
		map = null;

		if(hasSize){
			try{
				map = loadImageFrom.LoadImageFrom(Main.class, world_image_name+".png");
			}catch(Exception e){
				
			}
			
			for(int x = 0; x < world_width; x++){
				for(int y = 0; y < world_height; y++){
					
					int col = map.getRGB(x, y);
					
					switch(col & 0xFFFFFF){
					case 0x808080:
							tiles.blocks.add(new Block(new Vector2F(x*40, y*40), BlockType.STONE_1));
						break;
					case 0x606060:
							tiles.blocks.add(new Block(new Vector2F(x*40, y*40), BlockType.STONE_LEFT).isSolid(true));
						break;
					case 0x404040:
							tiles.blocks.add(new Block(new Vector2F(x*40, y*40), BlockType.STONE_2).isSolid(true));
						break;
					case 0x202020:
							tiles.blocks.add(new Block(new Vector2F(x*40, y*40), BlockType.STONE_RIGHT).isSolid(true));
						break;
					case 0x000000:
							tiles.blocks.add(new Block(new Vector2F(x*40, y*40), BlockType.WALL_1).isSolid(true));
						break;
					}
				}
			}
			
			//apple generator
			apple = Assets.getApple();
	        	Timer t = new Timer();
	    		t.schedule(new TimerTask() {
	    		    @Override
	    		    public void run() {
	    		    	int x = Block.randomNumber((int)(getPlayer().pos.xpos + map_pos.xpos) - 150, (int)(getPlayer().pos.xpos + map_pos.xpos) + 150);
	    		    	int y = Block.randomNumber((int)(getPlayer().pos.xpos + map_pos.ypos) - 150, (int)(getPlayer().pos.xpos + map_pos.ypos) + 150);
	    		    	World.dropBlockEntity(new Vector2F(x, y), apple);
	    		    }
	    		}, 0, 5000);
	    		
	        }
		hasGenerated = true;
	}

	public void setSize(int world_width, int world_height) {
		this.world_width = world_width;
		this.world_height = world_height;
		hasSize = true;
	}

	public void addPlayer(Player player) {
		this.player = player;
	}
	
	/*Adding and removing blocks*/
	//BLOCK ENTITY
	public static void dropBlockEntity(Vector2F pos, BufferedImage block_image){
		BlockEntity ent = new BlockEntity(pos, block_image);
			if(!blockents.contains(ent)){
				blockents.add(ent);
			}
	}

	public static void removeDroppedBlockEntity(BlockEntity ent){
		if(blockents.contains(ent)){
			blockents.remove(ent);
		}
	}
	
	/*Setting the World dimensions*/
	public void setWorldSpawn(float xpos, float ypos){
		if(xpos < world_width){
			if(ypos < world_height){
				Block spawn = new Block(new Vector2F(xpos * blockSize, ypos * blockSize));
				this.spawn = spawn;
			}
		}
	}
	
	public Vector2F getWorldSpawn(){
		return spawn.pos;
	}
	
	
	////////////////////////GETTERS - SETTERS //////////////////////////
	public Vector2F getWorldPos(){
		return map_pos;
	}
	
	public float getWorldXpos(){
		return map_pos.xpos;
	}
	
	public float getWorldYpos(){
		return map_pos.ypos;
	}
	
	public TileManager getWorldBlocks(){
		return tiles;
	}
	
	public static Player getPlayer(){
		return player;
		
	}
	
	public boolean hasGenerated(){
		return hasGenerated;
	}
	
	public void resetWorld(){
		tiles.getBlocks().clear();
		tiles.getLoadedBlocks().clear();
		blockents.clear();
		spawn = null;
	}
	
	public void changeToWorld(String wn, String mn){
		if(wn != worldName){
			resetWorld();
			gsm.states.push(new MapmakerLevelLoader(gsm, wn, mn));
			gsm.states.peek().init();
		} else {
			System.err.println("YOU ARE ALREADY IN THE WORLD");
		}
	}
}
