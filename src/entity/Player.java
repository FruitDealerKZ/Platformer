package entity;

import util.Sprite;
import map.TileMap;

public class Player extends GameObject {
	
	public Player(TileMap map) {
		super(64, 64, 64, map);
		setPosition(100, 100);
		setVector(3, 6);
		sprite = new Sprite("/sprites/player/player.png", 64, 64);
	}
	
	public void jump() {
		if(canJump())
			jumping = true;
	}
	
	public void moveLeft(boolean flag) {
		left = flag;
	}
	
	public void moveRight(boolean flag) {
		right = flag;
	}
}
