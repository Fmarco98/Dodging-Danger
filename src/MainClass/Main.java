package MainClass;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;

import javax.swing.Timer;

import GUI.GlobalFrame;
import GameObjects.Obstacle;
import GameObjects.PowerUp;
import Util.Cronometro;

/**
 * Main
 */
public class Main {
	//Impostazione 
	public static final boolean ENABLE_SOUNDTRACK = false;
	public static final boolean ENABLE_SOUNDEFFECTS = true;
	
	//KeyBinds 
	private static final int[] RIGHT_MOVE_KEYS = {68, 39};  // A; <
	private static final int[] LEFT_MOVE_KEYS = {65, 37};   // D; >
	private static final int[] BUTTONS_KEYS = {49, 50, 51}; // 1,2,3
	private static final int RESET_KEY = 109;				// - (tn)
	
	//Archivio keyPressed
	private static Set<Integer> pressedKeysCode = new HashSet<>();
	
	public static void main(String[] args) {
		GlobalFrame f = new GlobalFrame();
		
		f.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {}
			
			@Override
			public void keyPressed(KeyEvent e) {
				synchronized(pressedKeysCode){
					if(e.getKeyCode() == LEFT_MOVE_KEYS[0]) {
						pressedKeysCode.add(LEFT_MOVE_KEYS[1]);
					} else if(e.getKeyCode() == RIGHT_MOVE_KEYS[0]) {
						pressedKeysCode.add(RIGHT_MOVE_KEYS[1]);
					} else {
						pressedKeysCode.add(e.getKeyCode());
					}
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				synchronized(pressedKeysCode){
					if(e.getKeyCode() == LEFT_MOVE_KEYS[0]) {
						pressedKeysCode.remove(LEFT_MOVE_KEYS[1]);
					} else if(e.getKeyCode() == RIGHT_MOVE_KEYS[0]) {
						pressedKeysCode.remove(RIGHT_MOVE_KEYS[1]);
					} else {						
						pressedKeysCode.remove(new Integer(e.getKeyCode()));
					}
				}
			}
		});
		
		//Sincronizzazione delle azioni
		Timer t = new Timer(GlobalFrame.REFRESH_RATE, new ActionListener() {
			Cronometro c = new Cronometro(100);

			@Override
			public void actionPerformed(ActionEvent e) {

				if(pressedKeysCode.contains(RESET_KEY)) {
					pressedKeysCode.clear();
					c.stop();
					f.getGamePanel().reset();
				}

				for(int keyCode:pressedKeysCode) {
					if(keyCode == 65 || keyCode == 37) 
						f.getGamePanel().getGamePanel().getCar().moveLeft();
					if(keyCode == 68 || keyCode == 39) 
						f.getGamePanel().getGamePanel().getCar().moveRight();
					
					for(int i=0; i < BUTTONS_KEYS.length; i++) {
						if(keyCode == BUTTONS_KEYS[i]) 
							f.getGamePanel().getPowerUpPanel().getButtons()[i].doClick();
					}
				}
				
				if(f.isPanelType(GlobalFrame.GAME_PANEL)) {
					if(!c.isStarted()) {
						c.start();
					}
					if(c.getTimeElapsed() >= 5*1000) {
						if(!c.wasCalled()) {

							if(c.isElapsed(500)) {
								f.getGamePanel().getInfoPanel().increseScore();
							}
							if(c.isElapsed(Obstacle.GENERATION_TIME)) {
								f.getGamePanel().getGamePanel().getRoad().genObstacles();
								f.getGamePanel().getGamePanel().increaseV(Obstacle.GENERATION_TIME);
								
							} else if(c.isElapsed(PowerUp.GENERATION_TIME)) {								
								f.getGamePanel().getGamePanel().getRoad().genPowerUp();
							}
							c.call();
						}
					}
					
					f.getGamePanel().getInfoPanel().getSpeedmeter().setVs(f.getGamePanel().getGamePanel().getCurrentV(),f.getGamePanel().getGamePanel().getMaxV());

					for(Obstacle o:f.getGamePanel().getGamePanel().getRoad().getObstaclesList()) {
						//schianto
						if(f.getGamePanel().getGamePanel().getCar().intersects(o)) {
							if(f.getGamePanel().getGamePanel().canCarDie()) {								
								c.stop();
								f.changeToGameOver(f.getGamePanel().getInfoPanel().getScore());
								
								f.getGamePanel().reset();
							}
						}
					}
					
					for(PowerUp p:f.getGamePanel().getGamePanel().getRoad().getPowerUpsList()) {
						if(f.getGamePanel().getGamePanel().getCar().intersects(p))
							if(f.getGamePanel().getPowerUpPanel().addPowerUp(p.getItemPowerUp()))
								f.getGamePanel().getGamePanel().getRoad().removePowerUp(p);
					}
					
					f.getGamePanel().getGamePanel().moveItems();
				}
				
				f.requestFocus();
				f.repaint();
			}
		});
		t.start();
	}
}