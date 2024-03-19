package Buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import GUI.GlobalFrame;

/**
 * Classe per il PUlsante GIOCA
 */
public class PlayButton extends AbstractCustomButton{
	
	public PlayButton(GlobalFrame f) {
		super("Play");
		
		this.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				f.changeToGame();
			}
		});
	}
}