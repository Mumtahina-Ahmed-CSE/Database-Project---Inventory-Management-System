import java.awt.BorderLayout;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;

public class InventoryInfo extends JFrame {

	public JFrame frame = new JFrame();
	private JPanel contentPane;
	private JComboBox comboBoxName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InventoryInfo frame = new InventoryInfo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	Connection connection = null;
	private JLabel lblInventoryTools;
	private JTable table;
	private JScrollPane scrollPane;
	private JLabel lblId;
	private JTextField textFieldID;
	private JTextField textFieldName;
	private JTextField textFieldQuantity;
	private JTextField textFieldPrice;
	private JTextField textFieldCategory;
	private JButton btnCancel;
	
	public void refreshTable()
	{
		try {
			String query = " select * from InventoryInfo";
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
				comboBoxName.addItem(rs.getString("Name"));
				
			}
		
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Create the frame.
	 */
	public InventoryInfo() {
		connection = sqliteConnection.dbConnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1368, 689);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(102, 0, 255));
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnLoadTable = new JButton("Load Inventory Data");
		btnLoadTable.setBackground(new Color(102, 153, 204));
		btnLoadTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					String query = " select * from InventoryInfo";
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					pst.close();
					rs.close();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				
			}
		});
		btnLoadTable.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnLoadTable.setBounds(886, 76, 225, 31);
		contentPane.add(btnLoadTable);
		
		lblInventoryTools = new JLabel("Inventory Tools");
		lblInventoryTools.setForeground(new Color(51, 51, 153));
		lblInventoryTools.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblInventoryTools.setBounds(491, 36, 272, 31);
		contentPane.add(lblInventoryTools);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(610, 155, 792, 413);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setBackground(new Color(204, 204, 255));
		table.setForeground(new Color(51, 51, 153));
		table.setFont(new Font("Tahoma", Font.BOLD, 18));
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				try {
					int row = table.getSelectedRow();
					String ID_ = (table.getModel().getValueAt(row, 0)).toString();
					
					String query = "select * from InventoryInfo where 	id ='"+ID_+"'";
					PreparedStatement pst = connection.prepareStatement(query);
					
					ResultSet rs = pst.executeQuery();
					
					while(rs.next())
					{

						textFieldID.setText(rs.getString("ID"));
						textFieldName.setText(rs.getString("Name"));
						textFieldQuantity.setText(rs.getString("Quantity"));
						textFieldPrice.setText(rs.getString("Price"));
						textFieldCategory.setText(rs.getString("Category"));
						
						
					}
					
					
					pst.close();
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
		scrollPane.setViewportView(table);
		
		lblId = new JLabel("ID");
		lblId.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblId.setBounds(78, 235, 68, 23);
		contentPane.add(lblId);
		
		textFieldID = new JTextField();
		textFieldID.setFont(new Font("Tahoma", Font.BOLD, 15));
		textFieldID.setBackground(new Color(255, 204, 204));
		textFieldID.setBounds(197, 236, 145, 28);
		contentPane.add(textFieldID);
		textFieldID.setColumns(10);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblName.setBounds(78, 286, 90, 23);
		contentPane.add(lblName);
		
		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblQuantity.setBounds(76, 329, 112, 40);
		contentPane.add(lblQuantity);
		
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblPrice.setBounds(78, 388, 68, 23);
		contentPane.add(lblPrice);
		
		JLabel lblCategory = new JLabel("Category");
		lblCategory.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblCategory.setBounds(78, 435, 126, 37);
		contentPane.add(lblCategory);
		
		JButton btnNewButton = new JButton("Insert");
		btnNewButton.setBackground(new Color(102, 153, 204));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String query = "insert into InventoryInfo(ID,Name,Quantity,Price,Category) values (?,?,?,?,?) ";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString(1,textFieldID.getText());
					pst.setString(2,textFieldName.getText());
					pst.setString(3,textFieldQuantity.getText());
					pst.setString(4,textFieldPrice.getText());
					pst.setString(5,textFieldCategory.getText());
					
					pst.execute();
					
					JOptionPane.showMessageDialog(null,"Data Inserted");
					
					pst.close();
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				refreshTable();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnNewButton.setBounds(657, 603, 112, 23);
		contentPane.add(btnNewButton);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBackground(new Color(102, 153, 204));
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					String query = "delete from InventoryInfo where Name ='"+textFieldName.getText()+"'";
					PreparedStatement pst = connection.prepareStatement(query);
					
					pst.execute();
					
					JOptionPane.showMessageDialog(null,"Data Deleted");
					
					pst.close();
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				refreshTable();
				
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnDelete.setBounds(836, 603, 112, 23);
		contentPane.add(btnDelete);
		
		JButton btnUpdate = new JButton();
		btnUpdate.setBackground(new Color(102, 153, 204));
		btnUpdate.setText("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					String query = "Update InventoryInfo set ID = '"+textFieldID.getText()+"' ,Name = '"+textFieldName.getText()+"', Quantity = '"+textFieldQuantity.getText()+"', Price = '"+textFieldPrice.getText()+"', Category = '"+textFieldCategory.getText()+"' where ID='"+textFieldID.getText()+"' ";
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
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnUpdate.setBounds(1029, 603, 112, 23);
		contentPane.add(btnUpdate);
		
		textFieldName = new JTextField();
		textFieldName.setFont(new Font("Tahoma", Font.BOLD, 15));
		textFieldName.setBackground(new Color(255, 204, 204));
		textFieldName.setColumns(10);
		textFieldName.setBounds(197, 291, 145, 28);
		contentPane.add(textFieldName);
		
		textFieldQuantity = new JTextField();
		textFieldQuantity.setBackground(new Color(255, 204, 204));
		textFieldQuantity.setFont(new Font("Tahoma", Font.BOLD, 15));
		textFieldQuantity.setColumns(10);
		textFieldQuantity.setBounds(197, 341, 145, 28);
		contentPane.add(textFieldQuantity);
		
		textFieldPrice = new JTextField();
		textFieldPrice.setBackground(new Color(255, 204, 204));
		textFieldPrice.setFont(new Font("Tahoma", Font.BOLD, 15));
		textFieldPrice.setColumns(10);
		textFieldPrice.setBounds(197, 389, 145, 28);
		contentPane.add(textFieldPrice);
		
		textFieldCategory = new JTextField();
		textFieldCategory.setFont(new Font("Tahoma", Font.BOLD, 15));
		textFieldCategory.setBackground(new Color(255, 204, 204));
		textFieldCategory.setColumns(10);
		textFieldCategory.setBounds(197, 443, 145, 28);
		contentPane.add(textFieldCategory);
		
	    comboBoxName = new JComboBox();
	    comboBoxName.setBackground(new Color(102, 153, 204));
	    comboBoxName.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		try {
					String query = "select * from InventoryInfo where name =?";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString(1,(String)comboBoxName.getSelectedItem());
					ResultSet rs = pst.executeQuery();
					
					while(rs.next())
					{

						textFieldID.setText(rs.getString("ID"));
						textFieldName.setText(rs.getString("Name"));
						textFieldQuantity.setText(rs.getString("Quantity"));
						textFieldPrice.setText(rs.getString("Price"));
						textFieldCategory.setText(rs.getString("Category"));
						
						
					}
					
					
					pst.close();
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
	    	}
	    });
		comboBoxName.setFont(new Font("Tahoma", Font.BOLD, 20));
		comboBoxName.setBounds(126, 155, 179, 31);
		contentPane.add(comboBoxName);
		
		btnCancel = new JButton();
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				InventoryManagement inventoryManagement = new InventoryManagement();
				inventoryManagement.setVisible(true);
			}
		});
		btnCancel.setText("cancel");
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnCancel.setBackground(new Color(102, 153, 204));
		btnCancel.setBounds(1208, 603, 112, 23);
		contentPane.add(btnCancel);
		
		refreshTable();
		fillComboBox();
	}
}
