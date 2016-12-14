
import static org.junit.Assert.*;

import java.awt.image.BufferedImage;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import main.SpriteSheet;
import main.loadImage;

public class SpriteSheetTest {

	private BufferedImage image;

	@Test
	public void testGrabImage() throws IOException {
		loadImage loader = new loadImage();
		image = loader.LoadImageFrom("/SpriteSheet(3).png");
		SpriteSheet spriteSheet = new SpriteSheet(image);
		BufferedImage temp = spriteSheet.grabImage(0, 0, 45, 45);
		assertNotNull(spriteSheet);
		assertNotNull(temp);
	}

}
