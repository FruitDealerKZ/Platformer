package entity;

import util.Sprite;
import map.TileMap;

public class Player extends GameObject {
	
	public Player(TileMap map) {
		super(64, 64, map);
		setPosition(100, 100);
		setVector(0, 6);
		sprite = new Sprite("/sprites/player/player.png", 64, 64);
	}
	
	public void moveLeft() {
		setHorizontalVector(-2);
	}
	
	public void moveRight() {
		setHorizontalVector(2);
	}
	
	public void jump() {
		if(canJump())
			jumping = true;
	}
	
	public void stop() {
		setHorizontalVector(0);
	}
}
