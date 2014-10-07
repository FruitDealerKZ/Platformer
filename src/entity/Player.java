package entity;

import util.Sprite;
import map.TileMap;

public class Player extends GameObject {
	
	public Player(TileMap map) {
		super(64, 64, 64, map);
		setPosition(300, 100);
		setVector(3, 5);
		sprite = new Sprite("/sprites/player/player.png", 64, 64);
	}
	
	public void jump() {
		if(canJump())
			jumping = true;
	}
	
	public void moveLeft(boolean flag) {
		if(flag) {
			acurrent = -0.1d;
			left = true;
			stopping = false;
		}
		else {
			left = false;
		}
	}
	
	public void moveRight(boolean flag) {
		if(flag) {
			acurrent = 0.1d;
			right = true;
			stopping = false;
		}
		else {
			right = false;
		}
	}
}
