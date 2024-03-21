package Util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import javax.swing.JOptionPane;

/**
 * Manager dello score della partite
 */
public class ScoreManager {
	private static final String file_path = "saves.txt";
	
	private File file;
	
	/**
	 * Costruttore
	 */
	public ScoreManager() {
		this.file = new File(file_path);
		
		try {
			if(!file.exists()) 
				file.createNewFile();
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Impossibile aprire il file dei salvataggi in locale", "File error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * Aggiungi nuovo score
	 */
	public boolean addScore(int score) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
			
			writer.write(score+"\n");
			
			writer.close();
			
			return true;
		} catch (IOException e) {}
		return false;
	}
	
	/**
	 * Ottieni score più alto
	 */
	public int getHighestScore() {
		ArrayList<Integer> scores = this.getScores();
		
		Collections.sort(scores);
		Collections.reverse(scores);
		
		return scores.get(0);
	}
	
	/**
	 * Ottieni i primi n score più alti
	 */
	public ArrayList<Integer> getTopScores(int n) {
		ArrayList<Integer> scores = this.getScores();
		ArrayList<Integer> topScores = new ArrayList<>();
		
		Collections.sort(scores);
		Collections.reverse(scores);
		
		n = scores.size() < n ? scores.size() : n;
		for(int i=0; i < n; i++) {
			topScores.add(scores.get(i));
		}
		
		return topScores;
	}
	
	/**
	 * Cancellare il azzera i salvataggi degli score
	 */
	public boolean resetSaves() {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(file, false));
			
			writer.write("");
			
			writer.close();
			
			return true;
		} catch (IOException e) {}
		return false;
	}
	
	/**
	 * Ottieni tutti gli score
	 */
	public ArrayList<Integer> getScores() {
		try {
			Scanner sc = new Scanner(file);
			ArrayList<Integer> lines = new ArrayList<>();
			
			while(sc.hasNextInt()) {
				lines.add(sc.nextInt());
			}
			
			return lines;
		} catch(IOException e) {
			e.getStackTrace();
		}		
		return null;
	}
}