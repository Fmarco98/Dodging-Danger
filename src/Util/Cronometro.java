package Util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

/**
 * Classe per rappresentare un cronometro
 */
public class Cronometro {
	
	//Attributi
	private long time;
	private Timer timer;
	private boolean started;
	private boolean called;
	
	/**
	 * Costruttore
	 */
	public Cronometro(int ms) {
		time = 0;
		started = false;
		called = false;
		
		timer = new Timer(ms, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				called = false;
				time += ms;
			}
		});
	}
	
	/**
	 * Capire se è stato fatto un controllo sul tempo
	 */
	public boolean wasCalled() {
		return called;
	}
	
	/**
	 * Chiamare il controllo eseguito
	 */
	public void call() {
		called = true;
	}
	
	/**
	 * Cronometro avviato
	 */
	public boolean isStarted() {
		return started;
	}
	
	/**
	 * start del cronometro
	 */
	public void start() {
		time = 0;
		timer.start();
		started = true;
	}
	
	/**
	 * stop del cronometro
	 */
	public void stop() {
		timer.stop();
		started = false;
	}
	
	/**
	 * Controllo se è passato l'intervallo di tempo ms
	 */
	public boolean isElapsed(long ms) {
		return (time % ms)==0;
	}
	
	/**
	 * Ottieni il tempo trascorso
	 */
	public long getTimeElapsed() {
		return time;
	}
}
