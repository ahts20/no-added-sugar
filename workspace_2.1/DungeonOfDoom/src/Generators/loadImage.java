package Generators;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class loadImage {

	private BufferedImage image;

	/**
	 * the method that take a resource path and read the image
	 * 
	 * @param path
	 *            the configure path of the image
	 * @return an image from the configure path in argument
	 * @throws IOException
	 *             if it doesn't get any image of wrong input of path, it will
	 *             throw an IOException
	 */
	public BufferedImage LoadImageFrom(String path) throws IOException {
		image = ImageIO.read(getClass().getResource(path));
		return image;
	}
}
