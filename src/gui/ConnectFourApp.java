package gui;

import java.awt.Dimension;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ConnectFourApp{
	public static void main(String[] args) {
		
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		ConnectFour test = new ConnectFour(0, 0, 750, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(500, 500));
		
		//panel.setLayout(ConnectFour.theGrid);
		//panel.add(button[col][row]);
		frame.setVisible(true);
		frame.add(test);
		frame.pack();
	}
}
