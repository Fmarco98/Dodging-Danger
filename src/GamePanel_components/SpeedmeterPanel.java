package GamePanel_components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import GUI.GlobalFrame;
import Util.AbstractCustomPanel;
import Util.Point;

public class SpeedmeterPanel extends AbstractCustomPanel{
	//Impostazioni
	private static final int MAX_ANGLE = 80;
	private static final double[] PADDING = {0.15, 0.15};
	private static final String BG_PATH = "resources/images/speedmeter.png";
	
	//Elemento grafico
	private JLabel titleLabel;
	
	//Attributi
	private double vMove, vMax;
	private ImageIcon bg;
	
	/**
	 * Costruttore
	 */
	public SpeedmeterPanel(int width, int height) {
		super(width, height);
		this.setLayout(null);
		
		titleLabel = new JLabel("Speed");
		titleLabel.setFont(GlobalFrame.small_font);
		titleLabel.setHorizontalAlignment(JLabel.CENTER);
		
		this.bg = new ImageIcon(BG_PATH);
		this.vMax = GraphicGamePanel.V_MIN;
		this.vMove = GraphicGamePanel.V_MIN;
	}
	
	/**
	 * Imposta le velocità di riferimento del tachimetro
	 */
	public void setVs(double v, double max) {
		this.vMove = v;
		this.vMax = max;
	}
	
	@Override
	public void paint(Graphics g) {
		Dimension dim = this.getSize();
		int padx = (int)(dim.getWidth()*PADDING[0]);
		int pady = (int)(dim.getHeight()*PADDING[1]);
		int w = (int)(dim.getWidth()-2*padx);
		int h = (int)(dim.getHeight()-2*pady);
		
		double speedMultiplier = this.vMove/this.vMax;
		double speedAngle = speedMultiplier*MAX_ANGLE;
		double sinA = Math.sin(Math.toRadians(speedAngle));
		double cosA = Math.cos(Math.toRadians(speedAngle));		
		Point p0 = new Point(padx+w, pady+h);
		Point p1 = new Point(p0.getX()-(int)(cosA*w), p0.getY()-(int)(sinA*h));
		
		titleLabel.setBounds(padx,0,this.getWidth(), pady);
		
		g.setColor(Color.black);
		titleLabel.paint(g);
		g.drawImage(bg.getImage(), padx, pady, w, h, null);
		g.drawRect(padx, pady, w, h);
		g.drawLine(p1.getX(), p1.getY(), p0.getX(), p0.getY());
	}
}
