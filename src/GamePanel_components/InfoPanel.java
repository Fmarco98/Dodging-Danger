package GamePanel_components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;

import GUI.GlobalFrame;
import Util.AbstractCustomPanel;

/**
 * Classe per rappresentare il pannello delle info del gioco
 */
public class InfoPanel extends AbstractCustomPanel{
	
	//Elementi grific
	private JLabel scoreLabel;
	private int score;
	private JPanel scorePanel;
	private SpeedmeterPanel tachimetroPanel;
	
	/**
	 * Costruttore
	 */
	public InfoPanel(int width, int height, GraphicGamePanel game) {
		super(width, height);
		
		score = 0;
		
		this.setLayout(new GridLayout(2,1));
		
		scorePanel = new JPanel();
		scorePanel.setLayout(new FlowLayout());
		scorePanel.setOpaque(false);
		
		scoreLabel = new JLabel("Score: "+score);
		scoreLabel.setHorizontalAlignment(JLabel.CENTER);
		scoreLabel.setFont(GlobalFrame.SMALL_FONT);
		scorePanel.add(scoreLabel);
		
		this.add(scorePanel);
				
		tachimetroPanel = new SpeedmeterPanel(width, height/2);
		this.add(tachimetroPanel);
		
		this.setBackground(Color.LIGHT_GRAY);
		this.dimensionResized();
	}
	
	/**
	 * Incrementa lo score
	 */
	public void increseScore() {
		score++;
		scoreLabel.setText("Score: "+score);
	}
	
	/**
	 * Azzera lo score
	 */
	public void resetScore() {
		score = 0;
		scoreLabel.setText("Score: "+score);		
	}
	
	// metodi getter
	public SpeedmeterPanel getSpeedmeter() {
		return this.tachimetroPanel;
	}
	public int getScore() {
		return score;
	}
	
	//Ridimensione
	private void dimensionResized() {
		InfoPanel p = this;
		this.addComponentListener(new ComponentAdapter() {
            @Override
			public void componentResized(ComponentEvent e) {
            	Dimension dim = p.getSize();
            	p.getSpeedmeter().setSize((int)dim.getWidth(), (int)dim.getHeight()/2);
            }
        });
	}
	
}
