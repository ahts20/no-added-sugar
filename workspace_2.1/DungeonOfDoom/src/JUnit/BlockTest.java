package JUnit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Generators.Block;
import Generators.Block.BlockType;

public class BlockTest {

	private Block block;

	@Before
	public void before() {
		block = new Block(100, 100, 5, BlockType.GOLD);
	}

	@Test
	public void testInit() {
		assertTrue(block.gold);
		block = new Block(100, 100, 5, BlockType.DOOR);
		assertTrue(block.door);
		block = new Block(100, 100, 5, BlockType.RECTANGLE);
		assertTrue(block.rectangle);
		block = new Block(100, 100, 5, BlockType.WALL);
		assertTrue(block.wall);
	}

	@Test
	public void testChangeGoldToFloor() {
		block.changeGoldToFloor();
		assertTrue(block.rectangle);
		assertFalse(block.gold);
		assertFalse(block.wall);
	}

}
