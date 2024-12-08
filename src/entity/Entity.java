package entity;


import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import main.GamePanel;
public class Entity {
	
	public String name;
	public int worldX,worldY;
	public int speed;
	public BufferedImage up1, up2, down1, down2, right1, right2, left1, left2;
	public String direction;
	public int spriteCounter = 0;
	public int spriteNum = 1;
	public Rectangle solidArea;
	public int defaultSolidAreaX;
	public int defaultSolidAreaY;
	public boolean collisionOn = false;
	public boolean collision = true;
	public int maxHp;
	public String moves[];
	public int moveNum;
	public int agility;
	public int hp;
	public int level;
	public int xp = 0;
	GamePanel gp;
	
	
	public Entity(GamePanel gp) {
		this.gp = gp;
		moves = new String[4];
	}
	
	public BufferedImage setImage(String link) {
		try {
		return ImageIO.read(getClass().getResourceAsStream(link));
		} catch (Exception e) {
			System.out.println("did not work at setImage Entity");
			return null;
		}
	}
	public void setAction() {}
	
	public void update(int index) {
		setAction();
		
		boolean hit;
	
		
		collisionOn = false;
		gp.cChecker.checkTile(this);
		for (int i = 0; i < gp.entSet.entities.length; i++) {
			if (gp.entSet.entities[i] != null && i != index) {
				hit = gp.cChecker.checkEntity(this, gp.entSet.entities[i]);
				if (hit && i == 0) {
					System.out.println(gp.entSet.entities[index].name + " hit you!");
					
					gp.ui.entityIndex = index;
					if (gp.player.agility > this.agility ) {
						gp.ui.playerTurn = true;
					}
					gp.gameStatePlay = false;
					gp.ui.battleScreenOn = true;
					
				}
			}
			
		}
		if (collisionOn == false) {
			if (direction.compareTo("up") == 0) {
				worldY -= speed;
				
			}
			if (direction.compareTo("down") == 0) {
				worldY += speed;
						
			}
			if (direction.compareTo("right") == 0) {
				worldX += speed;
			}
			if (direction.compareTo("left") == 0) {
				worldX -= speed;
			}
		}
		spriteCounter++;
		if (spriteCounter > 13) {
			if (spriteNum == 1) {
				spriteNum = 2;
			} else {
				spriteNum = 1;
			}
			spriteCounter = 0;
		}
		
		
	}
	
	public void draw(Graphics2D g2) {
		int screenX = this.worldX - gp.player.worldX + gp.player.screenX;
		int screenY = this.worldY - gp.player.worldY + gp.player.screenY;
		BufferedImage image = null;
		
		if (direction.compareTo("right") == 0) {
			if(spriteNum == 1) {
				image = right1;
			} else if(spriteNum == 2) {
				image = right2;
			}
		} else if (direction.compareTo("left") == 0) {
			if(spriteNum == 1) {
				image = left1;
			} else if(spriteNum == 2) {
				image = left2;
			}
			
		} else if (direction.compareTo("up") == 0) {
			if(spriteNum == 1) {
				image = up1;
			} else if(spriteNum == 2) {
				image = up2;
			}
		} else if (direction.compareTo("down") == 0) {
			if(spriteNum == 1) {
				image = down1;
			} else if(spriteNum == 2) {
				image = down2;
			}
		}
		
		
		g2.drawImage(image,screenX, screenY, gp.tileSize, gp.tileSize, null);
		
		
		
	}
	
	public void setStats(int worldX, int worldY, int level, String moves[]) {
		this.worldX = worldX;
		this.worldY = worldY;
		this.moves = moves;
		this.level = level;
	}
}

