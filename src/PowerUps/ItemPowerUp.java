package PowerUps;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.Timer;

import GamePanel_components.GraphicGamePanel;
import GamePanel_components.PowerUpPanel;
import Util.GameMovingObject;

/**
 * Classe per rappresentare un Qualsiasi powerUp
 */
public abstract class ItemPowerUp{
	
	//Tipi di powerUp
	public static final int TANK_TYPE = 1;
	public static final int RESET_TYPE = 2;
	public static final int SPEEDY_STEARING_TYPE = 3;
	public static final int SLOW_GAME_DOWN_TYPE = 4;
	
	//Attributi
	private int type, duration; // tipo di pw, durata
	private Timer endDelay;		// Timer di delay fra inizio e fine
	private ImageIcon img;		// Immagine
	
	/**
	 * Cotruttore di Power Up
	 */
	public ItemPowerUp(int type, int duration, ImageIcon icon) {
		this.type = type;
		this.duration = duration;
		
		this.img = icon;
	}
	
	
	/**
	 * Medoto riciamato per eseguire l'effetto del powerUP
	 */
	public void effectAction(GraphicGamePanel g, PowerUpPanel powerUpButtonGroup) {
		Object[] args = this.startActions(g);
		
		int duration = this.getDuration() != 0 ? this.getDuration(): 1;
		
		ItemPowerUp pw = this;
		endDelay = new Timer(duration, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pw.endActions(g, args);
				
				powerUpButtonGroup.getCountDownBar().stopCountdown();
				powerUpButtonGroup.setPowerUpActived(null);
				powerUpButtonGroup.setPowerUpEnabled(false);
				endDelay.stop();
			}
		});
		powerUpButtonGroup.setPowerUpEnabled(true);
		powerUpButtonGroup.setPowerUpActived(this);
		powerUpButtonGroup.getCountDownBar().startCountdown(duration);
		endDelay.start();
	};
	
	/**
	 * Contiene le modifiche di inzio dell'effetto
	 */
	public abstract Object[] startActions(GraphicGamePanel g);
	
	/**
	 * Contiene le azioni di fine dell'effetto
	 */
	public abstract void endActions(GraphicGamePanel g, Object[] args);
	
	/**
	 * Metodo per interrompere un effetto
	 */
	public void interrupt() {
		endDelay.getActionListeners()[0].actionPerformed(null);
	}

	//Metodi getter
	public int getDuration() {
		return duration;
	}
	public ImageIcon getImage() {
		return img;
	}
	public int getType() {
		return type;
	}

}