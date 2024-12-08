package main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
	
	Clip clip;
	URL soundUrl[] = new URL[30];
	
	
	public Sound () {
		soundUrl[0] = getClass().getResource("/sounds/Sound/BlueBoyAdventure.wav");
		soundUrl[1] = getClass().getResource("/sounds/Sound/coin.wav");
		soundUrl[2] = getClass().getResource("/sounds/Sound/unlock.wav");
		soundUrl[3] = getClass().getResource("/sounds/Sound/fanfare.wav");
		soundUrl[4] = getClass().getResource("/sounds/Sound/blocked.wav");
		
		
		
	}
	
	public void setFile(int i) {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundUrl[i]);
			clip = AudioSystem.getClip();
			clip.open(ais);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void start() {
		clip.start();
	}
	
	public void loop() {
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	public void stop() {
		clip.stop();
	}
	
	
	
}
