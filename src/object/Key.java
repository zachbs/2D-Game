package object;

import javax.imageio.ImageIO;

import entity.Entity;
import entity.Player;
import main.GamePanel;

public class Key extends Object {

	
	public Key() {
		try {
		image = ImageIO.read(getClass().getResourceAsStream("/res/key.png"));
		} catch(Exception e) {
			e.printStackTrace();
		}
		name = "key";
		amount++;
	}
	
	public void interact(Entity entity, GamePanel gp) {
		int entityId = findId(entity);
		if (entityId == 0) {
			Player player = (Player)entity;
			this.interacted = true;
			player.keysObtained++;
			gp.playSE(1);
			gp.ui.showMessage("Good Job! You recived a key!");
		}
		
	}
	
}
