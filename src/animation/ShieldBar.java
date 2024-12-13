package animation;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import main.UI;

public class ShieldBar {
	public Rectangle background;
	public Rectangle redSpace1;
	public Rectangle yellowSpace1;
	public Rectangle redSpace2;
	public Rectangle yellowSpace2;
	public Rectangle greenSpace;
	public Rectangle selecter;
	UI ui;
	public boolean swing;
	int maxX, minX, currentX;
	public int moveX;
	public String colorPicked;
	
	
	public ShieldBar(UI ui) {
		this.ui = ui;
		this.background = new Rectangle(ui.xBoxPos[0][1], ui.yBoxPos[0][1] - 40, ui.gp.screenWidth - ui.xBoxPos[0][1] - 4, 35);
		// 95 is 25% of 380 or width of background
		this.redSpace1 = new Rectangle(background.x,background.y + 9, 95, 16);
		this.redSpace2 = new Rectangle(background.x + background.width - 95,background.y + 9, 95, 16);
		this.yellowSpace1 = new Rectangle(background.x + 95,background.y + 9, 76, 16);
		this.greenSpace = new Rectangle(yellowSpace1.x + 76,background.y + 9, 38, 16);
		this.yellowSpace2 = new Rectangle(greenSpace.x + 38,background.y + 9, 76, 16);
		
		
		colorPicked = "";
		swing = true;
		maxX = background.x + background.width - 4;
		minX = background.x;
		currentX = minX;
		this.selecter = new Rectangle(currentX,background.y + 9,4,16);
		moveX = 8;
	}
	
	public void draw(Graphics2D g2) {
		g2.setColor(Color.black);
		g2.fill(background);
		g2.setColor(Color.red);
		g2.fill(redSpace1);
		g2.fill(redSpace2);
		g2.setColor(Color.yellow);
		g2.fill(yellowSpace1);
		g2.fill(yellowSpace2);
		g2.setColor(Color.green);
		g2.fill(greenSpace);
		g2.setColor(Color.black);
		g2.fill(selecter);
		
	}
	
	public void update() {
		if (swing) {
			if (ui.gp.keyH.ePressed != true) {
				if (currentX < maxX + 4 && currentX >= minX) {
					currentX += moveX;
				} else {
					System.out.println("hello");
					moveX = moveX * -1;
					currentX += moveX;
				}
				this.selecter.x = currentX;
			} else {
				swing = false;
				if (selecter.intersects(greenSpace)) {
					System.out.println("green");
					colorPicked = "green";
				} else if (selecter.intersects(yellowSpace1) || selecter.intersects(yellowSpace2)) {
					System.out.println("yellow");
					colorPicked = "yellow";
				} else if (selecter.intersects(redSpace1) || selecter.intersects(redSpace2)) {
					System.out.println("red");
					colorPicked = "red";
				}
				moveX = Math.abs(moveX) + 2;
				currentX = minX;
			}
		}
	}
	
	
	
	
}
