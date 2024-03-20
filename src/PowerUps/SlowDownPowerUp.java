package PowerUps;

import javax.swing.ImageIcon;

import GamePanel_components.GraphicGamePanel;

/**
 * Classe per rapperesentare il power up rallenta
 */
public class SlowDownPowerUp extends ItemPowerUp{
	//Impostazioni
	private static final String ICON_PATH = "resources/powerUps/slowDown.png";
	
	//Metodo costruttore
	public SlowDownPowerUp() {
		super(ItemPowerUp.SLOW_GAME_DOWN_TYPE, 20000, new ImageIcon(ICON_PATH));
	}

	@Override
	public Object[] startActions(GraphicGamePanel g) {
		double currentV = g.getCurrentV();
		double acceleration = g.getAcceleration();
		
		g.setCurrentV(GraphicGamePanel.V_MIN);
		g.setAcceleration(0);
		
		Object[] args = {currentV, acceleration};
		return args;
	}

	@Override
	public void endActions(GraphicGamePanel g, Object[] args) {
		g.setCurrentV((double)args[0]);
		g.setAcceleration((double)args[1]);
	}
	
}
