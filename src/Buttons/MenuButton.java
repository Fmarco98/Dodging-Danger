package Buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import GUI.GlobalFrame;

/**
 * Pulsante per andare al menu
 */
public class MenuButton extends AbstractCustomButton{
	
	public MenuButton(GlobalFrame f) {
		super("Back to Menu");
		
		this.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				f.changeToMenu();
			}
		});
	}
}
