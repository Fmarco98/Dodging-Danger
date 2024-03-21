package Buttons;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.border.Border;

import GUI.GlobalFrame;

public class AbstractCustomButton extends JButton{
	private static final Color BG_COLOR = Color.LIGHT_GRAY;
	private static final Font FONT = GlobalFrame.SMALL_FONT;	
	private static final Border DEFAULT_STYLE = BorderFactory.createRaisedSoftBevelBorder();
	private static final Border MOUSE_ON_STYLE = BorderFactory.createLoweredBevelBorder();
	
	
	public AbstractCustomButton(String title) {
		super(title);
		
		this.setBorder(DEFAULT_STYLE);
		this.setBackground(BG_COLOR);
		this.setFont(FONT);
		
		AbstractCustomButton b = this;
		this.addMouseListener(new MouseListener() {
			@Override
			public void mouseEntered(MouseEvent e) {
				b.setBorder(MOUSE_ON_STYLE);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				b.setBorder(DEFAULT_STYLE);
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				b.setBorder(DEFAULT_STYLE);
			}
			
			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mouseClicked(MouseEvent e) {}
		});
	}
}
