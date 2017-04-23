package gui.board;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JComponent;
import javax.swing.event.MouseInputAdapter;

import backend.Player;

public class ConnectFourBoard extends JComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7987906913414541623L;

	private class ConnectFourMouseListener extends MouseInputAdapter {
		private final ConnectFourBoard callback;
		private int column = 0;
		private boolean click = false;

		public ConnectFourMouseListener(ConnectFourBoard connectFourBoard) {
			callback = connectFourBoard;
		}

		private int calculateColumn(int x) {
			return (7 * x) / callback.getWidth();
		}

		@Override
		public void mouseDragged(MouseEvent arg0) {
			if (calculateColumn(arg0.getX()) != column)
				click = false;
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			column = calculateColumn(arg0.getX());
			click = true;
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			if (click) {
				ConnectFourEvent e = new ConnectFourEvent(0, calculateColumn(arg0.getX()));
				for (ConnectFourListener l : callback.listeners) {
					l.eventOccurred(e);
				}
				click = false;
			}
		}

		@Override
		public void mouseMoved(MouseEvent arg0) {
			ConnectFourEvent e = new ConnectFourEvent(1, calculateColumn(arg0.getX()));
			for (ConnectFourListener l : callback.listeners) {
				l.eventOccurred(e);
			}
		}

	}

	private final Set<ConnectFourListener> listeners;
	private final Player[][] slot;
	private int preview_column;
	private Color preview_color;

	public ConnectFourBoard() {
		listeners = new HashSet<>();
		ConnectFourMouseListener listener = new ConnectFourMouseListener(this);
		this.addMouseListener(listener);
		this.addMouseMotionListener(listener);
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
		g.fillRect(0, (v_padding * 2) + v_size, getWidth(), getHeight() - (v_padding * 2) + v_size);
		g.setColor(Color.pink);
		g.fillRect(0, 0, getWidth(), (h_padding * 2) + h_size);
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 6; j++) {
				if (slot[i][j] == null)
					g.setColor(Color.white);
				else
					g.setColor(slot[i][j].color);
				int h_offset = i * ((h_padding * 2) + h_size) + h_padding;
				int v_offset = (j + 1) * ((v_padding * 2) + v_size) + v_padding;
				g.fillOval(h_padding + h_offset, v_padding + v_offset, h_size, v_size);
			}
		}
		if (preview_color != null)
			g.setColor(preview_color.darker());
		else
			g.setColor(Color.MAGENTA);
		g.fillOval(preview_column * ((h_padding * 2) + h_size) + (2*h_padding), v_padding, h_size, v_size);
	}

	public Player[][] getBoard() {
		return slot;
	}

	public void setPreview(int column, Color color) {
		preview_column = column;
		preview_color = color;
		repaint();
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