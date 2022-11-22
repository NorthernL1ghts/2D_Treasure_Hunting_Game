package com.KodiererStudios.BlueBoyAdventure.object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Key extends SuperObject {
	
	// CONSTRUCTOR:
	public OBJ_Key() {
		name = "Key";
		
		// LOAD OBJECT SPRITE:
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/key.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}