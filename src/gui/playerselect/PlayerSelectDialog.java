package gui.playerselect;

import java.awt.EventQueue;

import javax.swing.JDialog;
import java.awt.BorderLayout;
import javax.swing.JComboBox;
import javax.swing.JSpinner;

import backend.Player;

import javax.swing.DefaultComboBoxModel;
import gui.colorwheel.ColorWheel;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class PlayerSelectDialog extends JDialog {
	private JTextField myName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PlayerSelectDialog dialog = new PlayerSelectDialog();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public PlayerSelectDialog() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		if (Player.load(false)) {
			System.out.println("Profiles Loaded...");
			for (Player a : Player.player_records)
				System.out.println(a);
		}
		
		JComboBox<Player> comboBox = new JComboBox<>();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Player newPlayer = new Player();
				Player.player_records.add(newPlayer);
				comboBox.setModel(new DefaultComboBoxModel<Player>(Player.player_records.toArray(new Player[0])));
				comboBox.setSelectedItem(newPlayer);
			}
		});
		comboBox.setModel(new DefaultComboBoxModel<Player>(Player.player_records.toArray(new Player[0])));
		getContentPane().add(comboBox, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(200, 200));
		getContentPane().add(panel, BorderLayout.EAST);
		
		ColorWheel colorWheel = new ColorWheel(100);
		colorWheel.setPreferredSize(new Dimension(200, 200));
		panel.add(colorWheel);
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new GridLayout(1, 0, 0, 0));
		
		JButton New = new JButton("New");
		New.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Player.save();
			}
		});
		panel_1.add(New);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		panel_1.add(btnDelete);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		panel_1.add(btnCancel);
		
		JButton btnSelect = new JButton("Select");
		btnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
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
		
		JLabel wins = new JLabel("Games won: ");
		wins.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_2.add(wins);
		
		JLabel color = new JLabel("Color:");
		color.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_2.add(color);
		
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
		panel_3.add(myColor);

	}

}
