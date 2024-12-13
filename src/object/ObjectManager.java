package object;

import main.GamePanel;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import entity.Player;

public class ObjectManager {
	GamePanel gp;
	public Object[] objects;
	Player player;
	int[][] objectMap;
	public int amountObj;
	
	public ObjectManager(GamePanel gp) {
		this.gp = gp;
		objects = new Object[10];
		objects[0] = new Key();
		objects[1] = new Door();
		objects[2] = new Key();
		objects[3] = new Door();
		
		this.objectMap = new int[20][3];
		loadMap("/maps/objectWorld01.txt");
		for (int i = 0; i < amountObj; i++) {
			int id = this.objectMap[i][2];
			int worldRow = this.objectMap[i][1];
			int worldCol = this.objectMap[i][0];
		ObjectSetValues(worldCol,worldRow,id);
		}
		
		
	}
	
	public void ObjectSetValues(int worldCol, int worldRow, int id) {
		objects[id].worldX = worldCol * gp.tileSize;
		objects[id].worldY = worldRow * gp.tileSize;
		
	}
	
	public void draw(Graphics2D g2) {
			
			
			for (int i = 0; i < amountObj; i++) {	
				
				if (objects[i] != null) {
					int screenX = objects[i].worldX - gp.player.worldX + gp.player.screenX;
					int screenY = objects[i].worldY - gp.player.worldY + gp.player.screenY;
					
					if (objects[i].worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && 
						objects[i].worldX - gp.tileSize < gp.player.worldX + gp.player.screenX && 
						objects[i].worldY + gp.tileSize > gp.player.worldY - gp.player.screenY && 
						objects[i].worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
					
					g2.drawImage(objects[i].image, screenX, screenY,gp.tileSize, gp.tileSize, null);
					}
				} 
			}
			
		}
	
	public void loadMap(String map) {
		try {
			
			InputStream is = getClass().getResourceAsStream(map);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			int col = 0;
			boolean start = true;
			
			while (start) {
				String input = br.readLine();
				
				if (input != null) {
					for (int i = 0; i < 3; i++) {
						String[] numbers = input.split(" ");
						
						int num = Integer.parseInt(numbers[i]);
						//System.out.println("num " + num + " " + col + " " + i);
						this.objectMap[col][i] = num;
						//System.out.println("obj map " + this.objectMap[col][i] + " " );
						
					}
					col++;
				} else {
				start = false;
				}
			}
			br.close();
			amountObj = col;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Did not work");
		}
		
	}
	
	
	
}
