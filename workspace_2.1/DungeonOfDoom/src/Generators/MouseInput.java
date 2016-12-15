package Generators;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseInput implements MouseListener, MouseMotionListener {
	// Declared mouse coordinates
	public static int mouseMovedX, mouseMovedY;
	// Declared Point to record mouse coordinates
	public static Point mouse;
	// Declared boolean to judge whether mouse is pressed or not
	public static boolean pressed;

	/**
	 * update the mouse coordinates
	 */
	public void update() {
		mouse = new Point(mouseMovedX, mouseMovedY);
	}

	public void render(Graphics g) {
		// g.fillRect(mouseMovedX, mouseMovedY, 4, 4);
	}

	/**
	 * if mouse is pressed, then change pressed to true
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			pressed = true;
		}
	}

	/**
	 * if mouse is released, then change pressed to false
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			pressed = false;
		}

	}

	/**
	 * if mouse coordinates changed, then update them to mouseMoved
	 */
	@Override
	public void mouseDragged(MouseEvent e) {
		mouseMovedX = e.getX();
		mouseMovedY = e.getY();
	}

	/**
	 * if mouse coordinates changed, then update them to mouseMoved
	 */
	@Override
	public void mouseMoved(MouseEvent e) {
		mouseMovedX = e.getX();
		mouseMovedY = e.getY();
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	/**
	 * mouse x-coordinate getter
	 * 
	 * @return mouse new x-coordinate
	 */
	public static int getMouseMovedX() {
		return mouseMovedX;
	}

	/**
	 * mouse x-coordinate setter
	 * 
	 * @param mouseMovedX
	 *            the mouse x-coordinate expect to change
	 */
	public static void setMouseMovedX(int mouseMovedX) {
		MouseInput.mouseMovedX = mouseMovedX;
	}

	/**
	 * mouse y-coordinate getter
	 * 
	 * @return mouse new y-coordinate
	 */
	public static int getMouseMovedY() {
		return mouseMovedY;
	}

	/**
	 * mouse y-coordinate setter
	 * 
	 * @param mouseMovedY
	 *            the mouse y-coordinate expect to change
	 */
	public static void setMouseMovedY(int mouseMovedY) {
		MouseInput.mouseMovedY = mouseMovedY;
	}

	/**
	 * getter
	 * 
	 * @return pressed value
	 */
	public static boolean isPressed() {
		return pressed;
	}

	/**
	 * setter
	 * 
	 * @param pressed
	 *            set the value of pressed
	 */
	public static void setPressed(boolean pressed) {
		MouseInput.pressed = pressed;
	}

}
