package animation;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import main.UI;

public class ShieldAnimation extends Animation {

	public ShieldAnimation(int x, int y, int width, int height, UI ui) {
		super(x,y,width,height, ui);
		image = new BufferedImage[1];
		image[0] = returnImage("shield_wood");
		counter = 0;
		finished = false;
	}
	
	public void animate(Graphics2D g2) {
		if (counter < 75) {
			g2.drawImage(image[0], x, y, width, height, null);
			int alpha = 30 + counter * 3;
			g2.setColor(new Color(169,165,165,alpha));
			g2.fillRect(x, y, width, height);
			counter++;
		} else {
			finished = true;
		}
	}
	
	
}
