package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class ApplicationDao {
	Connection connection;
	Statement statement;
	public boolean loginCheck(String username, String password) throws SQLException {
		
		boolean loginVerified = false;
		connection = DBConnection.getConnectionToDatabase();
		// Still have to add SQL statement below
		String sql = "SELECT * FROM user;"; 
		statement = connection.createStatement();
		ResultSet set = statement.executeQuery(sql);
		
		while (set.next()) {
			// Checks if the username and password entered match a single DB entry
			if (set.getString("userName").equals(username) && set.getString("password").equals(password)) {
				loginVerified = true;
			}
		}
		
		return loginVerified; 
	}
	
	public boolean resetCheck(String username, String answer) throws SQLException {
		
		boolean loginVerified = false;
		connection = DBConnection.getConnectionToDatabase();
		// Still have to add SQL statement below
		String sql = "SELECT * FROM user;"; 
		statement = connection.createStatement();
		ResultSet set = statement.executeQuery(sql);
		
		while (set.next()) {
			// Checks if the username and password entered match a single DB entry
			if (set.getString("userName").equals(username) && set.getString("answer").equals(answer)) {
				loginVerified = true;
			}
		}
		
		return loginVerified;
	}	
	
}
