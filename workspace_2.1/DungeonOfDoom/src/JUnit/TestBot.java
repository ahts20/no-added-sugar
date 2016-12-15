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

public class TestBot {
	Bot bot;
	Player p, p2;
	World world;
	
	CopyOnWriteArrayList<Block> blocks = new CopyOnWriteArrayList<Block>();
	
	@Before
	public void setUpTestVariables() throws Exception {
		bot = new Bot();
		p = new Player1();
		p2 = new Player2();
		
		bot.init(p, p2, 100, 100);
		
		blocks.add(new Block(0,0,10,BlockType.RECTANGLE));
		blocks.add(new Block(0,0,10,BlockType.RECTANGLE));
		blocks.add(new Block(0,0,10,BlockType.RECTANGLE));
		// Create Level loader object and add player and blocks.
		world = new World(new GameStateManager());
		world.player1 = p;
		world.player2 = p2;
		world.blocks = blocks;
		world.bot = bot;
		
	}

	@Test
	public void testMoveToPlayerLeft() {
		//Set bot location
		bot.setX(100);
		bot.setY(100);
		//Set player location (must be touching floor object)
		p.setX(10);
		p.setY(10);
		p2.setX(10);
		p2.setY(10);
		
		//Set a strip of floor objects for player.
		blocks.get(0).x = 10;
		blocks.get(0).y = 10;
		blocks.get(1).x = 40;
		blocks.get(1).y = 10;
		blocks.get(2).x = 60;
		blocks.get(2).y = 10;
				
		world.update();

		assertEquals("LEFT", bot.getBotXdirection());
	}

	@Test
	public void testMoveToPlayerRight() {
		bot.setX(10);
		bot.setY(10);
		
		p.setX(60);
		p.setY(10);
		p2.setX(60);
		p2.setY(10);
		
		blocks.get(0).x = 10;
		blocks.get(0).y = 10;
		blocks.get(1).x = 40;
		blocks.get(1).y = 10;
		blocks.get(2).x = 60;
		blocks.get(2).y = 10;

		world.update();

		assertEquals("RIGHT", bot.getBotXdirection());
	}
	@Test
	public void testMoveToPlayerUp() {
		bot.setX(10);
		bot.setY(100);
		
		p.setX(10);
		p.setY(10);
		p2.setX(10);
		p2.setY(10);
		
		blocks.get(0).x = 10;
		blocks.get(0).y = 20;
		blocks.get(1).x = 10;
		blocks.get(1).y = 40;
		blocks.get(2).x = 10;
		blocks.get(2).y = 60;

		world.update();
		
		assertEquals("UP", bot.getBotYdirection());
	}
	@Test
	public void testMoveToPlayerDown() {
		bot.setX(10);
		bot.setY(10);
		
		p.setX(10);
		p.setY(60);
		p2.setX(10);
		p2.setY(60);
		
		blocks.get(0).x = 10;
		blocks.get(0).y = 20;
		blocks.get(1).x = 10;
		blocks.get(1).y = 40;
		blocks.get(2).x = 10;
		blocks.get(2).y = 60;

		world.update();
		
		assertEquals("DOWN", bot.getBotYdirection());
	}
	@Test
	public void testBotStealsGold() {
		int prevGold = 50;
		p.setScore(prevGold);
		
		bot.setX(10);
		bot.setY(10);
		
		blocks.get(0).x = 10;
		blocks.get(0).y = 10;
		
		p.setX(10);
		p.setY(10);
		p2.setX(100);
		p2.setY(100);

		world.update();

		assertEquals(true, p.getScore()<prevGold);
	}
	@Test
	public void testBotBecomesInactive() {
		
		bot.setX(10);
		bot.setY(10);

		blocks.get(0).x = 10;
		blocks.get(0).y = 10;
		
		p.setX(10);
		p.setY(10);
		p2.setX(10);
		p2.setY(10);
		//Active before
		assertEquals(true, bot.getActive());
		//Run logic
		world.update();
		//Becomes inactive
		assertEquals(false, bot.getActive());
	}
	@Test
	public void testBotPushesPlayerDown() {
		bot.setX(10);
		bot.setY(10);
		
		p.setX(10);
		p.setY(15);
		p2.setX(10);
		p2.setY(60);
		
		blocks.get(0).x = 10;
		blocks.get(0).y = 20;
		blocks.get(1).x = 10;
		blocks.get(1).y = 40;
		blocks.get(2).x = 10;
		blocks.get(2).y = 60;
	
		//Run logic
		world.update();

		assertEquals(true, (p.getY() > 10.0));
	}
	@Test
	public void testBotPushesPlayerUp() {
		bot.setX(10);
		bot.setY(60);
		
		p.setX(10);
		p.setY(55);
		p2.setX(10);
		p2.setY(10);
		
		//Make a strip for player to walk on.
		blocks.get(0).x = 10;
		blocks.get(0).y = 20;
		blocks.get(1).x = 10;
		blocks.get(1).y = 40;
		blocks.get(2).x = 10;
		blocks.get(2).y = 60;
	
		//Run logic
		world.update();

		assertEquals(true, p.getY() < 50);
	}
	@Test
	public void testBotPushesPlayerRight() {
		bot.setX(10);
		bot.setY(10);
		
		p.setX(15);
		p.setY(10);
		p2.setX(60);
		p2.setY(10);
		
		blocks.get(0).x = 10;
		blocks.get(0).y = 10;
		blocks.get(1).x = 40;
		blocks.get(1).y = 10;
		blocks.get(2).x = 60;
		blocks.get(2).y = 10;
	
		//Run logic
		world.update();

		//world.update();
		//Becomes inactive.
		//Becomes inactive.
		assertEquals(true, p.getX() > 20);
	}
	@Test
	public void testBotPushesPlayerLeft() {
		bot.setX(60);
		bot.setY(10);
		
		p.setX(55);
		p.setY(10);
		p2.setX(10);
		p2.setY(10);
		
		blocks.get(0).x = 10;
		blocks.get(0).y = 10;
		blocks.get(1).x = 40;
		blocks.get(1).y = 10;
		blocks.get(2).x = 60;
		blocks.get(2).y = 10;
	
		//Run logic
		world.update();
		//Becomes inactive.
		assertEquals(true, p.getX() < 50);
	}
}
