import static org.junit.Assert.*;

import java.util.concurrent.CopyOnWriteArrayList;

import org.junit.Test;

import GameStates.GameStateManager;
import main.Block;
import main.Block.BlockType;
import main.Bot;
import main.LevelLoader;
import main.Player;
import main.World;

public class JUnitGold {

	@Test
	public void goldExists() {
		Block b = new Block(0, 0, 10, BlockType.GOLD);
		assertEquals(true, b.gold);
	}

	@Test
	public void doorExists() {
		Block b = new Block(0, 0, 10, BlockType.DOOR);
		assertEquals(true, b.door);
	}

	@Test
	public void PlayerPicksUpGoldEventSequence() {
		// place block
		Block b = new Block(10, 10, 10, BlockType.GOLD);
		// place door
		Block d = new Block(100, 100, 10, BlockType.DOOR);
		// Make sure they initialise correctly
		assertEquals(true, b.gold);
		assertEquals(true, d.door);
		// Create block object for update functions.
		CopyOnWriteArrayList<Block> blocks = new CopyOnWriteArrayList<Block>();
		blocks.add(b);
		blocks.add(d);
		// Place player
		Player p = new Player();
		// Check start score is 0.
		assertEquals(true, p.getScore() == 0.0);
		// Check door is not visible
		assertEquals(false, d.isVisible);

		// Create Level loader object and add player and blocks.
		World world = new World("testWorld", new GameStateManager());
		world.player1 = p;
		world.blocks = blocks;
		world.bot = new Bot();

		// Change pos of player to gold block location.
		p.setX(10);
		p.setY(10);
		// Update game logic
		world.update();

		// Check if score increased by 10.
		assertEquals(true, p.getScore() == 10.0);
		// Check if gold became floor object
		assertEquals(true, b.rectangle);
		assertEquals(false, b.gold);

		// Check if door is visible now player has picked up the gold
		assertEquals(true, d.isVisible);

	}

}
