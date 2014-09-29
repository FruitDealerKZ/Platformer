package game;

import gamestate.GameStateManager;
import gamestate.MenuState;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Board extends JPanel implements Runnable, KeyListener {
	
	public static final int WIDTH = 640;
	public static final int HEIGHT = 480;
	public static final int SCALE = 1;
	
	/*game state*/
	private boolean running;
	
	/*image*/
	private BufferedImage image;
	private Graphics2D g;
	
	/*game thread*/
	private Thread thread;
	private int FPS = 60;
	private long targetTime = 1000 / FPS;
	
	private GameStateManager gsm;
	
	public Board() {
		super();
		setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		setFocusable(true);
		requestFocus();
	}
	
	public void run() {
		init();
		
		long start;
		long elapsed;
		long wait;
				
		while(running) {
			start = System.nanoTime();
			
			update();
			draw();
			
			elapsed = System.nanoTime() - start;
			
			wait = targetTime - elapsed / 1000000;
			
			if(wait < 0) wait = 5;
			
			try {
				Thread.sleep(wait);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}	
	}
	
	public void drawToScreen() {
		Graphics g2 = getGraphics();
		g2.drawImage(image, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);
		g2.dispose();
	}
	
	public void draw() {
		drawToScreen();
		gsm.draw();
	}
	
	public void update() {
		gsm.update();
	}
	
	public void addNotify() {
		super.addNotify();
		if(thread == null) {
			thread = new Thread(this);
			addKeyListener(this);
			thread.start();
		}
	}
	
	private void init() {
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D)image.getGraphics();
		
		gsm = new GameStateManager(g);
		
		MenuState menu = new MenuState(gsm);
		gsm.loadState(menu);
		
		running = true;
	}

	public void keyTyped(KeyEvent e) {
		
	}

	public void keyPressed(KeyEvent e) {
		gsm.keyPressed(e.getKeyCode());
	}

	public void keyReleased(KeyEvent e) {
		gsm.keyReleased(e.getKeyCode());
	}
}