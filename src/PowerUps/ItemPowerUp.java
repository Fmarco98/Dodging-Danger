package PowerUps;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.Timer;

import GamePanel.GraphicGamePanel;
import GamePanel.PowerUpPanel;
import Util.GameMovingObject;

/**
 * Classe per rappresentare un Qualsiasi powerUp
 */
public abstract class ItemPowerUp extends Rectangle implements GameMovingObject{
	//Impostazioni
	public static final double[] POWERUP_DIMENSION = {0.05, 0.05};//w, h
	public static final int GENERATION_TIME = (int)(33.5*1000);
	
	//Tipi di powerUp
	public static final int TANK_TYPE = 1;
	public static final int RESET_TYPE = 2;
	public static final int SPEEDY_STEARING_TYPE = 3;
	public static final int SLOW_GAME_DOWN_TYPE = 4;
	
	//Attributi
	private int type, duration; // tipo di pw, durata
	private Timer endDelay;		// Timer di delay fra inizio e fine
	private ImageIcon img;		// Immagine
	
	private double x, y, width, height;	//Attributi grafici
	
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

	// Metodi per disegno sul panello grafico
	
	/**
	 * Definire la posizione e dimensione del power up
	 */
	@Override
	public void setBounds(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		
		super.x = x;
		super.y = y;
		
		this.width = width;
		this.height = height;		
		super.width = width;
		super.height = height;
	}
	
	/**
	 * Disegna il power up
	 */
	public void draw(Graphics g) {
		if(this.img != null) {
			g.drawImage(this.img.getImage(), super.x, super.y, super.width, super.height, null);
		} else {
			g.setColor(Color.green);
			g.fillOval(super.x, super.y, super.width, super.height);			
		}
		
	}
	
	
	// --- metodi di movimento ---
	
	/**
	 * Zoomma l'oggetto dimensionalmente
	 */
	@Override
	public void zoom(double px) {
		
		double newWidth = px+width;
		double newHeight = px+height;
		
		this.width = newWidth;
		this.height = newHeight;
		super.width = (int)this.width;
		super.height = (int)this.height;		
		
		this.moveLeft(px/2);
	}
	
	/**
	 * Movimento a Destra
	 */
	@Override
	public void moveRight(double px) {
		x += px;
		super.x = (int)x;
	}
	
	/**
	 * Movimento verso il basso
	 */
	@Override
	public void moveDown(double px) {
		y += px;
		super.y = (int)y;
	}
	
	/**
	 * MOvimento verso sinistra
	 */
	@Override
	public void moveLeft(double px) {
		x -= px;		
		super.x = (int)x;
	}
	
}
