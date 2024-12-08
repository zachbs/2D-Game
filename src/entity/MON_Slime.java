package entity;

import java.awt.Rectangle;
import java.util.Random;

import main.GamePanel;

public class MON_Slime extends Entity {
	int actionLockCounter;
	public MON_Slime(GamePanel gp) {
		super(gp);
		name = "slime";
		maxHp = 10;
		hp = maxHp;
		speed = 1;
		agility = 1;
		up1 = setImage("/res/Monsters/greenslime_down_1.png");
		up2 = setImage("/res/Monsters/greenslime_down_2.png");
		down1 = setImage("/res/Monsters/greenslime_down_1.png");
		down2 = setImage("/res/Monsters/greenslime_down_2.png");
		right1 = setImage("/res/Monsters/greenslime_down_1.png");
		right2 = setImage("/res/Monsters/greenslime_down_2.png");
		left1 = setImage("/res/Monsters/greenslime_down_1.png");
		left2 = setImage("/res/Monsters/greenslime_down_2.png");
		direction = "down";
		actionLockCounter = 0;
		solidArea = new Rectangle(3,18,42,30);
		defaultSolidAreaX = 3;
		defaultSolidAreaY = 18;
		xp = 5;
		//moves[0] = "swordSlash";
	}
	
	public void setAction() {
		actionLockCounter++;
		if (actionLockCounter == 120) {
		
			Random random = new Random();
			int i = random.nextInt(100) + 1;
			
			if (i < 25) {
				direction = "up";
			} else if (i >= 25 && i < 50) {
				direction = "down";
			} else if (i >= 50 && i < 75) {
				direction = "right";
			} else if (i >= 75) {
				direction = "left";
			}
			
			actionLockCounter = 0;
		}
		
	}
	
	public void setStats(int worldX, int worldY, int level, String moves[]) {
		super.setStats(worldX, worldY, level, moves);
		moveNum = moves.length;
		agility = (int)(level * 0.5 + 0.99);
		maxHp = (int)(level * 5 + 0.99);
		hp = maxHp;
		xp = level * 10;
		
	}

}
