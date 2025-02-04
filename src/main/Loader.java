package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import object.Key;
import object.WoodShield;
import object.WoodSword;

import object.Object;

public class Loader {
GamePanel gp;
File file;
String path;

	public Loader(GamePanel gp) {
		this.gp = gp;
		path = "/Users/zbsmi/CSCI 1301/2d Game/src/maps/save.txt";
		file = new File(path);
		try {
			if (file.createNewFile()) {
				
			} else if (file.exists() && gp.loadGame) {
				gp.ui.showMessage("Loaded Save File...");
			} else if (gp.loadGame) {
				gp.ui.showMessage("Save Error");
			}
		} catch (Exception e) {
			gp.ui.showMessage("Save Error");
			e.printStackTrace();
		}
	}
	
	public void loadGame() {
		try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
			String line;
			String[] words;
			line = reader.readLine();
			int inventorySize = 0;
			Object object = new Object();
			if (line != null) {
				words = line.split(" ");
				if (words[1] != null) {
					inventorySize = Integer.parseInt(words[1]);
				} else {
					throw new Exception();
				}
			} else {
				throw new Exception();
			}
			for (int i = 0; i < inventorySize; i++) {
				line = reader.readLine();
				exceptionCheck(line);
				words = line.split(" ");
				if (words[0].compareTo("Wood_Sword") == 0) {
					object = new WoodSword();
				} else if (words[0].compareTo("Wood_Shield") == 0) {
					object = new WoodShield();
				} else if (words[0].compareTo("key") == 0) {
					object = new Key();
				}
				object.amount = Integer.parseInt(words[1]);
				object.position = Integer.parseInt(words[2]);
				
				if (words[0].compareTo("Wood_Sword") == 0) {
					gp.player.inventory.add((WoodSword)object);
					
				} else if (words[0].compareTo("Wood_Shield") == 0) {
					gp.player.inventory.add((WoodShield)object);
					
				} else if (words[0].compareTo("key") == 0) {
					gp.player.inventory.add((Key)object);
					Key.indexInInventory = gp.player.inventory.size() - 1;
					
				}
				
				
			}
			line = reader.readLine();
			line = reader.readLine();
			exceptionCheck(line);
			words = line.split(" ");
			gp.player.level = Integer.parseInt(words[0]);
			gp.player.hp = Integer.parseInt(words[1]);
			gp.player.maxHp = Integer.parseInt(words[2]);
			gp.player.nextLevel = Integer.parseInt(words[3]);
			gp.player.xp = Integer.parseInt(words[4]);
			gp.player.worldX = Integer.parseInt(words[5]);
			gp.player.worldY = Integer.parseInt(words[6]);
			gp.player.moves[0] = words[7];
			gp.player.moves[1] = words[8];
			gp.player.moves[2] = words[9];
			gp.player.moves[3] = words[10];
			
			line = reader.readLine();
			exceptionCheck(line);
			words = line.split(" ");
			int usedNums[] = new int[10];
			reader.mark(200);
			for (int i = 0; i < Integer.parseInt(words[1]); i++) {
				
				
			}
			
			
			
			
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
		gp.ui.showMessage("Loaded Save File...");
	}
	
	public void exceptionCheck(String line) throws Exception {
		if (line == null) {
			throw new Exception();
		}
	}
}
