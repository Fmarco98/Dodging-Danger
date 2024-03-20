package GUI;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JFrame;

import ScoreBoardPanel_components.ScoreBoardViewerPanel;
import Util.ScoreManager;

/**
 * Frame globale
 */
public class GlobalFrame extends JFrame{
	//Impostazioni
	public static final String GAME_TITLE = "Dodging Danger";
	public static final int REFRESH_RATE = 1000/60;//50
	public static final Font big_font = new Font(Font.DIALOG, Font.BOLD, 60);
	public static final Font small_font = new Font(Font.DIALOG, Font.BOLD, 20);
	private static int x=100,y=50,width=888,height=600;
	
	//Tipi di pannelli contenuti
	public static final int MENU_PANEL = 1;
	public static final int GAME_PANEL = 2;
	public static final int GAME_OVER_PANEL = 3;
	public static final int SCORE_BOARD_PANEL = 4;
	
	private ScoreManager scoreManager;
	
	//Pannelli da switchare durante il programma
	private MenuPanel menu;
	private GamePanel game;
	private GameOverPanel gameOver;
	private ScoreBoardPanel scoreBoard;
	
	//Attributo
	private int panelType; // indica il tipo del pannello visto nel frame
	
	/**
	 * Costruttore
	 */
	public GlobalFrame() {
		super();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(x, y, width, height);
		this.setMinimumSize(new Dimension(width, height));
		this.setLocationRelativeTo(null);
		
		menu = new MenuPanel(width, height, this);
		game = new GamePanel(width, height);
		gameOver = new GameOverPanel(width, height, this);
		scoreBoard = new ScoreBoardPanel(width, height, this);	
		
		this.changeToMenu();		
		
		this.dimensionResized();
		this.setVisible(true);
		
		scoreManager = new ScoreManager();
	}
	
	/**
	 * Cambiare il pannello al menu pricipale
	 */
	public void changeToMenu() {
		this.panelType = GlobalFrame.MENU_PANEL;
		this.getContentPane().removeAll();
		this.getContentPane().add(menu);
		
		this.invalidate();
		this.validate();
	}
	
	/**
	 * Cambiare il pannello al Gioco
	 */
	public void changeToGame() {
		this.panelType = GlobalFrame.GAME_PANEL;
		this.getContentPane().removeAll();
		this.getContentPane().add(game);
		
		this.invalidate();
		this.validate();
	}
	
	/**
	 * Cambiare il pannello al GameOver
	 */
	public void changeToGameOver(int score) {
		scoreManager.addScore(score);
		gameOver.setScore(score);
		gameOver.setHighestScore(scoreManager.getHighestScore());
		
		this.panelType = GlobalFrame.GAME_OVER_PANEL;
		this.getContentPane().removeAll();
		
		this.getContentPane().add(gameOver);
		
		this.invalidate();
		this.validate();
	}
	
	public void changeToScoreBoardPanel() {
		this.panelType = GlobalFrame.SCORE_BOARD_PANEL;
		this.getContentPane().removeAll();
		
		scoreBoard.setTopScores(scoreManager.getTopScores(ScoreBoardViewerPanel.SCORE_BAR_NUMBER));
		this.getContentPane().add(scoreBoard);
		
		this.invalidate();
		this.validate();
		
	}
	
	//metodi getter
	public boolean isPanelType(int panel) {
		return this.panelType == panel;
	}	
	public MenuPanel getMenuPanel() {
		return this.menu;
	}
	public GamePanel getGamePanel() {
		return this.game;
	}
	public GameOverPanel getGameOverPanel() {
		return this.gameOver;
	}
	public ScoreManager getScoreManager() {
		return this.scoreManager;
	}
	
	//Ridimensionamento
	private void dimensionResized() {
		GlobalFrame f = this;
		this.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				f.getGamePanel().setSize(f.getContentPane().getSize());
				f.getMenuPanel().setSize(f.getContentPane().getSize());
				f.getGameOverPanel().setSize(f.getContentPane().getSize());
			}
		});
	}
}
