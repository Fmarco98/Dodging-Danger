package PowerUps;

import javax.swing.ImageIcon;

import GamePanel_components.GraphicGamePanel;

/**
 * Classe per rappresentare il power up reset
 */
public class ResetPowerUp extends ItemPowerUp{
	private static final String ICON_PATH = "resources/images/powerUps/reset.png";
	
	public ResetPowerUp() {
		super(ItemPowerUp.RESET_TYPE, 0, new ImageIcon(ICON_PATH));
	}

	@Override
	public Object[] startActions(GraphicGamePanel g) {
		g.reset();
		
		return null;
	}

	@Override
	public void endActions(GraphicGamePanel g, Object[] args) {}
}
