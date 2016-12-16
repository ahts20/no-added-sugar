package Generators;
import java.awt.image.BufferedImage;
/**
 * Loads the image using the loadImageFrom() class.
 * and subsequently splits the image into required parts in order to create a matrix like layout to be used for animations.
 * Helps to store the parts in the BufferedImageArray in the Player class to be called when needed. 
 *
 * @author anonymous
 * @version 1.0
 * @release 16/12/2016
 * @See SpriteSheet.java
 */
public class SpriteSheet {
	private BufferedImage image;

	/**
	 * Constructor. Sets the field values. Get an argument in BufferedImage type
	 * 
	 * @param image
	 *            give an image when an object is created
	 */
	public SpriteSheet(BufferedImage image) {
		this.image = image;
	}

	/**
	 * The method that grab the image using location of the sub-image and size
	 * of the sub-image
	 * 
	 * @param col
	 *            column index of sub-image
	 * @param row
	 *            row index of sub-image
	 * @param width
	 *            width of sub-image
	 * @param height
	 *            height of sub-image
	 * @return returns the sub-image which is grabbed from the input image
	 */
	public BufferedImage grabImage(int col, int row, int width, int height) {
		// Our sprite sheet size is 180*180 in a 4*4 matrix, using method
		// getSubimage to grab image from the original image
		BufferedImage img = image.getSubimage((col * 180 / 4), (row * 180 / 4), width, height);
		return img;

	}
}
