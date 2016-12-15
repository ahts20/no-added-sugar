
import static org.junit.Assert.*;

import java.awt.image.BufferedImage;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import main.SpriteSheet;
import main.loadImage;

public class SpriteSheetTest {

	private BufferedImage image;
	private loadImage loader;
	private SpriteSheet spriteSheet;

	@Before
	public void before() throws IOException {
		loader = new loadImage();
		image = loader.LoadImageFrom("/SpriteSheet(3).png");
		spriteSheet = new SpriteSheet(image);
	}

	@Test
	public void testGrabImage() {
		BufferedImage temp = spriteSheet.grabImage(0, 0, 45, 45);
		assertNotNull(spriteSheet);
		assertNotNull(temp);
	}

}
