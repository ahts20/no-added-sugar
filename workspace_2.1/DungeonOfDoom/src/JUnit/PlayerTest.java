package JUnit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import MovableObjects.Player;
import MovableObjects.Player1;

public class PlayerTest {

	private Player player;
	private String direction;

	@Before
	public void before() {
		player = new Player1();
		player.init(10, 10);
	}

	@Test
	public void testMoveCords() {
		direction = "RIGHT";
		player.moveCords(5, direction);
		assertTrue(15 == player.getX());
		direction = "LEFT";
		player.moveCords(5, direction);
		assertTrue(10 == player.getX());
		direction = "DOWN";
		player.moveCords(5, direction);
		assertTrue(15 == player.getY());
		direction = "UP";
		player.moveCords(5, direction);
		assertTrue(10 == player.getY());
	}

	@Test
	public void testInitFloatFloat() {
		player.init(100, 150);
		assertTrue(100 == player.getX());
		assertTrue(150 == player.getY());
	}

}
