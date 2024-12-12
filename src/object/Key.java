package object;


import entity.Entity;
import entity.Player;
import main.GamePanel;

public class Key extends Object {

	
	public Key() {
		image = returnImage("key");
		name = "key";
	}
	
	public void interact(Entity entity, GamePanel gp) {
		int entityId = findId(entity);
		if (entityId == 0) {
			Player player = (Player)entity;
			this.interacted = true;
			player.inventory.get(0).amount++;
			gp.playSE(1);
			gp.ui.showMessage("Good Job! You recived a key!");
		}
		
	}
	
}
