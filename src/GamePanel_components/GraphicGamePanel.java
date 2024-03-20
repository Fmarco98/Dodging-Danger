package GamePanel_components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import GameObjects.Car;
import GameObjects.Obstacle;
import Util.AbstractCustomPanel;
import Util.Point;

/**
 * Classe per il pannello del gioco
 */
public class GraphicGamePanel extends AbstractCustomPanel {
	//Impostazioni
	public static final int V_MIN = 1;
	private static final double V_MAX_P = 0.023;
	private static final int OBSTACLE_GENERATED_TO_VMAX = 30;
	
	//Elementi del gioco
	private Road strada;
	private Car car;
	
	//Attributi
	private boolean carDies; 
	private double aMove;
	private int vMax;
	private double vMove = V_MIN;
	
	/**
	 * Costruttore
	 */
	public GraphicGamePanel(int width, int height) {
		super(width, height);
		carDies = true;
		
		vMax = (int)(this.getHeight()*V_MAX_P);
		aMove = (vMax-V_MIN)/(OBSTACLE_GENERATED_TO_VMAX*Obstacle.GENERATION_TIME);
		
		int wCar = (int)(this.getWidth()*Car.DIMENSION[0]);
		int hCar = (int)(this.getHeight()*Car.DIMENSION[1]);
		int uCar = (int)(this.getHeight()*Car.DIMENSION[2]);
		
		int b1 = (int)(this.getWidth()*Road.DIMENSION[0]);
		int b2 = (int)(this.getWidth()*Road.DIMENSION[1]);
		int h = this.getHeight()-(int)(this.getHeight()*Road.DIMENSION[2]);
		int diff = (b1-b2)/2;
		int liberoX = (this.getWidth()-b1)/2;
		
		Point A,B,C,D;
		A = new Point(liberoX+diff, h);
		B = new Point(this.getWidth()-liberoX-diff, h);
		C = new Point(this.getWidth()-liberoX, this.getHeight());
		D = new Point(liberoX, this.getHeight());
		Point[] roadDim = {A, B, C, D};
		
		int lineGap1 = b1/3, lineGap2 = b2/3;
		Point p11 = new Point(liberoX+diff+lineGap2,h);
		Point p12 = new Point(liberoX+lineGap1,this.getHeight());
		Point[] line1 = {p11, p12};
		
		Point p21 = new Point(liberoX+diff+lineGap2+lineGap2,h);
		Point p22 = new Point(liberoX+lineGap1+lineGap1,this.getHeight());
		Point[] line2 = {p21, p22};
		
		this.strada = new Road(roadDim, line1, line2);
		car = new Car(wCar, hCar, new Rectangle(liberoX+(diff/2), this.getHeight()-uCar-hCar, b1-diff, hCar));
		
		this.dimensionResized();
	}
	
	/**
	 * Reset del pannello
	 */
	public void reset() {
		vMove = V_MIN;
		
		this.strada.reset();
		this.car.centerPosition();
	}
	
	/**
	 * Movimento di tutti gli oggetti
	 */
	public void moveItems() {
		this.strada.moveItem(vMove);
	}
	
	/**
	 * Auomento della velocita
	 */
	public void increaseV(int ms) {
		this.vMove += ms*aMove;
		if(this.vMax < vMove) {
			vMove = vMax;
		}
	}
	
	//Metodi getter e setter
	public boolean canCarDie() {
		return this.carDies;
	}
	public void setCarDies(boolean b) {
		this.carDies = b;
	}
	
	public Car getCar() {
		return this.car;
	}
	public Road getRoad() {
		return this.strada;
	}
	public double getMaxV() {
		return vMax;
	}
	public double getCurrentV() {
		return this.vMove;
	}
	public void setCurrentV(double v) {
		this.vMove = v;
	}
	public void setAcceleration(double a) {
		this.aMove = a;
	}
	public double getAcceleration() {
		return aMove;
	}
	
	@Override
	public void paint(Graphics g) {
		g.setColor(new Color(130,255,243));
		g.fillRect(0, 0, this.getWidth(), this.getHeight());

		g.setColor(Color.orange);
		this.drawBuilding(g, 0);

		int b1 = (int)(this.getWidth()*Road.DIMENSION[0]);
		int b2 = (int)(this.getWidth()*Road.DIMENSION[1]);

		int liberoX = (this.getWidth()-b1)/2;
		int diff = (b1-b2)/2;
		this.drawBuilding(g, this.getWidth()-(liberoX+diff));
		
		this.strada.draw(g);
		
		this.car.draw(g);
	}
	
	//Disegno dell'edifico
	private void drawBuilding(Graphics g, int x) {
		int b1 = (int)(this.getWidth()*Road.DIMENSION[0]);
		int b2 = (int)(this.getWidth()*Road.DIMENSION[1]);
		int h = this.getHeight()-(int)(this.getHeight()*Road.DIMENSION[2]);

		int liberoX = (this.getWidth()-b1)/2;
		int diff = (b1-b2)/2;

		int width = liberoX+diff;

		g.fillRect(x, 0, width, this.getHeight());
	}
	
	//Ridimensiamento
	private void dimensionResized() {
		GraphicGamePanel p = this;
		this.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				vMax = (int)(p.getHeight()*V_MAX_P);
				aMove = (double)(vMax-V_MIN)/(OBSTACLE_GENERATED_TO_VMAX*Obstacle.GENERATION_TIME);
				
				int wCar = (int)(p.getWidth()*Car.DIMENSION[0]);
				int hCar = (int)(p.getHeight()*Car.DIMENSION[1]);
				int uCar = (int)(p.getHeight()*Car.DIMENSION[2]);

				int b1 = (int)(p.getWidth()*Road.DIMENSION[0]);
				int b2 = (int)(p.getWidth()*Road.DIMENSION[1]);
				int diff = (b1-b2)/2;
				int liberoX = (p.getWidth()-b1)/2;
				int h = p.getHeight()-(int)(p.getHeight()*Road.DIMENSION[2]);
				
				Point A,B,C,D;
				A = new Point(liberoX+diff, h);
				B = new Point(p.getWidth()-liberoX-diff, h);
				C = new Point(p.getWidth()-liberoX, p.getHeight());
				D = new Point(liberoX, p.getHeight());
				Point[] roadDim = {A, B, C, D};
				
				int lineGap1 = b1/3, lineGap2 = b2/3;
				Point p11 = new Point(liberoX+diff+lineGap2,h);
				Point p12 = new Point(liberoX+lineGap1,p.getHeight());
				Point[] line1 = {p11, p12};
				
				Point p21 = new Point(liberoX+diff+lineGap2+lineGap2,h);
				Point p22 = new Point(liberoX+lineGap1+lineGap1,p.getHeight());
				Point[] line2 = {p21, p22};
				
				p.getRoad().resize(roadDim, line1, line2);;
				p.getCar().setSize(wCar, hCar);
				p.getCar().setCarArea(new Rectangle(liberoX+(diff/2), p.getHeight()-uCar-hCar, b1-diff, hCar));
				
				p.invalidate();
				p.validate();
			}
		});
	}
}