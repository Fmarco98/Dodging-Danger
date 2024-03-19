package GameObjects;

import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

import Util.GameMovingObject;

/**
 * Classe per rappresentare la macchina
 */
public class Car extends Rectangle implements GameMovingObject{
	//Impostazioni
	public static final double[] DIMENSION = {0.11, 0.30, 0.03}; //base sotto, base sopra, altezza, gap l-r	
	private static final double IMG_PADDING = 0.30;
	private static final String IMG_PATH = "resources/car.png";
	private static final double VP_STEARING = 0.03;
		
	//Attributi
	private ImageIcon img;
	private Rectangle carArea;
	private int vStearing;
	
	/**
	 * Costruttore
	 */
	public Car(int width, int height, Rectangle carArea) {
		img = new ImageIcon(IMG_PATH);
		
		this.carArea = carArea;
		
		this.vStearing = (int)(carArea.getWidth()*VP_STEARING);
		
		this.width = width;
		this.height = height;
		
		this.y = (int)carArea.getY();
		this.x = (int)((carArea.getWidth()-width)/2 + carArea.getX());
	}
	
	/**
	 * Centra la posizione all'interno della carArea
	 */
	public void centerPosition() {
		y = (int)carArea.getY();
		x = (int)((carArea.getWidth()-width)/2 + carArea.getX());
	}
	
	// Movimento
	public void moveRight() {
		this.moveRight(vStearing);
	}
	public void moveLeft() {
		this.moveLeft(vStearing);
	}
	
	@Override
	public void moveLeft(double px) {
		this.x -= px;
		if(x < (int)carArea.getX()) {
			x = (int)carArea.getX();
		}
	}
	
	@Override
	public void moveRight(double px) {
		this.x += px;
		if(x+width > (int)(this.carArea.getWidth()+this.carArea.getX())) {
			x = (int)(this.carArea.getWidth()+this.carArea.getX())-width;
		}
	}
	
	@Override
	public void moveDown(double px) {}
	@Override
	public void zoom(double px) {}
	/**
	 * Disegno
	 */
	public void draw(Graphics g) {
		double paddingW = width*IMG_PADDING; 
		double paddingH = height*IMG_PADDING; 
		
		g.drawImage(img.getImage(), x-(int)(paddingW/2), y-(int)(paddingH/2), (int)(width+paddingW), (int)(height+paddingH), null);
	}
	
	//Metodi gettere e setter
	public void setCarArea(Rectangle area) {
		this.carArea = area;
		
		vStearing = (int)(carArea.getWidth()*VP_STEARING);
		this.centerPosition();
	}
	public int getvStearing() {
		return vStearing;
	}
	public void setvStearing(int vStearing) {
		this.vStearing = vStearing;
	}
	public ImageIcon getCarImg() {
		return img;
	}
	public void setCarImg(ImageIcon img) {
		this.img = img;
	}

}