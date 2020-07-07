import java.awt.BorderLayout;
import java.awt.EventQueue;

import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InventoryManagement extends JFrame {

	public JFrame frame = new JFrame();
	private JPanel contentPane;
	Connection connection = null;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InventoryManagement frame = new InventoryManagement();
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
	public InventoryManagement() {
		connection = sqliteConnection.dbConnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1368, 689);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(153, 153, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblWelcomeToInventory = new JLabel("Welcome  To  Inventory  Management  ");
		lblWelcomeToInventory.setForeground(new Color(0, 51, 0));
		lblWelcomeToInventory.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));
		lblWelcomeToInventory.setBackground(new Color(153, 204, 204));
		lblWelcomeToInventory.setBounds(356, 77, 806, 82);
		contentPane.add(lblWelcomeToInventory);
		
		JLabel label_1 = new JLabel("Search For Inventories");
		label_1.setForeground(new Color(204, 51, 102));
		label_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 25));
		label_1.setBounds(534, 270, 321, 49);
		contentPane.add(label_1);
		
		JButton button = new JButton("Search");
		button.setForeground(new Color(51, 51, 102));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				Search search = new Search();
				search.setVisible(true);
			}
		});
		button.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		button.setBackground(new Color(255, 255, 102));
		button.setBounds(610, 412, 150, 38);
		contentPane.add(button);
		
		JLabel label_2 = new JLabel("Use Inventory Tools");
		label_2.setForeground(new Color(204, 51, 102));
		label_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 25));
		label_2.setBounds(143, 270, 305, 49);
		contentPane.add(label_2);
		
		JButton buttonTools = new JButton("Tools");
		buttonTools.setForeground(new Color(51, 0, 102));
		buttonTools.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null,"Please Login as an Administrator to use Inventory Tools!");
				frame.dispose();
				Login login = new Login();
				login.setVisible(true);
			}
		});
		buttonTools.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		buttonTools.setBackground(new Color(255, 255, 102));
		buttonTools.setBounds(208, 412, 143, 38);
		contentPane.add(buttonTools);
		
		JLabel label_3 = new JLabel("Get an Overview");
		label_3.setForeground(new Color(204, 51, 102));
		label_3.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 25));
		label_3.setBounds(973, 278, 256, 32);
		contentPane.add(label_3);
		
		JButton buttonOverview = new JButton("Overview");
		buttonOverview.setForeground(new Color(51, 51, 102));
		buttonOverview.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				Overview overview = new Overview();
				overview.setVisible(true);
			}
		});
		buttonOverview.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		buttonOverview.setBackground(new Color(255, 255, 102));
		buttonOverview.setBounds(1005, 412, 157, 38);
		contentPane.add(buttonOverview);
	}
}
