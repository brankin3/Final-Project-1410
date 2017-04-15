package gui.board;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.Collections;
import java.util.Set;

import javax.swing.JComponent;
import javax.swing.event.MouseInputAdapter;

public class ConnectFourBoard extends JComponent implements ConnectFourListener{
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7987906913414541623L;

	private class ConnectFourMouseListener extends MouseInputAdapter {
		private final ConnectFourBoard callback;
		
		public ConnectFourMouseListener(ConnectFourBoard connectFourBoard) {
			callback = connectFourBoard;
		}

		@Override
		public void mouseClicked(MouseEvent arg0) {
			ConnectFourEvent e = new ConnectFourEvent(0, (7 * arg0.getX()) / callback.getWidth());
			for (ConnectFourListener l : callback.listeners) {
				l.eventOccurred(e);
			}
		}

		@Override
		public void mouseMoved(MouseEvent arg0) {
			super.mouseMoved(arg0);
		}
		
	}
	
	

	private final Set<ConnectFourListener> listeners;
	private final Color[][] slotColor;

	public ConnectFourBoard() {
		listeners = Collections.emptySet();
		this.addMouseListener(new ConnectFourMouseListener(this));
		slotColor = new Color[7][6];
		for (int i = 0; i < 42; i++) {
			slotColor[i%7][i/7] = Color.gray;
		}
		
		
		setSlotColor(1, 1, Color.CYAN);
		eventOccurred(E);
		
		drop(6, Color.RED);
		/*for(int i = 5; i > -1; i--) {
			drop(0, Color.ORANGE);
		}*/
		drop(3, Color.CYAN);
		drop(3, Color.RED);
		drop(3, Color.RED);
		drop(3, Color.RED);
		drop(3, Color.RED);
		drop(3, Color.CYAN);
		drop(4, Color.CYAN);
		drop(1, Color.CYAN);
		drop(2, Color.CYAN);
		drop(4, Color.RED);
		drop(1, Color.RED);
		drop(2, Color.RED);
		checkForWin();
		
	}
	
	public void addListener(ConnectFourListener l) {
		listeners.add(l);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.blue);
		g.fillRect(0, 20, getWidth(), getHeight()-20);
		for (int i = 0; i < 7; i++) {
			for (int j = 0 ; j < 6; j++) {
				g.setColor(slotColor[i][j]);
				g.fillOval(2+i*22, 22+j*22, 20, 20);
			}
		}
	}
	
	public void setPreview(int column, Color color) {
		
	}
	
	public void setSlotColor(int row, int column, Color color) {
		slotColor[row][column] = color;
	}
	
	public void drop(int c, Color color){
		for(int r = 5; r > -1; r--) {
			if(slotColor[c][r] == Color.GRAY) {
                slotColor[c][r] = color;
                break;
            }
		}
	}
	
	
	public void checkForWin(){
		//horizontal
		for(int c = 0; c < 6; c++) {
			for(int r = 0; r < 6; r++) {
				if(slotColor[c][r] == Color.RED && slotColor[c+1][r] == Color.RED && 
						slotColor[c+2][r] == Color.RED && slotColor[c+3][r] == Color.RED) {
					drop(6, Color.MAGENTA);
				}
			}
		}
		
		//vertical
		for(int c = 0; c < 7; c++) {
			for(int r = 0; r < 6; r++) {
				if(slotColor[c][r] == Color.RED && slotColor[c][r-1] == Color.RED && 
						slotColor[c][r-2] == Color.RED && slotColor[c][r-3] == Color.RED) {
					drop(6, Color.GREEN);
				}
			}
		}
		
	}
	
	
	
	ConnectFourEvent E;
	@Override
	public void eventOccurred(ConnectFourEvent e) {
		
		E = e;
		
	}
	
}