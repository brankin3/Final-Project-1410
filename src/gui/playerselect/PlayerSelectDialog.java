package gui.playerselect;

import java.awt.EventQueue;

import javax.swing.JDialog;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JComboBox;

import backend.Player;

import javax.swing.DefaultComboBoxModel;

import gui.colorwheel.ColorListener;
import gui.colorwheel.ColorWheel;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class PlayerSelectDialog extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2686703077435330915L;
	private JTextField myName;

	private JComboBox<Player> comboBox;
	private Set<PlayerSelectListener> listeners;

	/**
	 * Create the dialog.
	 */
	public PlayerSelectDialog() {
		listeners = new HashSet<>();
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel panel_3 = new JPanel();
		getContentPane().add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(new GridLayout(0, 1, 0, 0));

		myName = new JTextField();
		panel_3.add(myName);
		myName.setColumns(10);

		JLabel myPlays = new JLabel("");
		panel_3.add(myPlays);

		JLabel myWins = new JLabel("");
		panel_3.add(myWins);

		JLabel myColor = new JLabel("");
		myColor.setOpaque(true);
		panel_3.add(myColor);

		ColorWheel colorWheel = new ColorWheel(100); // Moved for earlier access

		comboBox = new JComboBox<>();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/*
				 * Player newPlayer = new Player();
				 * Player.player_records.add(newPlayer); comboBox.setModel(new
				 * DefaultComboBoxModel<Player>(Player.player_records.toArray(
				 * new Player[0]))); comboBox.setSelectedItem(newPlayer);
				 */

				Player newPlayer = Player.player_records.get(comboBox.getSelectedIndex());

				// Player.load(rootPaneCheckingEnabled);

				// comboBox.getSelectedItem();
				myName.setText(newPlayer.name);
				myPlays.setText("     " + newPlayer.plays);
				myWins.setText("     " + newPlayer.wins);
				myColor.setBackground(newPlayer.color);
				colorWheel.setSelectedColor(newPlayer.color);

				System.out.println(Player.player_records.get(comboBox.getSelectedIndex()));
				// Player.save();
				repaint();
			}
		});
		comboBox.setModel(new DefaultComboBoxModel<Player>(Player.player_records.toArray(new Player[0])));
		getContentPane().add(comboBox, BorderLayout.NORTH);

		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(200, 200));
		getContentPane().add(panel, BorderLayout.EAST);

		colorWheel.setPreferredSize(new Dimension(200, 200));
		colorWheel.addColorListener(new ColorListener() {

			@Override
			public void colorChanged(ColorWheel src) {
				myColor.setBackground(src.getSelectedColor());
				repaint();
			}

		});
		panel.add(colorWheel);

		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new GridLayout(1, 0, 0, 0));

		JButton New = new JButton("New");
		New.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Player newPlayer = new Player();
				newPlayer.name = "New Player";

				newPlayer.plays = 0;
				newPlayer.wins = 0;
				newPlayer.color = Color.WHITE;

				myPlays.setText("     " + newPlayer.plays);
				myWins.setText("     " + newPlayer.wins);
				myColor.setBackground(newPlayer.color);

				Player.player_records.add(newPlayer);
				comboBox.setModel(new DefaultComboBoxModel<Player>(Player.player_records.toArray(new Player[0])));
				comboBox.setSelectedItem(newPlayer);

				Player.save();
			}
		});
		panel_1.add(New);

		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Player.player_records.remove(comboBox.getSelectedIndex());
				comboBox.removeItemAt(comboBox.getSelectedIndex());
				Player.save();

			}
		});
		panel_1.add(btnDelete);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);

			}
		});
		panel_1.add(btnCancel);

		JButton btnSelect = new JButton("Select");
		btnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Player newPlayer = new Player();
				newPlayer = Player.player_records.get(comboBox.getSelectedIndex());
				newPlayer.name = myName.getText();
				newPlayer.color = colorWheel.getSelectedColor();
				Player.save();
				repaint();
				notifyListeners();
			}
		});
		panel_1.add(btnSelect);

		JPanel panel_2 = new JPanel();
		panel_2.setPreferredSize(new Dimension(90, 10));
		getContentPane().add(panel_2, BorderLayout.WEST);
		panel_2.setLayout(new GridLayout(0, 1, 0, 0));

		JLabel labelName = new JLabel("Name:");
		labelName.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_2.add(labelName);

		JLabel plays = new JLabel("Games played:");
		plays.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_2.add(plays);

		JLabel wins = new JLabel("Games won:");
		wins.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_2.add(wins);

		JLabel color = new JLabel("Color:");
		color.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_2.add(color);
		Player newPlayer;
		if ((newPlayer = (Player) comboBox.getSelectedItem()) != null) {

			myName.setText(newPlayer.name);
			myPlays.setText("     " + newPlayer.plays);
			myWins.setText("     " + newPlayer.wins);
			myColor.setBackground(newPlayer.color);
			colorWheel.setSelectedColor(newPlayer.color);
		}

	}

	private void notifyListeners() {
		for (PlayerSelectListener psl : listeners)
			psl.playerSelected(this);
	}

	public void addPlayerSelectListener(PlayerSelectListener psl) {
		listeners.add(psl);
	}

	public Player getSelectedPlayer() {
		return (Player) comboBox.getSelectedItem();
	}

}
