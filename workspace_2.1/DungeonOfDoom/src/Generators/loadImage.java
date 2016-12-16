package Generators;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
/**
 * loadImage imports image files into the program to be called where required.  
 *
 * @author anonymous
 * @version 1.0
 * @release 16/12/2016
 * @See loadImage.java
 */
public class loadImage {

	private BufferedImage image;

	/**
	 * Takes a resource path and reads the image.
	 * 
	 * @param path
	 *      the configure path of the image
	 * @return 
	 * 		an image from the configure path in argument
	 * @throws IOException
     *      if it doesn't get any image of wrong input of path, it will
     *      throw an IOException
	 */
	public BufferedImage LoadImageFrom(String path) throws IOException {
		image = ImageIO.read(getClass().getResource(path));
		return image;
	}
}
