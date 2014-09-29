package gamestate;

import game.Board;

import java.awt.Graphics2D;

public class Level implements GameState {

	private GameStateManager gsm;
	
	public Level(GameStateManager gsm) {
		this.gsm = gsm;
	}
	
	public void update() {
		// TODO Auto-generated method stub
		
	}

	public void draw(Graphics2D g) {
		g.clearRect(0, 0, Board.WIDTH, Board.HEIGHT);
	}

	public void keyPressed(int key) {
		// TODO Auto-generated method stub
		
	}

	public void keyReleased(int key) {
		// TODO Auto-generated method stub
		
	}

}
