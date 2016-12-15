package Generators;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class loadImage {

	private BufferedImage image;

	public BufferedImage LoadImageFrom(String path) throws IOException {
		image = ImageIO.read(getClass().getResource(path));
		return image;
	}
}
