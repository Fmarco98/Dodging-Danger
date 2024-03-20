package GamePanel_components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import Util.AbstractCustomPanel;

/**
 * Classe che rappresenta il pannello dedicato al countDown della fine del power up
 */
public class PowerUpDurationPanel extends AbstractCustomPanel{
	//Impostazioni
	public static final double[] DIMENSION = {1, 0.10};
	public static final int COUNTDOWN_BAR_REFRESHRATE = 100;//ms
	private static final double BAR_GAP = 0.3;
	
	//Attributi
	private Rectangle timeBar;
	private Timer t;
	private double refreshSize;
	
	/**
	 * Costruttore
	 */
	public PowerUpDurationPanel(int width, int height) {
		super(width, height);
		
		int gap = (int)(height*BAR_GAP);
		timeBar = new Rectangle();
		timeBar.setLocation(gap, gap);
		
		PowerUpDurationPanel p = this;
		t = new Timer(PowerUpDurationPanel.COUNTDOWN_BAR_REFRESHRATE, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Rectangle r = p.getCountDownBar();
				
				int newWidth = (int)(r.width-(p.getRefreshSize()*(double)PowerUpDurationPanel.COUNTDOWN_BAR_REFRESHRATE));
				if(newWidth < 0) {
					newWidth = 0;
				}
				r.setSize(newWidth, r.height);
			}
		});
		
	}
	
	/**
	 * Inizio del countDown
	 */
	public void startCountdown(long time) {
		int gap = (int)(this.getHeight()*BAR_GAP);
		timeBar.setSize(this.getWidth()-(2*gap), this.getHeight()-(2*gap));
		
		refreshSize = (double)timeBar.width/time;
		
		t.start();
	}
	
	/**
	 * Fine del countdown
	 */
	public void stopCountdown() {
		timeBar.setSize(timeBar.width, 0);
		refreshSize = 0;
		
		t.stop();
	}
	
	//Metodi settere e getter
	public Rectangle getCountDownBar() {
		return timeBar;
	}
	public double getRefreshSize() {
		return refreshSize;
	}
	public void setRefreshSize(int size) {
		refreshSize = size;
	}
	
	@Override
	public void paint(Graphics g) {
		g.setColor(Color.gray);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		g.setColor(Color.green);
		g.fillRect(timeBar.x, timeBar.y, timeBar.width, timeBar.height);
	}
}