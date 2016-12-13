package GameStates;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
/**
* GameLoop is the most important function of the game , called when the game is started. 
* Creates an infinite loop updating the update() and render() function 60 times a second (60 FPS).
* Uses GameStateManager class in order to connect to all the other declared states (LevelLoader, MenuState).
* Runs on a separate Thread.
* (Extends JPanel container and Implements Runnable)
*
* @version 1.0
* @release 13/12/2016
* @See GameLoop.java
*/
public class GameLoop extends JPanel implements Runnable {
	/*
	It is wise to declare it because if you don't declare one then when changing 
	the class it will get a different one generated automatically and the 
	serialisation will stop working.
	*/
	private static final long serialVersionUID = 1L;
	//Declared booleans (Infinite Loop)
	public boolean running = false;
	public boolean shouldRender = false;
	//Declaring new thread
	public Thread thread;
	//Declared Graphics
	public Graphics graphics;
	public Graphics g2;
	//Declaring Image to draw graphics on 
	public BufferedImage off_screen_gr_img;
	//Declaring the class used
	public GameStateManager gsm;
	//Declared integers
	public int width;
	public int height;
	public int updates = 0;
	public int frames = 0;
	public int fps;
	public int tps;
	/**
	 * Constructor. Sets the field values. Uses Width and Height to set new Dimension.
	 * @param width
	 * 		Specifies the width of the Dimension
	 * @param height
	 * 		Specifies the height of the Dimension.
	 */
	public GameLoop(int width, int height) {
		this.width = width;
		this.height = height;

		setPreferredSize(new Dimension(width, height));
		setFocusable(false);
		requestFocus();
	}
	/**
	 * Works with JFrame to start the new Thread using the JFrame
	 */
	public void addNotify() {
		super.addNotify();
		if (thread == null) {
			thread = new Thread(this);
			thread.start();
		}
	}
	/**
	 * Runs when the thread is started. The method that updates the program (60 FPS)
	 */
	public void run() {
		init();
		long lastTime = System.nanoTime();
		final double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;

		double delta = 0;
		long timer = System.currentTimeMillis();

		while (running) {

			//It takes time to get from lastTime to now
			long now = System.nanoTime();
			//Sets delta to be the ratio between right now and the total number
			//of frames desired within a second.
			//Calls update for each of these times.
			delta += (now - lastTime) / ns;
			lastTime = now;
			//Calls update 60 times a second
			while (delta >= 1) {
				updates++;
				update();
				delta--;
				shouldRender = true;
			}
			//Calls render 60 times a second
			if (shouldRender == true) {
				frames++;
				render();
			}

			//Sleepy
			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			//Every second just to display and test (JUnit)
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				fps = updates;
				tps = frames;
				updates = 0;
				frames = 0;
			}
		}
	}

	public void init() {
		// Making the off_screen_gr_img into a canvas to draw graphics on
		off_screen_gr_img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		// Setting graphics variable to allow the canvas to be an image
		// This STOPS the flicker as well as allows for lesser lag (Memory
		// Management)
		graphics = off_screen_gr_img.createGraphics();

		running = true;
		// Initialising GameStateManager
		gsm = new GameStateManager();
		gsm.init();

	}

	public void update() {
		// Ticking the GameStateManager
		gsm.update();
	}

	public void render() {
		// Clearing the canvas for further drawing
		graphics.clearRect(0, 0, width, height);
		
		// GameStateManager allowing to input graphics from other

		gsm.render(graphics);
		
		// Drawing and disposing of the image by using the loop
		// This is where the graphics are drawn on the screen
		drawImage();
	}

	public void drawImage() {
		// Initialising new graphics
		g2 = getGraphics();
		if (off_screen_gr_img != null) {
			// Drawing the image on the screen, this will contain graphics taken
			// from other classes
			// e,g, Player, Map etc.
			g2.drawImage(off_screen_gr_img, 0, 0, null);
		}
		// Disposing of the image, redrawing the image on the screen
		g2.dispose();
	}

}
