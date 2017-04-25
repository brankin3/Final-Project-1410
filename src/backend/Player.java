package backend;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import io.extended.ExtendedPrimitiveReader;
import io.extended.ExtendedPrimitiveWriter;

public class Player {

	private static final String filename = "player_data.dat";
	private static final File data_location;
	private static final boolean can_load;
	public static final ArrayList<Player> player_records;

	static {
		boolean loadable = true;
		player_records = new ArrayList<>();

		File location = new File(Player.class.getProtectionDomain().getCodeSource().getLocation().getFile())
				.getParentFile();
		File[] potential = location.listFiles((f, e) -> e.equalsIgnoreCase(filename));
		if (potential.length == 0) {
			data_location = new File(location, filename);
			try {
				data_location.createNewFile();
				InputStream default_profile = Player.class.getResourceAsStream("/" + filename);
				FileOutputStream copy_profile = new FileOutputStream(data_location);
				int read = 0;
				byte[] buffer = new byte[64];
				while (true) {
					read = default_profile.read(buffer);
					if (read == -1)
						break;
					copy_profile.write(buffer, 0, read);
				}
				default_profile.close();
				copy_profile.close();
			} catch (IOException e1) {
				loadable = false;
			}

		} else
			data_location = potential[0];
		can_load = loadable;
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
			return false;
			// throw new RuntimeException("Settings File not found!");
		} catch (IOException e) {
			return false;
		}
		return true;
	}
	
	public static boolean load(boolean force) {
		System.out.println("Loading Profiles");
		if (!force && !can_load)
			return false;
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
			player_records.clear();
			System.out.println("An Exception Occurred...");
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public int wins = 0;
	public int plays = 0;
	public Color color = Color.WHITE;
	public String name = "Player";
	
	
}
