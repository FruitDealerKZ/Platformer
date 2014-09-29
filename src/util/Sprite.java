package util;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Sprite {
	/*Current row and column in sprite set*/
	private int col;
	private int row;
	
	/*Tile size in sprite*/
	private int height;
	private int width;
	
	/*Sprite*/
	private BufferedImage sprite;
	
	public Sprite(String path, int height, int width) {
		try {
			sprite = ImageIO.read(getClass().getResourceAsStream(path));
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		col = 0;
		row = 0;
		
		this.height = height;
		this.width = width;
	}
	
	public BufferedImage getImage() {
		BufferedImage image = sprite.getSubimage(col * width, row * height, width, height);
		
		return image;
	}
	
	public void setRow(int row) {
		this.row = row;
	}
	
	public void setCol(int col) {
		this.col = col;
	}
}
