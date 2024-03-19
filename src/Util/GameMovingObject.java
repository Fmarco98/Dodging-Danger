package Util;

/**
 * Interfaccia posseduda da qualsiasi oggetto che ha un movimento indipendente nel gioco
 */
public interface GameMovingObject {
	
	public void moveLeft(double px);
	public void moveRight(double px);
	public void moveDown(double px);
	public void zoom(double px);
}
