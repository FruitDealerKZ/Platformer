package gamestate;

import game.Board;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.Color;

public class MenuState implements GameState {
	
	private String[] options = {"Game", "Settings", "Exit"};
	private int currentOption = 0;
	private GameStateManager gsm;
	
	public MenuState(GameStateManager gsm) {
		this.gsm = gsm;
	}
	
	public void update() {
		
	}

	public void draw(Graphics2D g) {
		int x = Board.WIDTH / 2 - 10;
		int y = Board.HEIGHT / 2;
		Color currentColor = Color.BLACK;
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, Board.WIDTH, Board.HEIGHT);
		for (int i = 0; i < options.length; i++) {
			if(i == currentOption)
				g.setColor(Color.GREEN);
			else
				g.setColor(currentColor);
			
			g.drawString(options[i], x, y);
			
			y += 20;
		}
		
		g.setColor(currentColor);
	}
	
	public void keyPressed(int key) {
		if(key == KeyEvent.VK_UP) {
			if(--currentOption == -1) {
				currentOption = options.length - 1;
			}
		}
		if(key == KeyEvent.VK_DOWN) {
			if(++currentOption == options.length) {
				currentOption = 0;
			}
		}
		if(key == KeyEvent.VK_ENTER) {
			select();
		}
	}
	
	private void select() {
		if(currentOption == 0) {
			// PLAY
			gsm.loadState(new Level(gsm));
		}
		if(currentOption == 1) {
			// SETTINGS
		}
		if(currentOption == 2) {
			// EXIT
			System.exit(0);
		}
	}

	public void keyReleased(int key) {}

}