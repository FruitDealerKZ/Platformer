package gamestate;

import java.awt.Graphics2D;

public class GameStateManager {
	private GameState currentState;
	private Graphics2D g;
	
	public GameStateManager(Graphics2D g) {
		this.g = g;
	}
	
	public void loadState(GameState state) {
		currentState = state;
	}
	
	public void update() {
		currentState.update();
	}
	
	public void draw() {
		currentState.draw(g);
	}
	
	public void keyPressed(int key) {
		currentState.keyPressed(key);
	}
	
	public void keyReleased(int key) {
		currentState.keyReleased(key);
	}
}
