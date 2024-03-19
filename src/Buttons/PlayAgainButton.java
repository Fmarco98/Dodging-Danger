package Buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import GUI.GlobalFrame;

/**
 * Bottone per il rigioca
 */
public class PlayAgainButton extends AbstractCustomButton{
	
	public PlayAgainButton(GlobalFrame f) {
		super("Play again");
		
		this.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				f.changeToGame();
			}
		});
	}
}
