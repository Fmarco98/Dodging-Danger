package PowerUps;

import javax.swing.ImageIcon;

import GamePanel_components.GraphicGamePanel;

/**
 * Classe per rappresnetare il power up tank
 */
public class TankPowerUp extends ItemPowerUp{
	//Impostazioni
	private static final String ICON_PATH = "resources/powerUps/tank.png";
	private static final String TANK_IMG = "resources/tank.png";
	
	public TankPowerUp() {
		super(ItemPowerUp.TANK_TYPE, 20000, new ImageIcon(ICON_PATH));
	}

	@Override
	public Object[] startActions(GraphicGamePanel g) {
		g.setCarDies(false);
		ImageIcon oldImg = g.getCar().getCarImg();
		g.getCar().setCarImg(new ImageIcon(TANK_IMG));
		
		Object[] args = {oldImg};
		return args;
	}

	@Override
	public void endActions(GraphicGamePanel g, Object[] args) {
		g.setCarDies(true);
		
		g.getCar().setCarImg((ImageIcon)args[0]);
	}
}
