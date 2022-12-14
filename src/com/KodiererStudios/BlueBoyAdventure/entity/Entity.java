package com.KodiererStudios.BlueBoyAdventure.entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity {
	public int worldX, worldY;
	public int speed;
	
	// SPRITE IMAGES:
	public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2; // Create a set of BufferedImage's for the Player's sprite.
	public String direction;
	
	// WALKING ANIMATION:
	public int spriteCounter = 0; 
	public int spriteNum = 1;
	
	// COLLISION:
	public Rectangle solidArea;
	public int solidAreaDefaultX, solidAreaDefaultY;
	public boolean collisionOn = false;
}