package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	// Create static member for single instance of jdbc connection class
	private static DBConnection jdbc;  
	// Prevent the instantiation from any other class
	private DBConnection() {  }  
	
	// Global point of access.
	 public static DBConnection getInstance() {    
		 if (jdbc == null)  
         {  
			 jdbc = new DBConnection();  
         }  
		 return jdbc;  
	 }  
	// Need to remove DBConnection 
	 
	 
	// Get connection from method calls
	public static Connection getConnectionToDatabase() {
		Connection connection = null;
		
		try {

			// load the driver class
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("MySQL JDBC Driver Registered!");

			// get hold of the DriverManager
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/web_ticket", "root", "root!");
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your MySQL JDBC Driver?");
			e.printStackTrace();

		}

		catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();

		}

		if (connection != null) {
			System.out.println("Connection made to DB!");
		}
		return connection;
	}

}
