package com.KodiererStudios.BlueBoyAdventure.core;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import com.KodiererStudios.BlueBoyAdventure.entity.Player;
import com.KodiererStudios.BlueBoyAdventure.object.SuperObject;
import com.KodiererStudios.BlueBoyAdventure.tile.TileManager;

public class GamePanel extends JPanel implements Runnable {
	private static final long serialVersionUID = 1L;
	
	// SCREEN SETTINGS:
	final int originalTileSize = 16; // 16x16 tile.
	final int scale = 3; // Scales everything in the game by a factor of 3.
	
	public final int tileSize = originalTileSize * scale; // 48x48 tile - This is the actual tile size.
	public final int maxScreenCol = 16; 
	public final int maxScreenRow = 12;
	public final int screenWidth = tileSize * maxScreenCol; // 48x16 = 768 pixels horizontally.
	public final int screenHeight = tileSize * maxScreenRow; // 48x12 = 576 pixels vertically.
	
	// WORLD SETTINGS:
	public final int maxWorldCol = 50;
	public final int maxWorldRow = 50;
	
	// FPS:
	int FPS = 60;
	
	// SYSTEM INSTANTIATION:
	TileManager tileM = new TileManager(this); // Instantiate the TileManager class.
	KeyHandler keyH = new KeyHandler(); // Instantiate the KeyHandler class.
	Sound music = new Sound(); // Instantiate the Sound class to play music.	
	Sound se = new Sound(); // Instantiate the Sound class to play sound effects.
	public CollisionChecker cChecker = new CollisionChecker(this); // Instantiate the CollisionChecker class.
	public AssetSetter aSetter = new AssetSetter(this); // Instantiate the AssetSetter class.
	public UI ui = new UI(this); // Instantiate the UI class.
	Thread gameThread; // Create a new process that can be started / stopped.
	
	// ENTITY AND OBJECT INSTANTIATION:
	public Player player = new Player(this, keyH); // Instantiate the Player class.
	public SuperObject obj[] = new SuperObject[10]; // Instantiate the SuperObject class & create a new array of objects.
	
	// CONSTRUCTOR:
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight)); // Set's the size of this JPanel class.
		this.setBackground(Color.black); // Set's the background colour of the GamePanel.
		this.setDoubleBuffered(true); // All drawing from this component will be done in an off-screen painting buffer to improve rendering performance.
		this.addKeyListener(keyH); // The GamePanel can recognise key input using the KeyHandler class.
		this.setFocusable(true); // GamePanel can be "focused" to receive key input.
	}

	// SET'S THE NECESSARY PROPERTIES OF THE GAME:
	public void setupGame() {
		aSetter.setObject(); // Call's the method to set object positions.
		
		playMusic(0); // Play's the "BlueBoyAdventure" theme song, when the Game starts.
	}
	
	public void startGameThread() {
		gameThread = new Thread(this); // Instantiate the gameThread.
		gameThread.start(); // Start's the gameThread by calling the run method (game loop).
	}
	
	// GAME LOOP:
	public void run() {
		double drawInterval = 1000000000 / FPS; // Convert nanoseconds to seconds for FPS precise calculations.
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		long timer = 0;
		int drawCount = 0;
		
		while (gameThread != null) { // As long as the gameThread exists, continuously loop.
			currentTime = System.nanoTime(); // Get's the current System time in nanoseconds.
			
			delta += (currentTime - lastTime) / drawInterval; // Calculate the current time and minus the last draw time (how much time has passed).
			
			timer += (currentTime - lastTime); // Passed time is added to the timer.
			
			lastTime = currentTime; // Last render time is now the current system time.
			
			if (delta >= 1) {
				update();
				repaint(); // Call's the paintComponent method.
				delta--; // Decrement.
				drawCount++; // Increment.
			}
			
			// DISPLAY RELEVANT INFORMATION:
			if (timer >= 1000000000) {
				System.out.println("FPS: " + drawCount);
				drawCount = 0; // Reset.
				timer = 0; // Reset.
			}
		}
	}
	
	// TICK:
	public void update() {
		player.update(); // Call's the update (tick) method from the Player class.
	}
	
	public void paintComponent(Graphics g) { // This paintComponent is something that already exists within Java to draw on JPanel.
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g; // Convert Graphics to Graphics2D for more functionality.
		
		// TILE:
		tileM.draw(g2); // Call's the draw (render) method from the TileManager class.
		
		// OBJECT:
		for (int i = 0; i < obj.length; i++) { // Loop through the object array to find out, what object it is,
			if (obj[i] != null) { // Check if slot is empty to prevent NullPointerError.
				obj[i].draw(g2, this); // Call method to draw objects.
			}
		}
		
		// PLAYER:
		player.draw(g2); // Call's the draw (render) method from the Player class.
		
		// UI:
		ui.draw(g2); // Call's the draw (render) method from the UI class.
		
		g2.dispose(); // Free's and releases non essential system resources and memory.
	}
	
	// PLAY MUSIC:
	public void playMusic(int i) {
		music.setFile(i); // Set's the audio file.
		music.play(); // Play the audio file.
		music.loop(); // Loop the audio file.
	}
	
	// STOP MUSIC:
	public void stopMusic() {
		music.stop(); // Stop's the music.
	}
	
	// SOUND EFFECTS:
	public void playSE(int i) {
		se.setFile(i); // Set's the audio file.
		se.play(); // Play's the sound effect.
	}
}