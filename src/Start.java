import backend.ConnectFourLogic;
import backend.DefaultPlayers;
import backend.Player;
import gui.board.ConnectFourBoard;

public class Start {
	public static void main(String... args) {
		if (Player.load(false))
			System.out.println("Player profiles successfully loaded!");
		if (Player.player_records.size() == 0) {
			Player.player_records.add(DefaultPlayers.player_one);
			Player.player_records.add(DefaultPlayers.player_two);
		}
		Player.save();
		ConnectFourBoard cw = new ConnectFourBoard();
		cw.addListener(new ConnectFourLogic(cw));
	}
}
