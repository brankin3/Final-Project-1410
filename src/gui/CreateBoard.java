package gui;

import java.awt.Color;

import javax.swing.JButton;

public class CreateBoard {
	int x;
	int y;
	public CreateBoard() {
		for (int row = 0; 7 > row; row += 1) {
            for (int col = 0; 6 > col; col += 1) {
            	button[row][col] = new JButton(c0);
            	button[row][col].addActionListener(new buttonListener());
            	panel.add(button[col][row]);

            }
        }
		
		
	}
	
	
}
