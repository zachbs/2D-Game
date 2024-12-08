package animation;

import java.awt.image.BufferedImage;

public class PlayerSwordSlash extends Animation {
	
	
	
	public PlayerSwordSlash (int x, int y) {
		super(x,y);
		image = new BufferedImage[1];
		image[0] = returnImage("SwordSlash2");
		this.speed = 9;
	}
}
