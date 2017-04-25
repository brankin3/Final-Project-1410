package gui.colorwheel;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Sample {
	public static void main(String... args) {
		JFrame frame = new JFrame();
		ColorWheel cw = new ColorWheel(250);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(600, 600));
		frame.setResizable(false);
		frame.add(cw);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
