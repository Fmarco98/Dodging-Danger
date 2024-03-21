package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Buttons.ExitButton;
import Buttons.PlayButton;
import Buttons.ScoreBoardButton;
import Util.AbstractCustomPanel;
import Util.Gap;

/**
 * Panello con il menu primcipare del gioco
 */
public class MenuPanel extends AbstractCustomPanel{
	//Impostazioni
	private static final double BORDER_WIDTH = 0.01;
	private static final Color BG_BORDER = Color.GRAY;
	private static final Color BG_COLOR = Color.LIGHT_GRAY;
	private static final String BG_PATH = GlobalFrame.BG_PATH;
	
	//Elementi grafici
	private ImageIcon bgc;
	private PlayButton play;
	private ScoreBoardButton scoreButton;
	private ExitButton exit;
	
	/**
	 * Costruttore
	 */
	public MenuPanel(int width, int height, GlobalFrame f) {
		super(width, height);
		
		play = new PlayButton(f);
		scoreButton = new ScoreBoardButton(f);
		exit = new ExitButton(f);
		bgc = new ImageIcon(BG_PATH);
		
		this.createPanel();
	}
	
	//Creazione dell'interfaccia
	private void createPanel() {
		this.setLayout(new BorderLayout(0, 10));
		this.setOpaque(false);
		
		JLabel gameTitle = new JLabel(GlobalFrame.GAME_TITLE);
		gameTitle.setFont(GlobalFrame.BIG_FONT);
		gameTitle.setHorizontalAlignment(JLabel.CENTER);
		gameTitle.setOpaque(true);
		gameTitle.setBackground(BG_COLOR);
		gameTitle.setBorder(BorderFactory.createLineBorder(BG_BORDER, (int)(this.getWidth()*BORDER_WIDTH)));
		this.add(gameTitle, BorderLayout.NORTH);
		
		JPanel internalPanel = new JPanel();
		internalPanel.setLayout(new GridLayout(1,3));
		internalPanel.setOpaque(false);
		
		internalPanel.add(new Gap());
		
		JPanel menuPanel = new JPanel();
		menuPanel.setLayout(new GridLayout(4,1, 10, 10));
		menuPanel.setOpaque(false);
		
		menuPanel.add(this.play);
		menuPanel.add(this.scoreButton);
		menuPanel.add(this.exit);
		
		internalPanel.add(menuPanel);
		internalPanel.add(new Gap());
		
		this.add(internalPanel, BorderLayout.CENTER);
	}
	
	@Override
	public void paint(Graphics g) {
		Dimension d = getSize();
		g.drawImage(bgc.getImage(), 0, 0, d.width, d.height, null);
		
		super.paint(g);
	}
}