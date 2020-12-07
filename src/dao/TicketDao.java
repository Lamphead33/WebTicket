package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

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
	
	public void deleteTicket(Ticket t){
		try{
			// Make connection from DB class - testing non static implementation
			Connection connection = DBConnection.getConnectionToDatabase();	
			// Create MySql Create Query
			String sql = "DELETE FROM tickets where ticketID= " + t.getTicketID() + ";";
			Statement statement = connection.createStatement();
			// Send to DB
			statement.execute(sql);
			
			// Close Connection instance
			connection.close();
		}catch(Exception e){System.out.println(e);}
		
	}
	
	// Currently incomplete function
	public void resolveTicket(Ticket t){
		try{
			// Make connection from DB class - testing non static implementation
			Connection connection = DBConnection.getConnectionToDatabase();	
			// Create MySql Create Query
			String sql = "UPDATE tickets SET isComplete = 1 WHERE id= " + t.getTicketID() + ";";
			Statement statement = connection.createStatement();
			// Send to DB
			statement.execute(sql);

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
}
