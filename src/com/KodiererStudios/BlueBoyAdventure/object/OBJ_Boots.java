package com.KodiererStudios.BlueBoyAdventure.object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Boots extends SuperObject {
	
	// CONSTRUCTOR:
	public OBJ_Boots() {
		name = "Boots";
		
		// LOAD OBJECT SPRITE:
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/boots.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}