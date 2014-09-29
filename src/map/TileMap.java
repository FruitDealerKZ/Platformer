package map;

import util.Sprite;
import game.Board;

import java.io.*;
import java.util.*;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;

public class TileMap {
	
	private static Map<Integer, BufferedImage> tiles = new HashMap<Integer, BufferedImage>();
	private static Map<Integer, TileCollisionType> collisionsMap = new HashMap<Integer, TileCollisionType>();
	static {
		Sprite sprite = new Sprite("/sprites/envr/surf.png", 32,32);
		tiles.put(0, sprite.getImage());
		sprite.setCol(1);
		tiles.put(1, sprite.getImage());
		sprite.setCol(2);
		tiles.put(2, sprite.getImage());
		sprite.setCol(3);
		tiles.put(3, sprite.getImage());
		
		collisionsMap.put(0, TileCollisionType.NON_BLOCKED);
		collisionsMap.put(1, TileCollisionType.BLOCKED);
		collisionsMap.put(2, TileCollisionType.NON_BLOCKED);
	}
	
	public static enum TileCollisionType {
		BLOCKED,
		NON_BLOCKED,
		LETHAL
	}
	
	private int colCount;
	private int rowCount;
	
	private int x;
	private int y;
	private int xmin;
	private int ymin;
	private int xmax;
	private int ymax;
	
	private int colOffset;
	private int rowOffset;
	private final int colOnScreen;
	private final int rowOnScreen;
	
	private int tileHeight = 32;
	private int tileWidth = 32;
	private int map[][];
	
	public TileMap(String path) {		
		InputStream in = getClass().getResourceAsStream(path);
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		
		try {
			colCount = Integer.parseInt(reader.readLine());
			rowCount = Integer.parseInt(reader.readLine());
			
			ymin = Board.WIDTH - colCount * tileWidth;
			xmin = Board.HEIGHT - rowCount * tileHeight;
			
			map = new int[rowCount][colCount];
			
			for(int i = 0; i < rowCount; i++) {
				String[] keys = reader.readLine().split(" ");
				for(int j = 0; j < colCount; j++) {
					map[i][j] = Integer.parseInt(keys[j]);
				}
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
		colOnScreen = Board.WIDTH / tileWidth;
		rowOnScreen = Board.HEIGHT / tileHeight;
	}
	
	public void setPosition(int x, int y) {
		this.x += (x - this.x) * 0.07;
		//this.y += y - this.y;
		fixBounds();
		System.out.println("x = " + this.x);
		System.out.println("col = " + rowOffset);
		rowOffset = -this.y / tileHeight;
		colOffset = -this.x / tileWidth;
	}
	
	private void fixBounds() {
		if(x < xmin) x = xmin;
		if(y < ymin) y = ymin;
		if(x > xmax) x = xmax;
		if(y > ymax) y = ymax;
	}
	
	public void draw(Graphics2D g) {
		for(int i = rowOffset; i < rowOffset + rowOnScreen; i++) {
			for(int j = colOffset; j < colOffset + colOnScreen; j++) {
				g.drawImage(tiles.get(map[i][j]), x + j * tileWidth, y + i * tileHeight, tileWidth, tileHeight, null);
			}
		}
	}
	
	public void update() {
		
	}
	
	public TileCollisionType getCollisionType(int x, int y) {
		int col = x / tileWidth;
		int row = y / tileHeight;
		
		return collisionsMap.get(map[row][col]);
	}
}