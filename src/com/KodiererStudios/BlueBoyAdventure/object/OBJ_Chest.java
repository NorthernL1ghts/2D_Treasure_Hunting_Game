package com.KodiererStudios.BlueBoyAdventure.object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Chest extends SuperObject {
	
	// CONSTRUCTOR:
	public OBJ_Chest() {
		name = "Chest";
		
		// LOAD OBJECT SPRITE:
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/chest.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}