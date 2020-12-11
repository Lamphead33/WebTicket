package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import bean.SubTicket;
import bean.Ticket;


public class TicketDao {
	
	public void createTicket(Ticket t){
		try{
			// Make connection from DB class - testing non static implementation
			Connection connection = DBConnection.getConnectionToDatabase();
			SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
			Date datecreated = new Date(System.currentTimeMillis());
			System.out.println(formatter.format(datecreated));
			
			// All other should be auto increment or not null for insert
			String sql = "INSERT INTO tickets (ticketName, ticketDesc, dateCreated)"
					+ " VALUES "
					+ "	('" + t.getTicketName() + "', '" + t.getTicketDesc() + "', '" + datecreated + "');";
			
			Statement statement = connection.createStatement();
			statement.execute(sql);
			// Close Connection instance
			connection.close();
				
			}
			catch (SQLException e) {
				e.printStackTrace();
			}	
	}
	
	public void createSubTicket(SubTicket t) {
		try {
			Connection connection = DBConnection.getConnectionToDatabase();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
			Date datecreated = new Date(System.currentTimeMillis());
			System.out.println(formatter.format(datecreated));
			
			// All other should be auto increment or not null for insert
			String sql = "INSERT INTO subtickets (subTicketName, subTicketDesc, dateCreated, parentID)"
					+ " VALUES "
					+ "	('" + t.getTicketName() + "', '" + t.getTicketDesc() + "', '" + datecreated + "', + '" + t.getParentID() + "');";
			
			Statement statement = connection.createStatement();
			statement.execute(sql);
			// Close Connection instance
			connection.close();
				
			}
			catch (SQLException e) {
				e.printStackTrace();
			}	
	}

	
	
	public void deleteTicket(Ticket t){
		try{
			// Make connection from DB class - testing non static implementation
			Connection connection = DBConnection.getConnectionToDatabase();	
			// Create MySql Create Query
			String sql1 = "DELETE FROM subtickets WHERE parentID = " + t.getTicketID() + ";";
			Statement statement = connection.createStatement();
			// Send to DB
			statement.execute(sql1);
			connection.close();
			
			connection = DBConnection.getConnectionToDatabase();	
			String sql2 = "DELETE FROM tickets where ticketID= " + t.getTicketID() + ";";
			statement = connection.createStatement();
			// Send to DB
			statement.execute(sql2);
			connection.close();
			
		}catch(Exception e){System.out.println(e);}
		
	}
	
	
	public void deleteSubTicket(SubTicket t){
		try{
			// Make connection from DB class - testing non static implementation
			Connection connection = DBConnection.getConnectionToDatabase();	
			// Create MySql Create Query
			String sql1 = "DELETE FROM subtickets WHERE id = " + t.getTicketID() + ";";
			Statement statement = connection.createStatement();
			// Send to DB
			statement.execute(sql1);
			connection.close();
			
		}catch(Exception e){System.out.println(e);}
		
	}
	
	// Currently incomplete function
	public void resolveTicket(Ticket t){
		try{
			// Make connection from DB class - testing non static implementation
			SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
			Date dateResolved = new Date(System.currentTimeMillis());
			Connection connection = DBConnection.getConnectionToDatabase();	
			// Create MySql Create Query
			String sql = "UPDATE tickets SET isComplete = 1 WHERE ticketID = " + t.getTicketID() + "; ";
			String sql2 = "UPDATE tickets SET dateResolved = \"" + dateResolved + "\" WHERE ticketID = " + t.getTicketID() + ";";
			Statement statement = connection.createStatement();
			// Send to DB
			statement.execute(sql);
			statement.execute(sql2);

			// Close Connection instance
			connection.close();
		}catch(Exception e){System.out.println(e);}

	}
	
	public void resolveSubTicket(SubTicket t){
		try{
			// Make connection from DB class - testing non static implementation
			SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
			Date dateResolved = new Date(System.currentTimeMillis());
			Connection connection = DBConnection.getConnectionToDatabase();	
			// Create MySql Create Query
			String sql = "UPDATE subtickets SET isComplete = 1 WHERE id = " + t.getTicketID() + "; ";
			String sql2 =  "UPDATE subtickets SET dateResolved = \"" + dateResolved + "\" WHERE id = " + t.getTicketID() + ";";
			Statement statement = connection.createStatement();
			// Send to DB
			statement.execute(sql);
			statement.execute(sql2);

			// Close Connection instance
			connection.close();
		}catch(Exception e){System.out.println(e);}

	}
	
	public static ArrayList<Ticket> getAllTickets(){
		ArrayList<Ticket> list=new ArrayList<Ticket>();
		try{
			// Make connection from DB class - testing non static implementation
			Connection connection = DBConnection.getConnectionToDatabase();	
			// Create MySql Create Query
			String sql = "SELECT * FROM tickets";
			Statement statement = connection.createStatement();
			// Send to DB - Use Result Set with While loop
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				Ticket t = new Ticket();
				t.setTicketID(rs.getInt(1));
				t.setTicketName(rs.getString(2));
				t.setTicketDesc(rs.getString(3));
				t.setDateCreated(rs.getString(4));
				t.setDateResolved(rs.getString(5));
				t.setIsComplete(rs.getBoolean(6));
				list.add(t);
			}
			// Close Connection after grabbing all tickets and creating instances
			connection.close();
		}catch(Exception e){System.out.println(e);}
		return list;
	}
	
	public static ArrayList<SubTicket> getAllSubTickets(Ticket t){
		ArrayList<SubTicket> list = new ArrayList<SubTicket>();
		try {
			Connection connection = DBConnection.getConnectionToDatabase();
			String sql = "SELECT * FROM subtickets WHERE parentid = " + t.getTicketID() + ";";
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				SubTicket st = new SubTicket();
				st.setTicketID(rs.getInt(1));
				st.setTicketName(rs.getString(2));
				st.setTicketDesc(rs.getString(6));
				st.setDateCreated(rs.getString(3));
				st.setDateResolved(rs.getString(4));
				st.setIsComplete(rs.getBoolean(5));
				st.setParentID(rs.getInt(7));
				list.add(st);
			}
			connection.close();
		}catch (Exception e) {System.out.println(e);}
		return list;
	}
	
}
