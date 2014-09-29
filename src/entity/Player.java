package entity;

import java.awt.Graphics2D;

import util.Sprite;
import map.TileMap;

public class Player extends GameObject {
	
	private Sprite sprite;
	public Player(TileMap map) {
		super(64, 64, map);
		setPosition(100, 100);
		setVector(0, 2);
		sprite = new Sprite("/sprites/player/player.png", 64, 64);
	}

	@Override
	public void draw(Graphics2D g) {
		g.drawImage(sprite.getImage(), x, y, 64, 64, null);
	}

	@Override
	public void update() {
		move();
	}
	
	public void moveLeft() {
		setHorizontalVector(-2);
	}
	
	public void moveRight() {
		setHorizontalVector(2);
	}
	
	public void stop() {
		setHorizontalVector(0);
	}
}
