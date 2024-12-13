package animation;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import main.UI;

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
	public int counter;
	public boolean finished;
	UI ui;
	
	public Animation(int x, int y, int width, int height, UI ui) {
		this.x = x;
		this.y = y;
		this.defaultY = y;
		this.defaultX = x;
		this.width = width;
		this.height = height;
		this.ui = ui;
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
	
	public void animate(Graphics2D g2) {
		
	}
	
}
