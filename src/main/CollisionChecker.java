package main;

import entity.Entity;

public class CollisionChecker {
	GamePanel gp;
	
	public CollisionChecker(GamePanel gp) {
		this.gp = gp;
	}
	
	public void checkTile(Entity entity) {
		int entityLeftWorldX = entity.worldX + entity.solidArea.x;
		int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
		int entityUpWorldY = entity.worldY + entity.solidArea.y;
		int entityDownWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;
		int entityLeftCol = entityLeftWorldX/gp.tileSize;
		int entityRightCol = entityRightWorldX/gp.tileSize;
		int entityUpRow = entityUpWorldY/gp.tileSize;
		int entityDownRow = entityDownWorldY/gp.tileSize;
		
		int tileNum1, tileNum2;
		
		if (entity.direction.compareTo("up") == 0) {
			entityUpRow = (entityUpWorldY - entity.speed)/ gp.tileSize; 
			tileNum1 = gp.tileManager.mapTileNum[entityLeftCol][entityUpRow];
			tileNum2 = gp.tileManager.mapTileNum[entityRightCol][entityUpRow];
			if (gp.tileManager.tile[tileNum1].collision || gp.tileManager.tile[tileNum2].collision ) {
				entity.collisionOn = true;
			}
		
			
		} else if (entity.direction.compareTo("down") == 0) {
			entityDownRow = (entityDownWorldY + entity.speed)/ gp.tileSize; 
			tileNum1 = gp.tileManager.mapTileNum[entityLeftCol][entityDownRow];
			tileNum2 = gp.tileManager.mapTileNum[entityRightCol][entityDownRow];
			if (gp.tileManager.tile[tileNum1].collision || gp.tileManager.tile[tileNum2].collision ) {
				entity.collisionOn = true;
			}
		} else if (entity.direction.compareTo("right") == 0) {
			entityRightCol = (entityRightWorldX + entity.speed)/ gp.tileSize; 
			tileNum1 = gp.tileManager.mapTileNum[entityRightCol][entityUpRow];
			tileNum2 = gp.tileManager.mapTileNum[entityRightCol][entityDownRow];
			if (gp.tileManager.tile[tileNum1].collision || gp.tileManager.tile[tileNum2].collision ) {
				entity.collisionOn = true;
			}
		} else if (entity.direction.compareTo("left") == 0) {
			entityLeftCol = (entityLeftWorldX - entity.speed)/ gp.tileSize; 
			tileNum1 = gp.tileManager.mapTileNum[entityLeftCol][entityUpRow];
			tileNum2 = gp.tileManager.mapTileNum[entityLeftCol][entityDownRow];
			if (gp.tileManager.tile[tileNum1].collision || gp.tileManager.tile[tileNum2].collision ) {
				entity.collisionOn = true;
			}
		}
	}
	
	
	public int checkObject(Entity entity, Boolean Player) {
		int index = 999;
		
		for(int i = 0; i < gp.objMan.objects.length; i++) {
			if (gp.objMan.objects[i] != null) {
				
				
				entity.solidArea.x = entity.worldX + entity.solidArea.x;
				entity.solidArea.y = entity.worldY + entity.solidArea.y;
				
				gp.objMan.objects[i].solidArea.x = gp.objMan.objects[i].worldX  + gp.objMan.objects[i].solidArea.x; 
				gp.objMan.objects[i].solidArea.y = gp.objMan.objects[i].worldY + gp.objMan.objects[i].solidArea.y;
				
				
				
				if (entity.direction.compareTo("up") == 0) {
					entity.solidArea.y -= entity.speed;
					if (entity.solidArea.intersects(gp.objMan.objects[i].solidArea)) {
						index = i;
						if (gp.objMan.objects[i].collision) {
							entity.collisionOn = true;
						}
					}
					
				} else if (entity.direction.compareTo("down") == 0) {
					entity.solidArea.y += entity.speed;
					if (entity.solidArea.intersects(gp.objMan.objects[i].solidArea)) {
						index = i;
						if (gp.objMan.objects[i].collision) {
							entity.collisionOn = true;
						}
					}
					
				} else if (entity.direction.compareTo("right") == 0) {
					entity.solidArea.x += entity.speed;
					if (entity.solidArea.intersects(gp.objMan.objects[i].solidArea)) {
						index = i;
						if (gp.objMan.objects[i].collision) {
							entity.collisionOn = true;
						}
					}
					
				} else if (entity.direction.compareTo("left") == 0) {
					entity.solidArea.x -= entity.speed;
					if (entity.solidArea.intersects(gp.objMan.objects[i].solidArea)) {
						index = i;
						if (gp.objMan.objects[i].collision) {
							entity.collisionOn = true;
						}
					}
					
				}
				entity.solidArea.x = entity.defaultSolidAreaX;
				entity.solidArea.y = entity.defaultSolidAreaY;
				
				gp.objMan.objects[i].solidArea.x = gp.objMan.objects[i].defaultSolidAreaX;
				gp.objMan.objects[i].solidArea.y = gp.objMan.objects[i].defaultSolidAreaY;
				
			}
			
			
		}
		
		return index;
	}
	
	public boolean checkEntity(Entity entity1, Entity entity2) {
		
		
	
			boolean monsterHit = false;
				
			entity1.solidArea.x = entity1.worldX + entity1.solidArea.x;
			entity1.solidArea.y = entity1.worldY + entity1.solidArea.y;
				
			entity2.solidArea.x = entity2.worldX  + entity2.solidArea.x; 
			entity2.solidArea.y = entity2.worldY + entity2.solidArea.y;
				
				
				
				if (entity1.direction.compareTo("up") == 0) {
					entity1.solidArea.y -= entity1.speed;
					if (entity1.solidArea.intersects(entity2.solidArea)) {
						monsterHit = true;
						if (entity2.collision) {
							entity1.collisionOn = true;
						}
					}
					
				} else if (entity1.direction.compareTo("down") == 0) {
					entity1.solidArea.y += entity1.speed;
					if (entity1.solidArea.intersects(entity2.solidArea)) {
						monsterHit = true;
						if (entity2.collision) {
							entity1.collisionOn = true;
						}
					}
					
				} else if (entity1.direction.compareTo("right") == 0) {
					entity1.solidArea.x += entity1.speed;
					if (entity1.solidArea.intersects(entity2.solidArea)) {
						monsterHit = true;
						if (entity2.collision) {
							entity1.collisionOn = true;
						}
					}
					
				} else if (entity1.direction.compareTo("left") == 0) {
					entity1.solidArea.x -= entity1.speed;
					if (entity1.solidArea.intersects(entity2.solidArea)) {
						monsterHit = true;
						if (entity2.collision) {
							entity1.collisionOn = true;
						}
					}
					
				}
				entity1.solidArea.x = entity1.defaultSolidAreaX;
				entity1.solidArea.y = entity1.defaultSolidAreaY;
				
				entity2.solidArea.x = entity2.defaultSolidAreaX;
				entity2.solidArea.y = entity2.defaultSolidAreaY;
				
			if (entity1.worldX/gp.tileSize == entity2.worldX/gp.tileSize && entity1.worldY/gp.tileSize == entity2.worldY/gp.tileSize ) monsterHit = true;
			
			
		return monsterHit;
		
		
	}
}
