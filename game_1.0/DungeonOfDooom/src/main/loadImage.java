package main;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class loadImage {
	//This finds an image in the resource folder and converts it to a buffered image class, which can be displayed on the screen.
	private BufferedImage image;
	
	public BufferedImage LoadImageFrom(String path) throws IOException{
		image = ImageIO.read(getClass().getResource(path));
		
		return image;
	}
}
