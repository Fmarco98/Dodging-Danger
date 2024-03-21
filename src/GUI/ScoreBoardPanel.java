package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Buttons.MenuButton;
import Buttons.ResetSavesButton;
import ScoreBoardPanel_components.ScoreBoardViewerPanel;
import Util.AbstractCustomPanel;
import Util.Gap;

public class ScoreBoardPanel extends AbstractCustomPanel{
	//Impostazioni generiche
	private static final double[] GAP_DIMENSION = {0.10, 0.13 , 0.15, 0.13}; //up, right, down, left
	private static final double BORDER_WIDTH = 0.01;
	private static final Color BG_COLOR = Color.LIGHT_GRAY;
	private static final Color BG_BORDER = Color.GRAY;	
	private static final String BG_PATH = GlobalFrame.BG_PATH;

	//Elementi grafici
	private ImageIcon bgc;
	private Gap[] gaps = new Gap[4];
	private JLabel highestScoreLabel;
	private ScoreBoardViewerPanel score;

	/**
	 * Costruttore del pannello
	 */
	public ScoreBoardPanel(int width, int height, GlobalFrame frame) {
		super(width, height);

		bgc = new ImageIcon(BG_PATH);
		score = new ScoreBoardViewerPanel();
		
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
		
		JLabel titleLabel = new JLabel("Score Board");
		titleLabel.setBorder(BorderFactory.createLineBorder(BG_BORDER, (int)(this.getWidth()*BORDER_WIDTH)));
		titleLabel.setHorizontalAlignment(JLabel.CENTER);
		titleLabel.setFont(GlobalFrame.BIG_FONT);
		titleLabel.setBackground(BG_COLOR);
		titleLabel.setOpaque(true);
		internalPanel.add(titleLabel, BorderLayout.NORTH);
		
		JPanel scoreBoardPanel = new JPanel();
		scoreBoardPanel.setBorder(BorderFactory.createLineBorder(BG_BORDER, (int)(this.getWidth()*BORDER_WIDTH)));
		scoreBoardPanel.setBackground(BG_COLOR);
		scoreBoardPanel.setLayout(new BorderLayout(0, 5));
		
		scoreBoardPanel.add(score, BorderLayout.CENTER);
		
		highestScoreLabel = new JLabel("Highest score: -----");
		highestScoreLabel.setHorizontalAlignment(JLabel.CENTER);
		highestScoreLabel.setFont(GlobalFrame.SMALL_FONT);
		scoreBoardPanel.add(highestScoreLabel, BorderLayout.SOUTH);
		
		internalPanel.add(scoreBoardPanel, BorderLayout.CENTER);
		
		JPanel buttonBar = new JPanel();
		buttonBar.setLayout(new GridLayout(1,2,10,10));
		buttonBar.setOpaque(false);
		buttonBar.add(new ResetSavesButton(frame));
		buttonBar.add(new MenuButton(frame));
		
		internalPanel.add(buttonBar, BorderLayout.SOUTH);
		
		this.add(internalPanel, BorderLayout.CENTER);
	}
	
	public void setTopScores(List<Integer> topScores) {
		highestScoreLabel.setText("Highest score: "+ (topScores.size() > 0 ? topScores.get(0) : "-----"));
		this.score.setTopScores(topScores);
	}
	
	@Override
	public void paint(Graphics g) {
		g.drawImage(bgc.getImage(), 0, 0, this.getWidth(), this.getHeight(), null);

		super.paint(g);
	}
	
	public Gap[] getGaps() {
		return this.gaps;
	}
	
	//Ridimensionamento
	private void dimensionResized() {
		ScoreBoardPanel p = this;
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