package game;

import java.awt.Dimension;

import javax.swing.JFrame;

public class Game {
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(new Dimension(Board.WIDTH * Board.SCALE, Board.HEIGHT * Board.SCALE));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setContentPane(new Board());
		frame.setResizable(false);
		frame.pack();
		frame.setVisible(true);
	}
}