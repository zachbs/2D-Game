package entity;


import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity{

	
	KeyHandler keyH;
	boolean facingRight;
	public int screenX;
	public int screenY;
	boolean disableRight = false;
	boolean disableLeft = false;
	boolean disableUp = false; 
	boolean disableDown = false;
	public int nextLevel;
	
	
	public int keysObtained;
	int soundCounter = 0;
	
	
	
	public Player (GamePanel gp) {
		super(gp);
		this.keyH = gp.keyH;
		setDefaultValues();
		this.solidArea = new Rectangle();
		solidArea.x = 8;
		solidArea.y = 16;
		defaultSolidAreaX = solidArea.x;
		defaultSolidAreaY = solidArea.y;
		solidArea.width = 32;
		solidArea.height = 32;
		maxHp = 15;
		agility = 3;
		hp = maxHp;
		level = 1;
		nextLevel = 30;
		
		keysObtained = 0;
		name = "player";
		getPlayerImage();
		moves = new String[4];
		moves[0] = "SwordSlash";
		
	}
	
	public void setDefaultValues() {
		this.speed = 4;
		this.worldX = gp.tileSize * 23;
		this.worldY = gp.tileSize * 21;
		this.direction = "down";
		this.screenX = gp.screenWidth/2 - (gp.tileSize/2);
		this.screenY = gp.screenHeight/2 - (gp.tileSize/2);
		 
	}
	
	public void update(int index) {
		if (xp >= nextLevel) {
			xp = xp - nextLevel;
			level++;
		}
		
		if (keyH.upPressed == true && disableUp != true) {
			direction = "up";
		} else if (keyH.downPressed == true && disableDown != true) {
			direction = "down";
		} else if (keyH.rightPressed == true && disableRight != true) {
			 direction = "right";
			 this.facingRight = true;
		} else if (keyH.leftPressed == true && disableLeft != true) {
			direction = "left";
		}
		
	
		if (keyH.upPressed == true || keyH.downPressed == true || keyH.rightPressed == true || keyH.leftPressed == true) {
			spriteCounter++;
			if (spriteCounter > 11) {
				if (spriteNum == 1) {
					spriteNum = 2;
				} else {
					spriteNum = 1;
				}
				spriteCounter = 0;
			}
		}
		
		
		disableUp = false;
		disableDown = false;
		disableRight = false;
		disableLeft = false;
		collisionOn = false;
		boolean monsterHit = false;
		gp.cChecker.checkTile(this);
		for (int i = 1; i < gp.entSet.entities.length; i++) {
			if (gp.entSet.entities[i] != null) {
				monsterHit = gp.cChecker.checkEntity(this, gp.entSet.entities[i]);
				if (monsterHit) {
					gp.ui.entityIndex = i;
					if (gp.player.agility > gp.entSet.entities[i].agility ) {
						gp.ui.playerTurn = true;
					}
					System.out.println(gp.entSet.entities[i].name + " hit you!");
					gp.gameStatePlay = false;
					gp.ui.battleScreenOn = true;
				}
			}
			
		}
		int objIndex = gp.cChecker.checkObject(this,true);
		 if (collisionOn == false) {
			 soundCounter = 0;
			
			if (keyH.upPressed == true && disableUp != true) {
				worldY -= speed;
				disableRight = true;
				disableLeft = true;
				
			}
			if (keyH.downPressed == true && disableDown != true) {
				worldY += speed;
				disableRight = true;
				disableLeft = true;
						
			}
			if (keyH.rightPressed == true && disableRight != true) {
				worldX += speed;
			}
			if (keyH.leftPressed == true && disableLeft != true) {
				worldX -= speed;
			}
			
		} else if (monsterHit = false) {
			if (soundCounter == 0) {
				gp.playSE(4);
				soundCounter++;
			} else if (soundCounter > 60) {
				soundCounter = 0;
			} else {
				soundCounter++;
			}
		}
		
		
		
		if (gp.maxWorldWidth - worldX - screenX - gp.tileSize  <= 0) {
			disableRight = true;
		} else {
			disableRight = false;
		}
		if (worldX  <= screenX) {
			disableLeft = true;
		} else {
			disableLeft = false;
		}
		if (worldY  <= screenY) {
			disableUp = true;
		} else {
			disableUp = false;
		}
		if (gp.maxWorldHeight - worldY - screenY - gp.tileSize <= 0) {
			disableDown = true;
		} else {
			disableDown = false;
		}
		
		if (keyH.ePressed && objIndex != 999) {
			gp.objMan.objects[objIndex].interact(this,gp);
			
			if (gp.objMan.objects[objIndex].interacted) {
			gp.objMan.objects[objIndex] = null;
			}
		} else if (objIndex != 999 ) {
				if (gp.objMan.objects[objIndex] != null) {
					gp.objMan.objects[objIndex].soundCounter = 0;
				}
		}
		
		 
	}
	
	public void getPlayerImage() {
		try {
			up1 = ImageIO.read(getClass().getResourceAsStream("/res/Walking sprites/player_up_1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/res/Walking sprites/player_up_2.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/res/Walking sprites/player_down_1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/res/Walking sprites/player_down_2.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/res/Walking sprites/player_right_1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/res/Walking sprites/player_right_2.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/res/Walking sprites/player_left_1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/res/Walking sprites/player_left_2.png"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void draw(Graphics2D g2) {
		//g2.setColor(Color.white);
		//g2.fillRect(x, y, gp.tileSize, gp.tileSize);
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
		g2.drawImage(image, screenX , screenY, gp.tileSize, gp.tileSize, null);
		//g2.fillRect(solidArea.x + screenX, solidArea.y + screenY, solidArea.width, solidArea.height);

	}

	
}
