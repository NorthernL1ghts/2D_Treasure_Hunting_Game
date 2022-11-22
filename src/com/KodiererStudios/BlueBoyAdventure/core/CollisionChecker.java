package com.KodiererStudios.BlueBoyAdventure.core;

import com.KodiererStudios.BlueBoyAdventure.entity.Entity;

public class CollisionChecker {
	GamePanel gp; // Instantiate and implement the GamePanel class.

	// CONSTRUCTOR:
	public CollisionChecker(GamePanel gp) {
		this.gp = gp;
	}
	
	// CHECK TILE:
	public void checkTile(Entity entity) {
		// CHECK TO SEE IF THE CORNERS ARE SOLID:
		int entityLeftWorldX = entity.worldX + entity.solidArea.x;
		int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
		int entityTopWorldY = entity.worldY + entity.solidArea.y;
		int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;
		
		int entityLeftCol = entityLeftWorldX / gp.tileSize;
		int entityRightCol = entityRightWorldX / gp.tileSize;
		int entityTopRow = entityTopWorldY / gp.tileSize;
		int entityBottomRow = entityBottomWorldY / gp.tileSize;
	
		int tileNum1, tileNum2;
		
		switch (entity.direction) {
		case "up":
			entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
			// CHECK IF TILE IS SOLID:
			if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				entity.collisionOn = true;
			}
			break;
		case "down":
			entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
			// CHECK IF TILE IS SOLID:
			if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				entity.collisionOn = true;
			}
			break;
		case "left": 
			entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
			// CHECK IF TILE IS SOLID:
			if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				entity.collisionOn = true;
			}
			break;
		case "right":
			entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
			// CHECK IF TILE IS SOLID:
			if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				entity.collisionOn = true;
			}
			break;
		}
	}
	
	// CHECK OBJECT COLLISION:
	public int checkObject(Entity entity, boolean player) {
		int index = 999;
		
			for (int i = 0; i < gp.obj.length; i++) {
				// CHECK IF OBJECT IS NULL:
				if (gp.obj[i] != null) {
					// GET ENTITIY'S SOLID ARE POSITION:
					entity.solidArea.x = entity.worldX + entity.solidArea.x;
					entity.solidArea.y = entity.worldY + entity.solidArea.y;
					
					// GET THE OBJECT'S SOLID AREA POSITION:
					gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
					gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;
					
					switch (entity.direction) {
					case "up":
						// CHECK WHERE THE ENTITY WILL BE MOVED TO:
						entity.solidArea.y -= entity.speed;
						if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
							// CHECK IF THE OBJECT IS SOLID:
							if (gp.obj[i].collision == true) {
								entity.collisionOn = true;
							}
							// CHECK IF THE PLAYER IS INTERACTING WITH OBJECT:
							if (player == true) {
								index = i; // Get the index.
							}
						}
						break;
					case "down":
						// CHECK WHERE THE ENTITY WILL BE MOVED TO:
						entity.solidArea.y += entity.speed;
						if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
							// CHECK IF THE OBJECT IS SOLID:
							if (gp.obj[i].collision == true) {
								entity.collisionOn = true;
							}
							// CHECK IF THE PLAYER IS INTERACTING WITH OBJECT:
							if (player == true) {
								index = i; // Get the index.
							}
						}
						break;
					case "left":
						// CHECK WHERE THE ENTITY WILL BE MOVED TO:
						entity.solidArea.x -= entity.speed;
						if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
							// CHECK IF THE OBJECT IS SOLID:
							if (gp.obj[i].collision == true) {
								entity.collisionOn = true;
							}
							// CHECK IF THE PLAYER IS INTERACTING WITH OBJECT:
							if (player == true) {
								index = i; // Get the index.
							}
						}
						break;
					case "right":
						// CHECK WHERE THE ENTITY WILL BE MOVED TO:
						entity.solidArea.x += entity.speed;
						if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
							// CHECK IF THE OBJECT IS SOLID:
							if (gp.obj[i].collision == true) {
								entity.collisionOn = true;
							}
							// CHECK IF THE PLAYER IS INTERACTING WITH OBJECT:
							if (player == true) {
								index = i; // Get the index.
							}
						break;
					}
				}
				// RESET:
				entity.solidArea.x = entity.solidAreaDefaultX;
				entity.solidArea.y = entity.solidAreaDefaultY;
				gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
				gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;
			}
		}
		return index;
	}
}