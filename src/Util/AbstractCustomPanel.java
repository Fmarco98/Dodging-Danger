package Util;

import java.awt.Dimension;

import javax.swing.JPanel;

/**
 * Classe per rapprensetare un qualsiasi pannello 
 */
public abstract class AbstractCustomPanel extends JPanel{
	
	/**
	 * Costruttore vuota
	 */
	public AbstractCustomPanel() {
		this(0,0);
	}
	
	/**
	 * Costruttore da dimensione
	 */
	public AbstractCustomPanel(Dimension dim) {
		this((int)dim.getWidth(), (int)dim.getHeight());
	}
	
	/**
	 * Costruttore da misure
	 */
	public AbstractCustomPanel(int width, int height) {
		super();
		
		this.setSize(width, height);		
	}
	
	@Override
	public void setSize(Dimension dim) {
		this.setSize((int)dim.getWidth(), (int)dim.getHeight());
	}
	
	@Override
	public void setSize(int width, int height) {
		//Parametri delle dimensioni per il layout manager
		this.setMinimumSize(new Dimension(width, height));
		this.setPreferredSize(new Dimension(width, height));
		this.setMaximumSize(new Dimension(width, height));
		
		super.setSize(width, height);
	}
}
