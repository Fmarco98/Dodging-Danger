package Util;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;

public class SoundPlayer {
	public static final boolean DO_LOOP = true;
	public static final boolean NOT_LOOP = false;
	
	private File file;
	private AudioInputStream audio;
	private Clip music;
	
	private boolean loop;
	private boolean started;
	
	public SoundPlayer(String filepath, boolean loop) {
		this(filepath, loop, true);
	}
	
	public SoundPlayer(String filepath, boolean loop, boolean enable) {
		if(enable) {
			this.loop = loop;
			file = new File(filepath);
			try {
				audio = AudioSystem.getAudioInputStream(file);
				music = AudioSystem.getClip();
				music.open(audio);
			} catch(Exception e) {
				JOptionPane.showMessageDialog(null, "Impossibile accedere al sound: "+filepath, "Sound error", JOptionPane.ERROR_MESSAGE);
			}
			this.stop();			
		}
	}
	
	public void start() {
		if(music != null) {
			started = true;
			music.start();
			if(loop) music.loop(Clip.LOOP_CONTINUOUSLY);	
		}
	}
	
	public void restart() {
		if(music != null) {
			this.stop();
			this.start();
		}
	}
	
	public void stop() {
		if(music != null) {
			started = false;
			music.stop();
			music.setMicrosecondPosition(0);
		}
	}
	
	public void close() {
		if(music != null) {
			music.close();
			try {
				audio.close();
			} catch (IOException e) {}
		}
	}
}
