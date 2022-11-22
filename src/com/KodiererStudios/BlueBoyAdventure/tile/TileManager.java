package com.KodiererStudios.BlueBoyAdventure.tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import com.KodiererStudios.BlueBoyAdventure.core.GamePanel;

public class TileManager {
	GamePanel gp; // Instantiate and implement the GamePanel class.	
	public Tile[] tile; // Create a tile array.
	public int mapTileNum[][];
	
	// CONSTRUCTOR:
	public TileManager(GamePanel gp) {
		this.gp = gp;
		
		tile = new Tile[10]; // Create 10 variants of tiles.
		mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow]; // Instantiate a 2D array for map tiles.
		
		getTileImage(); // Call method to get the necessary tile images.
		loadMap("/maps/world01.txt"); // Call method to load the map from text files.
	}
	
	// GET TILE IMAGE:
	public void getTileImage() {
		try {
			tile[0] = new Tile(); // Instantiate a new Tile.
			tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));
			
			tile[1] = new Tile(); // Instantiate a new Tile.
			tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));
			tile[1].collision = true; // Collision is on.
			
			tile[2] = new Tile(); // Instantiate a new Tile.
			tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));
			tile[2].collision = true; // Collision is on.
			
			tile[3] = new Tile(); // Instantiate a new Tile.
			tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/earth.png"));
			
			tile[4] = new Tile(); // Instantiate a new Tile.
			tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/tree.png"));
			tile[4].collision = true; // Collision is on.

			tile[5] = new Tile(); // Instantiate a new Tile.
			tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/sand.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// LOAD MAP DATA:
	public void loadMap(String filePath) {
		try {
			InputStream is = getClass().getResourceAsStream(filePath); // Instantiate a new InputStream to retrieve the map data file.
			BufferedReader br = new BufferedReader(new InputStreamReader(is)); // Use the BufferedReader to read the content of the map file.
			
			int col = 0; // Column.
			int row = 0; // Row.
			
			while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
				// READ THE MAP DATA FROM THE TEXT FILE:
				String line = br.readLine(); // BufferedReader can read a single line of the map data and place it into the String.
			
				while (col < gp.maxWorldCol) {
					String numbers[] = line.split(" "); // Split's the numbers from the text file and place them into the array.
					
					int num = Integer.parseInt(numbers[col]); // Convert the data type from String to Integer so we can use number array.
					
					mapTileNum[col][row] = num;  // Store's the extracted number in the mapTileNum array.
					col++; // Increment.
				}
				if (col == gp.maxWorldCol) { // If the text exceeds the map file.
					col = 0; // Reset.
					row++; // Increment.
				}
			}
			br.close(); // Close's the BufferedReader if there is no data to read.
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// RENDER:
	public void draw(Graphics2D g2) {
		// AUTOMATING THE TILE RENDERING:
		int worldCol = 0; // World Column.
		int worldRow = 0; // World Row.
		
		while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
			// DRAW DATA FROM TEXT FILE:
			int tileNum = mapTileNum[worldCol][worldRow]; // Extract a number that is stored, and get it as a tile number.
			
			// CALCULATE THE X & Y:
			int worldX = worldCol * gp.tileSize;
			int worldY = worldRow * gp.tileSize;
			int screenX = worldX - gp.player.worldX + gp.player.screenX;
			int screenY = worldY - gp.player.worldY + gp.player.screenY;
			
			// CREATE A BOUNDARY FROM THE CENTRE OF SCREEN (ONLY RENDER TILES AROUND PLAYER):
			if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && 
				worldX - gp.tileSize < gp.player.worldX + gp.player.screenX && 
				worldY + gp.tileSize > gp.player.worldY - gp.player.screenY && 
				worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
				
				g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);				
			}
			worldCol++; // Increment.
			
			if (worldCol == gp.maxWorldCol) { // If column hits 16.
				worldCol = 0; // Reset.
				worldRow++; // Increment.
			}
		}
	}
}