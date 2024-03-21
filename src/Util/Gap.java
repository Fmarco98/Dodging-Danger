package Util;

/**
 * Gap per sapazio vuoto
 */
public class Gap extends AbstractCustomPanel{
	
	/**
	 * Costruttore base
	 */
	public Gap() {
		this(0,0);
	}
	
	/**
	 * Costruttore con dimensioni
	 */
	public Gap(int width, int height) {
		super(width, height);
		
		this.setOpaque(false);
		this.setSize(width, height);
	}
}
