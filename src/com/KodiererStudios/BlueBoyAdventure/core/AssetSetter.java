package com.KodiererStudios.BlueBoyAdventure.core;

import com.KodiererStudios.BlueBoyAdventure.object.OBJ_Boots;
import com.KodiererStudios.BlueBoyAdventure.object.OBJ_Chest;
import com.KodiererStudios.BlueBoyAdventure.object.OBJ_Door;
import com.KodiererStudios.BlueBoyAdventure.object.OBJ_Key;

public class AssetSetter {
	GamePanel gp; // Instantiate and implement the GamePanel class.
	
	// CONSTRUCTOR:
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}
	
	// SET OBJECT:
	public void setObject() {
		gp.obj[0] = new OBJ_Key(); // Instantiate a new Object.
		gp.obj[0].worldX = 23 * gp.tileSize; // Set's the object's worldX position.
		gp.obj[0].worldY = 7 * gp.tileSize; // Set's the object's worldX position.
		
		gp.obj[1] = new OBJ_Key(); // Instantiate a new Object.
		gp.obj[1].worldX = 23 * gp.tileSize; // Set's the object's worldX position.
		gp.obj[1].worldY = 40 * gp.tileSize; // Set's the object's worldX position.
		
		gp.obj[2] = new OBJ_Key(); // Instantiate a new Object.
		gp.obj[2].worldX = 38 * gp.tileSize; // Set's the object's worldX position.
		gp.obj[2].worldY = 8 * gp.tileSize; // Set's the object's worldX position.
		
		gp.obj[3] = new OBJ_Door(); // Instantiate a new Object.
		gp.obj[3].worldX = 10 * gp.tileSize; // Set's the object's worldX position.
		gp.obj[3].worldY = 11 * gp.tileSize; // Set's the object's worldX position.
		
		gp.obj[4] = new OBJ_Door(); // Instantiate a new Object.
		gp.obj[4].worldX = 8 * gp.tileSize; // Set's the object's worldX position.
		gp.obj[4].worldY = 28 * gp.tileSize; // Set's the object's worldX position.
		
		gp.obj[5] = new OBJ_Door(); // Instantiate a new Object.
		gp.obj[5].worldX = 12 * gp.tileSize; // Set's the object's worldX position.
		gp.obj[5].worldY = 22 * gp.tileSize; // Set's the object's worldX position.
		
		gp.obj[6] = new OBJ_Chest(); // Instantiate a new Object.
		gp.obj[6].worldX = 10 * gp.tileSize; // Set's the object's worldX position.
		gp.obj[6].worldY = 7 * gp.tileSize; // Set's the object's worldX position.
		
		gp.obj[7] = new OBJ_Boots(); // Instantiate a new Object.
		gp.obj[7].worldX = 37 * gp.tileSize; // Set's the object's worldX position.
		gp.obj[7].worldY = 42 * gp.tileSize; // Set's the object's worldX position.
	}
}