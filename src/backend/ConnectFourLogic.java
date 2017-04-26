package backend;

import javax.swing.JOptionPane;

import gui.board.ConnectFourBoard;
import gui.board.ConnectFourEvent;
import gui.board.ConnectFourListener;
import gui.playerselect.PlayerSelectDialog;
import gui.playerselect.PlayerSelectListener;

public class ConnectFourLogic implements ConnectFourListener, PlayerSelectListener {

	@Override
	public void eventOccurred(ConnectFourEvent e) {
		if (e.getID() == ConnectFourEvent.MOUSEOVER) {
			board.setPreview(e.getColumn(), current_player.color);
			return;
		}
		if (e.getID() == ConnectFourEvent.CLICK) {
			if (!board.drop(e.getColumn(), current_player))
				return;
			if (checkForWin()) {
				current_player.wins++;
				Player.save();
				int response = JOptionPane.showConfirmDialog(board, "Rematch?", current_player.name + " wins!",
						JOptionPane.YES_NO_CANCEL_OPTION);
				switch (response) {
				case JOptionPane.YES_OPTION:
					board.clear();
					player1.plays++;
					player2.plays++;
					Player.save();
					break;
				case JOptionPane.NO_OPTION:
					board.setVisible(false);
					board.clear();
					player1 = null;
					player2 = null;
					PlayerSelectDialog psd = new PlayerSelectDialog();
					psd.addPlayerSelectListener(this);
					psd.setTitle("Player 1 Select");
					psd.setVisible(true);
					return;
				case JOptionPane.CANCEL_OPTION:
					board.setVisible(false);
					System.exit(0);
					return;
				default:
					System.exit(0);
				}
			}
			if (current_player == player1)
				current_player = player2;
			else
				current_player = player1;
			board.setPreview(e.getColumn(), current_player.color);
		}
	}

	private Player player1;
	private Player player2;
	private Player current_player;
	private final ConnectFourBoard board;

	public ConnectFourLogic(ConnectFourBoard associate) {
		board = associate;
		PlayerSelectDialog psd = new PlayerSelectDialog();
		psd.addPlayerSelectListener(this);
		psd.setTitle("Player 1 Select");
		psd.setVisible(true);
	}

	public boolean checkForWin() {
		Player[][] slot = board.getSlots();
		// horizontal
		for (int c = 0; c < 4; c++) {
			for (int r = 0; r < 6; r++) {
				if (slot[c][r] == current_player && slot[c + 1][r] == current_player && slot[c + 2][r] == current_player
						&& slot[c + 3][r] == current_player) {
					return true;
				}
			}
		}

		// vertical
		for (int c = 0; c < 7; c++) {
			for (int r = 3; r < 6; r++) {
				if (slot[c][r] == current_player && slot[c][r - 1] == current_player && slot[c][r - 2] == current_player
						&& slot[c][r - 3] == current_player) {
					return true;
				}
			}
		}
		// horizontal
		for (int c = 0; c < 4; c++) {
			for (int r = 3; r < 6; r++) {
				if (slot[c][r] == current_player && slot[c + 1][r - 1] == current_player
						&& slot[c + 2][r - 2] == current_player && slot[c + 3][r - 3] == current_player) {
					return true;

				}
			}
		}
		for (int c = 3; c < 7; c++) {
			for (int r = 3; r < 6; r++) {
				if (slot[c][r] == current_player && slot[c - 1][r - 1] == current_player
						&& slot[c - 2][r - 2] == current_player && slot[c - 3][r - 3] == current_player) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public void playerSelected(PlayerSelectDialog src) {
		if (player1 == null) {
			player1 = src.getSelectedPlayer();
			src.setTitle("Player 2 Select");
			return;
		}
		if (player2 == null) {
			current_player = player1;
			player2 = src.getSelectedPlayer();
			player1.plays++;
			player2.plays++;
			Player.save();
			src.setVisible(false);
			board.setVisible(true);
			src.dispose();
		}
	}

}
