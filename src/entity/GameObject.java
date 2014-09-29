package entity;

import java.awt.Graphics2D;

import map.TileMap;
import static map.TileMap.TileCollisionType.*;

public abstract class GameObject {
	/*Current position on the map*/
	protected int x;
	protected int y;
	
	private int deltaX;
	private int deltaY;
	
	/*Object size*/
	private int height;
	private int width;
	
	/*Indicates whether object on the ground*/
	private boolean onGround;
	
	/*Current map*/
	private TileMap map;
	
	public GameObject(int height, int width, TileMap map) {
		this.map = map;
		this.height = height;
		this.width = width;
	}
	
	public abstract void draw(Graphics2D g);
	public abstract void update();
	
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
			int tmpX = x + deltaX + width;
			
			if(map.getCollisionType(tmpX, y) != BLOCKED) {
				x += deltaX;
			}
			else {
				deltaX = 0;
			}
		}
		if(deltaX < 0) {
			int tmpX = x + deltaY;
			
			if(map.getCollisionType(tmpX, y) != BLOCKED) {
				x += deltaX;
			}
			else {
				deltaX = 0;
			}
		}
		
		if(!onGround) {
			if(deltaY > 0) {
				int tmpY = y + deltaY + height;
				if(map.getCollisionType(0, tmpY) != BLOCKED) {
					y += deltaY;
				}
				else {
					deltaY = 0;
					onGround = true;
				}
			}
		}
	}
}
