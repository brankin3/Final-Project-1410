package gui.board;

import java.awt.Dimension;

import javax.swing.JFrame;

public class ConnectFourGui {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		ConnectFourBoard board = new ConnectFourBoard();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(600, 600));
		frame.add(board);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
	}

}
