package ScoreBoardPanel_components;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import Util.AbstractCustomPanel;

/**
 * Pannello grafico della score board
 */
public class ScoreBoardViewerPanel extends AbstractCustomPanel{
	public static final int SCORE_BAR_NUMBER = 9;
	
	//Impostazini
	private static final Color SCORE_BAR_COLOR = new Color(26, 191, 8);	
	private static final double BLACK_BAR_HEIGHT = 0.02;
	private static final double BLACK_BAR_LATERAL_GAP = 0.03;
	private static final double UPPER_GAP = 0.03;
	private static final double LATERAL_GAP = 0.05;
	private static final double BAR_PADDING = 0.015;
	
	//Atrtibuti
	private ArrayList<Integer> scores = new ArrayList<>();
	
	/**
	 * Costruttore
	 */
	public ScoreBoardViewerPanel() {
		super(0,0);
	}
	
	//metodo setter
	public void setTopScores(List<Integer> topScores) {
		this.scores = (ArrayList<Integer>) topScores;
	}
	
	@Override
	public void paint(Graphics g) {
		int globalLateralGap = (int)(this.getWidth()*BLACK_BAR_LATERAL_GAP);
		int lateralGap = (int)(this.getWidth()*LATERAL_GAP);
		int scoreBarGroupWidth = this.getWidth()-(lateralGap+globalLateralGap)*2;
		int barPadding = (int)(scoreBarGroupWidth*BAR_PADDING);
		int barWidth = scoreBarGroupWidth/SCORE_BAR_NUMBER;
		
		int maxHeight = this.getHeight()-(int)(this.getHeight()*UPPER_GAP)-(int)(this.getHeight()*BLACK_BAR_HEIGHT);
		double heightPerPx = 0;
		try {			
			heightPerPx = (double)maxHeight/scores.get(0);
		} catch(Exception e) {}
		
		//Disegno
		g.setColor(SCORE_BAR_COLOR);
		for(int i=0; i < SCORE_BAR_NUMBER; i++) {
			try {				
				int height = (int)(heightPerPx*scores.get(i));
				g.fillRect(globalLateralGap+lateralGap+barPadding+barWidth*i, (int)(this.getHeight()*UPPER_GAP)+maxHeight-height, barWidth-barPadding, height);
			} catch(Exception e) {}
		}
		
		g.setColor(Color.black);
		g.fillRect(globalLateralGap, this.getHeight()-(int)(this.getHeight()*BLACK_BAR_HEIGHT), this.getWidth()-globalLateralGap*2, (int)(this.getHeight()*BLACK_BAR_HEIGHT));
	}
}
