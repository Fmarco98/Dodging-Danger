package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import GamePanel_components.GraphicGamePanel;
import GamePanel_components.InfoPanel;
import GamePanel_components.PowerUpPanel;
import MainClass.Main;
import Util.AbstractCustomPanel;
import Util.SoundPlayer;

/**
 * Pannello con il gioco vero e proprio
 */
public class GamePanel extends AbstractCustomPanel{
	//dimesioni percentuali
	private static final double[] GAME_DIMENSION = {0.79, 0.65};
	private static final double[] INFO_DIMENSION = {0.21, 0.65};
	private static final double[] POWERUPS_DIMENSION = {1.0, 0.35};
	
	//Componenti 
	private GraphicGamePanel game;	//Pannello del gioco
	private InfoPanel info;			//Info del gioco
	private PowerUpPanel powerUp;	//Contenitore di power up
	
	private SoundPlayer gameSoundtrak = new SoundPlayer("resources/sounds/game.wav", SoundPlayer.DO_LOOP, Main.soundtrack);
	private SoundPlayer powerUpEnableSound = new SoundPlayer("resources/sounds/powerup_ON.wav", SoundPlayer.NOT_LOOP, Main.soundeffects);
	
	/**
	 * Costruttore
	 */
	public GamePanel(int width, int height) {
		super(width, height);
		
		//Creazione oggetti nelle loro posizioni
		Dimension pDim = this.getSize();
    	int gamePWidth = (int)(pDim.getWidth()*GAME_DIMENSION[0]);
    	int gamePHeight = (int)(pDim.getHeight()*GAME_DIMENSION[1]);
    	int infoPWidth = (int)(pDim.getWidth()*INFO_DIMENSION[0]);
    	int infoPHeight = (int)(pDim.getHeight()*INFO_DIMENSION[1]);
    	int pwWidth = (int)(pDim.getWidth()*POWERUPS_DIMENSION[0]);
    	int pwPHeight = (int)(pDim.getHeight()*POWERUPS_DIMENSION[1]);
		
    	game = new GraphicGamePanel(gamePWidth, gamePHeight);
    	info = new InfoPanel(infoPWidth, infoPHeight, game);
    	powerUp = new PowerUpPanel(pwWidth, pwPHeight, this);
    	
		this.setLayout(new BorderLayout());
		this.add(game, BorderLayout.CENTER);
		this.add(info, BorderLayout.EAST);
		this.add(powerUp, BorderLayout.SOUTH);
		
		this.dimensionResized();
	}
	
	/**
	 * Reset del gioco intero
	 */
	public void reset() {
		this.powerUp.reset();
		this.info.resetScore();
		this.game.reset();
	}
	
	//Metodi getter
	public GraphicGamePanel getGamePanel() {
		return this.game;
	}
	public InfoPanel getInfoPanel() {
		return info;
	}
	public PowerUpPanel getPowerUpPanel() {
		return powerUp;
	}
	public SoundPlayer getSoundtrackPlayer() {
		return this.gameSoundtrak;
	}
	public SoundPlayer getEffectPlayer() {
		return this.powerUpEnableSound;
	}

	//Ridimensionamento
	private void dimensionResized() {	
		GamePanel p = this;
		this.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				Dimension pDim = p.getSize();
				int gamePWidth = (int)(pDim.getWidth()*GAME_DIMENSION[0]);
				int gamePHeight = (int)(pDim.getHeight()*GAME_DIMENSION[1]);
				int infoPWidth = (int)(pDim.getWidth()*INFO_DIMENSION[0]);
				int infoPHeight = (int)(pDim.getHeight()*INFO_DIMENSION[1]);
				int pwWidth = (int)(pDim.getWidth()*POWERUPS_DIMENSION[0]);
				int pwPHeight = (int)(pDim.getHeight()*POWERUPS_DIMENSION[1]);	
				
				//resize
				p.getGamePanel().setSize(gamePWidth, gamePHeight);
				p.getInfoPanel().setSize(infoPWidth, infoPHeight);
				p.getPowerUpPanel().setSize(pwWidth, pwPHeight);
				
				p.invalidate();
				p.validate();
			}
		});
	}
}