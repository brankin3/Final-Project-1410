package gui;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import javax.swing.ImageIcon;
import java.awt.Cursor;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.SystemColor;
import java.awt.Canvas;

public class ConnectFourGui extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConnectFourGui frame = new ConnectFourGui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ConnectFourGui() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel topPanel = new JPanel();
		contentPane.add(topPanel, BorderLayout.NORTH);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JPanel chipInsert = new JPanel();
		panel_1.add(chipInsert, BorderLayout.NORTH);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(ConnectFourGui.class.getResource("/CSIS1410Project/149178975447639.png")));
		chipInsert.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(ConnectFourGui.class.getResource("/CSIS1410Project/lpHn2.png")));
		chipInsert.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("New label");
		chipInsert.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("New label");
		chipInsert.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("New label");
		chipInsert.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("New label");
		chipInsert.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("New label");
		chipInsert.add(lblNewLabel_8);
		
		JPanel board = new JPanel();
		board.setBackground(SystemColor.textHighlight);
		panel_1.add(board, BorderLayout.CENTER);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(ConnectFourGui.class.getResource("/CSIS1410Project/149178975447639.png")));
		board.add(lblNewLabel_1);
		
		JLabel lblNewLabel_9 = new JLabel("");
		lblNewLabel_9.setIcon(new ImageIcon(ConnectFourGui.class.getResource("/CSIS1410Project/149178975447639.png")));
		board.add(lblNewLabel_9);
		
		JLabel lblNewLabel_10 = new JLabel("");
		lblNewLabel_10.setIcon(new ImageIcon(ConnectFourGui.class.getResource("/CSIS1410Project/149178975447639.png")));
		board.add(lblNewLabel_10);
		
		JLabel lblNewLabel_11 = new JLabel("");
		lblNewLabel_11.setIcon(new ImageIcon(ConnectFourGui.class.getResource("/CSIS1410Project/149178975447639.png")));
		board.add(lblNewLabel_11);
		
		JLabel lblNewLabel_12 = new JLabel("");
		lblNewLabel_12.setIcon(new ImageIcon(ConnectFourGui.class.getResource("/CSIS1410Project/149178975447639.png")));
		board.add(lblNewLabel_12);
		
		JLabel lblNewLabel_13 = new JLabel("");
		lblNewLabel_13.setIcon(new ImageIcon(ConnectFourGui.class.getResource("/CSIS1410Project/149178975447639.png")));
		board.add(lblNewLabel_13);
		
		JLabel lblNewLabel_14 = new JLabel("");
		lblNewLabel_14.setIcon(new ImageIcon(ConnectFourGui.class.getResource("/CSIS1410Project/149178975447639.png")));
		board.add(lblNewLabel_14);
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.SOUTH);
		panel_2.setBackground(Color.CYAN);
		
		JButton btnNewButton = new JButton("");
		panel_2.add(btnNewButton);
		btnNewButton.setForeground(Color.GREEN);
		btnNewButton.setBackground(Color.GREEN);
		
		JLabel lblNewLabel = new JLabel("New label");
		panel_2.add(lblNewLabel);
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			}
		});
	}
}
