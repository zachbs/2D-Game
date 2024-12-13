package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.Consumer;

import javax.imageio.ImageIO;
import javax.swing.SwingUtilities;

import object.Object;

public class InventoryManager {

BufferedImage darkEffectBackground;
Rectangle background;
Rectangle blocks[];
BufferedImage imageBlocks[];
ArrayList<Object> inventory;
GamePanel gp;
boolean inventoryOn, clickImageOn;
boolean counterOn, released;
short counter, counter2;
Object objects[];
int mouseX, mouseY, mouseX2, mouseY2, boxId, boxId2;
int imagesX[], imagesY[];
int totalItems;

//delete this
BufferedImage images[];

	public InventoryManager(GamePanel gp) {
		inventory = gp.player.inventory;
		this.gp = gp;
		inventoryOn = false;
		darkEffectBackground = returnDarkEffect(background);
		counterOn = false;
		counter = 0;
		counter2 = 0;
		clickImageOn = false;
		blocks = new Rectangle[36];
		objects = new Object[20];
		imagesX = new int[20];
		imagesY = new int[20];
		totalItems = 0;
		released = false;
		//delete this
		images = new BufferedImage[20];
		
		mouseX = 0;
		mouseY = 0;
		boxId = -1;
		boxId2 = -1;
		mouseX2 = 0;
		mouseY2 = 0;
		setUpImages();
		setBlocks();
		
	}
	
