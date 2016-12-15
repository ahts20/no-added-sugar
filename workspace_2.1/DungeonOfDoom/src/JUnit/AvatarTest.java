package JUnit;
import static org.junit.Assert.*;

import java.util.concurrent.CopyOnWriteArrayList;

import org.junit.Before;
import org.junit.Test;

import Generators.Block;
import Generators.World;
import Generators.Block.BlockType;
import Managers.GameStateManager;
import MovableObjects.Bot;
import MovableObjects.Player;
import MovableObjects.Player1;
import MovableObjects.Player2;

public class AvatarTest {
	Bot bot;
	Player p, p2;
	World world;
	
	CopyOnWriteArrayList<Block> blocks = new CopyOnWriteArrayList<Block>();
	
	@Before
	public void setUpTestVariables() throws Exception {
		bot = new Bot();
		p = new Player1();
		p2 = new Player2();
		bot.init(p, p2, 500, 500);
		
		blocks.add(new Block(0,0,10,BlockType.RECTANGLE));
		// Create Level loader object and add player and blocks.
		world = new World(new GameStateManager());
		world.player1 = p;
		world.blocks = blocks;
		world.bot = bot;
	}

}
