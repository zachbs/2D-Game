package animation;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Animation {
	public int x;
	public int y;
	public int defaultX, defaultY, speed;
	public BufferedImage image[];
	int width;
	int height;
	
	public Animation(int x, int y) {
		this.x = x;
		this.y = y;
		this.defaultY = y;
		this.defaultX = x;
		
				
	}
	
	public BufferedImage returnImage (String text) {
		BufferedImage image = null;
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/res/" + text + ".png"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return image;
	}
	
}
