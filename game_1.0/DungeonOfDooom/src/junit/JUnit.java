package junit;

import static org.junit.Assert.*;
import main.Player;

import org.junit.Test;

public class JUnit {
	
	private Player player = new Player();
	
	@Test
	public void testPlayer() {
		int width = player.playerWidth;
		int height = player.playerHeight;
		assertEquals(20, width);
		assertEquals(20, height);
	}	

}
