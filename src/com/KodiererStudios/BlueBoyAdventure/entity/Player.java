package com.KodiererStudios.BlueBoyAdventure.entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.KodiererStudios.BlueBoyAdventure.core.GamePanel;
import com.KodiererStudios.BlueBoyAdventure.core.KeyHandler;

public class Player extends Entity {	
	GamePanel gp; // Instantiate and implement the GamePanel class.
	KeyHandler keyH; // Instantiate and implement the KeyHandler class.
	
	// PLAYER'S POSITION ON MAP:
	public final int screenX;  
	public final int screenY;
	
	// OBJECT INTERACTION:
	public int hasKey = 0; // Indicates how many Keys the Player current has.
	
	// CONSTRUCTOR:
	public Player(GamePanel gp, KeyHandler keyH) {
		this.gp = gp;
		this.keyH = keyH;
		
		// CENTRE PLAYER'S POSITION ON WORLD MAP:
		screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
		screenY = gp.screenHeight / 2 - (gp.tileSize / 2);
		
		// SOLID AREA:
		solidArea = new Rectangle(); // Instantiate.
		solidArea.x = 0;
		solidArea.y = 16;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 32;
		solidArea.height = 32;
		
		setDefaultValues(); // Call's the method that set's the Player's default values on start.
		getPlayerImage(); // Call's the method that gets the Player's sprite's on start.
	}
	
	// SET'S THE PLAYER'S DEFAULT VALUES:
	public void setDefaultValues() {
		worldX = gp.tileSize * 23;
		worldY = gp.tileSize * 21;
		speed = 4;
		direction = "down";
	}
	
	// GET PLAYER SPRITE'S:
	public void getPlayerImage() {
		try {
			up1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_2.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_2.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_2.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_2.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// TICK:
	public void update() {
		if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true) {
			// PLAYER'S MOVEMENT:
			if (keyH.upPressed == true) {
				direction = "up";
			}
			else if (keyH.downPressed == true) {
				direction = "down";
			}
			else if (keyH.leftPressed == true) {
				direction = "left";
			}
			else if (keyH.rightPressed == true) {
				direction = "right";
			}
			
			// CHECK TILE COLLISION:
			collisionOn = false;
			gp.cChecker.checkTile(this);
			
			// CHECK OBJECT COLLISION:
			int objIndex = gp.cChecker.checkObject(this, true);
			pickupObject(objIndex); // Call method to allow the Player to pick up objects.
			
			// IF COLLISION IS FALSE, PLAYER CAN MOVE:
			if (collisionOn == false) {
				switch (direction) {
				case "up": worldY -= speed;	break;
				case "down": worldY += speed; break;
				case "left": worldX -= speed; break;
				case "right": worldX += speed; break;
				}
			}
			
			// WALKING ANIMATION:
			spriteCounter++; // Increment.
			if (spriteCounter > 12) {
				if (spriteNum == 1) {
					spriteNum = 2;
				}
				else if (spriteNum == 2) {
					spriteNum = 1;
				}
				spriteCounter = 0; // Reset.
			}
		}
	}
	
	// PICK UP OBJECT:
	public void pickupObject(int i) {
		if (i != 999) { // If index is 999, we did not interact with object, if index is less than 999 then we interacted.
			String objectName = gp.obj[i].name; // Get's the name of the object.
			
			// OBJECT SPECIFIC EVENTS:
			switch (objectName) {
			case "Key":
				gp.playSE(1); // Play the "Coin" sound effect when the Player interacts with Key Object.
				hasKey++; // When Player interacts, the Player gains a key.
				gp.obj[i] = null; // Remove the Object from world map, when the Player interacts.
				gp.ui.showMessage("You got a key!"); // Display notification message when Player interacts.
				break;
			case "Door":
				// CHECK IF PLAYER HAS A KEY TO OPEN DOOR:
				if (hasKey > 0) {
					gp.playSE(3); // Play the "Unlock" sound effect when the Player interacts with Door Object.
					gp.obj[i] = null; // Remove the Object from world map, when the Player interacts.
					hasKey--; //Remove a Key from the Player when the Door is opened..
					gp.ui.showMessage("You opened the door!"); // Display notification message when Player interacts.
				}
				// IF YOU DO NOT HAVE A KEY:
				else {
					gp.ui.showMessage("You need a key!"); // Display notification message when Player interacts.
				}
				break;
			case "Boots":
				gp.playSE(2); // Play the "Power up" sound effect when the Player interacts with Boots Object.
				speed += 1; // When the Player interacts with boots, the Player's speed will be increased.
				gp.obj[i] = null; // Remove the Object from world map, when the Player interacts.
				gp.ui.showMessage("Speed up!"); // Display notification message when Player interacts.
				break;
			case "Chest":
				gp.ui.gameFinished = true; // Game is over.
				gp.stopMusic(); // Stop's playing music, when the game is over.
				gp.playSE(4); // Play's the "fun-fare" sound effect, when the game is over.
				break;
			}
		}
	}
	
	// RENDER:
	public void draw(Graphics2D g2) {
		BufferedImage image = null; // Create's a new BufferedImage.
		
		switch (direction) {
		case "up":
			if (spriteNum == 1) {
				image = up1;				
			}
			if (spriteNum == 2) {
				image = up2;				
			}
			break;
		case "down":
			if (spriteNum == 1) {
				image = down1;				
			}
			if (spriteNum == 2) {
				image = down2;				
			}
			break;
		case "left":
			if (spriteNum == 1) {
				image = left1;				
			}
			if (spriteNum == 2) {
				image = left2;				
			}
			break;
		case "right":
			if (spriteNum == 1) {
				image = right1;				
			}
			if (spriteNum == 2) {
				image = right2;				
			}
			break;
		}
		
		g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null); // Render's the Player sprite on screen.
	}
}