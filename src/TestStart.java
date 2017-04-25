import java.awt.Dimension;

import javax.swing.JFrame;

import backend.ConnectFourLogic;
import backend.Player;
import gui.board.ConnectFourBoard;

public class TestStart {
	public static void main(String... args) {
		if (Player.load(false))
			System.out.println("Player profiles successfully loaded!");
		Player.save();
		JFrame frame = new JFrame();
		ConnectFourBoard cw = new ConnectFourBoard();
		cw.addListener(new ConnectFourLogic(cw));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(600, 600));
		frame.setResizable(false);
		frame.add(cw);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
