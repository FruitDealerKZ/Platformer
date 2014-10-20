package entity;

import java.awt.Graphics2D;
import java.awt.Point;

import util.Sprite;
import map.TileMap;
import static map.TileMap.TileCollisionType.*;

public class GameObject {
	/*Current position on the map*/
	protected int x;
	protected int y;
	
	protected boolean left;
	protected boolean right;
	protected boolean stopping;
	private boolean blocked;
	
	private double deltaX;
	private int deltaY;
		
	private double awalking = 1;
	protected double acurrent;
	private double vmax = 5;
	
	/*Object size*/
	private int height;
	private int width;
	
	private int tileSize;
	
	/*Jump characteristics*/
	private int jumpHeight = 160;
	private int jumpSpeed = -8;
	private int currentJumpHeight;
	protected boolean jumping;
	
	/*Indicates whether object on the ground*/
	private boolean falling;
	
	protected Sprite sprite;
	
	/*Current map*/
	private TileMap map;
	
	public GameObject(int height, int width, int tileSize, TileMap map) {
		this.map = map;
		this.height = height;
		this.width = width;
		this.tileSize = tileSize;
		
		acurrent = awalking;
		
		falling = false;
	}
	
	public void draw(Graphics2D g) {
		g.drawImage(sprite.getImage(), x + map.getX() - width / 2, y, tileSize, tileSize, null);
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
		deltaX += acurrent;
		
		if(Math.abs(deltaX) > vmax) deltaX = vmax * Math.signum(deltaX);
				
		if(!left && !right) {
			if(stopping) {
				if((int)deltaX == 0) {
					deltaX = 0;
					acurrent = 0;
				}
			}
			
			if(!stopping) {
				stopping = true;
				
				acurrent = -acurrent;
			}
		}

		checkCollisions();
		
		if(!blocked) {
			x += deltaX;
		}
		
		if(falling) {
			System.out.println("deltaY = " + deltaY + " y = " + y);
			y += deltaY;
		}
		
		if(jumping) {
			System.out.println("Jumping");
			int tmpY = y + jumpSpeed + height / 2;
			int x1 = x - width / 2;
			int x2 = x + width / 2;
			if(map.getCollisionType(x1, tmpY) != BLOCKED && map.getCollisionType(x2, tmpY) != BLOCKED) {
				y += jumpSpeed;
				currentJumpHeight += Math.abs(jumpSpeed);
				
				if(currentJumpHeight == jumpHeight) {
					currentJumpHeight = 0;
					jumping = false;
					falling = true;
				}
			}
			else {
				currentJumpHeight = 0;
				jumping = false;
				falling = true;
			}
		}
	}
	
	public boolean canJump() {
		return !falling;
	}
	
	private void checkCollisions() {		
		/*Checks if object can stay on the ground*/
		if(deltaY > 0) {
			Point bottomRay1 = new Point(x - width / 2, y + height + deltaY);
			Point bottomRay2 = new Point(x + width / 2, y + height + deltaY);
			Point bottomRayCenter = new Point(x, 		y + height + deltaY);
			if(map.getCollisionType(bottomRay1.x, bottomRay1.y) == NON_BLOCKED
					&& map.getCollisionType(bottomRay2.x, bottomRay2.y) == NON_BLOCKED
					&& map.getCollisionType(bottomRayCenter.x, bottomRayCenter.y) == NON_BLOCKED) {
				falling = true;
				System.out.println("Falling Yes");
			}
			else {
				falling = false;
				System.out.println("Falling Not");
			}
		}
		
		if(deltaX > 0) {
			Point topRay = new Point((int)(x + width / 2 + deltaX), y);
			Point bottomRay = new Point((int)(x + width / 2 + deltaX), y + height / 2);
			
			if(map.getCollisionType(topRay.x, topRay.y) == BLOCKED ||
					map.getCollisionType(bottomRay.x, bottomRay.y) == BLOCKED) {
				x += deltaX - (x + width / 2 + deltaX) % 32;
				deltaX = 0;
				blocked = true;
			}
			else {
				blocked = false;
			}
		}
		
		if(deltaX < 0) {
			Point topRay = new Point((int)(x - width / 2 + deltaX), y);
			Point bottomRay = new Point((int)(x - width / 2 + deltaX), y + height / 2);
			
			if(map.getCollisionType(topRay.x, topRay.y) == BLOCKED ||
					map.getCollisionType(bottomRay.x, bottomRay.y) == BLOCKED) {
				deltaX = 0;
				blocked = true;
			}
			else {
				blocked = false;
			}
		}
	}
}
