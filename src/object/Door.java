package object;

import java.util.Iterator;

import javax.imageio.ImageIO;

import entity.Entity;
import entity.Player;
import main.GamePanel;

public class Door extends Object {
	
	public Door() {
		collision = true;
		try {
		image = ImageIO.read(getClass().getResourceAsStream("/res/door.png"));
		} catch(Exception e) {
			e.printStackTrace();
		}
		name = "door";
		amount++;
	}
	
	public void interact(Entity entity, GamePanel gp) {
		int entityId = findId(entity);
		if (entityId == 0) {
			Player player = (Player)entity;
			int id = -1;
			int count = 0;
			Iterator<Object> i = gp.player.inventory.iterator();
			while (i.hasNext()) {
				//System.out.println(gp.player.inventory.size());
				Object obj = i.next();
				if (obj.amount > 0) {
					if (obj.name.compareTo("key") == 0) {
						id = count;
					}
					
				}
				count++;
			}
			
			if (id != -1) {
				player.inventory.get(id).amount--;
				this.interacted = true;
				gp.playSE(2);
				if (player.inventory.get(id).amount == 0) {
					player.inventory.remove(id);
				}
			} else if (soundCounter == 0){
				gp.playSE(4);
				gp.ui.showMessage("The door is locked. Maybe a key will open it.");
				soundCounter++;
			} else {
				soundCounter++;
			}
			if (soundCounter > 25) {
				soundCounter = 0;
				
			}
		}
		
		
	}
}
