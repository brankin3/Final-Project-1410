package backend;

import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import io.extended.ExtendedPrimitiveReader;
import io.extended.ExtendedPrimitiveWriter;

public class Player {

	private static final String data_location;
	public static final ArrayList<Player> player_records;

	static {
		player_records = new ArrayList<>();
		URL data_location_url = Player.class.getResource("/player_data.dat");

		if (data_location_url != null) {
			data_location = data_location_url.getFile();
			System.out.println(data_location);
			try {
				ExtendedPrimitiveReader epr = new ExtendedPrimitiveReader(new FileInputStream(data_location), 16);
				int playerCounts = epr.readInt();
				for (int i = 0; i < playerCounts; i++) {
					Player addition = new Player();
					char[] name = new char[epr.readInt()];
					epr.readChars(name);
					addition.name = String.valueOf(name);
					addition.color = new Color(epr.readInt());
					addition.plays = epr.readInt();
					addition.wins = epr.readInt();
					player_records.add(addition);
				}
				epr.close();
			} catch (IOException e) {
			}
		} else {
			throw new RuntimeException("Settings File not found!");
		}
	}
	
	public static boolean save() {
		try {
			ExtendedPrimitiveWriter epw = new ExtendedPrimitiveWriter(new FileOutputStream(data_location, false), 16);
			epw.writeInt(player_records.size());
			for (Player p : player_records) {
				char[] name = p.name.toCharArray();
				epw.writeInt(name.length);
				epw.writeChars(name);
				epw.writeInt(p.color.getRGB());
				epw.writeInt(p.plays);
				epw.writeInt(p.wins);
			}
			epw.close();
		} catch (FileNotFoundException e) {
			throw new RuntimeException("Settings File not found!");
		} catch (IOException e) {
			return false;
		}
		return true;
	}

	public int wins = 0;
	public int plays = 0;
	public Color color = Color.WHITE;
	public String name = "Player";
}
