package gui.board;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.Collections;
import java.util.Set;

import javax.swing.JComponent;
import javax.swing.event.MouseInputAdapter;

public class ConnectFourBoard extends JComponent {
	
	
	
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
		slotColor = new Color[6][7];
		for (int i = 0; i < 42; i++) {
			slotColor[i/7][i%7] = Color.gray;
		}
	}
	
	public void addListener(ConnectFourListener l) {
		listeners.add(l);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.blue);
		g.fillRect(0, 20, getWidth(), getHeight()-20);
		for (int i = 0; i < 6; i++) {
			for (int j = 0 ; j < 7; j++) {
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

}
