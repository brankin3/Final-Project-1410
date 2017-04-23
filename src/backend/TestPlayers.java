package backend;

import java.awt.Color;

public class TestPlayers {
	public static final Player player_one, player_two;
	static {
		player_one = new Player();
		player_two = new Player();
		player_one.name = "Test Player 1";
		player_one.color = Color.red;
		
		player_two.name = "Test Player 2";
		player_two.color = Color.cyan;
	}
}
