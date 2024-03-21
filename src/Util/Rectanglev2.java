package Util;

import java.awt.Rectangle;

public class Rectanglev2 extends Rectangle{
	
	private double width, height;
	
	public Rectanglev2(int x, int y, int width, int height) {
		super(x, y, width, height);
		
		this.width = width;
		this.height = height;
	}
	
	public void setSize(double width, double height) {
		super.setSize((int)width, (int)height);
		
		this.width = width;
		this.height = height;
	}
	
	public double getWidth() {
		return this.width;
	}
	public double getHeight() {
		return this.height;
	}
}
