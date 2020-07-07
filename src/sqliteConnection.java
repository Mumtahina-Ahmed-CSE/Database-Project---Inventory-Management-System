import java.sql.*;

import javax.swing.*;

public class sqliteConnection {
	
	Connection Conn = null;
	
	public static Connection dbConnector()
	{
		try
		{
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:F:\\Database\\Project\\Inventory Management with Database\\InventoryDatabase.sqlite");
			//JOptionPane.showMessageDialog(null, "Connection Successful");
			
			return conn;
			
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
		
		
	}

}