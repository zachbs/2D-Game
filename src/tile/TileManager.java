package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import entity.Player;
import main.GamePanel;

public class TileManager {

	GamePanel gp;
	public Tile[] tile;
	public int mapTileNum[][];
	Player player;
	
	public TileManager(GamePanel gp, Player player) {
		tile = new Tile[10];
		this.gp = gp;
		this.player = player;
		getTileImage();
		mapTileNum = new int[gp.maxWorldColumn][gp.maxWorldRow];
		loadMap();
	}
	
	
	public void getTileImage() {
		
		try {
			tile[0] = new Tile();
			tile[0].image = ImageIO.read(getClass().getResourceAsStream("/res/Old Tiles/grass.png"));
			tile[1] = new Tile();
			tile[1].image = ImageIO.read(getClass().getResourceAsStream("/res/Old Tiles/wall.png"));
			tile[1].collision = true;
			tile[2] = new Tile();
			tile[2].image = ImageIO.read(getClass().getResourceAsStream("/res/Old Tiles/water.png"));
			tile[2].collision = true;
			tile[3] = new Tile();
			tile[3].image = ImageIO.read(getClass().getResourceAsStream("/res/Old Tiles/earth.png"));
			tile[4] = new Tile();
			tile[4].image = ImageIO.read(getClass().getResourceAsStream("/res/Old Tiles/tree.png"));
			tile[4].collision = true;
			tile[5] = new Tile();
			tile[5].image = ImageIO.read(getClass().getResourceAsStream("/res/Old Tiles/sand.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics2D g2) {
		int worldCol = 0;
		int worldRow = 0;
		while (worldCol < gp.maxWorldColumn && worldRow < gp.maxWorldRow) {
			
			int tileNum = mapTileNum[worldCol][worldRow];			
			int worldX = worldCol * gp.tileSize;
			int worldY = worldRow * gp.tileSize;
			int screenX = worldX - gp.player.worldX + gp.player.screenX;
			int screenY = worldY - gp.player.worldY + gp.player.screenY;
			
			if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && 
				worldX - gp.tileSize < gp.player.worldX + gp.player.screenX && 
				worldY + gp.tileSize > gp.player.worldY - gp.player.screenY && 
				worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
			
			g2.drawImage(tile[tileNum].image, screenX, screenY,gp.tileSize, gp.tileSize, null);
			}
			worldCol++;
		
			
			if (worldCol == gp.maxWorldColumn) {
				worldCol = 0;
				
				
				worldRow++;
			}
			
		}
	}
	
	public void loadMap() {
		try {
			
			InputStream is = getClass().getResourceAsStream("/maps/world01.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			int col = 0;
			int row = 0;
			
			
			while (col < gp.maxWorldColumn && row < gp.maxWorldRow) {
				String input = br.readLine();
				
				while (col < gp.maxWorldColumn) {
					String[] numbers = input.split(" ");
					
					int num = Integer.parseInt(numbers[col]);
					
					mapTileNum[col][row] = num;
					//System.out.print(mapTileNum[col][row] + " ");
					col++;
				}
				if (col == gp.maxWorldColumn) {
				col = 0;
				row++;
				//System.out.println("");
				}
			}
			br.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
