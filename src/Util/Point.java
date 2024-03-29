package Util;

/**
 * Classe per identificare un singolo punto
 */
public class Point {
	private int x,y;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	
	@Override
	public String toString() {
		return "Point: X:"+x+", Y: "+y;
	}
}