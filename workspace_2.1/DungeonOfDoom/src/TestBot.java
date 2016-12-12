import static org.junit.Assert.*;

import java.util.concurrent.CopyOnWriteArrayList;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import GameStates.GameStateManager;
import main.Block;
import main.Block.BlockType;
import main.Bot;
import main.Player;
import main.World;

public class TestBot {
	Bot bot;
	Player p;
	World world;
	
	CopyOnWriteArrayList<Block> blocks = new CopyOnWriteArrayList<Block>();
	
	@Before
	public void setUpTestVariables() throws Exception {
		bot = new Bot();
		System.out.println(bot);
		p = new Player();
		bot.init(p);
		
		blocks.add(new Block(0,0,10,BlockType.RECTANGLE));
		// Create Level loader object and add player and blocks.
		world = new World("testWorld", new GameStateManager());
		world.player = p;
		world.blocks = blocks;
		world.bot = bot;
		
	}

	@Test
	public void testMoveToPlayer() {
		System.out.println(this.bot);
		bot.setX(50);
		bot.setY(0);
		p.setX(0);
		p.setY(0);
				
		world.update();
		
	}

}
