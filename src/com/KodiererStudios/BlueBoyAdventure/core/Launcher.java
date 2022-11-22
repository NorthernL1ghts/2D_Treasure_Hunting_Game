package com.KodiererStudios.BlueBoyAdventure.core;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Launcher {

	public static void main(String[] args) {
		// JFRAME WINDOW:
		JFrame window = new JFrame(); // Create's a new JFrame window.
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close and terminate the program.
		window.setResizable(false); // Do not allow the Player to re-size the JFrame window.
		window.setTitle("Blue Boy Adventure"); // Set's the title for the Game.
		
		GamePanel gamePanel = new GamePanel(); // Create a new instance of the GamePanel. 
		window.add(gamePanel); // Add's the GamePanel to the JFrame window.
		
		window.pack(); // Causes this JFrame window to be sized to fit the preferred size and layouts of the GamePanel's sub-components.
		
		// ADD THE LOGO TO GAME'S JFRAME WINDOW:
		try {
			BufferedImage logo = ImageIO.read(Launcher.class.getResourceAsStream("/logo.png")); // Load the window Logo.
			window.setIconImage(logo);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		window.setLocationRelativeTo(null); // The JFrame window will be displayed at the centre of the screen.
		window.setVisible(true); // Make the JFrame window visible to the Player.

		gamePanel.setupGame(); // Call method to start the game with objects etc.
		gamePanel.startGameThread(); // Call method to start the gameThread.
	}
}