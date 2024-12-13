package main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseHandler implements MouseListener{

	boolean mouseClicked, mouseReleased;
	@Override
	public void mouseClicked(MouseEvent e) {
	
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		mouseClicked = true;
		mouseReleased = false;
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
		mouseClicked = false;
		mouseReleased = true;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
