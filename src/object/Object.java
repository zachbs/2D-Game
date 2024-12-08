package object;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import entity.Entity;
import main.GamePanel;

public class Object {

	public boolean collision = false;
	BufferedImage image;
	String name;
	public boolean interacted = false;
	public int worldX;
	public int worldY;
	static int amount;
	public Rectangle solidArea = new Rectangle(0,0,48,48);
	public int defaultSolidAreaX = 0;
	public int defaultSolidAreaY = 0;
	public int soundCounter = 0;
	
	
	public int findId(Entity entity ) {
		int entityId = -1;
		if (entity.name.compareTo("player") == 0) {
			entityId = 0;
		}
		return entityId;
	}
	
	public void interact(Entity entity, GamePanel gp) {
		
	}
	
	
}
