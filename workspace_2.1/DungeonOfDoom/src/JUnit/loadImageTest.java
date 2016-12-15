package JUnit;
import static org.junit.Assert.*;

import java.awt.image.BufferedImage;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import Generators.loadImage;

public class loadImageTest {

	private BufferedImage image;
	private loadImage loader;
	
	@Before
	public void before(){
		loader = new loadImage();
	}

	@Test
	public void testLoadImageFrom() throws IOException {
		image = loader.LoadImageFrom("/SpriteSheet(3).png");
		assertNotNull(image);
	}

}
