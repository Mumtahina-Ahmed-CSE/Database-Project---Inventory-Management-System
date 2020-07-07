import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Window;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.AbstractListModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Search extends JFrame {
	
	public JFrame frame = new JFrame();
	private JPanel contentPane;
	private JTable table;
	private JTextField textFieldSearch;
	private JList listName;
	private JList listPrice;
	private JComboBox comboBoxSelection;
	Connection connection = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Search frame = new Search();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void loadList()
	{
		try {
			
			String query = "select * from InventoryInfo";
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			
			DefaultListModel DLM = new DefaultListModel();
			DefaultListModel DLM_1 = new DefaultListModel();
			while(rs.next())
			{
				DLM.addElement(rs.getString("Name"));
				DLM_1.addElement(rs.getString("pRICE"));
			}
			listName.setModel(DLM);
			listPrice.setModel(DLM_1);
			pst.close();
			rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * Create the frame.
	 */
	public Search() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		connection = sqliteConnection.dbConnector();
		setBounds(0, 0, 1368, 689);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(153, 204, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Search Inventories");
		lblNewLabel.setBackground(new Color(0, 51, 153));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 25));
		lblNewLabel.setBounds(527, 34, 397, 46);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(657, 129, 611, 459);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
	    listPrice = new JList();
	    listPrice.setBackground(new Color(204, 255, 153));
	    listPrice.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		listPrice.setBounds(357, 274, 216, 314);
		contentPane.add(listPrice);
		
		textFieldSearch = new JTextField();
		textFieldSearch.setFont(new Font("Tahoma", Font.BOLD, 15));
		textFieldSearch.setBackground(new Color(204, 255, 102));
		textFieldSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				try {
					
				    String selection = (String)comboBoxSelection.getSelectedItem();
					String query = "select* from InventoryInfo where "+selection+"=? ";
			
					System.out.println(query);PreparedStatement pst = connection.prepareStatement(query);
					pst.setString(1,textFieldSearch.getText());
					ResultSet rs = pst.executeQuery();
					
					table.setModel(DbUtils.resultSetToTableModel(rs));
					/*while(rs.next())
					{

						
						
						
					}*/
					
					
					pst.close();
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		textFieldSearch.setBounds(349, 129, 224, 46);
		contentPane.add(textFieldSearch);
		textFieldSearch.setColumns(10);
		
	    listName = new JList();
	    listName.setBackground(new Color(204, 255, 102));
	    listName.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		listName.setBounds(83, 274, 209, 314);
		contentPane.add(listName);
		
	    comboBoxSelection = new JComboBox();
	    comboBoxSelection.setFont(new Font("Tahoma", Font.BOLD, 20));
	    comboBoxSelection.setBackground(new Color(102, 255, 153));
		comboBoxSelection.setModel(new DefaultComboBoxModel(new String[] {"ID", "Name", "Quantity", "Price ", "Category"}));
		comboBoxSelection.setBounds(83, 129, 209, 45);
		contentPane.add(comboBoxSelection);
		
		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel_1.setBounds(146, 243, 77, 23);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblPrice.setBounds(433, 243, 89, 20);
		contentPane.add(lblPrice);
		
		JButton btnNewButton = new JButton("cancel");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				InventoryManagement inventoryManagement = new InventoryManagement();
				inventoryManagement.setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton.setBounds(574, 617, 89, 23);
		contentPane.add(btnNewButton);
		
		loadList();
	}
}
