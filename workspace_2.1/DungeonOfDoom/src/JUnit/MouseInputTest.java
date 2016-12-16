package JUnit;
import static org.junit.Assert.*;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Managers.GameLoop;
import main.GameWindow;
import main.Main;
import Generators.MouseInput;

public class MouseInputTest extends JFrame implements MouseListener{
	
	private MouseInput mi;
	private main.GameWindow gw;
	private static final long serialVersionUID = 1L;
	private GameLoop gl;

	public MouseInputTest(){
		//JFrame
		add(gl = new GameLoop(main.Main.width, main.Main.height));
		setTitle("JUNIT");
		setSize(main.Main.width, main.Main.height);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		mi = new MouseInput();
		gw.addMouseListener(mi);
	}

	@Test
	public void testUpdate() {
		mi.update();
	}

	@Test
	public void testRender() {
		Graphics g = null;
		mi.render(g);
	}

	@Test
	public void testMousePressed(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1){
			assertTrue(mi.isPressed());
		}
	}

	@Test
	public void testMouseReleased(MouseEvent e) {
		mi.mouseReleased(e);
		assertFalse(mi.isPressed());
	}

	@Test
	public void testMouseDragged(MouseEvent e) {
		mi.mouseDragged(e);
		assertEquals(e.getX(), mi.getMouseMovedX());
		assertEquals(e.getY(), mi.getMouseMovedY());
	}

	@Test
	public void testMouseMoved(MouseEvent e) {
		mi.mouseMoved(e);
		assertEquals(e.getX(), mi.getMouseMovedY());
		assertEquals(e.getY(), mi.getMouseMovedY());
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

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
