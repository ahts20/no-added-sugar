package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class GameGraphics extends JPanel {

	private int width;
	private int height;

	/*
	 * public GameGraphics(int width, int height){ this.width = width;
	 * this.height = height;
	 * 
	 * setPreferredSize(new Dimension(width, height)); setFocusable(false);
	 * requestFocus(); }
	 */

	public void render(Graphics g) {
		super.paintComponent(g);
		this.setBackground(Color.BLACK);
		g.setColor(Color.WHITE);

	}
}
