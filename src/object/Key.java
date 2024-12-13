package object;


import entity.Entity;
import entity.Player;
import main.GamePanel;

public class Key extends Object {
	static int indexInInventory = -1;
	
	public Key() {
		image = returnImage("key");
		name = "key";
		amount = 1;
	}
	
	public void interact(Entity entity, GamePanel gp) {
		System.out.println(indexInInventory);
		int entityId = findId(entity);
		if (entityId == 0) {
			Player player = (Player)entity;
			this.interacted = true;
			if (indexInInventory == -1) {
				Key key = new Key();
				indexInInventory = player.inventory.size();
				key.position = indexInInventory;
				player.inventory.add(key);
				player.inventory.get(indexInInventory).invX = 184 + (indexInInventory % 6 ) * 68;
				player.inventory.get(indexInInventory).invY = 60 + (indexInInventory/ 6 ) * 80;
			} else {
			player.inventory.get(indexInInventory).amount++;
			}
			//player.inventory.get(0).amount++;
			gp.playSE(1);
			gp.ui.showMessage("Good Job! You recived a key!");
			//gp.invMan.refreshUI();
			
			
			
			
		}
		
	}
	
}
