package GamePanel_components;

import java.util.ArrayList;
import java.util.List;

import GameObjects.Obstacle;
import GameObjects.PowerUp;
import PowerUps.ItemPowerUp;
import PowerUps.ResetPowerUp;
import PowerUps.SlowDownPowerUp;
import PowerUps.SpeedyStearingPowerUp;
import PowerUps.TankPowerUp;
import Util.Point;

/**
 * Classe che rappresenta una corsia della strada
 */
public class Roadway {
	//Tipi di corsia
	public static final int LEFT_ROADWAY = 1;
	public static final int CENTER_ROADWAY = 2;
	public static final int RIGHT_ROADWAY = 3;
	
	//Attributi 
	private int upWidth, downWidth, height, type;
	private Point[] dimension = new Point[4];
	private List<Obstacle> obstacles = new ArrayList<>();
	private List<PowerUp> powerUps = new ArrayList<>();
	private double pxLateralMoving;
	
	/**
	 * Costruttore
	 */
	public Roadway(Point[] upPoints, Point[] downPoints) {
		this.resize(upPoints, downPoints);
	}
	
	/**
	 * Svuotare la scorsia
	 */
	public void clear() {
		this.obstacles.clear();
		this.powerUps.clear();
	}
	
	/**
	 * Genera un ostacolo
	 */
	public void genObstacle() {
		int ostacoloWidth = (int)(upWidth*Obstacle.OSTACOLO_DIMENSION[0]);
		int ostacoloHeight = (int)(height*Obstacle.OSTACOLO_DIMENSION[1]);
		int ostacoloGap = (upWidth-ostacoloWidth)/2;
		
		Obstacle o = new Obstacle(this.dimension[0].getX()+ostacoloGap, this.dimension[0].getY()-ostacoloHeight, ostacoloWidth, ostacoloHeight);
		this.obstacles.add(o);
	}
	
	/**
	 * Gener un power up
	 */
	public void genPowerUp() {
		int type = (int)(Math.random()*4)+1;
		
		int powerupWidth = (int)(upWidth*PowerUp.POWERUP_DIMENSION[0]);
		int powerupHeight = (int)(height*PowerUp.POWERUP_DIMENSION[1]);
		int powerupGap = (upWidth-powerupWidth)/2;
		int pwX = this.dimension[0].getX()+powerupGap;
		int pwY = this.dimension[0].getY()-powerupHeight;
		
		PowerUp pw = null;
		if(type == ItemPowerUp.TANK_TYPE) {
			pw = new PowerUp(pwX, pwY, powerupWidth, powerupHeight, new TankPowerUp());
		} else if(type == ItemPowerUp.RESET_TYPE) {
			pw = new PowerUp(pwX, pwY, powerupWidth, powerupHeight, new ResetPowerUp());
		} else if(type == ItemPowerUp.SPEEDY_STEARING_TYPE) {
			pw = new PowerUp(pwX, pwY, powerupWidth, powerupHeight, new SpeedyStearingPowerUp());
		} else if(type == ItemPowerUp.SLOW_GAME_DOWN_TYPE) {
			pw = new PowerUp(pwX, pwY, powerupWidth, powerupHeight, new SlowDownPowerUp());
		}
		
		this.powerUps.add(pw);
	}
	
	/**
	 * Movimento di tutti gli oggetti nella corsia
	 */
	public void nextPosition(double v) {
		int startWidth = (int)(upWidth-2*(upWidth*Obstacle.OSTACOLO_DIMENSION[1]));
		int finalWidth = (int)(downWidth-2*(downWidth*Obstacle.OSTACOLO_DIMENSION[1]));
		int zoomWidth = finalWidth-startWidth;
		double zoomPerPx = (double)zoomWidth/height;
		
		//muovo ostacoli
		for(int i=0; i < this.obstacles.size(); i++) {
			Obstacle o = obstacles.get(i);
			
			if(type == LEFT_ROADWAY) {
				o.moveLeft(pxLateralMoving*v);
			} else if(type == RIGHT_ROADWAY) {
				o.moveRight(pxLateralMoving*v);				
			}
			o.moveDown(v);
			
			o.zoom(v*zoomPerPx);
			
			if(o.getY() > dimension[2].getY()) {
				this.obstacles.remove(o);
				i--;
			}
		}
		
		//muovo powerup
		for(int i=0; i < this.powerUps.size(); i++) {
			PowerUp u = this.powerUps.get(i);
			
			if(type == LEFT_ROADWAY) {
				u.moveLeft(pxLateralMoving*v);
			} else if(type == RIGHT_ROADWAY) {
				u.moveRight(pxLateralMoving*v);				
			}
			u.moveDown(v);
			
			u.zoom(v*zoomPerPx);
			
			if(u.getY() > dimension[2].getY()) {
				this.powerUps.remove(u);
				i--;
			}
		}
	}
	
	/**
	 * Ridimensione della corsia
	 */
	public void resize(Point[] upPoints, Point[] downPoints) {
		this.dimension[0] = upPoints[0];
		this.dimension[1] = upPoints[1];
		this.dimension[2] = downPoints[0];
		this.dimension[3] = downPoints[1];
		
		height = downPoints[0].getY()-upPoints[0].getY();
		
		upWidth = upPoints[1].getX()-upPoints[0].getX();
		downWidth = downPoints[1].getX()-downPoints[0].getX();
		
		int sxOffset = Math.abs(downPoints[0].getX()-upPoints[0].getX());
		int dxOffset = Math.abs(downPoints[1].getX()-upPoints[1].getX());
		
		if(this.intorno(dxOffset, sxOffset, 5)) {
			type = CENTER_ROADWAY;
		} else if(sxOffset > dxOffset) {
			type = LEFT_ROADWAY;			
		} else {
			type = RIGHT_ROADWAY;						
		}
		
		this.pxLateralMoving = (double)(downWidth-upWidth)/height;
	}
	
	//metodi getter
	public List<Obstacle> getObstacles() {
		return this.obstacles;
	}
	public List<PowerUp> getPowerUps() {
		return this.powerUps;
	}
	public int getType() {
		return type;
	}
	
	//Intorno della dimensione
	private boolean intorno(int a, int b, int gap) {
		for(int i=0; i <= gap; i++) {
			if(a == b+i) return true;
			if(a == b-i) return true;
		}
		for(int i=0; i <= gap; i++) {
			if(b == a+i) return true;
			if(b == a-i) return true;
		}
		return false;
	}	
}