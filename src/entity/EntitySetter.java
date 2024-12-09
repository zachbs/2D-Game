package entity;

import java.awt.Graphics2D;

import main.GamePanel;

public class EntitySetter {
	GamePanel gp;
	public Entity entities[];
	Player player;
	int counter;
	
	
	public EntitySetter(GamePanel gp, Player player) {
		this.gp = gp;
		this.player = player;
		this.setEntities();
		counter = 0;
	}
	
	public void setEntities() {
		String moves[] = {"SwordSlash"};
		entities = new Entity[25];
		entities[0] = player;
		entities[1] = new MON_Slime(gp);
		entities[1].setStats(25 * gp.tileSize, 21 * gp.tileSize, 1, moves);
		
		
		entities[2] = new MON_Slime(gp);
		entities[2].setStats(24 * gp.tileSize, 22 * gp.tileSize, 1, moves);
		
	}
	
	public void update() {
		
		for (int i = 0; i < entities.length; i++) {
			if (entities[i] != null && i != 0) {
				entities[i].update(i);
			} else if (entities[i] == null){
				slimeSpawn(i);
			}
		}
	}
	
	public void slimeSpawn(int i) {
		if (i == 1 || i == 2) {
			if (player.worldX + player.screenX < 21 * gp.tileSize ||
					player.worldX - player.screenX > 25 * gp.tileSize || 
					player.worldY + player.screenY < 19 * gp.tileSize || 
					player.worldY - player.screenY > 23 * gp.tileSize)  {
				counter++;
				if (counter >= 701) {
					counter = 0;
				} 
			}
			if (counter >= 700){
				String moves[] = {"SwordSlash"};
				int j = (int) (Math.random() * 5);
				int p = (int) (Math.random() * 5);
				int arr[] = {21,22,23,24,25};
				int arr2[] = {19,20,21,22,23};
				entities[i] = new MON_Slime(gp);
				int level = entities[0].level;
				entities[i].setStats(arr[p] * gp.tileSize, arr2[j] * gp.tileSize, level, moves);
				hitSpawningCheck(i,1,2);
				counter = 0;
			}
		}
	}
	
	public void draw(Graphics2D g2) {
		for (int i = 0; i < entities.length; i++) {
			if (entities[i] != null) {
				entities[i].draw(g2);
			}
		}
		
	}
	
	public void hitSpawningCheck(int i, int start, int end) {
		boolean hit = false;
		for (int q = start; q < end; q++) {
			if (entities[q] != null && q != i) {
				hit = gp.cChecker.checkEntity(entities[i], entities[q]);
				if (hit) {
					q = entities.length;
				}
			}
		}
		if (hit != true) {
			hit = gp.cChecker.checkEntity(entities[i], entities[0]);
		}
		if (hit) {
			entities[i] = null;
			System.out.println("hit other spawning");
		}
	}
	
}
