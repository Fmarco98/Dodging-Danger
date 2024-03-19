package Buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import GUI.GlobalFrame;

/**
 * Classe per il pulsante ESCI
 */
public class ExitButton extends AbstractCustomButton{
	
	public ExitButton(GlobalFrame f) {
		super("Exit");
		
		this.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				f.dispose();
			}
		});
	}
}