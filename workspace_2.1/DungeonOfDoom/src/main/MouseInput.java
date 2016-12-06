package main;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import GameStates.GameLoop;
import GameStates.GameLoop.STATE;

public class MouseInput implements MouseListener {

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		int mx = e.getX();
		int my = e.getY();

		// Play Button
		if (mx >= Main.width / 3 + 150 && mx <= Main.width / 3 + 250) {
			if (my >= 150 && my <= 200) {
				// Pressed Play Button
				GameLoop.State = GameLoop.STATE.GAME;
			}
		}

		// Quit Button
		if (mx >= Main.width / 3 + 150 && mx <= Main.width / 3 + 250) {
			if (my >= 350 && my <= 400) {
				// Pressed Quit Button
				System.exit(1);
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
