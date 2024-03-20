package GameObjects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import PowerUps.ItemPowerUp;
import Util.GameMovingObject;

public class PowerUp extends Rectangle implements GameMovingObject{
	public static final double[] POWERUP_DIMENSION = {0.05, 0.05};//w, h
	public static final int GENERATION_TIME = (int)(33.5*1000);
	
	private double x, y, width, height;	
	private ItemPowerUp powerUp;
	
	public PowerUp(int x, int y, int width, int height, ItemPowerUp pw) {
		this.powerUp = pw;
		
		this.x = x;
		super.x = x;
		this.y = y;
		super.y = y;
		this.width = width;
		super.width = width;
		this.height = height;
		super.height = height;
	}
	
	/**
	 * Disegna il power up
	 */
	public void draw(Graphics g) {
		if(powerUp.getImage() != null) {
			g.drawImage(powerUp.getImage().getImage(), super.x, super.y, super.width, super.height, null);
		} else {
			g.setColor(Color.green);
			g.fillOval(super.x, super.y, super.width, super.height);			
		}
		
	}
	
	/**
	 * MOvimento verso sinistra
	 */
	@Override
	public void moveLeft(double px) {
		x -= px;		
		super.x = (int)x;
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
	
	public ItemPowerUp getItemPowerUp() {
		return this.powerUp;
	}
}
