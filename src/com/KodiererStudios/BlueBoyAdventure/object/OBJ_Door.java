package com.KodiererStudios.BlueBoyAdventure.object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Door extends SuperObject {
	
	// CONSTRUCTOR:
	public OBJ_Door() {
		name = "Door";
		
		// LOAD OBJECT SPRITE:
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/door.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		collision = true; // Collision is true.
	}
}