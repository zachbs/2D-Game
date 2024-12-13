package object;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import entity.Entity;
import main.GamePanel;

public class Object {

	public boolean collision = false;
	public BufferedImage image;
	public String name;
	public boolean interacted = false;
	public int worldX;
	public int worldY;
	public int amount = 0;
	public Rectangle solidArea = new Rectangle(0,0,48,48);
	public int defaultSolidAreaX = 0;
	public int defaultSolidAreaY = 0;
	public int soundCounter = 0;
	public int invX;
	public int invY;
	public int position;
	
	
	public int findId(Entity entity ) {
		int entityId = -1;
		if (entity.name.compareTo("player") == 0) {
			entityId = 0;
		}
		return entityId;
	}
	
	public void interact(Entity entity, GamePanel gp) {
		
	}
	
	public BufferedImage returnImage(String str) {
		BufferedImage image = null;
		try {
		image = ImageIO.read(getClass().getResourceAsStream("/res/" + str + ".png"));
		} catch (Exception e) {
			
		}
		return image;
	}
	
}
