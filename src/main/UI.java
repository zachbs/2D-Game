package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import animation.Animation;
import animation.EntitySwordSlash;
import animation.PlayerSwordSlash;
import animation.ShieldAnimation;
import animation.ShieldBar;



public class UI {
	public GamePanel gp;
	Font arial_15;
	boolean messageOn;
	int messageTimer;
	String message;
	String direction;
	// boolean for when you are in the battle state
	public boolean battleScreenOn;
	// fightAnimationOn when a move is being animated
	// displayingHealth when the game pauses a little to display health after each move
	public boolean fightAnimationOn, entityHit, playerTurn, gameOver, displayingHealth, showMoves, counterOn, criticalDamage, escapeOn, testMoveOrder;
	int x;
	int y;
	public int xBoxPos[][];
	public int yBoxPos[][];
	int playerX, playerY, entityX, entityY, defaultPlayerX, defaultPlayerY, defaultEntityX, defaultEntityY, slideX, slideXP, counter, escapeTimer, moveCounter;
	// slope between player and entity
	double slope;
	// value of entity in the array in entitySetter 
	public int entityIndex;
	String move, playerMove;
	int i;
	BufferedImage escapeButton1, escapeButton2, fightButton1, fightButton2, itemsButton1, itemsButton2, teamButton1, teamButton2, plainButton1, plainButton2;
	Animation animation[] = new Animation[10];
	ShieldBar shieldBar, shiledBarEntity;
	boolean deflect;
	
	
	public UI (GamePanel gp) {
		this.gp = gp;
		arial_15 = new Font("Arial",Font.PLAIN,15);
		messageOn = false;
		battleScreenOn = false;
		fightAnimationOn = false;
		entityHit = false;
		messageTimer = 0;
		direction = "up";
		criticalDamage = false;
		deflect = false;
		playerTurn = true;
		testMoveOrder = true;
		x = 0;
		y = 0;
		i = 0;
		counter = 0;
		moveCounter = 0;
		move = "";
		playerMove = "";
		displayingHealth = false;
		showMoves = false;
		slideX = 4;
		slideXP = -4;
		playerX = 160;
		playerY = gp.screenHeight/2 - 10;
		entityX = gp.screenWidth - 225;
		entityY = 75;
		defaultPlayerX = playerX;
		defaultPlayerY = playerY;
		defaultEntityX = entityX;
		defaultEntityY = entityY;
		slope = ((double)(entityY - playerY)/ (double)(entityX - playerX));
		xBoxPos = new int[2][2];
		yBoxPos = new int[2][2];
		entityIndex = 999;
		gameOver = false;
		counterOn = false;
		escapeOn = false;
		escapeTimer = 0;
		escapeButton1 = returnImage("EscapeButton1");
		escapeButton2 = returnImage("EscapeButton2");
		fightButton1 = returnImage("FightButton1");
		fightButton2 = returnImage("FightButton2");
		itemsButton1 = returnImage("ItemsButton1");
		itemsButton2 = returnImage("ItemsButton2");
		teamButton1 = returnImage("TeamButton1");
		teamButton2 = returnImage("TeamButton2");
		plainButton1 = returnImage("PlainButton1");
		plainButton2 = returnImage("PlainButton2");
		
		
		
		// it is 380 because intercept was around there
		// even is player odd is entity animation;
		animation[0] = new PlayerSwordSlash(playerX + 28, 380 + (int)((playerX + 28) * slope ), gp.tileSize * 2, gp.tileSize * 2, this);
		animation[1] = new EntitySwordSlash(entityX - 28,380 + (int)((entityX - 28) * slope ), gp.tileSize * 2, gp.tileSize * 2, this);
		animation[3] = new ShieldAnimation(playerX, playerY, gp.tileSize * 2, gp.tileSize * 2, this);
		animation[4] = new ShieldAnimation(entityX, entityY, gp.tileSize * 2, gp.tileSize * 2, this);
		setBoxPos();
		shieldBar = new ShieldBar(this);
		shiledBarEntity = new ShieldBar(this);
		
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
	
	
	public void draw(Graphics2D g2) {
		
		// checks and displays a message on a black bar at the bottom of the screen
		if (messageOn) {
			messageTimer++;
			drawDialougeBox(g2, message);
			if (messageTimer >= 120) {
				messageOn = false;
				messageTimer = 0;
			}
		}
		// saves data when paused and e is clicked and shows pause screen when escape is clicked
		if (gp.gameStatePlay == false && battleScreenOn == false && gameOver == false) {
			if (gp.keyH.ePressed) {
			gp.saver.saveData();
			}
			showBlackScreen(g2, "Paused");
			g2.setFont(arial_15);
			g2.drawString("Save? press E",gp.screenWidth/2 - 70, gp.screenHeight/2 + 40);
			
			// shows battleScreen 
		} else if (gp.gameStatePlay == false && battleScreenOn) {
			if (moveCounter == 0 || moveCounter == 2) {
				if (gp.player.agility >= gp.entSet.entities[entityIndex].agility) {
					playerTurn = true;
				} else {
					playerTurn = false;
				}
				moveCounter = 0;
			}
			if (fightAnimationOn != true && displayingHealth != true && escapeOn != true) {
				battleUpdate();
				
				if (gp.entSet.entities[entityIndex] != null) {
					i = (int)(Math.random() * gp.entSet.entities[entityIndex].moveNum);
				}
			}
			// if escapeOn is true then do not enter the battle screen
			if (gp.gameStatePlay == false) {
			showBattleScreen(g2);
			}
			if (displayingHealth != true && escapeOn != true) {
				if (fightAnimationOn == true) {
					if (moveCounter == 0) {
						if (playerMove.compareTo("Shield") == 0) {
							playerTurn = true;
						}
					}
					
					if (playerTurn) {
						playerMovePicker(g2);
					} else {
						System.out.println("working");
						entityMovePicker(g2,i);
					}
				}
			} else if (escapeOn != true) {
				if (criticalDamage){
					g2.setFont(arial_15);
					g2.setColor(Color.white);
					g2.drawString("Critical Hit", gp.player.screenX, gp.player.screenY);
				}
				if (counter < 60) {
					counter++;
				} else if (counter >= 60) {
					counter = 0;
					criticalDamage = false;
					displayingHealth = false;
					checkHealthStatus();
				} 
			} 
		} else if (gp.gameStatePlay == false && battleScreenOn == false && gameOver == true) {
			showBlackScreen(g2, "GameOver");
		} else if (gp.gameStatePlay == true && gameOver != true) {
			shieldBar.moveX = 8;
		}
		drawFps(g2);
		
		
	}
	
	public void showMessage(String message) {
		this.message = message;
		messageOn = true; 
		messageTimer = 0;
	}
	
	public void drawFps(Graphics2D g2) {
		String str = "fps: ";
		str += gp.fpsCount;
		g2.setFont(arial_15);
		g2.setColor(Color.white);
		g2.drawString(str, gp.tileSize/4, gp.tileSize/4);
	}
	
	public void drawDialougeBox(Graphics2D g2, String str) {
		g2.setColor(Color.black);
		g2.fillRect(0, 500, 768, 76);
		g2.setFont(new Font ("Arial", Font.PLAIN,19));
		g2.setColor(Color.white);
		g2.drawString(str, 5, 520);
		
	}
	
	public void showBlackScreen(Graphics2D g2, String text) {
		g2.setColor(new Color(0,0,0,0.60f));
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
		g2.setFont(new Font("Arial",Font.PLAIN,40));
		g2.setColor(Color.white);
		g2.drawString(text, gp.screenWidth/2 - 75, gp.screenHeight/2 + 15);
	}
	
	public void showBattleScreen(Graphics2D g2) {
		g2.setColor(Color.green);
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
		g2.setFont(new Font("Arial",Font.PLAIN,40));
		g2.setColor(Color.white);
		g2.drawString("BattleScreen", gp.screenWidth/2 - 75, 50);
		int height = yBoxPos[1][0] - yBoxPos[0][0];
		int width = gp.screenWidth/2;
		if (gp.entSet.entities[entityIndex] != null) {
			g2.drawImage(gp.entSet.entities[entityIndex].down1, entityX, entityY, gp.tileSize * 2, gp.tileSize * 2, null);
		}
		g2.drawImage(gp.player.up1,playerX,playerY,gp.tileSize * 2,gp.tileSize * 2,null);
		
		if (showMoves == false) {
			g2.drawImage(escapeButton1, xBoxPos[1][1], yBoxPos[1][1], width, height, null);
			g2.drawImage(fightButton1, xBoxPos[0][0], yBoxPos[0][0], width, height, null);
			g2.drawImage(itemsButton1, xBoxPos[1][0], yBoxPos[1][0], width, height, null);
			g2.drawImage(teamButton1, xBoxPos[0][1], yBoxPos[0][1], width, height, null);
			displayHealthBar(g2);
			
			
			if (x == 0 && y == 0) {
				g2.drawImage(fightButton2, xBoxPos[0][0], yBoxPos[0][0], width, height, null);
			} else if (x == 1 && y == 0) {
				g2.drawImage(teamButton2, xBoxPos[0][1], yBoxPos[0][1], width, height, null);
			} else if (x == 0 && y == 1) {
				g2.drawImage(itemsButton2, xBoxPos[1][0], yBoxPos[1][0], width, height, null);
			} else if (x == 1 && y == 1) {
				g2.drawImage(escapeButton2, xBoxPos[1][1], yBoxPos[1][1], width, height, null);
			}
		} else if (showMoves == true) {
		
			
			g2.drawImage(plainButton1, xBoxPos[1][1], yBoxPos[1][1], width, height, null);
			g2.drawImage(plainButton1, xBoxPos[0][0], yBoxPos[0][0], width, height, null);
			g2.drawImage(plainButton1, xBoxPos[1][0], yBoxPos[1][0], width, height, null);
			g2.drawImage(plainButton1, xBoxPos[0][1], yBoxPos[0][1], width, height, null);
			displayHealthBar(g2);
			
			
			if (x == 0 && y == 0) {
				g2.drawImage(plainButton2, xBoxPos[0][0], yBoxPos[0][0], width, height, null);
			} else if (x == 1 && y == 0) {
				g2.drawImage(plainButton2, xBoxPos[0][1], yBoxPos[0][1], width, height, null);
			} else if (x == 0 && y == 1) {
				g2.drawImage(plainButton2, xBoxPos[1][0], yBoxPos[1][0], width, height, null);
			} else if (x == 1 && y == 1) {
				g2.drawImage(plainButton2, xBoxPos[1][1], yBoxPos[1][1], width, height, null);
			}
			g2.setFont(new Font("Arial", Font.BOLD, 20));
			g2.setColor(Color.black);
			g2.drawString(gp.player.moves[0], xBoxPos[0][0] + 100, yBoxPos[0][0] + 50);
			g2.drawString(gp.player.moves[1], xBoxPos[0][1] + 100, yBoxPos[0][1] + 50);
		}
		
	}
	
	
	public void battleUpdate() {
		if (gp.keyH.upPressed == true) {
			if (y == 1) {
				y--;
			}
		} else if (gp.keyH.downPressed == true) {
			if (y == 0) {
				y++;
			}
		} else if (gp.keyH.rightPressed == true) {
			 if (x == 0) {
				 x++;
			 }
		} else if (gp.keyH.leftPressed == true) {
			if (x == 1) {
				x--;
			}
		}
		if (showMoves == false) {
			if (gp.keyH.ePressed) {
					if (x == 0 && y == 0) {
						showMoves = true;
						counterOn = true;
					} else if (x == 1 && y == 0) {
						
					} else if (x == 0 && y == 1) {
						
					} else if (x == 1 && y == 1) {
						tryEscape();
						
					}
			}
		} else { 
			if (counterOn) {
				counter++;
			}
			if (counter >= 30 || counterOn == false) {
				if (gp.keyH.ePressed) {
					if (x == 0 && y == 0) {
						fightAnimationOn = true;
						showMoves = false;
						playerMove = "SwordSlash";
					} else if (x == 1 && y == 0) {
						fightAnimationOn = true;
						showMoves = false;
						playerMove = "Shield";
					} else if (x == 0 && y == 1) {
						
					} else if (x == 1 && y == 1) {
						
					}
				}
				counter = 0;
				counterOn = false;
			}
		}
		if (gp.keyH.escapePressed) {
			showMoves = false;
		}
		
	}
	public void setBoxPos() {
		xBoxPos[0][0] = 0;
		xBoxPos[0][1] = gp.screenWidth/2;
		xBoxPos[1][0] = 0;
		xBoxPos[1][1] = gp.screenWidth/2;
		yBoxPos[0][0] = 400;
		yBoxPos[0][1] = 400;
		yBoxPos[1][0] = yBoxPos[0][0] + (gp.screenHeight - yBoxPos[0][0])/2;
		yBoxPos[1][1] = yBoxPos[0][0] + (gp.screenHeight - yBoxPos[0][0])/2;
		
	
	}
	
	public void playerSwordSlashAnimation(Graphics2D g2) {
		if (fightAnimationOn) {
			if (animation[0].x <= entityX - 20 ) {
				animation[0].x += animation[0].speed;
				animation[0].y = 380 + ((int)((animation[0].x * slope )));
				g2.drawImage(animation[0].image[0],animation[0].x,animation[0].y, animation[0].width , animation[0].height,null);
				
			} else {
				entityHit = true;
				g2.drawImage(gp.entSet.entities[entityIndex].down1, entityX, entityY, gp.tileSize * 2, gp.tileSize * 2, null);
				if (entityX >= defaultEntityX && entityX < defaultEntityX + 48) {
					entityX += slideX;
					if (entityX < defaultEntityX) {
						entityX = defaultEntityX;
						slideX = 4;
						entityHit = false;
					}
				} else {
					
					slideX = -4;
					entityX += slideX;
				}
				if (entityHit == false) {
					moveCounter++;
					if (moveCounter == 2) {
						fightAnimationOn = false;
						move = "";
						playerMove = "";
						}
					gp.entSet.entities[entityIndex].hp -= (gp.player.level * (animation[0].damage + criticalDamage())) + 0.99;
					//fightAnimationOn = false;
					displayingHealth = true;
					resetAnimation();
					
					playerTurn = false;
					showBattleScreen(g2);
					
					
				}
			}
		} 
			
		
	}
	
	
	
	public void entitySwordSlashAnimation(Graphics2D g2) {
		if (fightAnimationOn) {
			if (animation[1].x >= playerX + 20 ) {
				animation[1].x -= animation[0].speed;
				animation[1].y = 380 + ((int)((animation[1].x * slope )));
				g2.drawImage(animation[1].image[0],animation[1].x,animation[1].y, animation[1].width , animation[1].height,null);
				
			} else {
				entityHit = true;
				
				g2.drawImage(gp.player.up1, playerX, playerY, gp.tileSize * 2, gp.tileSize * 2, null);
				if (playerX <= defaultPlayerX && playerX > defaultPlayerX - 48) {
					
					playerX += slideXP;
					if (playerX > defaultPlayerX) {
						playerX = defaultPlayerX;
						slideXP = -4;
						entityHit = false;
					}
				} else {
					slideXP = 4;
					playerX += slideX;
				}
				if (entityHit == false) {
					if (deflect) {
						gp.entSet.entities[entityIndex].hp -= (gp.entSet.entities[entityIndex].level * (1.75 + criticalDamage())) + 0.99;
						gp.player.hp -= (gp.entSet.entities[entityIndex].level * (1.75 + criticalDamage()) * gp.player.defense) + 0.99;
						deflect = false;
					} else {
					gp.player.hp -= (gp.entSet.entities[entityIndex].level * (1.75 + criticalDamage()) * gp.player.defense) + 0.99;
					}
					gp.player.defense = 1.0f;
					moveCounter++;
					if (moveCounter == 2) {
						System.out.println(moveCounter);
					fightAnimationOn = false;
					move = "";
					playerMove = "";
					}
					
					playerTurn = true;
					displayingHealth = true;
					
					showBattleScreen(g2);
					resetAnimation();
				}
			}
		} 
	}
	
	public void checkHealthStatus() {
		if (gp.player.hp <= 0) {
			battleScreenOn = false;
			fightAnimationOn = false;
			gameOver = true;
			try {
				Thread.sleep(500);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (gp.entSet.entities[entityIndex].hp <= 0) {
			gp.gameStatePlay = true;
			battleScreenOn = false;
			fightAnimationOn = false;
			showMessage("You killed the " + gp.entSet.entities[entityIndex].name + " and gained " + gp.entSet.entities[entityIndex].xp + " xp!");
			gp.player.xp += gp.entSet.entities[entityIndex].xp;
			gp.entSet.entities[entityIndex] = null;
			try {
				Thread.sleep(500);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void resetAnimation() {
		for (int i = 0; i < animation.length; i++) {
			if (animation[i] != null) {
				animation[i].x = animation[i].defaultX;
				animation[0].y = animation[i].defaultY;
			} else {
				i = animation.length;
			}
			
		}
		
		
	}
	
	public void entityMovePicker(Graphics2D g2, int i) {
		
		if (i == 0) {
			move = gp.entSet.entities[entityIndex].moves[0];
		} else if (i == 1) {
			move = gp.entSet.entities[entityIndex].moves[0];
		} else if (i == 2) {
			move = gp.entSet.entities[entityIndex].moves[0];
		} else if (i == 3) {
			move = gp.entSet.entities[entityIndex].moves[0];
		}
		if (move.compareTo("SwordSlash") == 0 ) {
			entitySwordSlashAnimation(g2);
		}
		
	}
	
	public void playerMovePicker(Graphics2D g2) {
		if (playerMove.compareTo("SwordSlash") == 0 ) {
			playerSwordSlashAnimation(g2);
		} else if (playerMove.compareTo("Shield") == 0) {
			playerShieldAnimation(g2);
		} else {
			System.out.println("error");
		}
				
	}
	
	public void displayHealthBar(Graphics2D g2) {
		
		double hpPerBarPlayer = 0;
		double hpPerBarEntity = 0;
		double xpPerBar = 0;
		xpPerBar = (double)(gp.tileSize * 4 - 8) / gp.player.nextLevel;
		hpPerBarPlayer = (double)(gp.tileSize * 4 - 8) / gp.player.maxHp; 
		if (gp.entSet.entities[entityIndex] != null) {
			hpPerBarEntity = (double)(gp.tileSize * 4 - 8) / gp.entSet.entities[entityIndex].maxHp;
		
		
		if (gp.entSet.entities[entityIndex].hp < 0) gp.entSet.entities[entityIndex].hp = 0;
		}
		if (gp.player.hp < 0) gp.player.hp = 0;
		g2.setColor(Color.black);
		g2.fillRect(defaultPlayerX - 100, defaultPlayerY - 70, gp.tileSize * 4, gp.tileSize);
		g2.setColor(Color.white);
		g2.setFont(new Font("Arial", Font.PLAIN, 15));
		g2.drawString("Level " + gp.player.level + "               hp " + gp.player.hp + "/" + gp.player.maxHp, defaultPlayerX - 90, defaultPlayerY - 45);
		g2.setColor(Color.gray);
		g2.fillRect(defaultPlayerX - 96, defaultPlayerY - 60 + gp.tileSize/2, gp.tileSize * 4 - 8, 4);
		g2.setColor(Color.red);
		g2.fillRect(defaultPlayerX - 96, defaultPlayerY - 60 + gp.tileSize/2, (int)(gp.player.hp * hpPerBarPlayer), 4);
		
		if (gp.entSet.entities[entityIndex] != null) {
			g2.setColor(Color.black);
			g2.fillRect(defaultEntityX + 30, defaultEntityY - 50, gp.tileSize * 4, gp.tileSize);
			g2.setColor(Color.white);
			g2.setFont(new Font("Arial", Font.PLAIN, 15));
			g2.drawString("Level " + gp.entSet.entities[entityIndex].level + "               hp " + gp.entSet.entities[entityIndex].hp + "/" + 
			gp.entSet.entities[entityIndex].maxHp, defaultEntityX + 40, defaultEntityY - 25);
			g2.setColor(Color.gray);
			g2.fillRect(defaultEntityX + 34, defaultEntityY - 40 + gp.tileSize/2, gp.tileSize * 4 - 8, 4);
			g2.setColor(Color.red);
			g2.fillRect(defaultEntityX + 34, defaultEntityY - 40 + gp.tileSize/2, (int)(gp.entSet.entities[entityIndex].hp * hpPerBarEntity), 4);
		} else {
			g2.setColor(Color.black);
			g2.fillRect(defaultEntityX + 30, defaultEntityY - 50, gp.tileSize * 4, gp.tileSize);
			g2.setColor(Color.white);
			g2.setFont(new Font("Arial", Font.PLAIN, 15));
			g2.drawString("                       hp 0", defaultEntityX + 40, defaultEntityY - 25);
			g2.setColor(Color.gray);
			g2.fillRect(defaultEntityX + 34, defaultEntityY - 40 + gp.tileSize/2, gp.tileSize * 4 - 8, 4);
		}
		g2.setColor(Color.green);
		g2.setColor(Color.gray);
		g2.fillRect(defaultPlayerX - 96, defaultPlayerY - 55 + gp.tileSize/2, gp.tileSize * 4 - 8, 4);
		g2.setColor(Color.green);
		g2.fillRect(defaultPlayerX - 96, defaultPlayerY - 55 + gp.tileSize/2, (int)(gp.player.xp * xpPerBar), 4);
		
	}
	
	public double criticalDamage() {
		double multiplier = 0.0;
		int ran = (int)(Math.random() * 10);
		if (ran < 1) {
			multiplier = 0.3;
			criticalDamage = true;
			System.out.println("crit");
		} else {
			criticalDamage = false;
		}
		return multiplier;
	}
	
	public void playerShieldAnimation(Graphics2D g2) {
		if (fightAnimationOn) {
			animation[3].animate(g2);
				if (animation[3].finished == true) {
					if (shieldBar.swing == true) {
					shieldBar.draw(g2);
					shieldBar.update();
					} else {
						if (shieldBar.colorPicked.compareTo("green") == 0) {
							deflect = true;
							gp.player.defense = 0.20f;
						} else if (shieldBar.colorPicked.compareTo("yellow") == 0) {
							gp.player.defense = 0.40f;
						} else if (shieldBar.colorPicked.compareTo("red") == 0) {
							gp.player.defense = 0.80f;
						}
						animation[3].finished = false;
						animation[3].counter = 0;
						moveCounter++;
						displayingHealth = true;
						move = "";
						playerTurn = false;
						shieldBar.swing = true;
						if (moveCounter == 2) {
							fightAnimationOn = false;
							move = "";
							playerMove = "";
						}
						showBattleScreen(g2);
					}
					
					
					
					
				}
			}
		} 
	
	public void entityShieldAnimation(Graphics2D g2) {
		
	}
			
		
	public void tryEscape() {
		escapeOn = false;
		if (gp.entSet.entities[entityIndex].agility - gp.player.agility > 0 ) {
			int ran1 = (int) (Math.random() * (gp.entSet.entities[entityIndex].agility - gp.player.agility));
			int ran2 = (int) (Math.random() * (gp.entSet.entities[entityIndex].agility - gp.player.agility));
			if (ran2 == ran1) {
				escapeOn = true;
			}
		} else {
			
			escapeOn = true;
		}
		
		if (escapeOn == true) {
			showMessage("You fled the battle");
			gp.gameStatePlay = true;
			battleScreenOn = false;
			gp.entSet.entities[entityIndex] = null;
			escapeOn = false;
		} else {
			fightAnimationOn = true;
			playerTurn = false;
		}
		
	}
	
	
}
