package gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ConnectFour extends JPanel{

	int x;
	int y;
	int w;
	int h;
	
	JPanel panel = new JPanel();
	
	final int row = 7;
	final int col = 6;

	JButton[][] button = new JButton[row][col];
	int[][] grid = new int[row][col];
	
	static GridLayout theGrid = new GridLayout(6,7);
	
	
	public ConnectFour(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		
		
	}
	
	public void CreateBoard() {
		for (int row = 0; 7 > row; row += 1) {
            for (int col = 0; 6 > col; col += 1) {
            	button[row][col] = new JButton();
            	panel.add(button[col][row]);

            }
        }
		
		
	}
	
	public boolean win(){
		
		//if()
		return false;
		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		//setBounds(50, 50, 250, 540);
		//Rectangle r = new Rectangle(15,15,80,80);
		
		setBackground(Color.WHITE);
		g.setColor(Color.CYAN);
		g.fillRect(10, 10, 500, 400);
		
		g.setColor(Color.BLACK);
		g.drawOval(20, 20, 10, 10);
		
		for (int row = 0; 7 > row; row += 1) {
            for (int col = 0; 6 > col; col += 1) {
            	g.setColor(Color.BLACK);
        		g.fillOval(20 + (row*80), 20 + (col*80), 50, 50);
        		
            }
        }
		
	}
}
