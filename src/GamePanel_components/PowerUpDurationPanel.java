package GamePanel_components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.Timer;

import Util.AbstractCustomPanel;
import Util.Rectanglev2;

/**
 * Classe che rappresenta il pannello dedicato al countDown della fine del power up
 */
public class PowerUpDurationPanel extends AbstractCustomPanel{
	//Impostazioni
	public static final double[] DIMENSION = {1, 0.10};
	public static final int COUNTDOWN_BAR_REFRESHRATE = 100;//ms
	private static final double BAR_GAP = 0.3;
	
	//Attributi
	private Rectanglev2 timeBar;
	private Timer t;
	private double refreshSize;
	
	/**
	 * Costruttore
	 */
	public PowerUpDurationPanel(int width, int height) {
		super(width, height);
		
		int gap = (int)(height*BAR_GAP);
		timeBar = new Rectanglev2(gap, gap, 0, height-2*gap);

		
// Problema la timebar non funzia
		PowerUpDurationPanel p = this;
		t = new Timer(PowerUpDurationPanel.COUNTDOWN_BAR_REFRESHRATE, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Rectanglev2 r = p.getCountDownBar();
				
				double newWidth = r.getWidth()-(p.getRefreshSize()*(double)PowerUpDurationPanel.COUNTDOWN_BAR_REFRESHRATE);
				if(newWidth < 0) {
					newWidth = 0;
				}
				r.setSize(newWidth, r.getHeight());
			}
		});
		
		this.dimensionResized();
	}
	
	/**
	 * Inizio del countDown
	 */
	public void startCountdown(long time) {
		int gap = (int)(this.getHeight()*BAR_GAP);
		timeBar.setSize((double)this.getWidth()-(2*gap), (double)this.getHeight()-(2*gap));
		
		refreshSize = timeBar.getWidth()/time;
		
		t.start();
	}
	
	/**
	 * Fine del countdown
	 */
	public void stopCountdown() {
		timeBar.setSize(0, timeBar.getHeight());
		refreshSize = 0;
		
		t.stop();
	}
	
	//Metodi settere e getter
	public Rectanglev2 getCountDownBar() {
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
		g.fillRect(timeBar.x, timeBar.y, (int)timeBar.getWidth(), (int)timeBar.getHeight());
	}
	
	//Ridimensionamento
		private void dimensionResized() {
			PowerUpDurationPanel p = this;
			this.addComponentListener(new ComponentAdapter() {
				@Override
				public void componentResized(ComponentEvent e) {
					Dimension dim = p.getSize();
					Rectangle r = p.getCountDownBar();
					
					int gap = (int)(dim.height*BAR_GAP);
					r.setBounds(gap, gap, (int)r.getWidth(), dim.height-2*gap);
					
					p.invalidate();
					p.validate();
				}
			});
		}
}