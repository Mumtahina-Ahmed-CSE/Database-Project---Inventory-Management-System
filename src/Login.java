import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class Login extends JFrame {
	
	public JFrame frame = new JFrame();
	Connection connection = null;
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		
		connection = sqliteConnection.dbConnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 635, 406);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 204, 102));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("UserName");
		label.setForeground(new Color(204, 102, 0));
		label.setFont(new Font("Tahoma", Font.BOLD, 20));
		label.setBounds(146, 114, 118, 38);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("Password");
		label_1.setForeground(new Color(204, 102, 0));
		label_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		label_1.setBounds(146, 192, 118, 38);
		contentPane.add(label_1);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.BOLD, 15));
		textField.setColumns(10);
		textField.setBackground(new Color(255, 255, 153));
		textField.setBounds(386, 117, 126, 38);
		contentPane.add(textField);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.BOLD, 15));
		passwordField.setBackground(new Color(255, 255, 153));
		passwordField.setBounds(386, 197, 126, 33);
		contentPane.add(passwordField);
		
		JButton button = new JButton("Login");
		button.setForeground(new Color(255, 255, 153));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try
				{
					String query = "select * from LoginInfo where username=? and password=? ";
					PreparedStatement pst =  connection.prepareStatement(query);
					pst.setString(1,textField.getText());
					pst.setString(2,passwordField.getText());
					
					ResultSet rs = pst.executeQuery();
					
					int count = 0;
					while(rs.next())
					{
						
						count = count +1;
					}
					if(count==1)
					{
						JOptionPane.showMessageDialog(null,  "Username and password is correct");
						frame.dispose();
						InventoryInfo inventoryInfo = new InventoryInfo();
						inventoryInfo.setVisible(true);
					}
					else if (count >1)
					{
						JOptionPane.showMessageDialog(null,  "Duplicate Username and password is correct");
					}
					else
					{
						JOptionPane.showMessageDialog(null,  "Username and password is not correct. Try again..");
					}
					rs.close();
					pst.close();
				}
				catch(Exception e1)
				{
					JOptionPane.showMessageDialog(null, e);
					
				}
		
			}
		});
		button.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		button.setBackground(new Color(204, 153, 51));
		button.setBounds(262, 271, 106, 32);
		contentPane.add(button);
		
		JLabel lblLoginAsAdmin = new JLabel("Login For Admins");
		lblLoginAsAdmin.setForeground(new Color(153, 0, 51));
		lblLoginAsAdmin.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblLoginAsAdmin.setBounds(224, 33, 237, 44);
		contentPane.add(lblLoginAsAdmin);
		
		JButton btnNewButton = new JButton("Cancel");
		btnNewButton.setBackground(new Color(204, 153, 51));
		btnNewButton.setForeground(new Color(255, 255, 153));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				InventoryManagement inventoryManagement = new InventoryManagement();
				inventoryManagement.setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton.setBounds(520, 333, 89, 23);
		contentPane.add(btnNewButton);
	}
}
