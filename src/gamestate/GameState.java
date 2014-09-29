package gamestate;

public interface GameState {
	public void update();
	public void draw(java.awt.Graphics2D g);
	public void keyPressed(int key);
	public void keyReleased(int key);
}
