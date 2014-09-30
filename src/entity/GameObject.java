package entity;

import java.awt.Graphics2D;

import util.Sprite;
import map.TileMap;
import static map.TileMap.TileCollisionType.*;

public class GameObject {
	/*Current position on the map*/
	protected int x;
	protected int y;
	
	private int deltaX;
	private int deltaY;
	
	/*Object size*/
	private int height;
	private int width;
	
	/*Indicates whether object on the ground*/
	private boolean falling;
	
	protected Sprite sprite;
	
	/*Current map*/
	private TileMap map;
	
	public GameObject(int height, int width, TileMap map) {
		this.map = map;
		this.height = height;
		this.width = width;
		
		falling = true;
	}
	
	public void draw(Graphics2D g) {
		g.drawImage(sprite.getImage(), x + map.getX() - width / 2, y, 64, 64, null);
	}

	public void update() {
		move();
	}
	
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void setVector(int deltaX, int deltaY) {
		this.deltaX = deltaX;
		this.deltaY = deltaY;
	}
	
	public void setHorizontalVector(int deltaX) {
		this.deltaX = deltaX;
	}
	
	public void move() {
		calculateNextPosition();
	}
	
	public int getX() { return x; }
	public int getY() { return y; }
	
	private void calculateNextPosition() {
		if(deltaX > 0) {
			int tmpX = x + deltaX + width / 2;
			if(map.getCollisionType(tmpX, y) != BLOCKED) {
				x += deltaX;
			}
			else {
				deltaX = 0;
			}
		}
		if(deltaX < 0) {
			int tmpX = x + deltaX - width / 2;
			if(map.getCollisionType(tmpX, y) != BLOCKED) {
				x += deltaX;
			}
			else {
				deltaX = 0;
			}
		}
		if(falling) {
			int tmpY = y + deltaY + height;
			if(map.getCollisionType(x, tmpY) != BLOCKED) {
				y += deltaY;
			}
			else {
				y += deltaY - (y + deltaY) % 32;
				deltaY = 0;
				falling = false;
			}
		}
	}
	
	private void checkCollisions() {
	}
}
