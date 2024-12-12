package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
	
	public boolean upPressed, downPressed, rightPressed, leftPressed, spacePressed, ePressed, escapePressed, iPressed;
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		
		if (code == KeyEvent.VK_W) {
			upPressed = true;
				
		} 
		if (code == KeyEvent.VK_S) {
			downPressed = true;
		} 
		if (code == KeyEvent.VK_D) {
			rightPressed = true;
				
		} 
		if (code == KeyEvent.VK_A) {
			leftPressed = true;
				
		}	
		if (code == KeyEvent.VK_SPACE) {
		spacePressed = true;
		}
		
		if (code == KeyEvent.VK_E) {
			ePressed = true;
		}
		if (code == KeyEvent.VK_ESCAPE) {
			escapePressed = true;
		}
		if (code == KeyEvent.VK_I) {
			iPressed = true;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		if (code == KeyEvent.VK_W) {
			upPressed = false;
			
		}
		if (code == KeyEvent.VK_S) {
			downPressed = false;
			
		}
		if (code == KeyEvent.VK_D) {
			rightPressed = false;
			
		}
		if (code == KeyEvent.VK_A) {
			leftPressed = false;
			
		}
		if (code == KeyEvent.VK_SPACE) {
			spacePressed = false;
		}
		if (code == KeyEvent.VK_E) {
			ePressed = false;
		}
		if (code == KeyEvent.VK_ESCAPE) {
			escapePressed = false;
		}
		if (code == KeyEvent.VK_I) {
			iPressed = false;
		}
	}

}
