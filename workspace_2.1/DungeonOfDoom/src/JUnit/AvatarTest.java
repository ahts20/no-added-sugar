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
		
		p.setX(0);
		p.setY(0);
		
		
		blocks.add(new Block(0,0,10,BlockType.RECTANGLE));
		// Create Level loader object and add player and blocks.
		world = new World(new GameStateManager());
		world.player1 = p;
		world.blocks = blocks;
		world.bot = bot;
	}
	

	@Test
	public void testMoveCordsDOWN(){
		p.moveCords(10, "DOWN");
		
		assertEquals(true, p.getY() == 10.0);
	}
	
	@Test
	public void testMoveCordsUP(){
		p.moveCords(10, "UP");
		
		assertEquals(true, p.getY() == -10.0);
	}
	@Test
	public void testMoveCordsLEFT(){
		p.moveCords(10, "LEFT");
		
		assertEquals(true, p.getX() == -10.0);
	}
	@Test
	public void testMoveCordsRIGHT(){
		p.moveCords(10, "RIGHT");
		
		assertEquals(true, p.getX() == 10.0);
	}

}
