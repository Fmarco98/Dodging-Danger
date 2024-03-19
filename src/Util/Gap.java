package Util;

import java.awt.Dimension;

import javax.swing.JPanel;

/**
 * Panell grafico per lo spazio vuoto
 */
public class Gap extends AbstractCustomPanel{
	
	public Gap() {
		this(0,0);
	}
	
	public Gap(int width, int height) {
		super(width, height);
		
		this.setOpaque(false);
		this.setSize(width, height);
	}
}
