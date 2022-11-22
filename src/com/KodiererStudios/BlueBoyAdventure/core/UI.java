package com.KodiererStudios.BlueBoyAdventure.core;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

import com.KodiererStudios.BlueBoyAdventure.object.OBJ_Key;

public class UI {
	GamePanel gp; // Instantiate and implement the GamePanel class.
	Font arial_40, arial_80B; // Create a new font.
	BufferedImage keyImage; // Create a new BufferedImage to display the amount of keys.
	
	// NOTIFICATIONS:
	public boolean messageOn = false;
	public String message = "";
	int messageCounter = 0;
	
	// GAME OVER:
	public boolean gameFinished = false;
	
	// PLAY TIME:
	double playTime;
	DecimalFormat dFormat = new DecimalFormat("#0.00"); // Format's the playTime counter.
			
	// CONSTRUCTOR:
	public UI(GamePanel gp) {
		this.gp = gp;
	
		arial_40 = new Font("Arial", Font.PLAIN, 40); // Instantiate the font.
		arial_80B = new Font("Arial", Font.BOLD, 80); // Instantiate the font.
		OBJ_Key key = new OBJ_Key(); // Instantiate a new Key Object.
		keyImage = key.image; // Get's the key image.
	}
	
	public void showMessage(String text) {
		message = text;
		messageOn = true;
	}
	
	// RENDER:
	public void draw(Graphics2D g2) {
		// CHECK IF THE GAME IS FINISHED:
		if (gameFinished == true) {
			// SET FONT:
			g2.setFont(arial_40); // Format -> Font name, font style, font size.
			g2.setColor(Color.white); // Set's the font's colour.
			
			String text;
			int textLength;
			int x;
			int y;
			
			text = "You found the treasure!";
			textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth(); // Calculate the length of the text.
			
			// CENTRE THE GAME OVER TEXT:
			x = gp.screenWidth / 2 - textLength / 2;
			y = gp.screenHeight / 2 - (gp.tileSize * 3);
			g2.drawString(text, x, y); // Display the game over text.
						
			// DISPLAY PLAY TIME ON GAME OVER SCREEN:
			text = "Your Time is : " + dFormat.format(playTime) + "!";
			textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth(); // Calculate the length of the text.
			
			// CENTRE THE GAME OVER TEXT:
			x = gp.screenWidth / 2 - textLength / 2;
			y = gp.screenHeight / 2 + (gp.tileSize * 4);
			g2.drawString(text, x, y); // Display the game over text.
						
			// SET FONT:
			g2.setFont(arial_80B); // Format -> Font name, font style, font size.
			g2.setColor(Color.yellow); // Set's the font's colour.
					
			text = "Congratulations!";
			textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth(); // Calculate the length of the text.
			
			// CENTRE THE GAME OVER TEXT:
			x = gp.screenWidth / 2 - textLength / 2;
			y = gp.screenHeight / 2 + (gp.tileSize * 2);
			g2.drawString(text, x, y); // Display the game over text.
			
			gp.gameThread = null; // Stop's the gameThread, so the game ends.
		} 
		else {
			// SET FONT:
			g2.setFont(arial_40); // Format -> Font name, font style, font size.
			g2.setColor(Color.white); // Set's the font's colour.
			g2.drawImage(keyImage, gp.tileSize / 2, gp.tileSize / 2, gp.tileSize, gp.tileSize, null);
			g2.drawString("x " + gp.player.hasKey, 74, 65); // Display's the number of keys that the Player has.

			// PLAY TIME:
			playTime += (double) 1 / 60; // Add's 1/60th of a second to playTime counter.
			g2.drawString("Time: " + dFormat.format(playTime), gp.tileSize * 11, 65);
			
			// NOTIFICATIONS:
			if (messageOn == true) {
				g2.setFont(g2.getFont().deriveFont(30F)); // Change the font's size.
				g2.drawString(message, gp.tileSize / 2, gp.tileSize * 5); // Display notification message on screen.

				// NOTIFICATION TIMER:
				messageCounter++;
				
				if (messageCounter > 120) { // Every 120 frames.
					messageCounter = 0; // Reset.
					messageOn = false; // Make's the notification text disappear.
				}
			}	
		}
	}
}