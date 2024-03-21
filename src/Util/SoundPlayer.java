package Util;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;

/**
 * Sound player 
 */
public class SoundPlayer {
	public static final boolean DO_LOOP = true;
	public static final boolean NOT_LOOP = false;
	
	private Clip music;
	
	private boolean loop;
	
	/**
	 * Costruttore
	 */
	public SoundPlayer(String filepath, boolean loop, boolean enable) {
		if(enable) {
			this.loop = loop;
			File file = new File(filepath);
			try {
				//Stream audio
				AudioInputStream audio = AudioSystem.getAudioInputStream(file);
				music = AudioSystem.getClip();
				music.open(audio);
			} catch(Exception e) {
				JOptionPane.showMessageDialog(null, "Impossibile accedere al sound: "+filepath, "Sound error", JOptionPane.ERROR_MESSAGE);
			}
			this.stop();			
		}
	}
	
	/**
	 * Inizia l'audio
	 */
	public void start() {
		if(music != null) {
			music.start();
			if(loop) music.loop(Clip.LOOP_CONTINUOUSLY);	
		}
	}
	
	/**
	 * riinizia l'audio
	 */
	public void restart() {
		if(music != null) {
			this.stop();
			this.start();
		}
	}
	
	/**
	 * Ferma l'audio
	 */
	public void stop() {
		if(music != null) {
			music.stop();
			music.setMicrosecondPosition(0);
		}
	}
}