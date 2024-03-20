package PowerUps;

import javax.swing.ImageIcon;

import GamePanel_components.GraphicGamePanel;

/**
 * Classe per rappresentare il power up sterzo veloce
 */
public class SpeedyStearingPowerUp extends ItemPowerUp{
	//Impostazioni
	private static final String ICON_PATH = "resources/powerUps/speedyStearing.png";
	
	public SpeedyStearingPowerUp() {
		super(ItemPowerUp.SPEEDY_STEARING_TYPE, 15000, new ImageIcon(ICON_PATH));
	}
	
	@Override
	public Object[] startActions(GraphicGamePanel g) {
		int vStearing = g.getCar().getvStearing();
		
		g.getCar().setvStearing(vStearing*2);
		
		Object[] args = {vStearing};
		return args;
	}

	@Override
	public void endActions(GraphicGamePanel g, Object[] args) {
		g.getCar().setvStearing((int)args[0]);
	}
}
