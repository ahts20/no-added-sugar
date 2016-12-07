package my.mm.generator;

import java.awt.Graphics2D;
import java.util.concurrent.CopyOnWriteArrayList;

import my.mm.MoveableObjects.Player;

public class TileManager {
	
	public static CopyOnWriteArrayList<Block> blocks = new CopyOnWriteArrayList<Block>();
	public static CopyOnWriteArrayList<Block> loaded_blocks = new CopyOnWriteArrayList<Block>();
	
	private World world;
	
	
	public TileManager(World world) {
		this.world = world;
		
	}

	public void tick(double deltaTime){
		for(Block block : blocks){
			block.tick(deltaTime);
			
			if(Player.render.intersects(block)){
				block.setAlive(true);
				
				if(!loaded_blocks.contains(block)){
					loaded_blocks.add(block);
				}
			} else {
				block.setAlive(false);
				if(loaded_blocks.contains(block)){
					loaded_blocks.remove(block);
				}
			}
		}
		
		
		
		if(!world.getPlayer().isDebugging()){
			if(!loaded_blocks.isEmpty()){
				loaded_blocks.clear();
			}
		}
		
	}
	
	public void render(Graphics2D g){
		for(Block block : blocks){
			block.render(g);
		}
	}
	
	public CopyOnWriteArrayList<Block> getBlocks(){
		return blocks;
		
	}
	public CopyOnWriteArrayList<Block> getLoadedBlocks(){
		return loaded_blocks;
	}

}