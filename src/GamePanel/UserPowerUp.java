package GamePanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import PowerUps.ItemPowerUp;

/**
 * Classe astratta per rappresentare i vari powerups
 */
public class UserPowerUp extends JButton{
	
	//Oggetto power up contenuto
	private ItemPowerUp powerUp;
	
	private GraphicGamePanel game;		//Gioco dove ricadono gli effetti
	private PowerUpPanel powerUpGroup;	//Gruppo der pulsanti
	
	/**
	 * Costruttore di un power up vuoto
	 */
	public UserPowerUp(PowerUpPanel p, GraphicGamePanel g) {
		super();
		this.setEnabled(false);
		
		this.powerUpGroup = p;
		this.game = g;
		
		UserPowerUp u = this;
		this.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!u.getPowerUpGroup().isPowerUpEnabled()) u.usePowerUp();
			}
		});
	}
	
	/**
	 * Uso del power up contenuto
	 */
	public void usePowerUp() {
		if(this.powerUp != null) this.powerUp.effectAction(game, this.powerUpGroup);		
		
		powerUpGroup.removePowerUp(this.powerUp);
		powerUpGroup.refresh();
	}
	
	/**
	 * Cliccare il pulsante
	 */
	public void doClick() {
		if(this.isEnabled()) super.doClick();
	}
	
	//Metod getter e setter
	
	public void setPowerUp(ItemPowerUp powerUp) {
		this.powerUp = powerUp;
		
		if(powerUp != null) {
			this.setIcon(powerUp.getImage());
			this.setEnabled(true);
		} else {
			this.setIcon(null);
			this.setEnabled(false);
		}
	}
	public ItemPowerUp getPowerUp() {
		return powerUp;
	}
	public PowerUpPanel getPowerUpGroup() {
		return powerUpGroup;
	}
}