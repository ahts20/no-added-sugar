package main;

import java.awt.image.BufferedImage;

public class SpriteSheet {
	private BufferedImage image;

	public SpriteSheet(BufferedImage image) {
		this.image = image;
	}

	public BufferedImage grabImage(int col, int row, int width, int height) {
		BufferedImage img = image.getSubimage((col * 180 / 4), (row * 180 / 4), width, height);
		return img;

	}
}
