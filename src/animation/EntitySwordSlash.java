package animation;

import java.awt.image.BufferedImage;

public class EntitySwordSlash extends Animation {
	
	
	
	public EntitySwordSlash (int x, int y) {
		super(x,y);
		image = new BufferedImage[1];
		image[0] = returnImage("EntitySwordSlash2");
		this.speed = 9;
	}
}
