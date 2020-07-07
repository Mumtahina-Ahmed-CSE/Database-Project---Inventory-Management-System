import java.awt.BorderLayout;
import java.sql.*;

import javax.swing.*;
import java.awt.EventQueue;

import javax.swing.border.EmptyBorder;

import java.awt.Font;

import com.toedter.calendar.JDayChooser;

import net.proteanit.sql.DbUtils;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class Overview extends JFrame {

	public JFrame frame = new JFrame();
	private JPanel contentPane;
	private JComboBox comboBox;
	private JTable table;
	private JTextField textFieldBuyer;
	private JTextField textFieldName;
	private JTextField textFieldCategory;
	private JTextField textFieldPrice;
	private JTextField textFieldRating;
	private JDateChooser dateChooser;
	
	Connection connection = null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Overview frame = new Overview();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public void refreshTable()
	{
		try {
			String query = " select * from 	Overview";
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			pst.close();
			rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public void fillComboBox()
	{
		try {
			String query = " select * from InventoryInfo";
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next())
			{
				comboBox.addItem(rs.getString("Name"));
				
			}
		
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 */
	public Overview() {
		connection = sqliteConnection.dbConnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1368, 689);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 204, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(483, 136, 796, 386);
		contentPane.add(scrollPane_1);
		
		table = new JTable();
		table.setForeground(new Color(255, 204, 51));
		table.setBackground(new Color(255, 255, 204));
		table.setFont(new Font("Tahoma", Font.BOLD, 18));
		scrollPane_1.setViewportView(table);
		
		JLabel lbl = new JLabel("Buyer");
		lbl.setForeground(new Color(255, 255, 0));
		lbl.setFont(new Font("Tahoma", Font.BOLD, 20));
		lbl.setBounds(90, 199, 68, 28);
		contentPane.add(lbl);
		
		textFieldBuyer = new JTextField();
		textFieldBuyer.setBackground(new Color(255, 255, 153));
		textFieldBuyer.setFont(new Font("Tahoma", Font.BOLD, 15));
		textFieldBuyer.setColumns(10);
		textFieldBuyer.setBounds(233, 202, 145, 28);
		contentPane.add(textFieldBuyer);
		
		JLabel lblName_2 = new JLabel("Name");
		lblName_2.setForeground(new Color(255, 255, 51));
		lblName_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblName_2.setBounds(91, 260, 87, 23);
		contentPane.add(lblName_2);
		
		textFieldName = new JTextField();
		textFieldName.setBackground(new Color(255, 255, 153));
		textFieldName.setFont(new Font("Tahoma", Font.BOLD, 15));
		textFieldName.setColumns(10);
		textFieldName.setBounds(233, 260, 145, 28);
		contentPane.add(textFieldName);
		
		textFieldCategory = new JTextField();
		textFieldCategory.setBackground(new Color(255, 255, 153));
		textFieldCategory.setFont(new Font("Tahoma", Font.BOLD, 15));
		textFieldCategory.setColumns(10);
		textFieldCategory.setBounds(233, 315, 145, 28);
		contentPane.add(textFieldCategory);
		
		JLabel lblName_1 = new JLabel("Category");
		lblName_1.setForeground(new Color(255, 255, 51));
		lblName_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblName_1.setBounds(91, 315, 101, 23);
		contentPane.add(lblName_1);
		
		JButton btnsave = new JButton("Save");
		btnsave.setBackground(new Color(153, 204, 102));
		btnsave.setForeground(new Color(0, 0, 0));
		btnsave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					String query = "insert into Overview(Buyer,Name,Category,Price,Rating,Date) values (?,?,?,?,?,?) ";
					PreparedStatement pst = connection.prepareStatement(query);
					
					pst.setString(1,textFieldBuyer.getText());
					pst.setString(2,textFieldName.getText());
					pst.setString(3,textFieldCategory.getText());
					pst.setString(4,textFieldPrice.getText());
					pst.setString(5,textFieldRating.getText());
					pst.setString(6,((JTextField)dateChooser.getDateEditor().getUiComponent()).getText());
					
					pst.execute();
					
					JOptionPane.showMessageDialog(null,"Data Inserted");
					
					pst.close();
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				refreshTable();
			}
		});
		btnsave.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
		btnsave.setBounds(164, 558, 130, 34);
		contentPane.add(btnsave);
		
		/*JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					String query = "Update Overview set Buyer = '"+textFieldBuyer.getText()+"' ,Name = '"+textFieldName.getText()+"', Category = '"+textFieldCategory.getText()+"', Price = '"+textFieldPrice.getText()+"', Rating = '"+textFieldRating.getText()+"', Date= '"+((JTextField)dateChooser.getDateEditor().getUiComponent()).getText()+"' where Name='"+textFieldName.getText()+"' ";
					PreparedStatement pst = connection.prepareStatement(query);
					
					pst.execute();
					
					JOptionPane.showMessageDialog(null,"Data Updated");
					
					pst.close();
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				refreshTable();
				
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
		btnUpdate.setBounds(81, 571, 122, 23);
		contentPane.add(btnUpdate);*/
		
		JLabel lblRating = new JLabel("Rating");
		lblRating.setForeground(new Color(255, 255, 51));
		lblRating.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblRating.setBounds(91, 431, 101, 23);
		contentPane.add(lblRating);
		
		textFieldRating = new JTextField();
		textFieldRating.setBackground(new Color(255, 255, 153));
		textFieldRating.setFont(new Font("Tahoma", Font.BOLD, 15));
		textFieldRating.setColumns(10);
		textFieldRating.setBounds(233, 431, 145, 28);
		contentPane.add(textFieldRating);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setForeground(new Color(255, 255, 51));
		lblDate.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblDate.setBounds(91, 486, 101, 23);
		contentPane.add(lblDate);
		
	    dateChooser = new JDateChooser();
	    dateChooser.setBackground(new Color(255, 255, 153));
	    dateChooser.setDateFormatString("yyyy-MM-dd");
		dateChooser.setBounds(233, 486, 145, 34);
		contentPane.add(dateChooser);
		
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setForeground(new Color(255, 255, 51));
		lblPrice.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblPrice.setBounds(91, 371, 101, 23);
		contentPane.add(lblPrice);
		
		textFieldPrice = new JTextField();
		textFieldPrice.setBackground(new Color(255, 255, 153));
		textFieldPrice.setFont(new Font("Tahoma", Font.BOLD, 15));
		textFieldPrice.setColumns(10);
		textFieldPrice.setBounds(233, 371, 145, 28);
		contentPane.add(textFieldPrice);
		
		comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.BOLD, 20));
		comboBox.setBackground(new Color(153, 204, 102));
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String query = "select * from InventoryInfo where name =?";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString(1,(String)comboBox.getSelectedItem());
					ResultSet rs = pst.executeQuery();
					
					while(rs.next())
					{

						textFieldName.setText(rs.getString("Name"));
						textFieldPrice.setText(rs.getString("Price"));
						textFieldCategory.setText(rs.getString("Category"));
						
						
					}
					
					
					pst.close();
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		comboBox.setBounds(149, 130, 145, 41);
		contentPane.add(comboBox);
		
		JLabel lblNewLabel = new JLabel("Overview");
		lblNewLabel.setForeground(new Color(204, 0, 0));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));
		lblNewLabel.setBounds(580, 36, 179, 34);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Cancel");
		btnNewButton.setBackground(new Color(153, 204, 102));
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame.dispose();
				InventoryManagement inventoryManagement = new InventoryManagement();
				inventoryManagement.setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton.setBounds(782, 569, 101, 28);
		contentPane.add(btnNewButton);
		refreshTable();
		fillComboBox();
	}
}