	public BufferedImage returnDarkEffect(Rectangle background) {
		BufferedImage image = new BufferedImage(gp.screenWidth,gp.screenHeight, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = (Graphics2D)image.getGraphics();
		g2.setColor(new Color(0,0,0,0.95f));
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
		g2.setColor(Color.gray);
		background = new Rectangle(160, 40, gp.screenWidth - 2 * 160, gp.screenHeight - 2 * 40);
		g2.fill(background);
		return image;
	}
	
	public void setBlocks() {
		int x = 184;
		int y = 60;
		for (int j = 0; j < 6; j++) {
			for (int i = 0; i < 6; i++) {
				blocks[i + j * 6] = new Rectangle(x,y,60,60);
				x += 68;
				
			}
			x = 184;
			y += 80;
		}
	}
	
	public void update() {
		if (counterOn == false) {
			if (gp.keyH.iPressed == true) {
				if (inventoryOn) {
					inventoryOn = false;
				} else {
					inventoryOn = true;
				}
				counterOn = true;
			}
		} else if (counterOn = true) {
			counter++;
			if (counter > 10) {
				counter = 10;
			}
			if (counter >= 10 && gp.keyH.iPressed == false) {
				counterOn = false;
				counter = 0;
			}
		}
		if (clickImageOn == true) {
			if (counter2 == 0) {
				int x = 184; // 210
				int y = 60; // 458
				for (int j = 0; j < 6; j++) {
					for (int i = 0; i < 6; i++) {
						if ((mouseX >= x  && mouseX < x  + 68) && (mouseY >= y && mouseY < y  + 80) ) {
							boxId = i + j * 6;
							i = 6;
							j = 6;
						}
						
						x += 68;
						
					}
					x = 184;
					y += 80;
				}
				
			}
			counter2++;
			if (counter2 > 10) {
				counter2 = 1;
			}
		}
			if (gp.mouseH.mouseClicked == true) {
				PointerInfo pi = MouseInfo.getPointerInfo();
				Point p = pi.getLocation();
				SwingUtilities.convertPointFromScreen(p, gp);
				
				mouseX = (int)p.getX();
				mouseY = (int)p.getY();
				clickImageOn = true;
				released = true;
			} else if (gp.mouseH.mouseClicked == false) {
				if (released) {
				
				clickImageOn = false;
				counter2 = 0;
				released = false;
				PointerInfo pi = MouseInfo.getPointerInfo();
				Point p = pi.getLocation();
				SwingUtilities.convertPointFromScreen(p, gp);
				
				mouseX2 = (int)p.getX();
				mouseY2 = (int)p.getY();
				int x = 184; 
				int y = 60; 
				for (int j = 0; j < 6; j++) {
					for (int i = 0; i < 6; i++) {
						if ((mouseX2 >= x  && mouseX2 < x  + 68) && (mouseY2 >= y && mouseY2 < y  + 80) ) {
							boxId2 = i + j * 6;
							i = 6;
							j = 6;
						}
						
						x += 68;
						
					}
					x = 184;
					y += 80;
				}
				if (boxId2 != -1 && boxId != -1) {
					int id1 = findPosId(boxId);
					int id2 = findPosId(boxId2);
					if (id1 != -1 && id2 != -1) {
					swapPos(gp.player.inventory.get(id1), gp.player.inventory.get(id2));
					} else if (id1 != -1) {
						gp.player.inventory.get(id1).position = boxId2;
					}
				}
				boxId2 = -1;
				boxId = -1;
				}
				
			}
		
	}
	
	public void draw(Graphics2D g2) {
		if (inventoryOn && gp.gameStatePlay) {
			g2.drawImage(darkEffectBackground, 0, 0, gp.screenWidth, gp.screenHeight, null);
			g2.setColor(Color.darkGray);
			for (int i = 0; i < 36; i ++) {
				g2.fill(blocks[i]);
				
			}
			for (int i = 0; i < gp.player.inventory.size(); i++) {
				if (gp.player.inventory.get(i).amount > 0) {
					gp.player.inventory.get(i).invX = 184 + (gp.player.inventory.get(i).position % 6 ) * 68;
					gp.player.inventory.get(i).invY = 60 + (gp.player.inventory.get(i).position / 6 ) * 80;
					g2.drawImage(gp.player.inventory.get(i).image, gp.player.inventory.get(i).invX, gp.player.inventory.get(i).invY, 60, 60, null);
					if (gp.player.inventory.get(i).amount > 1) {
						g2.setFont(new Font("Arial",Font.PLAIN,10));
						g2.setColor(Color.white);
						String str = "" + gp.player.inventory.get(i).amount;
						g2.drawString(str, gp.player.inventory.get(i).invX + 50, gp.player.inventory.get(i).invY + 55);
					}
				}
			}
			//g2.drawImage(images[0],blocks[0].x,blocks[0].y,60,60,null);
			//g2.drawImage(images[1],blocks[1].x,blocks[1].y,60,60,null);
			//g2.drawImage(images[2],blocks[2].x,blocks[2].y,60,60,null);
			if (boxId != -1) {
				int id = findPosId(boxId);
				if (id != -1) {
					g2.setColor(Color.DARK_GRAY);
					g2.fill(blocks[boxId]);
					g2.drawImage(gp.player.inventory.get(id).image, mouseX - 30, mouseY - 30, 60, 60, null);
				}
				
			}
		}
	}
	
	public BufferedImage returnImage(String str) {
		BufferedImage image = null;
		try {
		image = ImageIO.read(getClass().getResourceAsStream("/res/" + str + ".png"));
		} catch (Exception e) {
			
		}
		return image;
	}
	public void setUpImages() {
		int x = 184;
		int y = 60;
		int count = 0;
		Iterator<Object> i = gp.player.inventory.iterator();
		while (i.hasNext()) {
			//System.out.println(gp.player.inventory.size());
			Object obj = i.next();
			if (obj.amount > 0) {
				obj.invX = x;
				obj.invY = y;
				//System.out.println(obj.invX + " " + obj.invY + " " + obj.name);
				count++;
				totalItems++;
				x += 68;
				if (count == 5) {
					x = 184;
					y += 80;
					count = 0;
				}
			}
			
		}
		
	}
	
	public void swapPos(Object obj1, Object obj2) {
		int position = -1;
		position = obj1.position;
		obj1.position = obj2.position;
		obj2.position = position;
		
		
		
	}
	
	public int findPosId(int boxId) {
		int id = -1;
		int count = 0;
		Iterator<Object> i = gp.player.inventory.iterator();
		while (i.hasNext()) {
			//System.out.println(gp.player.inventory.size());
			Object obj = i.next();
			if (obj.amount > 0) {
				if (boxId == obj.position) {
					id = count;
				}
				
			}
			count++;
		}
		return id;
	}
	
}
