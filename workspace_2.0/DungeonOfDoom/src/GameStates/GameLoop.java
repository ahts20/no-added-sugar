package GameStates;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import main.GameGraphics;
import main.Level;
import main.Player;


public class GameLoop extends JPanel implements Runnable{
	
	private boolean running = false;
	private Thread thread;
	
	public Graphics graphics;
	public BufferedImage img;
	
	public GameStateManager gsm;
	
	private int width;
	private int height;
	
	
	public GameLoop(int width, int height) {
		this.width = width;
		this.height = height;
		
		setPreferredSize(new Dimension(width, height));
		setFocusable(false);
		requestFocus();
	}
	
	public void addNotify() {
		super.addNotify();
		if(thread == null){
			thread = new Thread(this);
			thread.start();
		}
	}
	
	
	public void run() {
		init();
		long lastTime = System.nanoTime();
		final double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
	
		double delta = 0;
		int updates = 0;
		int frames = 0;
		long timer = System.currentTimeMillis();
	
		while(running){

			//It takes time to get from lastTime to now
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			
			boolean shouldRender = false;
			
			while(delta >= 1){
				update();
				updates++;
				//Gets out of the if statements
				delta--;
				shouldRender = true;
			}
			
			if(shouldRender == true){
				frames++;
				render();
			}
			
			//sleepy
			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			//every second just to display
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				//System.out.println(updates);
				updates = 0;
				frames = 0;
			}
		
		}

	}
	
	public void init(){		
		gsm = new GameStateManager();
		gsm.init();
		
		running = true;
	}
	
	public void update(){
		gsm.update();
	}
	
	public void render(){
		graphics = getGraphics();
		super.paintComponent(graphics);
		this.setBackground(Color.BLACK);
		
		gsm.render(graphics);
	}
		
}
