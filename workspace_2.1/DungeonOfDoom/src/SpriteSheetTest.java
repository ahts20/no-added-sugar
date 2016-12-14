import static org.junit.Assert.*;

import java.awt.image.BufferedImage;

import org.junit.Before;
import org.junit.Test;

import main.SpriteSheet;

public class SpriteSheetTest {
	
	private BufferedImage image=null;

	@Before
	public void setUp() throws Exception {
		SpriteSheet spriteSheet = new SpriteSheet(image);
	}

	@Test
	public void testSpriteSheet(SpriteSheet spriteSheet) {		
		assertEquals(null, spriteSheet);
		fail("Not yet implemented");
	}

	@Test
	public void testGrabImage() {
		fail("Not yet implemented");
	}

}
