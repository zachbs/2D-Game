package animation;

import java.awt.image.BufferedImage;

public class EntitySwordSlash extends Animation {
	
	
	
	public EntitySwordSlash (int x, int y, int width, int height) {
		super(x,y,width, height);
		image = new BufferedImage[1];
		image[0] = returnImage("EntitySwordSlash2");
		this.speed = 9;
		effect = 0;
		damage = 1.75f;
		accuracy = 0.90f;
	}
}
