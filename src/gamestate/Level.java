package gamestate;

import entity.Player;
import game.Board;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import map.TileMap;

public class Level implements GameState {

	private GameStateManager gsm;
	private Player player;
	private TileMap map;
	
	public Level(GameStateManager gsm) {
		this.gsm = gsm;
		map = new TileMap("/level/map.txt");
		player = new Player(map);
	}
	
	public void update() {
		player.update();		
		map.setPosition(Board.WIDTH / 2 - player.getX(), Board.HEIGHT / 2 - player.getY());
	}

	public void draw(Graphics2D g) {
		g.clearRect(0, 0, Board.WIDTH, Board.HEIGHT);
		map.draw(g);
		player.draw(g);
	}

	public void keyPressed(int key) {
		if(key == KeyEvent.VK_LEFT) {
			player.moveLeft();
		}
		if(key == KeyEvent.VK_RIGHT) {
			player.moveRight();
		}
	}

	public void keyReleased(int key) {
		if(key == KeyEvent.VK_LEFT || key == KeyEvent.VK_RIGHT) {
			player.stop();
		}
	}
}
