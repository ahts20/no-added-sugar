import java.awt.Graphics;

import org.junit.BeforeClass;
import org.junit.Test;

import GameStates.GameStateManager;
import main.MenuState;

public class MenuStateTest {
	
	private static MenuState menu;
	private static GameStateManager gsm;
	
	@BeforeClass
	public static void beforeClass(){		
		menu = new MenuState(gsm);
	}
	
	@Test
	public void testInit() {
		menu.init();
	}

	@Test
	public void testUpdate() {
		menu.update();
	}

//	@Test
//	public void testRender() {
//		Graphics g = null;
//		menu.render(g);
//	}

}
