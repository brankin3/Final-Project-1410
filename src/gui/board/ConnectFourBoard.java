package gui.board;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JComponent;
import javax.swing.event.MouseInputAdapter;

public class ConnectFourBoard extends JComponent{

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
	private final Player[][] slot;

	public ConnectFourBoard() {
		listeners = new HashSet<>();
		this.addMouseListener(new ConnectFourMouseListener(this));
		slot = new Player[7][6];
	}

	public void addListener(ConnectFourListener l) {
		listeners.add(l);
	}

	@Override
	protected void paintComponent(Graphics g) {
		int h_padding = getWidth() / 72;
		int h_size = getWidth() / 9;
		int v_padding = getHeight() / 72;
		int v_size = getHeight() / 9;
		super.paintComponent(g);
		g.setColor(Color.blue);
		g.fillRect(0, (h_padding * 2) + h_size, getWidth(), getHeight() - (v_padding * 2) + v_size);
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 6; j++) {
				if (slot[i][j] == null)
					g.setColor(Color.white);
				else
					g.setColor(slot[i][j].color);
				g.fillOval(h_padding + (i*(h_size + (2*h_padding))), v_padding + ((j+1)*(v_size + (2*v_padding))), h_size, v_size);
			}
		}
	}

	public Player[][] getBoard(){
		return slot;
	}
	
	public void setPreview(int column, Color color) {

	}

	public void setSlot(int row, int column, Player color) {
		slot[row][column] = color;
		repaint();
	}

	public void drop(int c, Player player) {
		for (int r = 5; r > -1; r--) {
			if (slot[c][r] == null) {
				slot[c][r] = player;
				break;
			}
		}
		repaint();
	}

	public Player[][] getSlots() {
		return slot;
	}

}