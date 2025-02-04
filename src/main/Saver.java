package main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class Saver {
	GamePanel gp;
	File file;
	String path;
	
	public Saver(GamePanel gp) {
		this.gp = gp;
		path = "/Users/zbsmi/CSCI 1301/2d Game/src/maps/save.txt";
		file = new File(path);
		try {
			if (file.createNewFile()) {
				gp.ui.showMessage("Creating Save File...");
			} else if (file.exists()) {
				
			} else {
				gp.ui.showMessage("Save Error");
			}
		} catch (Exception e) {
			gp.ui.showMessage("Save Error");
			e.printStackTrace();
		}
	}
	
	public void saveData() {
		gp.ui.showMessage("Saving Data");
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
			writer.write("Inventory " + gp.player.inventory.size());
			writer.newLine();
			for (int i = 0; i < gp.player.inventory.size(); i++) {
				writer.write(gp.player.inventory.get(i).name + " ");
				writer.write(gp.player.inventory.get(i).amount + " ");
				writer.write(gp.player.inventory.get(i).position + "");
				writer.newLine();
			}
			writer.write("Player");
			writer.newLine();
			writer.write(gp.player.level + " ");
			writer.write(gp.player.hp + " ");
			writer.write(gp.player.maxHp + " ");
			writer.write(gp.player.nextLevel + " ");
			writer.write(gp.player.xp + " ");
			writer.write(gp.player.worldX + " ");
			writer.write(gp.player.worldY + " ");
			writer.write(gp.player.moves[0] + " ");
			writer.write(gp.player.moves[1] + " ");
			if (gp.player.moves[3] != null) {
				writer.write(gp.player.moves[0] + " ");
				
			} else {
				writer.write("null ");
			}
			if (gp.player.moves[3] != null) {
				writer.write(gp.player.moves[1] + " ");
			} else {
				writer.write("null ");
			}
			writer.newLine();
			
			writer.write("Objects");
			int count = 0;
			for (int i = 0; i < 10; i++) {
				if (gp.objMan.objects[i] != null) {
					count++;
				}
			}
			writer.write("Objects " + count );
			writer.newLine();
			for (int i = 0; i < 10; i++) {
				if (gp.objMan.objects[i] != null) {
					writer.write(i + " ");
					writer.write(gp.objMan.objects[i].worldX + " ");
					writer.write(gp.objMan.objects[i].worldY + " ");
					writer.newLine();
				}
			}
			
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
	}

}
