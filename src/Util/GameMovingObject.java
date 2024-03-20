package Util;

/**
 * Interfaccia posseduda da qualsiasi oggetto che ha un movimento indipendente nel gioco
 */
public interface GameMovingObject {
	
	/**
	 * Movimento a sinistra
	 */
	public void moveLeft(double px);
	
	/**
	 * MOvimento a destra
	 */
	public void moveRight(double px);
	
	/**
	 * Moviemento giù
	 */
	public void moveDown(double px);
	
	/**
	 * zoom
	 */
	public void zoom(double px);
}
