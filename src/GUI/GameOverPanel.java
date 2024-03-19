package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Buttons.MenuButton;
import Buttons.PlayAgainButton;
import Util.AbstractCustomPanel;
import Util.Gap;

/**
 * Classe che rappresenta il pannello di GameOver
 */
public class GameOverPanel extends AbstractCustomPanel {
	//Impostazioni generiche
	private static final double[] GAP_DIMENSION = {0.20, 0.20 , 0.27, 0.20}; //up, right, down, left
	private static final double BORDER_WIDTH = 0.01;
	private static final Color BG_COLOR = Color.LIGHT_GRAY;
	private static final Color BG_BORDER = Color.GRAY;	
	private static final String BGC_PATH = "resources/bgc.png";
	
	//Elementi grafici
	private JLabel scoreLabel;
	private JLabel highestScoreLabel;
	private ImageIcon bgc;
	private Gap[] gaps = new Gap[4];
	
	/**
	 * Costruttore del pannello
	 */
	public GameOverPanel(int width, int height, GlobalFrame frame) {
		super(width, height);
		
		bgc = new ImageIcon(BGC_PATH);
		scoreLabel = new JLabel("Score: ---");
		highestScoreLabel = new JLabel("Highest Score: ---");
		
		this.createPanel(frame);
		this.dimensionResized();
	}
	
	//Crezione dell'interfaccia
	private void createPanel(GlobalFrame frame) {
		this.setLayout(new BorderLayout());
		this.setOpaque(false);
		
		//Metto i gap laterali
		gaps[0] = new Gap(this.getWidth(), (int)(this.getHeight()*GAP_DIMENSION[0]));
		this.add(gaps[0], BorderLayout.NORTH);
		gaps[1] = new Gap((int)(this.getWidth()*GAP_DIMENSION[1]), this.getHeight());
		this.add(gaps[1], BorderLayout.EAST);
		gaps[2] = new Gap(this.getWidth(), (int)(this.getHeight()*GAP_DIMENSION[2]));
		this.add(gaps[2], BorderLayout.SOUTH);
		gaps[3] = new Gap((int)(this.getWidth()*GAP_DIMENSION[3]), this.getHeight());
		this.add(gaps[3], BorderLayout.WEST);
		
		//Pannello di interazione
		JPanel internalPanel = new JPanel();
		internalPanel.setOpaque(false);
		internalPanel.setLayout(new BorderLayout(0, 10));
		
		JPanel labelsZone = new JPanel();
		labelsZone.setBorder(BorderFactory.createLineBorder(BG_BORDER, (int)(this.getWidth()*BORDER_WIDTH)));
		labelsZone.setBackground(BG_COLOR);
		labelsZone.setLayout(new GridLayout(4, 1));
		
		JLabel gameOverDisplay = new JLabel("Game Over");
		gameOverDisplay.setOpaque(false);
		gameOverDisplay.setFont(GlobalFrame.big_font);
		gameOverDisplay.setHorizontalAlignment(JLabel.CENTER);
		
		labelsZone.add(gameOverDisplay);
		
		scoreLabel.setOpaque(false);
		scoreLabel.setFont(GlobalFrame.small_font);
		scoreLabel.setHorizontalAlignment(JLabel.CENTER);
		highestScoreLabel.setOpaque(false);
		highestScoreLabel.setFont(GlobalFrame.small_font);
		highestScoreLabel.setHorizontalAlignment(JLabel.CENTER);
		
		labelsZone.add(highestScoreLabel);
		labelsZone.add(scoreLabel);
		
		internalPanel.add(labelsZone);
		
		JPanel buttonsBar = new JPanel();
		buttonsBar.setOpaque(false);
		buttonsBar.setLayout(new GridLayout(1,2,10,10));
		buttonsBar.add(new PlayAgainButton(frame));
		buttonsBar.add(new MenuButton(frame));
		
		internalPanel.add(buttonsBar, BorderLayout.SOUTH);
		
		this.add(internalPanel, BorderLayout.CENTER);
	}
	
	@Override
	public void paint(Graphics g) {
		g.drawImage(bgc.getImage(), 0, 0, this.getWidth(), this.getHeight(), null);
		
		super.paint(g);
	}
	
	// Metodi setter e getter
	public void setScore(int score) {
		scoreLabel.setText("Score: "+ score);
	}
	public void setHighestScore(int score) {
		highestScoreLabel.setText("Highest Score: "+ score);
	}
	public Gap[] getGaps() {
		return this.gaps;
	}
	
	//Ridimensionamento
	private void dimensionResized() {
		GameOverPanel p = this;
		this.addComponentListener(new ComponentAdapter() {
			@Override
            public void componentResized(ComponentEvent e) {
            	Dimension size = p.getSize();
            	Gap[] gaps = p.getGaps();
            	
            	gaps[0].setSize(size.width, (int)(size.height*GAP_DIMENSION[0]));
        		gaps[1].setSize((int)(size.width*GAP_DIMENSION[1]), size.height);
        		gaps[2].setSize(size.width, (int)(size.height*GAP_DIMENSION[2]));
        		gaps[3].setSize((int)(size.width*GAP_DIMENSION[3]), size.height);
        		
        		p.invalidate();
        		p.validate();
            }
        });
	}
}
