package JUnit;
import static org.junit.Assert.*;

import javax.swing.JFrame;

import org.junit.After;
import org.junit.Test;

import main.Main;
import Managers.GameLoop;
import Managers.GameStateManager;

public class JUnitGameLoopTest extends JFrame{
	private static final long serialVersionUID = 1L;
	private GameLoop gl;
	
	public JUnitGameLoopTest() {
		//JFrame
		add(gl = new GameLoop(main.Main.width, main.Main.height));
		setTitle("JUNIT");
		setSize(Main.width, Main.height);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	}
	
	@Test
	public void testAddNotify() {
		assertNotNull(gl.thread);
	}

	@Test
	public void testGameLoop() {
		assertEquals(Main.width, gl.width);
		assertEquals(Main.height, gl.height);
	}

	@Test
	public void testRun() {
		//sleepy
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		assertEquals(60, gl.fps, 5);
	}
	
	@Test
	public void testInit() {
		assertNotNull(gl.off_screen_gr_img);
		assertEquals(true, gl.running);
	}

	@Test
	public void testUpdate() {
		assertNotNull(GameStateManager.states);
		assertNotNull(GameStateManager.states.peek());
	}

	@SuppressWarnings("deprecation")
	@After
	public void cleanIndex() {
	    gl.thread.stop();
	}

}
