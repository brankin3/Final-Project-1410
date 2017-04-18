package gui.board;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.Collections;
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
		listeners = Collections.emptySet();
		this.addMouseListener(new ConnectFourMouseListener(this));
		slot = new Player[7][6];

		setSlotColor(1, 1, Color.CYAN);
		eventOccurred(E);

		drop(6, Color.RED);
		/*
		 * for(int i = 5; i > -1; i--) { drop(0, Color.ORANGE); }
		 */
		drop(3, Color.CYAN);
		drop(3, Color.RED);
		drop(3, Color.RED);
		drop(3, Color.RED);
		drop(3, Color.RED);
		drop(4, Color.RED);
		drop(4, Color.RED);
		drop(5, Color.RED);
		drop(5, Color.RED);
		drop(5, Color.RED);
		drop(5, Color.RED);
		drop(3, Color.CYAN);
		// drop(3, Color.RED);

		/*
		 * drop(3, Color.CYAN); drop(4, Color.CYAN); drop(1, Color.CYAN);
		 * drop(2, Color.CYAN);
		 */
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
		g.fillRect(0, 20, getWidth(), getHeight() - 20);
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 6; j++) {
				if (slot[i][j] == null)
					g.setColor(Color.white);
				else
					g.setColor(slot[i][j].color);
				g.fillOval(2 + i * 22, 22 + j * 22, 20, 20);
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
	}

	public void drop(int c, Player player) {
		for (int r = 5; r > -1; r--) {
			if (slot[c][r] == null) {
				slot[c][r] = player;
				break;
			}
		}
	}

	public void checkForWin() {
		// horizontal
		for (int c = 0; c < 4; c++) {
			for (int r = 0; r < 6; r++) {
				if (slot[c][r] == Color.RED && slot[c + 1][r] == Color.RED && slot[c + 2][r] == Color.RED
						&& slot[c + 3][r] == Color.RED) {
					drop(6, Color.MAGENTA);
				}
			}
		}

		// vertical
		for (int c = 0; c < 7; c++) {
			for (int r = 0; r < 6; r++) {
				if (slot[c][r] == Color.RED && slot[c][r - 1] == Color.RED && slot[c][r - 2] == Color.RED
						&& slot[c][r - 3] == Color.RED) {
					drop(6, Color.GREEN);
				}
			}
		}
		// horizontal
		for (int c = 0; c < 6; c++) {
			for (int r = 0; r < 6; r++) {
				if (slot[c][r] == Color.RED && slot[c + 1][r - 1] == Color.RED
						&& slot[c + 2][r - 2] == Color.RED && slot[c + 3][r - 3] == Color.RED) {
					drop(6, Color.pink);
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