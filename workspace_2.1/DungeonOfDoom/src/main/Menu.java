/**
 * 
 */
package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 * @author think
 *
 */
public class Menu {
	public Rectangle playButton = new Rectangle(Main.width / 3 + 150, 150, 100, 50);
	public Rectangle helpButton = new Rectangle(Main.width / 3 + 150, 250, 100, 50);
	public Rectangle quitButton = new Rectangle(Main.width / 3 + 150, 350, 100, 50);
	
	
	public void render(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		
		Font fnt0 = new Font("arial", Font.BOLD, 50);
		g.setFont(fnt0);
		g.setColor(Color.white);
		g.drawString("Dungeon Of Dooom", Main.width/3, 100);
		
		Font fnt1 = new Font("arial", Font.BOLD, 30);
		g.setFont(fnt1);
		g.drawString("Play", playButton.x+20, playButton.y+35);
		g2d.draw(playButton);
		g.drawString("Help", helpButton.x+20, helpButton.y+35);
		g2d.draw(helpButton);
		g.drawString("Quit", quitButton.x+20, quitButton.y+35);
		g2d.draw(quitButton);
	}

}
