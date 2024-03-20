package GamePanel_components;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import GameObjects.Obstacle;
import GameObjects.PowerUp;
import PowerUps.ItemPowerUp;
import Util.Point;

public class Road {
	//Impostazioni
	public static final double[] DIMENSION = {0.70, 0.40, 0.70}; //base sotto, base sopra, altezza
	
	private Point[] dimension, line1, line2;
	private Roadway[] corsie = new Roadway[3];
	
	/**
	 * Costruttore
	 */
	public Road(Point[] estremi, Point[] line1, Point[] line2) {
		this.dimension = estremi;
		this.line1 = line1;
		this.line2 = line2;
		
		Point[] upPoints = {dimension[0], this.line1[0]};
		Point[] downPoints = {dimension[3], this.line1[1]};
		corsie[0] = new Roadway(upPoints, downPoints);
		
		upPoints[0] = this.line1[0];
		upPoints[1] = this.line2[0];
		downPoints[0] = this.line1[1];
		downPoints[1] = this.line2[1];
		corsie[1] = new Roadway(upPoints, downPoints);
		
		upPoints[0] = this.line2[0];
		upPoints[1] = dimension[1];
		downPoints[0] = this.line2[1];
		downPoints[1] = dimension[2];
		corsie[2] = new Roadway(upPoints, downPoints);
	}
	
	/**
	 * Svuoto la strada
	 */
	public void reset() {
		for(Roadway c:this.corsie) {
			c.clear();
		}
	}

	/**
	 * Muovo gli oggetti per tutte le corsie
	 */
	public void moveItem(double v) {
		for(Roadway c:this.corsie) {
			c.nextPosition(v);
		}
	}
	
	/**
	 * Generazione di un ostacolo un strada
	 */
	public void genObstacles() {
		int nOstacoli = (int)(Math.random()*(corsie.length-1))+1;
		
		boolean[] corsie = {false, false, false};
		
		for(int i=0; i < nOstacoli; i++) {
			int position=0;
			do {
				position = (int)(Math.random()*3);
			} while(corsie[position]);
			corsie[position] = true;
			
			this.corsie[position].genObstacle();
		}
	}
	
	/**
	 * Generazione di un power up
	 */
	public void genPowerUp() {
		int corsia = (int)(Math.random()*corsie.length);
		
		this.corsie[corsia].genPowerUp();
	}
	
	/**
	 * Rimozione di un power up
	 */
	public void removePowerUp(PowerUp p) {
		for(Roadway r:this.corsie) {
			if(r.getPowerUps().contains(p)) {
				r.getPowerUps().remove(p);
				break;
			}
		}
	}
	
	/**
	 * Ridimensionamento della strada
	 */
	public void resize(Point[] estremi, Point[] line1, Point[] line2) {
		this.dimension = estremi;
		this.line1 = line1;
		this.line2 = line2;
		
		Point[] upPoints = {dimension[0], this.line1[0]};
		Point[] downPoints = {dimension[3], this.line1[1]};
		corsie[0].resize(upPoints, downPoints);
		
		upPoints[0] = this.line1[0];
		upPoints[1] = this.line2[0];
		downPoints[0] = this.line1[1];
		downPoints[1] = this.line2[1];
		corsie[1].resize(upPoints, downPoints);
		
		upPoints[0] = this.line2[0];
		upPoints[1] = dimension[1];
		downPoints[0] = this.line2[1];
		downPoints[1] = dimension[2];
		corsie[2].resize(upPoints, downPoints);
		
		for(Roadway c:this.corsie) {
			c.clear();
		}
	}
	
	// Metodi getter
	public List<Obstacle> getObstaclesList() {
		List<Obstacle> obstacles = new ArrayList<Obstacle>();
		
		for(Roadway c:this.corsie) {
			obstacles.addAll(c.getObstacles());
		}
		
		return obstacles;
	}
	
	public List<PowerUp> getPowerUpsList() {
		List<PowerUp> powerUps = new ArrayList<>();
		
		for(Roadway c:this.corsie) {
			powerUps.addAll(c.getPowerUps());
		}
		
		return powerUps;
	}
	
	public Point[] getDimensio() {
		return this.dimension;
	}
	
	/**
	 * Disegno della strada
	 */
	public void draw(Graphics g) {
		g.setColor(Color.DARK_GRAY);
		
		int[] xPoints = {dimension[0].getX(), dimension[1].getX(), dimension[2].getX(), dimension[3].getX()};
		int[] yPoints = {dimension[0].getY(), dimension[1].getY(), dimension[2].getY(), dimension[3].getY()};
		g.fillPolygon(xPoints, yPoints, dimension.length);
		
		g.setColor(Color.WHITE);
		g.drawLine(line1[0].getX(), line1[0].getY(), line1[1].getX(), line1[1].getY());
		g.drawLine(line2[0].getX(), line2[0].getY(), line2[1].getX(), line2[1].getY());
		
		for(Obstacle o:this.getObstaclesList()) {
			o.draw(g);
		}
		
		for(PowerUp u:this.getPowerUpsList()) {
			u.draw(g);
		}
	}
}