package Buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import GUI.GlobalFrame;



/**
 * Classe per il pulsante per visualizzare la ScoreBoard
 */
public class ScoreBoardButton extends AbstractCustomButton{
	public ScoreBoardButton(GlobalFrame f) {
		super("Score Board");
		
		this.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				f.changeToScoreBoardPanel();
			}
		});
	}
}