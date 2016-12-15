package Generators;

import java.awt.image.BufferedImage;

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
