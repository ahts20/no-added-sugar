package JUnit;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.junit.Before;
import org.junit.Test;

import Generators.World;

public class WorldTest {
	private World world;

	@Before
	public void before() {
		world = new World(null);
	}

	@Test
	public void testInit() {
		world.init();
	}

	@Test
	public void testResetWorld() {
		world.resetWorld();
	}

	@Test
	public void testSavePlayer1Score() throws FileNotFoundException {
		world.savePlayer1Score();
		FileReader fr = new FileReader("res/scorePlayer1.txt");
		assertNotNull(fr);
	}

	@Test
	public void testSavePlayer2Score() throws FileNotFoundException {
		world.savePlayer2Score();
		FileReader fr = new FileReader("res/scorePlayer2.txt");
		assertNotNull(fr);
	}

}
