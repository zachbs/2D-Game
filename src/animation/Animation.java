package animation;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Animation {
	public int x;
	public int y;
	public int defaultX, defaultY, speed;
	public BufferedImage image[];
	public int width;
	public int height;
	public float damage;
	public short effect;
	public float accuracy;
	
	public Animation(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.defaultY = y;
		this.defaultX = x;
		this.width = width;
		this.height = height;
				
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
