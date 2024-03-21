package Util;

import java.awt.Rectangle;

/**
 * Classe rectangle di awt, ma rimodellata con dimensione double
 */
public class Rectanglev2 extends Rectangle{
	
	private double width, height;
	
	/**
	 * Costruttore
	 */
	public Rectanglev2(int x, int y, int width, int height) {
		super(x, y, width, height);
		
		this.width = width;
		this.height = height;
	}
	
	/**
	 * Imposta le dimensioni
	 */
	public void setSize(double width, double height) {
		super.setSize((int)width, (int)height);
		
		this.width = width;
		this.height = height;
	}
	
	/**
	 * Imposta dimensione e posizione del rettangolo
	 */
	public void setBounds(int x, int y, double width, double height) {
		super.setLocation(x, y);
		this.setSize(width, height);
	}
	
	//Metodi getter
	
	@Override
	public double getWidth() {
		return this.width;
	}
	@Override
	public double getHeight() {
		return this.height;
	}
}