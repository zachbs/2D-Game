package entity;



import java.awt.Color;
import java.awt.Graphics2D;

import main.GamePanel;
import main.KeyHandler;

public class Gun extends Entity {

	
	KeyHandler keyH;
	Player player;
	int bulletVel;
	int playerX;
	int playerY;
	int x;
	int y;
	boolean bulletStop;
	String straightPath;
	
	public Gun (GamePanel gp) {
		super(gp);
		this.keyH = gp.keyH;
		this.player = gp.player;
		worldX = 100;
		worldY = 100;
		x = player.screenX;
		y = player.screenY;
		speed = 5;
		bulletVel = 0;
		playerX = 100;
		playerY = 100;
		bulletStop = true;
		straightPath = "right";
	}


	
	
	public void update() {
		if (keyH.spacePressed == true) {
			if (bulletStop) {
				straightPath = player.direction;
				bulletStop = false;
				playerX = player.worldX;
				playerY = player.worldY;
				x = player.screenX;
				y = player.screenY;
				if (straightPath.compareTo("right") == 0 || straightPath.compareTo("down") == 0) {
					bulletVel += speed;
				} else {
					bulletVel -= speed;
				}
			}
		}
	}
	
	public void draw(Graphics2D g2) {
		g2.setColor(Color.red);
		x = playerX - player.worldX + player.screenX;
		y =  playerY - player.worldY + player.screenY;
		if (bulletVel > 0 && straightPath.compareTo("right") == 0) {
			
			g2.fillRect(x + bulletVel, y, gp.tileSize/2,gp.tileSize/2);
			bulletVel += 8;
			if (bulletVel + x >= gp.screenWidth) {
				bulletVel = 0;
				bulletStop = true;
			}
		} else if (bulletVel < 0 && straightPath.compareTo("left") == 0) {
			g2.fillRect(x + bulletVel, y, gp.tileSize/2,gp.tileSize/2);
			bulletVel -= 8;
			if (bulletVel + x <= (-1 * gp.tileSize) ) {
				bulletVel = 0;
				bulletStop = true;
			}
		} else if (bulletVel < 0 && straightPath.compareTo("up") == 0) {
			g2.fillRect(x, y + bulletVel, gp.tileSize/2,gp.tileSize/2);
			bulletVel -= 8;
			if (bulletVel + y <= (-1 * gp.tileSize)) {
				bulletVel = 0;
				bulletStop = true;
			}
		} else if (bulletVel > 0 && straightPath.compareTo("down") == 0) {
			g2.fillRect(x, y + bulletVel, gp.tileSize/2,gp.tileSize/2);
			bulletVel += 8;
			if (bulletVel + y >= gp.screenHeight) {
				bulletVel = 0;
				bulletStop = true;
			}
		}
	}
}

