package Buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import GUI.GlobalFrame;

public class ResetSavesButton extends AbstractCustomButton{
	public ResetSavesButton(GlobalFrame f) {
		super("Reset Saves");
		
		this.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				f.getScoreManager().resetSaves();
				f.changeToScoreBoardPanel();
			}
		});
	}
}
