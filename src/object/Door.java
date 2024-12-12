package object;

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
			if (player.inventory.get(0).amount > 0) {
				player.inventory.get(0).amount--;
				this.interacted = true;
				gp.playSE(2);
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
