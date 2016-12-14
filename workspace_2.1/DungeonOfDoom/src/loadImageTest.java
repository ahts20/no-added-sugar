import static org.junit.Assert.*;

import java.awt.image.BufferedImage;
import java.io.IOException;

import org.junit.Test;

import main.loadImage;

public class loadImageTest {

	private BufferedImage image;

	@Test
	public void testLoadImageFrom() throws IOException {
		loadImage loader = new loadImage();
		image = loader.LoadImageFrom("/SpriteSheetl(3).png");
		assertNotNull(image);
	}

}
