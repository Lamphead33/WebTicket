package dao;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import bean.Ticket;
import bean.User;

public class UserDao {
	
	// Implementing a Strategy Pattern for when we have our profile page setup:
	// Not Called in code yet
	public void createOrUpdate(User u) {
		int userID = u.getUserID();
		// check if user exists in DB
		if(userID < 0) {
		
			// Create Logic
			try {
				Connection connection = DBConnection.getConnectionToDatabase();
				// Adding roles 
				String sql = "INSERT INTO user (userName, password, answer, isEmployee, isTech, isAdmin)"
						+ " VALUES "
						+ "	('" + u.getUserName() + "', '" + u.getPassword() + "', '" + u.getAnswer() + "', '" + u.getIsEmployee() + "', '" + u.getIsTech() + "', '"+ u.getIsAdmin() + "');";
			
				Statement statement = connection.createStatement();
				statement.execute(sql);	
			}
			catch (SQLException e) {
				e.printStackTrace();
			}	
		} 
			
		else {
			// User exists, so we are looking to update values for the desired function instead of creating a new user.
			try {
				Connection connection = DBConnection.getConnectionToDatabase();
				// Update UserName from Profile page 
				String sql = "UPDATE user SET userName = "
						+ u.getUserName()  
						+ " WHERE userID = " 
						+ userID 
						+ "; ";
					Statement statement = connection.createStatement();
					statement.execute(sql);
				} catch (SQLException e) {
					e.printStackTrace();
			}
		}
			
	}
	
	// Current Create function - soon to be merged into a Create or Update function to reduce code complexity when checking if user exists each time.
	public void create(User u) {
		try {
			Connection connection = DBConnection.getConnectionToDatabase();
			// Adding roles 
			String sql = "INSERT INTO user (userName, password, answer, isEmployee, isTech, isAdmin)"
					+ " VALUES "
					+ "	('" + u.getUserName() + "', '" + u.getPassword() + "', '" + u.getAnswer() + "', '" + u.getIsEmployee() + "', '" + u.getIsTech() + "', '"+ u.getIsAdmin() + "');";
		
			Statement statement = connection.createStatement();
			statement.execute(sql);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
	// New feature 1.1
	public void resetPassword(User u) {
		try {
			Connection connection = DBConnection.getConnectionToDatabase();
			String sql = "UPDATE USER"
					+ " SET PASSWORD = '" + u.getPassword() + "'"
					
					+ " WHERE USERID = '" + u.getUserID() + "'";
		
			Statement statement = connection.createStatement();
			statement.execute(sql);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}	
	}	
	
	// New feature 1.1
	public void deleteUser(User u){
		try{
			// Make connection from DB class - testing non static implementation
			Connection connection = DBConnection.getConnectionToDatabase();	
			// Create MySql Create Query
			String sql = "DELETE FROM user where userID= " + u.getUserID() + ";";
			Statement statement = connection.createStatement();
			// Send to DB
			statement.execute(sql);
			
			// Close Connection instance
			connection.close();
		}catch(Exception e){System.out.println(e);}
		
	}
	
	// New feature 1.1
	public static ArrayList<User> getAllUsers(){
		ArrayList<User> list=new ArrayList<User>();
		try{
			// Make connection from DB class - testing non static implementation
			Connection connection = DBConnection.getConnectionToDatabase();	
			// Create MySql Create Query
			String sql = "SELECT * FROM user";
			Statement statement = connection.createStatement();
			// Send to DB - Use Result Set with While loop
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				User u = new User();
				u.setUserID(rs.getInt(1));
				u.setUserName(rs.getString(2));
				u.setPassword(rs.getString(3));
				u.setAnswer(rs.getString(4));
				u.setIsEmployee(rs.getInt(5));
				u.setIsTech(rs.getInt(6));
				u.setIsAdmin(rs.getInt(7));
				list.add(u);
			}
			// Close Connection after grabbing all tickets and creating instances
			connection.close();
		}catch(Exception e){System.out.println(e);}
		return list;
	}
	
	
}
