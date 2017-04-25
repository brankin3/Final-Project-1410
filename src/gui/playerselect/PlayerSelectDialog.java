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

public class PlayerSelectDialog extends JDialog {

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
		panel_1.add(New);
		
		JButton btnDelete = new JButton("Delete");
		panel_1.add(btnDelete);
		
		JButton btnCancel = new JButton("Cancel");
		panel_1.add(btnCancel);
		
		JButton btnSelect = new JButton("Select");
		panel_1.add(btnSelect);

	}

}
