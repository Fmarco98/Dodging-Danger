package GameObjects;

import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

import Util.GameMovingObject;

/**
 * Classe che rappresenta un ostacolo
 */
public class Obstacle extends Rectangle implements GameMovingObject{
	//Impostazioni
	public static final int GENERATION_TIME = 5*1000;
	public static final double[] OSTACOLO_DIMENSION = {0.70, 0.10};//w, h
	private static final String IMG_PATH = "resources/images/obstacle.png";
	
	//Attributi
	private ImageIcon img;
	private double x, y, width, height;
	
	/**
	 * Costruttore
	 */
	public Obstacle(int x, int y, int width, int height) {
		this.img = new ImageIcon(IMG_PATH);
		
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
	 * Disegno
	 */
	public void draw(Graphics g) {
		g.drawImage(img.getImage(), super.x, super.y, super.width, super.height, null);
	}
	
	//Metodi di movimento
	
	@Override
	public void zoom(double px) {		
		double ratio = (double)height/width;
		
		double newWidth = px+width;
		double newHeight = ratio*newWidth;
		
		this.width = newWidth;
		this.height = newHeight;
		super.width = (int)this.width;
		super.height = (int)this.height;		
		
		this.moveLeft(px/2);
	}
	
	@Override
	public void moveRight(double px) {
		x += px;
		super.x = (int)x;
	}
	
	@Override
	public void moveDown(double px) {
		y += px;
		super.y = (int)y;
	}

	@Override
	public void moveLeft(double px) {
		x -= px;		
		super.x = (int)x;
	}
}
