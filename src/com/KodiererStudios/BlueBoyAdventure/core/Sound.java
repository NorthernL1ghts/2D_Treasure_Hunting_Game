package com.KodiererStudios.BlueBoyAdventure.core;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
	Clip clip; // Use to open audio files.
	URL soundURL[] = new URL[30]; // Create an array to store the file path of audio files.
	
	// CONSTRUCTOR:
	public Sound() {
		// LOAD THE NECESSARY AUDIO FILES:
		soundURL[0] = getClass().getResource("/sounds/BlueBoyAdventure.wav"); // Main Game theme.
		soundURL[1] = getClass().getResource("/sounds/coin.wav");
		soundURL[2] = getClass().getResource("/sounds/powerup.wav");
		soundURL[3] = getClass().getResource("/sounds/unlock.wav");
		soundURL[4] = getClass().getResource("/sounds/fanfare.wav");
	}
	
	// SET AUDIO FILES:
	public void setFile(int i) {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]); // Get the soundURL with index.
			clip = AudioSystem.getClip(); // Get's the audio clip.
			clip.open(ais); // Opens the clip with the format and audio data. 
		} catch (Exception e) {
			
		}
	}
	
	// PLAY AUDIO FILES:
	public void play() {
		clip.start(); // Start the clip.
	}
	
	public void loop() {
		clip.loop(Clip.LOOP_CONTINUOUSLY); // Continuously loop audio files.
	}
	
	public void stop() {
		clip.stop(); // Stop's the audio file.
	}
}