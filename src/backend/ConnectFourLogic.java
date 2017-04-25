package backend;

import gui.board.ConnectFourBoard;
import gui.board.ConnectFourEvent;
import gui.board.ConnectFourListener;

public class ConnectFourLogic implements ConnectFourListener {

	@Override
	public void eventOccurred(ConnectFourEvent e) {
		if (player1 == null)
			player1 = DefaultPlayers.player_one;
		if (player2 == null)
			player2 = DefaultPlayers.player_two;
		if (current_player == null)
			current_player = player1;
		if (e.getID() == 1) {
			board.setPreview(e.getColumn(), current_player.color);
			return;
		}
		if (e.getID() == ConnectFourEvent.CLICK) {
			if (!board.drop(e.getColumn(), current_player))
				return;
			checkForWin();
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
	}

	public void checkForWin() {
		Player[][] slot = board.getSlots();
		// horizontal
		for (int c = 0; c < 4; c++) {
			for (int r = 0; r < 6; r++) {
				if (slot[c][r] == current_player && slot[c + 1][r] == current_player && slot[c + 2][r] == current_player
						&& slot[c + 3][r] == current_player) {
					System.out.println("Horizontal Check passed");
				}
			}
		}

		// vertical
		for (int c = 0; c < 7; c++) {
			for (int r = 3; r < 6; r++) {
				if (slot[c][r] == current_player && slot[c][r - 1] == current_player && slot[c][r - 2] == current_player
						&& slot[c][r - 3] == current_player) {
					System.out.println("Vertical Check passed");
				}
			}
		}
		// horizontal
		for (int c = 0; c < 4; c++) {
			for (int r = 3; r < 6; r++) {
				if (slot[c][r] == current_player && slot[c + 1][r - 1] == current_player
						&& slot[c + 2][r - 2] == current_player && slot[c + 3][r - 3] == current_player) {
					System.out.println("Diagonal Backwards Check Passed");
					;
				}
			}
		}
		for (int c = 3; c < 7; c++) {
			for (int r = 3; r < 6; r++) {
				if (slot[c][r] == current_player && slot[c - 1][r - 1] == current_player
						&& slot[c - 2][r - 2] == current_player && slot[c - 3][r - 3] == current_player) {
					System.out.println("Diagonal Forwards Check Passed");
					;
				}
			}
		}
	}

}
