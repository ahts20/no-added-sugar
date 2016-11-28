import static org.junit.Assert.*;

import java.util.concurrent.CopyOnWriteArrayList;

import org.junit.Test;

import main.Block;
import main.Block.BlockType;
import main.Player;

public class JUnitGold {

	@Test
	public void goldExists() {
		Block b = new Block(0,0,10,BlockType.GOLD);
		assertEquals(true, b.gold);
	}
	
	@Test
	public void PlayerPicksUp() {
		//place block
		Block b = new Block(10,10,10,BlockType.GOLD);
		assertEquals(true, b.gold);
		//Place player
		Player p = new Player();
		//Check start score is 0.
		assertEquals(true, p.getScore() == 0.0);
		//Change pos to gold block and update.
		p.setX(10);
		p.setY(10);
		CopyOnWriteArrayList<Block> blocks = new CopyOnWriteArrayList<Block>();
		blocks.add(b);
		p.update(blocks);
		//Check score increase by 10.
		assertEquals(true, p.getScore() == 10.0);
	}

}
