package GamePanel_components;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;

import javax.swing.JPanel;

import PowerUps.ItemPowerUp;
import Util.AbstractCustomPanel;

/**
 * Classe per rappresentare sia il pannello che il gruppo di power up utilizzabili dall'utente
 */
public class PowerUpPanel extends AbstractCustomPanel {
	//Impostazioni
	private static final int TOT_POWERUPS = 3;
	
	//Elementi dedicati ai PowerUp
	private ArrayList<ItemPowerUp> powerUps = new ArrayList<>();	//pw nell'inventario
	private boolean powerUpEnabled;	//serve pk solo 1 pw per volta deve essere attivo
	private ItemPowerUp powerUpActived;
	
	//Elementi grafici
	private PowerUpDurationPanel countDown;
	private UserPowerUp[] buttons;
	
	/**
	 * Costruttore del pannello
	 */
	public PowerUpPanel(int width, int height, GraphicGamePanel g) {
		super(width, height);
		
		countDown = new PowerUpDurationPanel((int)(width*PowerUpDurationPanel.DIMENSION[0]), (int)(height*PowerUpDurationPanel.DIMENSION[1]));
		powerUpEnabled = false;
		
		this.createPanel(g);
		this.dimensionResized();
	}
	
	//Metodo per creare l'interfaccia
	private void createPanel(GraphicGamePanel game) {
		this.setLayout(new BorderLayout());
		this.add(countDown, BorderLayout.NORTH);
		
		JPanel buttonsPanel = new JPanel();
		buttons = new UserPowerUp[TOT_POWERUPS];
		
		buttonsPanel.setLayout(new GridLayout(1,3));
		for(int i=0; i < TOT_POWERUPS; i++) {
			buttons[i] = new UserPowerUp(this, game);
			
			buttonsPanel.add(buttons[i]);
		}
		this.add(buttonsPanel, BorderLayout.CENTER);
	}
	
	/**
	 * Metodo per aggiornare la grafica del pannello
	 */
	public void refresh() {
		for(UserPowerUp up: buttons) {
			up.setPowerUp(null);
		}
		
		for(int i=0; i < this.powerUps.size(); i++) {
			buttons[i].setPowerUp(powerUps.get(i));
		}
		
		this.invalidate();
		this.validate();
	}
	
	/**
	 * Aggiunge un power Up all'inventario
	 */
	public boolean addPowerUp(ItemPowerUp u) {
		if(this.powerUps.size() < TOT_POWERUPS) {
			this.powerUps.add(u);
			
			this.refresh();
			return true;
		}
		return false;
	}
	
	/**
	 * Rimuove il power up dall'invetario
	 */
	public void removePowerUp(ItemPowerUp u) {
		this.powerUps.remove(u);
	}
	
	/**
	 * Resetta il pannello
	 */
	public void reset() {
		this.powerUps.clear();
		
		if(powerUpActived != null) {
			powerUpActived.interrupt();
		}
		
		this.refresh();
	}
	
	// Metodo sette e getter
	
	public boolean isPowerUpEnabled() {
		return powerUpEnabled;
	}
	public void setPowerUpEnabled(boolean b) {
		this.powerUpEnabled = b;
	}
	public ItemPowerUp getPowerUpActived() {
		return powerUpActived;
	}
	public void setPowerUpActived(ItemPowerUp powerUpActived) {
		this.powerUpActived = powerUpActived;
	}
	public PowerUpDurationPanel getCountDownBar() {
		return countDown;
	}
	public UserPowerUp[] getButtons() {
		return this.buttons;
	}
	
	//ridimensionamento del pannello
	private void dimensionResized() {
		PowerUpPanel p = this;	
		this.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
            	p.getCountDownBar().setSize((int)(p.getWidth()*PowerUpDurationPanel.DIMENSION[0]), (int)(p.getHeight()*PowerUpDurationPanel.DIMENSION[1]));
            	
            	p.invalidate();
            	p.validate();
            }
        });
	}

}
